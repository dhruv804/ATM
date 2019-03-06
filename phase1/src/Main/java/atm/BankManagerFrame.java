package atm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class BankManagerFrame {
    private JTabbedPane tabbedPane1;
    private JPanel panel1;
    private JTextArea num5;
    private JTextArea num10;
    private JTextArea num20;
    private JTextArea num50;
    private JTextArea num100;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton button1;
    private JButton refreshButton;
    private JTextArea accountDetailsTextArea;
    private JComboBox comboBox1;
    private Machine machine = new Machine();


    public BankManagerFrame() {
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int add_5 = Integer.valueOf(textField1.getText());
                    machine.restock_bill(5, add_5);
                } catch (NumberFormatException n){

                }

                try {
                    int add_10 = Integer.valueOf(textField2.getText());
                    machine.restock_bill(10, add_10);
                } catch (NumberFormatException n){

                }

                try {
                    int add_20 = Integer.valueOf(textField3.getText());
                    machine.restock_bill(20, add_20);
                } catch (NumberFormatException n){

                }

                try {
                    int add_50 = Integer.valueOf(textField4.getText());
                    machine.restock_bill(50, add_50);
                } catch (NumberFormatException n){

                }

                try {
                    int add_100 = Integer.valueOf(textField5.getText());
                    machine.restock_bill(100, add_100);
                } catch (NumberFormatException n){

                }

                update();
            }
        });
    }

    public void run() {
        this.update();
        JFrame frame = new JFrame("Bank Manager");
        frame.setContentPane(new BankManagerFrame().tabbedPane1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public void update(){
        num5.setText("Number of 5's: " + String.valueOf(machine.get_number_of(5)));
        num10.setText("Number of 10's: " + String.valueOf(machine.get_number_of(10)));
        num20.setText("Number of 20's: " + String.valueOf(machine.get_number_of(20)));
        num50.setText("Number of 50's: " + String.valueOf(machine.get_number_of(50)));
        num100.setText("Number of 100's: " + String.valueOf(machine.get_number_of(100)));
    }

    public void setUp(Machine machine){
        machine.restock_bill(5, 10);
        machine.restock_bill(10, 10);
        machine.restock_bill(20, 10);
        machine.restock_bill(50, 10);
        machine.restock_bill(100, 10);
    }

}
