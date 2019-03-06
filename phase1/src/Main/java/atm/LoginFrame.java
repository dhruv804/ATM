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

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BankManagerFrame bank_manager_frame = new BankManagerFrame(bank_manager);
                bank_manager_frame.run();
            }
        });

        apply.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bank_manager.create_user_request(name_field.getText());
                JOptionPane.showMessageDialog(null, "Application Sent");
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
