package atm;

import java.util.ArrayList;

public class User {
    protected String name;
    protected ArrayList<Account> account_list;
    protected String user_id;
    protected String user_pass;


    public User(String name) {
        this.name = name;
        this.account_list = new ArrayList<>();
    }

    public void set_user_id(String id){

        this.user_id = id;
    }

    public void set_user_pass(String pass){
        this.user_pass = pass;
    }


    public void add_account(Account account){
        this.account_list.add(account);
    }

}
