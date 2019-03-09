package atm;

import javax.sound.sampled.Line;
import javax.swing.*;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class Main {

    public static void main(String[] args) {
        Machine machine = new Machine();
        BankManager bank_manager = new BankManager();
        User u = new User("Ved", "vshah", "cool");
        User u2 = new User("Empty", "empty", "empty");
        Chequing a = new Chequing();
        Savings b = new Savings();
        CreditCard c = new CreditCard();
        LineOfCredit d = new LineOfCredit();
        a.setBalance(-1);
        b.setBalance(251);
        u.add_account(a);
        u.add_account(b);
        u.add_account(c);
        u.add_account(d);
        bank_manager.all_users.add(u);
        bank_manager.all_users.add(u2);
        LoginFrame login_frame = new LoginFrame(bank_manager, machine);
        login_frame.run();
    }

}
