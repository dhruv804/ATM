package atm;

import javax.swing.*;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class Main {

    public static void main(String[] args) {
        BankManager bank_manager = new BankManager();
        User u1 = new User("Ved");
        Chequing c1 = new Chequing();
        bank_manager.request_account(u1, c1);
        BankManagerFrame b = new BankManagerFrame(bank_manager);
        BankManagerFrame b2 = new BankManagerFrame(bank_manager);
        b.run();
        b2.run();

    }

}
