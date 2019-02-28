package atm;

import java.util.ArrayList;

/**
 * Created by vedantshah on 2019-02-27.
 */
public class BankManager {

    /**
     * This is a class for requests, we will add it to the GUI so requests can be processed
     */
    protected class Request{
        User user_requesting;
        Account account_requested;
        //TODO: Someone implement pls (Make constructor)

        public Request(User user_requesting, Account account_requested) {
            this.user_requesting = user_requesting;
            this.account_requested = account_requested;
        }
    }

    ArrayList<Request> pending_requests = new ArrayList<>();


    /**
     * This method will allow users to request an account from the bank manager.
     * It will add the request to a list of requests to be processed.
     *
     * @param user: This is the user requesting an account
     * @param account_type: The type of account requested for
     *
     */
    public void request_account(User user, Account account_type){
        //TODO: Someone implement pls
        Request req = new Request(user,account_type);
        pending_requests.add(req);
    }

    /**
     * This method will add an account to a user from the pending requests
     * It will also remove the request from the list of pending requests
     */
    public void add_all_accounts(){
        //TODO: Someone implement pls
        for (Request r : pending_requests){
            r.user_requesting.add_account(r.account_requested);
            pending_requests.remove(r);
        }
    }

}
