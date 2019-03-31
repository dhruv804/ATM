package atm;

import java.io.Serializable;
import java.util.ArrayList;

public class JRManager extends User implements Serializable{

    ArrayList<Request> pending_acc_requests = new ArrayList<>();
    ArrayList<User> all_users = new ArrayList<>();
    ArrayList<String> pending_user_requests = new ArrayList<>();
    BankManager manager;


    /**
     *
     * This method is used to initialize a new JRManager and pass in his basic information and login credentials and
     * which manager he is answerable to.
     *
     * @param name The JRManager's name
     * @param user_id The JRManager's user id
     * @param user_pass The JRManager's password
     * @param manager The JRManager's BankManager
     */

    public JRManager(String name, String user_id, String user_pass, BankManager manager) {
        super(name, user_id, user_pass);
        this.manager = manager;
    }

    /**
     * This method returns the BankManager of the JRManager
     *
     * @return The JRManager's BankManager
     */

    public BankManager getManager() {
        return manager;
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
            this.manager.all_users.add(u);
            pending_user_requests.remove(name);
            this.manager.pending_user_requests.remove(name);
            return  true;
        }
        else{return false;}
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
