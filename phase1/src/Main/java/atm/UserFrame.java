package atm;

import javax.swing.*;
import java.awt.event.*;
import java.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

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
    private JSlider slider2;
    private JButton buyButton;
    private JLabel priceLabel;
    private JLabel feesLabel;
    private JLabel totalLabel;
    private JButton sellButton;
    private JSlider slider3;
    private JTextArea numberOfStocksTextArea;
    private JTextArea priceOfStockTextArea;
    private JButton refreshButton1;
    private User user;
    private Machine machine;


    public UserFrame(User user, Machine machine) {
        this.user = user;
        this.machine = machine;
        update_accounts();

        slider2.setMinimum(1);
        slider2.setMaximum(10);
        slider2.setMajorTickSpacing(1);
        slider2.setValue(0);
        slider2.setPaintLabels(true);
        slider2.setPaintTicks(true);
        slider2.setPaintTrack(true);
        slider2.setSnapToTicks(true);


        update_sell_slider();



        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Account acc : user.account_list){
                    if (((DefaultComboBoxModel)comboBox1.getModel()).getIndexOf(acc) == -1) {
                        comboBox1.addItem(acc);
                        comboBox2.addItem(acc);
                        comboBox3.addItem(acc);
                        comboBox4.addItem(acc);
                        comboBox5.addItem(acc);
                        comboBox6.addItem(acc);
                        comboBox7.addItem(acc);
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
                if (machine.withdraw(amount) ){
                    if (acc.withdraw(amount)) {
                        System.out.println("Successfully withdrawn");
                        JOptionPane.showMessageDialog(null, "Successfully withdrawn");
                    } else {
                        System.out.println("Failed withdrawal, account");
                        JOptionPane.showMessageDialog(null, "Failed withdrawal, Please check your" +
                                "balance and limit");
                    }
                } else {
                    System.out.println("Failed withdrawal, machine");
                    JOptionPane.showMessageDialog(null, "Failed withdrawal, Machine needs" +
                            "restocking. Sorry for any inconvenience, please try again later");
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
                try {
                    double amt = Double.valueOf(amount.getText());
                    if (user.transfer(from_acc, to_acc, amt)){
                        JOptionPane.showMessageDialog(null, "Transfer Successful.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer Failed.");
                    }
                } catch (Exception f){
                    JOptionPane.showMessageDialog(null, "Please Enter a valid amount");
                }

            }
        });
        transferButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account from_acc = (Account)comboBox5.getSelectedItem();
                try {
                    double amt = Double.valueOf(amount2.getText());
                    String user_name = username.getText();
                    machine.update_users();
                    User u = machine.username_exists(user_name);

                    if (u != null) {
                        if (u.transfer(from_acc, u, amt)) {
                            JOptionPane.showMessageDialog(null, "Transfer Successful.");
                        } else {
                            JOptionPane.showMessageDialog(null, "Transfer Failed. Check account details.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Transfer Failed. User does not exist");
                    }
                } catch (Exception f){
                    JOptionPane.showMessageDialog(null, "Please Enter a valid amount");
                }
            }
        });
        transferButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Account from_acc = (Account)comboBox6.getSelectedItem();
                double amt = Integer.valueOf(amount3.getText());
                if (user.transfer(from_acc, amt)){
                    JOptionPane.showMessageDialog(null, "Transfer Successful.");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Transfer Failed.");
                }
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


        slider2.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                //System.out.println("hi");
                int num_stocks = slider2.getValue();
                priceLabel.setText("Price per Share: " + String.valueOf(StockPortfolio.getPrice()));
                feesLabel.setText("Fee per Share: " + String.valueOf(StockPortfolio.getFee()));
                double total_price = num_stocks*(StockPortfolio.getFee() + StockPortfolio.getPrice());
                totalLabel.setText("Total: " + String.valueOf(total_price));
            }
        });
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Chequing c = (Chequing) user.get_primary_chequing();
                int num_stocks = slider2.getValue();
                double total_price = num_stocks*(StockPortfolio.getFee() + StockPortfolio.getPrice());
                if (c.withdraw(total_price)){
                    user.num_stocks += num_stocks;
                    slider2.setValue(1);
                } else {
                    JOptionPane.showMessageDialog(null, "Could not process. Please check balance" +
                            " in primary Chequing account.");
                }
            }
        });
        sellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int sell_amt = slider3.getValue();

                user.deposit(sell_amt*StockPortfolio.getPrice());
                user.num_stocks -= sell_amt;

                update_sell_slider();
            }
        });
        refreshButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update_sell_slider();
            }
        });
    }

    public void run(){
        JFrame frame = new JFrame(user.get_Name());
        frame.setContentPane(user_jpanel);
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
                if (acc instanceof CreditCard){
                    ;
                } else {
                    comboBox3.addItem(acc);
                }
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox4.getModel()).getIndexOf(acc) == -1) {
                comboBox4.addItem(acc);
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox5.getModel()).getIndexOf(acc) == -1) {
                if (acc instanceof CreditCard){
                    ;
                } else {
                    comboBox5.addItem(acc);
                }
            }
        }

        for (Account acc : user.account_list){
            if (((DefaultComboBoxModel)comboBox6.getModel()).getIndexOf(acc) == -1) {
                if (acc instanceof CreditCard){
                    ;
                } else {
                    comboBox6.addItem(acc);
                }
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

    private void update_sell_slider(){

        if (user.num_stocks > 0) {
            slider3.setMinimum(1);
            slider3.setMaximum(Math.min(10, user.num_stocks));
            slider3.setMajorTickSpacing(1);
            slider3.setValue(0);
            slider3.setPaintLabels(true);
            slider3.setPaintTicks(true);
            slider3.setPaintTrack(true);
            slider3.setSnapToTicks(true);
        } else {
            slider3.setMinimum(0);
            slider3.setMaximum(0);
            slider3.setMajorTickSpacing(1);
            slider3.setValue(0);
            slider3.setPaintLabels(true);
            slider3.setPaintTicks(true);
            slider3.setPaintTrack(true);
            slider3.setSnapToTicks(true);
        }

        numberOfStocksTextArea.setText("Number of Stocks: " + String.valueOf(user.num_stocks));
        priceOfStockTextArea.setText("Price of Stock: " + String.valueOf(StockPortfolio.price));
    }
}
