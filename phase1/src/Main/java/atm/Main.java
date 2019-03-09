package atm;

import javax.sound.sampled.Line;
import javax.swing.*;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class Main {

    public static void main(String[] args) {
        BankManager bank_manager = new BankManager();
        Machine machine = new Machine(bank_manager);
        User u = new User("Ved", "vshah", "cool");
        User u2 = new User("Dhruv", "dk", "wow");
        Chequing a = new Chequing();
        Savings b = new Savings();
        CreditCard c = new CreditCard();
        LineOfCredit d = new LineOfCredit();
        Chequing a2 = new Chequing();
        Savings b2 = new Savings();
        a.setBalance(-1);
        b.setBalance(251);
        u.add_account(a);
        u.add_account(b);
        u.add_account(c);
        u.add_account(d);
        u2.add_account(a2);
        u2.add_account(b2);
        bank_manager.all_users.add(u);
        bank_manager.all_users.add(u2);
        LoginFrame login_frame = new LoginFrame(bank_manager, machine);
        login_frame.run();
    }

}
