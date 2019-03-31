package atm;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vedantshah on 2019-02-27.
 */
public class BankManager implements Serializable {


    ArrayList<Request> pending_acc_requests = new ArrayList<>();
    ArrayList<User> all_users = new ArrayList<>();
    ArrayList<String> pending_user_requests = new ArrayList<>();


    /**
     * This method adds user to all_accounts if user is not already in all_accounts
     * @param user is the user being added to all_accounts
     */
    public void add_account(User user){
        boolean found = false;

        for(Request r: pending_acc_requests) {
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
        for (Request r : pending_acc_requests){
            r.user_requesting.add_account(r.account_requested);
        }
        pending_acc_requests.clear();
    }

    /**
     * This method will restock the Machine to have 20 of each bill.
     * @param machine: This is the machine being restocked
     */
    private void restock_machine(Machine machine){
        machine.restock_machine();
    }

    /**
     * This method is used to update newer requests to the existing ArrayList of pending_acc_requests
     */

    protected void update_requests(){
        pending_acc_requests.clear();
        for (User u : all_users){
            this.pending_acc_requests.addAll(u.requested_accounts);
        }

    }

    /**
     * This method is used to create a new user request and add it to existing ArrayList of pending_acc_requests with
     * user's name.
     *
     * @param name Name of the requesting user.
     */

    public void create_user_request(String name){
        pending_user_requests.add(name);
    }

    /**
     * Helper method for create_logins method
     *
     * @param username User username
     * @return boolean true for a success & false for a failure whether user name already exists
     */

    public boolean check_avail(String username){
        for (User u : all_users){
            if (u.user_id.equals(username)){
                return false;
            }
        }
        return true;
    }

    /**
     * Method is used to create login credentials for a user
     *
     * @param name User name
     * @param username User username
     * @param password User password
     * @return boolean true for a success & false for a failure for a creating of new login credentials
     */

    public boolean create_logins(String name, String username, String password){
        if (check_avail(username)){
            User u = new User(name, username, password);
            all_users.add(u);
            pending_user_requests.remove(name);
            JRManager jrm = get_jrmanager();
            jrm.pending_user_requests.remove(name);
            return  true;
        }
        else{return false;}
    }


    /**
     * This method is used to get the JRManager assigned to the BankManager.
     *
     * @return The JRManagers details or null if none exists.
     */

    public JRManager get_jrmanager(){
        for (User u : all_users){
            if (u instanceof JRManager){
                return (JRManager) u;
            }
        }
        return null;
    }

    /**
     * Method is used in the GUI to check for a login in an ATM session
     *
     * @param username User username
     * @param password User password
     * @return user if user exists or else null if it does not exist
     */

 public User get_user_from_login(String username, String password){
        for (User u : all_users){
            if (u.get_user_id().equals(username) && u.get_user_pass().equals(password)){
                return u;
            }
        }
        return null;
    }
}
