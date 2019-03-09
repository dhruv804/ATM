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
        CreditCard c = new CreditCard();
        LineOfCredit d = new LineOfCredit();
        u.add_account(c);
        u.add_account(d);
        bank_manager.all_users.add(u);
        LoginFrame login_frame = new LoginFrame(bank_manager, machine);
        login_frame.run();

    }

}
