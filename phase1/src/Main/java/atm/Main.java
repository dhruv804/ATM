package atm;

import javax.crypto.Mac;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.io.*;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class Main {

    public static void main(String[] args) {
        String filename = "phase1/final.ser";
        File tmp = new File(filename);
        System.out.println(tmp.exists());

        if (! tmp.isFile()) {
            BankManager bankManager = new BankManager();
            Machine machine = new Machine(bankManager);

            //Saving of object in a file
            LoginFrame loginFrame = new LoginFrame(bankManager, machine);
            loginFrame.run();

        } else {

            try {
                FileInputStream fileIn = new FileInputStream(filename);
                ObjectInputStream in = new ObjectInputStream(fileIn);
                Machine machine = (atm.Machine) in.readObject();
                in.close();
                fileIn.close();

                LoginFrame loginFrame = new LoginFrame(machine.getManager(), machine);
                loginFrame.run();
            } catch (Exception i) {
                System.out.println(i);
            }


        }


    }

}
