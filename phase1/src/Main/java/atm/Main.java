package atm;

import javax.swing.*;

/**
 * Created by vedantshah on 2019-03-04.
 */
public class Main {

    public static void main(String[] args) {
        BankManager bank_manager = new BankManager();
        bank_manager.all_users.add(new User("Ved", "vshah", "cool"));
        LoginFrame login_frame = new LoginFrame(bank_manager);
        login_frame.run();
    }

}
