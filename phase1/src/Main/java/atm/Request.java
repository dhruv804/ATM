package atm;

import java.io.Serializable;

/**
 * Created by vedantshah on 2019-03-06.
 */
public class Request implements Serializable {
    User user_requesting;
    Account account_requested;
    User join_user = null;

    /**
     *
     * This method is used to initialize the Request class
     *
     * @param user_requesting Name of the user requesting a request
     * @param account_requested Type of account requested
     */

    public Request(User user_requesting, Account account_requested) {
        this.user_requesting = user_requesting;
        this.account_requested = account_requested;
    }

    public Request(User user_requesting, Account account_requested, User join_user) {
        this.user_requesting = user_requesting;
        this.account_requested = account_requested;
        this.join_user = join_user;
    }

    /**
     * This method returns the information of the user's request for a particular account.
     *
     * @return concatenated user name, user id and account type requested
     */

    public String get_info(){
        String n = "Name: " + user_requesting.get_Name() + "; \n";
        String i = "ID: " + user_requesting.get_user_id() + "; \n";
        String a = "Account Type Requested: " + account_requested.toString() + ";\n";
        if (this.join_user != null){
            a = "Join Account Type Requested: " + account_requested.toString() + " with " + this.join_user.name + ";\n";
        }
        return n+i+a;
    }

    /**
     *
     * This method returns the name of the requesting user.
     *
     * @return Name of requesting user
     */

    @Override
    public String toString(){
        return user_requesting.name;
    }
}