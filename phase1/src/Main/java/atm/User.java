package atm;

import java.util.ArrayList;

public class User {
    private String name;
    private ArrayList<Account> account_list;

    public User(String name) {
        this.name = name;
    }

    public void add_account(Account account){
        account_list.add(account);
    }

}
