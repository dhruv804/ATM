package atm;

/**
 * Created by vedantshah on 2019-03-06.
 */
public class Request{
    User user_requesting;
    Account account_requested;

    public Request(User user_requesting, Account account_requested) {
        this.user_requesting = user_requesting;
        this.account_requested = account_requested;
    }

    public String get_info(){
        String n = "Name: " + user_requesting.get_Name() + "; ";
        String i = "ID: " + user_requesting.get_user_id() + "; ";
        String a = "Account Type Requested: " + account_requested.get_account_type() + ";";
        return n+i+a;
    }
}