package atm;

import java.util.ArrayList;

public class User {
    protected String name;
    public ArrayList<Request> requested_accounts; //list of requested accounts from all users
    protected ArrayList<Account> account_list;
    protected String user_id;
    protected String user_pass;


    public User(String name) {
        this.name = name;
        this.account_list = new ArrayList<>();
    }

    /**
     * This method will allow users to request an account from the bank manager.
     * It will add the request to a list of requests to be processed in the User class.
     *
     * @param account_type: The type of account requested for
     */
    public void request_account(Account account_type){
        Request req = new Request(this,account_type);
        requested_accounts.add(req);
    }

    public void set_user_id(String id){
        this.user_id = id;
    }

    public String get_user_id(){
        return this.user_id;
    }

    public String getName() {
        return name;
    }

    public void set_user_pass(String pass){
        this.user_pass = pass;
    }


    public void add_account(Account account){
        this.account_list.add(account);
    }

}
