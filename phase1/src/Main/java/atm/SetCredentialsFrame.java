package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vedantshah on 2019-03-06.
 */
public class SetCredentialsFrame {
    private JTextArea usernameTextArea;
    private JTextField username_field;
    private JTextField password_field;
    private JTextArea passwordTextArea;
    private JButton createButton;
    private JPanel credentials_jpanel;
    private String name;
    private BankManager bank_manager;
    private JRManager jrManager;
    private JFrame frame;


    public SetCredentialsFrame(String name, BankManager bank_manager) {
        frame = new JFrame(name);
        this.name = name;
        this.bank_manager = bank_manager;
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_user();
                close();
            }
        });
    }
    public SetCredentialsFrame(String name, JRManager jrManager) {
        frame = new JFrame(name);
        this.name = name;
        this.jrManager = jrManager;
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_user();
                close();
            }
        });
    }

    public void run(){
        frame.setContentPane(credentials_jpanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
        frame.dispose();
    }

    public void create_user(){
        if (bank_manager != null){
            if (bank_manager.create_logins(name, username_field.getText(), password_field.getText())){
                JOptionPane.showMessageDialog(null, "Account Creation Successful");
            } else {
                JOptionPane.showMessageDialog(null, "Username already taken");
            }
        }
        else if (jrManager != null){
            if (jrManager.create_logins(name, username_field.getText(), password_field.getText())){
                JOptionPane.showMessageDialog(null, "Account Creation Successful");
            } else {
                JOptionPane.showMessageDialog(null, "Username already taken");
            }
        }

    }
}
