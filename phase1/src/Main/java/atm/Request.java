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
        String n = "Name: " + user_requesting.get_Name() + "; \n";
        String i = "ID: " + user_requesting.get_user_id() + "; \n";
        String a = "Account Type Requested: " + account_requested.toString() + ";\n";
        return n+i+a;
    }

    @Override
    public String toString(){
        return user_requesting.name;
    }
}