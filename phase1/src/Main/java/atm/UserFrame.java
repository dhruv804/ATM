package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.*;

/**
 * Created by vedantshah on 2019-03-06.
 */
public class UserFrame {
    private JComboBox comboBox1;
    private JButton refreshButton;
    private JTextArea details_area;
    private JPanel user_jpanel;
    private JTabbedPane tabbedPane1;
    private JTextField old_pass_field;
    private JTextField new_pass_field;
    private JTextField confirm_pass_field;
    private JTextArea oldPasswordTextArea;
    private JTextArea newPasswordTextArea;
    private JTextArea confirmNewPasswordTextArea;
    private JButton changePasswordButton;
    private JSlider slider1;
    private JTextArea withdraw_text;
    private JComboBox comboBox2;
    private JTextArea selectAnAccountToTextArea;
    private JButton withdrawButton;
    private JTextArea balanceTextArea;
    private JTextArea transferBetweenAccountsTextArea;
    private JTextArea transferToAnotherUserTextArea;
    private JTextArea payABillTextArea;
    private JTextField amount;
    private JComboBox comboBox3;
    private JComboBox comboBox4;
    private JComboBox comboBox5;
    private JTextField username;
    private JTextField amount2;
    private JComboBox comboBox6;
    private JTextField payee_name;
    private JTextField amount3;
    private JButton transferButton;
    private JButton transferButton1;
    private JButton transferButton2;
    private JTabbedPane tabbedPane2;
    private JComboBox comboBox7;
    private JTextField amount4;
    private JButton depositButton;
    private JTextArea depositdetails;
    private JTextPane pleaseSelectAnAccountTextPane;
    private JComboBox comboBox8;
    private JButton applyButton;
    private User user;


    public UserFrame(User user) {
        this.user = user;
        update_accounts();

        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Account acc : user.account_list){
                    if (((DefaultComboBoxModel)comboBox1.getModel()).getIndexOf(acc) == -1) { //TODO: Do similar for others
                        comboBox1.addItem(acc);
                    }
                }
            }
        });
        comboBox1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account temp_acc = (Account)comboBox1.getSelectedItem();
                String s1 = temp_acc.get_account_details();
                String s2 = "Net Balance: " + String.valueOf(user.get_net_total()) + "\n";
                System.out.println(user.get_net_total());
                details_area.setText(s1+s2);
            }
        });
        changePasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (old_pass_field.getText().equals(user.get_user_pass())){
                    if (new_pass_field.getText().equals(confirm_pass_field.getText())){
                        user.set_user_pass(new_pass_field.getText());
                        JOptionPane.showMessageDialog(null, "Password Changed");
                    } else {
                        JOptionPane.showMessageDialog(null, "Passwords do not match. Please Try Again.");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Old Password Incorrect. Please Try Again.");
                }

                old_pass_field.setText("");
                new_pass_field.setText("");
                confirm_pass_field.setText("");
            }
        });
        comboBox2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update_slider();
            }
        });
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboBox2.getItemCount() == 0){
                    return;
                }

                Account acc = (Account) comboBox2.getSelectedItem();

                int amount = slider1.getValue();
                if (acc.withdraw(amount)){
                    System.out.println("Successfully withdrawn");
                } else {
                    System.out.println("Failed withdrawal");
                }

                update_accounts();
                update_slider();
            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account from_acc = (Account)comboBox3.getSelectedItem();
                Account to_acc = (Account)comboBox4.getSelectedItem();
                double amt = Double.valueOf(amount.getText()); //TODO: Check if double
                if (user.transfer(from_acc, to_acc, amt)){
                    JOptionPane.showMessageDialog(null, "Transfer Successful.");
                } else {
                    JOptionPane.showMessageDialog(null, "Transfer Failed.");
                }
            }
        });
        transferButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {//TODO: Finish this
                Account from_acc = (Account)comboBox5.getSelectedItem();
                double amt = Integer.valueOf(amount2.getText());
                String user_name = username.getText();

            }
        });
        transferButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account from_acc = (Account)comboBox6.getSelectedItem();
                double amt = Integer.valueOf(amount3.getText());
                user.transfer(from_acc, amt);
            }
        });
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account acc = (Account)comboBox7.getSelectedItem();
                double amt = Double.valueOf(amount4.getText());
                user.deposit(acc, amt);
                String s1 = "Deposit Successful!" + "\n";
                String s2 = acc.get_account_details();
                depositdetails.setText(s1 + s2);
            }
        });
        applyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String r = (String) comboBox8.getSelectedItem();
                System.out.println(r);

                switch (r){
                    case "Savings":
                        user.request_account(new Savings());
                        JOptionPane.showMessageDialog(null, "Saving account requested.");
                        break;
                    case "Chequing":
                        user.request_account(new Chequing());
                        JOptionPane.showMessageDialog(null, "Chequing account requested.");
                        break;
                    case "Credit Card":
                        user.request_account(new CreditCard());
                        JOptionPane.showMessageDialog(null, "Credit Card account requested.");
                        break;
                    case "Line of Credit":
                        user.request_account(new LineOfCredit());
                        JOptionPane.showMessageDialog(null, "Line of Credit account requested.");
                        break;
                }
            }
        });
    }

    public void run(){
        JFrame frame = new JFrame(user.get_Name());
        frame.setContentPane(new UserFrame(user).user_jpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update_accounts(){
        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox1.getModel()).getIndexOf(acc) == -1) {
                comboBox1.addItem(acc);
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox2.getModel()).getIndexOf(acc) == -1) {
                comboBox2.addItem(acc);
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox3.getModel()).getIndexOf(acc) == -1) {
                comboBox3.addItem(acc);
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox4.getModel()).getIndexOf(acc) == -1) {
                comboBox4.addItem(acc);
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox5.getModel()).getIndexOf(acc) == -1) {
                comboBox5.addItem(acc);
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox6.getModel()).getIndexOf(acc) == -1) {
                comboBox6.addItem(acc);
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox7.getModel()).getIndexOf(acc) == -1) {
                comboBox7.addItem(acc);
            }
        }
    }

    public void update_slider(){
        if (comboBox2.getItemCount() == 0){
            return;
        }
        Account acc = (Account) comboBox2.getSelectedItem();
        int max_num = 200;
        if (acc instanceof Savings) {
            double max_double = acc.getBalance();
            int max_int = (int) java.lang.Math.floor(max_double);
            max_num = (max_int / 5) * 5;
        } else if (acc instanceof Chequing){
            double max_double = acc.getBalance();
            int max_int = (int) java.lang.Math.floor(max_double);
            max_num = (max_int / 5) * 5;
            if (max_double>=0){
                max_num += 100;
            }
        }
        if (max_num < 0) max_num = 0;
        if (max_num > 200) max_num = 200;

        slider1.setMinimum(0);
        slider1.setMaximum(max_num);
        slider1.setMajorTickSpacing(10);
        slider1.setMinorTickSpacing(5);
        slider1.setValue(0);
        slider1.setPaintLabels(true);
        slider1.setPaintTicks(true);
        slider1.setPaintTrack(true);
        slider1.setSnapToTicks(true);

        balanceTextArea.setText(acc.get_account_details());
    }
}
