package atm;

import com.sun.org.apache.regexp.internal.RE;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class BankManagerFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextArea num5;
    private JTextArea num10;
    private JTextArea num20;
    private JTextArea num50;
    private JTextArea num100;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button1;
    private JButton refreshButton;
    private JTextArea accountDetailsTextArea;
    private JComboBox account_select;
    private JButton refreshButton1;
    private JComboBox names_requested;
    private JButton setUsernameAndPasswordButton;
    private JButton refresh_user_requests;
    private JButton createButton;
    private JButton rejectButton;
    private static Machine machine;
    private static BankManager bank_manager;


    public BankManagerFrame(BankManager bank_manager, Machine machine) {
        this.bank_manager = bank_manager;
        this.machine = machine;
        update_bill_amounts();
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update_bill_amounts();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int add_5 = Integer.valueOf(textField1.getText());
                    machine.restock_bill(5, add_5);
                } catch (NumberFormatException n){

                }

                try {
                    int add_10 = Integer.valueOf(textField2.getText());
                    machine.restock_bill(10, add_10);
                } catch (NumberFormatException n){

                }

                try {
                    int add_20 = Integer.valueOf(textField3.getText());
                    machine.restock_bill(20, add_20);
                } catch (NumberFormatException n){

                }

                try {
                    int add_50 = Integer.valueOf(textField4.getText());
                    machine.restock_bill(50, add_50);
                } catch (NumberFormatException n){

                }

                try {
                    int add_100 = Integer.valueOf(textField5.getText());
                    machine.restock_bill(100, add_100);
                } catch (NumberFormatException n){

                }

                update_bill_amounts();
            }
        });
        refreshButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Refresh Account Creation");
                update_requests();
            }
        });
        refresh_user_requests.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Refresh User Creation");
                update_requests();
            }
        });
        setUsernameAndPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (names_requested.getItemCount() != 0) {
                    String s = (String)names_requested.getSelectedItem();
                    SetCredentialsFrame set_c_frame = new SetCredentialsFrame(s, bank_manager);
                    set_c_frame.run();
                    names_requested.removeItem(s);
                }
            }
        });
        account_select.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (account_select.getItemCount() == 0) return;

                Request r = (Request) account_select.getSelectedItem();
                accountDetailsTextArea.setText(r.get_info());
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (account_select.getItemCount() == 0) return;

                Request r = (Request) account_select.getSelectedItem();
                User u = r.user_requesting;
                u.requested_accounts.remove(r);
                bank_manager.pending_acc_requests.remove(r);
                u.add_account(r.account_requested);
                if (r.account_requested instanceof Chequing){
                    Chequing c = (Chequing) r.account_requested;
                    c.setDefault_chequing(false);
                }
                update_requests();
                accountDetailsTextArea.setText("");
            }
        });
        rejectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (account_select.getItemCount() == 0) return;

                Request r = (Request) account_select.getSelectedItem();
                User u = r.user_requesting;
                u.requested_accounts.remove(r);
                bank_manager.pending_acc_requests.remove(r);
                update_requests();
                accountDetailsTextArea.setText("");
            }
        });
    }

    public void run() {
        JFrame frame = new JFrame("Bank Manager");
        frame.setContentPane(tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update_bill_amounts(){
        num5.setText("Number of 5's: " + String.valueOf(machine.get_number_of(5)));
        num10.setText("Number of 10's: " + String.valueOf(machine.get_number_of(10)));
        num20.setText("Number of 20's: " + String.valueOf(machine.get_number_of(20)));
        num50.setText("Number of 50's: " + String.valueOf(machine.get_number_of(50)));
        num100.setText("Number of 100's: " + String.valueOf(machine.get_number_of(100)));

        textField1.setText("0");
        textField2.setText("0");
        textField3.setText("0");
        textField4.setText("0");
        textField5.setText("0");

    }

    public void update_requests(){
        bank_manager.update_requests();
        account_select.removeAllItems();
        for (Request r: bank_manager.pending_acc_requests){
            account_select.addItem(r);
        }

        names_requested.removeAllItems();
        for (String n : bank_manager.pending_user_requests){
            names_requested.addItem(n);
        }
    }

    public void setUp(Machine machine){
        machine.restock_bill(5, 10);
        machine.restock_bill(10, 10);
        machine.restock_bill(20, 10);
        machine.restock_bill(50, 10);
        machine.restock_bill(100, 10);
    }

    private void createUIComponents() {
        account_select = new JComboBox();
        names_requested = new JComboBox();
    }
}
