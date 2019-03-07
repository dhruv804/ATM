package atm;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vedantshah on 2019-03-06.
 */
public class LoginFrame {
    private JTextArea usernameTextArea;
    private JTextField username_field;
    private JTextArea passwordTextArea;
    private JPasswordField password_field;
    private JButton submitButton;
    private JPanel login_jframe;
    private JTabbedPane tabbedPane1;
    private JButton apply;
    private JTextField name_field;
    private JTextPane enter_name;
    private BankManager bank_manager;

    public LoginFrame(BankManager bank_manager) {
        this.bank_manager = bank_manager;
        username_field.setText("vshah");
        password_field.setText("cool");

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username_field.getText().equals("admin") && password_field.getText().equals("pass")) {
                    BankManagerFrame bank_manager_frame = new BankManagerFrame(bank_manager);
                    bank_manager_frame.run();
                } else{
                    User u = bank_manager.get_user_from_login(username_field.getText(), password_field.getText());

                    if (u != null) {
                        UserFrame user_frame = new UserFrame(u);
                        user_frame.run();
                    } else {
                        JOptionPane.showMessageDialog(null, "Username and Password not found");
                    }

                }
            }
        });

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bank_manager.create_user_request(name_field.getText());
                JOptionPane.showMessageDialog(null, "Application Sent");
                name_field.setText("");
            }
        });
    }

    public void run(){

        JFrame frame = new JFrame("Login");
        frame.setContentPane(new LoginFrame(bank_manager).login_jframe);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

}
