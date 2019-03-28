package atm;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    protected String name;
    protected ArrayList<Request> requested_accounts; //list of requested accounts from all users
    protected ArrayList<Account> account_list;
    protected String user_id;
    protected String user_pass;
    protected int num_stocks;


    public User(String name, String user_id, String user_pass) {
        this.name = name;
        this.account_list = new ArrayList<>();
        account_list.add(new Chequing());
        account_list.add(new Savings());
        this.requested_accounts = new ArrayList<>();
        this.user_id = user_id;
        this.user_pass = user_pass;
        this.num_stocks = 0;
    }

    /**
     * This method will allow users to request an account from the bank manager.
     * It will add the request to a list of requests to be processed in the User class.
     *
     * @param account_type: The type of account requested for
     */
    public void request_account(Account account_type){
        Request req = new Request(this, account_type);
        requested_accounts.add(req);
    }

    public boolean transfer(Account from, Account to, double amount) {
        return from.transfer(to, amount);
    }

    public boolean transfer(Account from, User to, double amount) {
        if (from.withdraw(amount)){
            to.deposit(amount);
            return true;
        }
        return false;
    }

    public void deposit(Account acc, double amount){
        acc.deposit(amount);
    }
    public Account get_primary_chequing(){
        for (Account a : account_list){
            if (a instanceof Chequing){
                if (((Chequing) a).default_chequing){return a;}
            }
        }
        return null;
    }
    public void deposit(double amount){
        Account primary = get_primary_chequing();
        primary.deposit(amount);
    }

    public boolean transfer(Account from, double amount) {
        return from.withdraw(amount);
    }

    public String get_user_id(){
        return this.user_id;
    }

    public String get_user_pass() {
        return user_pass;
    }

    public String get_Name() {
        return name;
    }

    public void set_user_pass(String pass){
        this.user_pass = pass;
    }


    public void add_account(Account account){
        this.account_list.add(account);
    }

    public double get_net_total(){
        double total = 0;
        for (Account a : account_list){
            if (a instanceof Chequing || a instanceof Savings){
                total += a.getBalance();
            }
            else if (a instanceof CreditCard || a instanceof LineOfCredit){
                total -= a.getBalance();
            }
        }
        return total;
    }


}
