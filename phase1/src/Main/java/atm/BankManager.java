package atm;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vedantshah on 2019-02-27.
 */
public class BankManager {
    /**
     * This is a class for requests, we will add it to the GUI so requests can be processed
     */

    ArrayList<Request> pending_requests = new ArrayList<>();
    ArrayList<User> all_users = new ArrayList<>();

    /**
     * This method adds user to all_accounts if user is not already in all_accounts
     * @param user is the user being added to all_accounts
     */
    public void add_account(User user){
        boolean found = false;

        for(Request r: pending_requests) {
            if (r.user_requesting.get_user_id().equals(user.get_user_id())) {
                found = true;
            }
        }
        if(!found){
            all_users.add(user);
        }

    }

    /**
     * This method adds all requests to all_accounts if accounts are not already in all_accounts
     */
    public void add_all_accounts(){
        for (Request r : pending_requests){
            r.user_requesting.add_account(r.account_requested);
        }
        pending_requests.clear();
    }

    /**
     * This method will restock the Machine to have 20 of each bill.
     * @param machine: This is the machine being restocked
     */
    private void restock_machine(Machine machine){
        machine.restock_machine();
    }

}
