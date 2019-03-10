package atm;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vedantshah on 2019-03-06.
 */
public class LoginFrame{
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
    private JButton ENDDAYButton;
    private JTextField exit_user;
    private JTextField exit_pass;
    private BankManager bank_manager;
    private Machine machine;
    private JFrame frame;


    public LoginFrame(BankManager bank_manager, Machine machine) {
        this.bank_manager = bank_manager;
        this.machine = machine;
        username_field.setText("");
        password_field.setText("");
        machine.depositInterest();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (username_field.getText().equals("admin") && password_field.getText().equals("pass")) {
                    BankManagerFrame bank_manager_frame = new BankManagerFrame(bank_manager,machine);
                    bank_manager_frame.run();
                } else{
                    User u = bank_manager.get_user_from_login(username_field.getText(), password_field.getText());

                    if (u != null) {
                        UserFrame user_frame = new UserFrame(u, machine);
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


        ENDDAYButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s1 = exit_user.getText();
                String s2 = exit_pass.getText();
                if (s1.equals("admin") && s2.equals("pass")) {
                    String filename = "phase1/final.ser";
                    File tmp = new File(filename);
                    //System.out.println(machine.getDate());
                    machine.nextDay();
                    try {
                        FileOutputStream file = new FileOutputStream(filename);
                        ObjectOutputStream out = new ObjectOutputStream(file);
                        out.writeObject(machine);
                        out.close();
                        file.close();
                        frame.dispose();
                    } catch (Exception f) {
                        System.out.println(f);
                    }


                    //System.out.println("Object has been serialized");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Username and Password not found");
                }

            }
        });
    }

    public void run(){

        frame = new JFrame("Login");
        frame.setContentPane(login_jframe);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }


}
