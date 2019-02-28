package atm;

import java.util.ArrayList;

public class User {
    protected String name;
    protected ArrayList<Account> account_list;

    public User(String name) {
        this.name = name;
        this.account_list = new ArrayList<>();
    }

    public void add_account(Account account){
        this.account_list.add(account);
    }

}
