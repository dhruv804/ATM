package atm;

import javax.crypto.Mac;
import javax.sound.sampled.Line;
import javax.swing.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class Main {

    public static void main(String[] args) {
        String filename = "phase1/final.ser";
        File tmp = new File(filename);

        String myDate = "2019/01/01";

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        try {
            date = sdf.parse(myDate);
        } catch (java.text.ParseException p){
            System.out.println(p);
        }

        if (! tmp.isFile()) {
            User u = new User("Vedant Shah", "vshah", "csc207");
            BankManager bankManager = new BankManager();
            bankManager.add_account(u);
            JRManager jrManager = new JRManager("John", "john", "cool");
            bankManager.add_account(jrManager);
            Machine machine = new Machine(bankManager, date);

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
            } catch (IOException | ClassNotFoundException i) {
                System.out.println(i);
            }


        }


    }

}
