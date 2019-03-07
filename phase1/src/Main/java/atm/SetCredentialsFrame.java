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
    private JFrame frame;


    public SetCredentialsFrame(String name, BankManager bank_manager) {
        frame = new JFrame(name);
        this.name = name;
        this.bank_manager = bank_manager;

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                create_user();
            }
        });

    }

    public void run(){
        frame.setContentPane(new SetCredentialsFrame(name, bank_manager).credentials_jpanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //TODO: Change to DO_NOTHING
        frame.pack();
        frame.setVisible(true);
    }

    public void close(){
        frame.setVisible(false);
        frame.dispose(); //TODO: Figure this out
    }

    public void create_user(){
        if (bank_manager.create_logins(name, username_field.getText(), password_field.getText())){
            JOptionPane.showMessageDialog(null, "Account Creation Successful");
        } else {
            JOptionPane.showMessageDialog(null, "Username already taken");
        }
    }
}
