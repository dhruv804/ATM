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
    protected  ArrayList<Transaction> transactions;
    protected ArrayList<Account> join_accounts;


    /**
     * This method initializes the User class. It creates a blank ArrayList for transactions, account lists and,
     * requested accounts.
     *
     * It at also sets the name and login credentials for a new user. It also sets the initial
     * number of stocks owned by the user to 0.
     *
     * @param name The name of the user
     * @param user_id The user id of the user
     * @param user_pass The password of the user
     */

    public User(String name, String user_id, String user_pass) {
        this.transactions = new ArrayList<>();
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
     * It will add the request to a list of account requests to be processed in the User class.
     *
     * @param account_type: The type of account requested for
     */

    public void request_account(Account account_type){
        Request req = new Request(this, account_type);
        requested_accounts.add(req);
    }

    /**
     * This method will allow users to request a joint account from the bank manager.
     * It will add the request to a list of account requests to be processed in the User class.
     *
     * @param u The requesting user
     * @param account The account to be converted to a joint account.
     */

    public void request_join_account(User u , Account account){
        Request req = new Request(this, account, u);
        requested_accounts.add(req);
    }

    /**
     * This boolean method is used to determine a success or failure of an attempted transfer between accounts.
     *
     * @param from Account from which money is to be transferred
     * @param to Account to which money is to be transferred
     * @param amount Amount of money to be transferred between accounts
     *
     * @return true for successful transfer and false for unsuccessful transfer.
     */

    public boolean transfer(Account from, Account to, double amount) {
        Transaction t = new Transaction(from, to, amount);
        transactions.add(t);
        return from.transfer(to, amount);
    }

    /**
     * This boolean method is used to determine a success or failure of an attempted transfer between a Account and a
     * User.
     *
     * @param from Account from which money is to be transferred
     * @param to User to which money is to be transferred
     * @param amount Amount of money to be transferred between a Account and a User
     *
     * @return true for successful transfer and false for unsuccessful transfer.
     */

    public boolean transfer(Account from, User to, double amount) {
        Transaction t = new Transaction(from, to.get_primary_chequing(), amount);
        transactions.add(t);
        if (from.withdraw(amount)){
            to.deposit(amount);
            return true;
        }
        return false;
    }

    /**
     * This method is used to process a user's deposit of money into his account.
     *
     * @param acc Account in which money is to be deposited
     * @param amount Amount of money to be deposited
     */

    public void deposit(Account acc, double amount){
        Transaction t = new Transaction(null, acc, amount);
        transactions.add(t);
        acc.deposit(amount);
    }

    /**
     * This method is used to return the primary chequing account of a user.
     *
     * @return returns the primary chequing account or null if no primary chequing account exists.
     */

    public Account get_primary_chequing(){
        for (Account a : account_list){
            if (a instanceof Chequing){
                if (((Chequing) a).default_chequing){return a;}
            }
        }
        return null;
    }

    /**
     * This method is used to deposit money directly into the primary chequing account of a user.
     *
     * @param amount Amount to be deposited into the primary chequing account
     */

    public void deposit(double amount){
        Account primary = get_primary_chequing();

        Transaction t = new Transaction(null, primary, amount);
        transactions.add(t);

        primary.deposit(amount);
    }

    /**
     * This boolean method is used to process the success of a withdrawal of money from a users account to transfer.
     *
     * @param from Account from which money is to be transferred
     * @param amount Amount of money to be transferred
     *
     * @return true for successful transfer and false for unsuccessful transfer
     */

    public boolean transfer(Account from, double amount) {
        Transaction t = new Transaction(from, null, amount);
        transactions.add(t);

        return from.withdraw(amount);
    }

    /**
     * The method returns user id of the user.
     *
     * @return user id of the user
     */

    public String get_user_id(){
        return this.user_id;
    }

    /**
     * The method returns password of the user.
     *
     * @return password of the user
     */

    public String get_user_pass() {
        return user_pass;
    }

    /**
     * The method returns name of the user.
     *
     * @return name of the user
     */

    public String get_Name() {
        return name;
    }

    /**
     * The method is used to set a password for the user.
     *
     * @return set a password for the user
     */

    public void set_user_pass(String pass){
        this.user_pass = pass;
    }


    /**
     * This method is used to add a account for a user
     *
     * @param account Account to be added for the user
     */

    public void add_account(Account account){
        this.account_list.add(account);
    }

    /**
     * This method is used to provide a net summary of the users accounts. It provides the user's net worth.
     *
     * @return net worth of the user
     */

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
