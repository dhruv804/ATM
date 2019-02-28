package atm;

import com.sun.org.apache.regexp.internal.RE;
import jdk.internal.util.xml.impl.Pair;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vedantshah on 2019-02-27.
 */
public class BankManager {

    /**
     * This is a class for requests, we will add it to the GUI so requests can be processed
     */
    private class Request{
        User user_requesting;
        Account account_requested;
        //TODO: Someone implement pls (Make constructor)
    }

    ArrayList<Request> pending_requests;


    /**
     * This method will allow users to request an account from the bank manager.
     * It will add the request to a list of requests to be processed.
     *
     * @param user: This is the user requesting an account
     * @param account_type: This is a string of the account type
     *
     */
    public void request_account(User user, String account_type){
        return; //TODO: Someone implement pls
    }

    /**
     * This method will add an account to a user from the pending requests
     * It will also remove the request from the list of pending requests
     *
     * @param request: This is the request that is being processed
     */
    public void add_account_to_user(Request request){
        return; //TODO: Someone implement pls
    }

}
