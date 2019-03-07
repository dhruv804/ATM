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
    }

    public void update_slider(){
        if (comboBox2.getItemCount() == 0){
            return;
        }
        Account acc = (Account) comboBox2.getSelectedItem();
        int max_num = 500;
        if (acc instanceof Savings) {
            double max_double = ((Savings) acc).getBalance();
            int max_int = (int) java.lang.Math.floor(max_double);
            max_num = (max_int / 5) * 5;
        } else if (acc instanceof Chequing){
            double max_double = ((Chequing) acc).getBalance();
            int max_int = (int) java.lang.Math.floor(max_double);
            max_num = (max_int / 5) * 5;
            if (max_double>=0){
                max_num += 100;
            }
        }
        if (max_num < 0) max_num = 0;

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
