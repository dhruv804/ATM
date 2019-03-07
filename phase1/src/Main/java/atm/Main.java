package atm;

import javax.swing.*;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class Main {

    public static void main(String[] args) {
        BankManager bank_manager = new BankManager();
        User u = new User("Ved", "vshah", "cool");
        Account a = new Chequing();
        Account b = new Savings();
        u.add_account(a);
        u.add_account(b);
        bank_manager.all_users.add(u);
        LoginFrame login_frame = new LoginFrame(bank_manager);
        login_frame.run();
    }

}
