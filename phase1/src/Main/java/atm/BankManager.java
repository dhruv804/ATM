package atm;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by vedantshah on 2019-02-27.
 */
public class BankManager implements Serializable {
    /**
     * This is a class for requests, we will add it to the GUI so requests can be processed
     */

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

    protected void update_requests(){
        pending_acc_requests.clear();
        for (User u : all_users){
            this.pending_acc_requests.addAll(u.requested_accounts);
        }

    }

    public void create_user_request(String name){
        pending_user_requests.add(name);
    }

    public boolean check_avail(String username){
        for (User u : all_users){
            if (u.user_id.equals(username)){
                return false;
            }
        }
        return true;
    }

    public boolean create_logins(String name, String username, String password){
        if (check_avail(username)){
            User u = new User(name, username, password);
            all_users.add(u);
            pending_user_requests.remove(name);
            return  true;
        }
        else{return false;}
    }

    public User get_user_from_login(String username, String password){
        for (User u : all_users){
            if (u.get_user_id().equals(username) && u.get_user_pass().equals(password)){
                return u;
            }
        }
        return null;
    }
}
