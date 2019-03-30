package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JRManagerFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JButton refreshButton;
    private JComboBox names_requested;
    private JButton setUsernameAndPasswordButton;
    private JButton refreshButton1;
    private JComboBox account_select;
    private JButton createButton;
    private JButton rejectButton;
    private JTextArea accountDetailsTextArea;
    private JButton goToPersonalBankingButton;
    private JRManager jrManager;
    private Machine machine;

    public JRManagerFrame(JRManager jrManager, Machine machine) {
        this.jrManager = jrManager;
        this.machine = machine;

        refreshButton.addActionListener(new ActionListener() {
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
                    SetCredentialsFrame set_c_frame = new SetCredentialsFrame(s, jrManager);
                    set_c_frame.run();
                    names_requested.removeItem(s);
                }
            }
        });
        refreshButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Refresh Account Creation");
                update_requests();
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
                BankManager manager = jrManager.getManager();
                Request r = (Request) account_select.getSelectedItem();
                User u = r.user_requesting;
                u.requested_accounts.remove(r);
                jrManager.pending_acc_requests.remove(r);
                manager.pending_acc_requests.remove(r);
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
                jrManager.pending_acc_requests.remove(r);
                update_requests();
                accountDetailsTextArea.setText("");
            }
        });
        goToPersonalBankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserFrame userFrame = new UserFrame(jrManager, machine);
                userFrame.run();
            }
        });
    }

    public void run() {
        JFrame frame = new JFrame("Junior Manager");
        frame.setContentPane(tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update_requests(){
        jrManager.update_requests();
        account_select.removeAllItems();
        for (Request r: jrManager.pending_acc_requests){
            account_select.addItem(r);
        }

        names_requested.removeAllItems();
        for (String n : jrManager.pending_user_requests){
            names_requested.addItem(n);
        }
    }
}
