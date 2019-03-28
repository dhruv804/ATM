package atm;

import java.io.Serializable;
import java.util.ArrayList;

public class JRManager extends User implements Serializable{

    ArrayList<Request> pending_acc_requests = new ArrayList<>();
    ArrayList<User> all_users = new ArrayList<>();
    ArrayList<String> pending_user_requests = new ArrayList<>();


    public JRManager(String name, String user_id, String user_pass) {
        super(name, user_id, user_pass);
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
