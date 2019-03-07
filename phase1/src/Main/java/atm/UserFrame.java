package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by vedantshah on 2019-03-06.
 */
public class UserFrame {
    private JComboBox comboBox1;
    private JButton refreshButton;
    private JTextArea details_area;
    private JPanel user_jpanel;
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
                details_area.setText(temp_acc.get_account_details());
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
    }
}
