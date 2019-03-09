package atm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Machine implements Serializable{

    public Map<Integer, Integer> number_of_bills = new HashMap<Integer, Integer>();
    public int total_cash = 0;
    private String path = "group_0315/phase1/alerts.txt";
    private ArrayList<User> all_users = new ArrayList<>();
    private BankManager manager;
    private static int serialVersionUID = 75857858;

    public Machine(BankManager manager) {
        number_of_bills.put(5, 0);
        number_of_bills.put(10, 0);
        number_of_bills.put(20, 0);
        number_of_bills.put(50, 0);
        number_of_bills.put(100, 0);
        this.manager = manager;
    }

    public int get_total_cash(){
        for(int i: number_of_bills.keySet()){
            total_cash += number_of_bills.get(i) * i;
        }
        return total_cash;
    }

    public int get_number_of(int denom){
        try {
            return number_of_bills.get(denom);
        } catch (NullPointerException n){
            return -1;
        }
    }

    public boolean needs_restocking(){
        if (get_number_of(5) < 20){
            return true;
        }
        else if (get_number_of(10) < 20){
            return true;
        }
        else if (get_number_of(20) < 20){
            return true;
        }
        else if (get_number_of(50) < 20){
            return true;
        }
        else if (get_number_of(100) < 20){
            return true;
        }
        else{return false;}
    }


    public void create_alert(int denom) throws IOException{
        try {

            FileWriter write = new FileWriter(path, true);
            PrintWriter print_alert = new PrintWriter(write);
            print_alert.printf("Machine has less than 20 %d", denom); // maybe
            print_alert.close();
        }

        catch (IOException e) {
            System.out.println("Sorry manager - no can do!");

        }

        //Todo: try-catch using FileWriter and PrintWriter

    }

    // Changed return type from boolean to void for bankmanager restock method
    public void restock_bill(int denom, int amount){ // Changed return type from boolean to void for bankmanager restock method
        int prev = get_number_of(denom);
        if (prev >= 0){
            number_of_bills.put(denom, amount + prev);
        }
    }

    /**
     * This method will subtract the bill denomination amounts, and figure out
     * how to give the cash to the consumer.
     *
     * @param cash: The amount to be withdrawn from the ATM
     * @return true if can be done, false if not
     */
    public boolean withdraw(int cash){
        if (cash < 5){
            return false;
        }
        if (cash > get_total_cash()){
            return false;
        }

        int num_5_taken = 0;
        int num_10_taken = 0;
        int num_20_taken = 0;
        int num_50_taken = 0;
        int num_100_taken = 0;
        int money_taken = 0;
        while (money_taken < cash){
            if (cash - money_taken >= 100 && this.get_number_of(100) > 0){
                num_100_taken += 1;
                money_taken += 100;
            } else if (cash - money_taken >= 50 && this.get_number_of(50) > 0){
                num_50_taken += 1;
                money_taken += 50;
            } else if (cash - money_taken >= 20 && this.get_number_of(20) > 0){
                num_20_taken += 1;
                money_taken += 20;
            } else if (cash - money_taken >= 10 && this.get_number_of(10) > 0){
                num_10_taken += 1;
                money_taken += 10;
            } else if (cash - money_taken >= 5 && this.get_number_of(5) > 0){
                num_5_taken += 1;
                money_taken += 5;
            } else {
                break;
            }
        }

        if (money_taken == cash) {
            this.restock_bill(5, 0- num_5_taken);
            this.restock_bill(10, 0- num_10_taken);
            this.restock_bill(20, 0- num_20_taken);
            this.restock_bill(50, 0- num_50_taken);
            this.restock_bill(100, 0- num_100_taken);
            return true;
        }
        else {
            this.restock_bill(5, num_5_taken);
            this.restock_bill(10, num_10_taken);
            this.restock_bill(20, num_20_taken);
            this.restock_bill(50, num_50_taken);
            this.restock_bill(100, num_100_taken);
            return false;
        }
    }

    /**
     * @return This method will return a map of denominations to how many bills must be inserted for the number of bills
     * to be restocked to 20. If a bill denomination already has more than 20 bills, the denomination is mapped to 0
     */
    public HashMap<Integer, Integer> has_enough_cash(){

        HashMap<Integer, Integer> restock_bills = new HashMap<Integer, Integer>();

        int[] denominations = {5, 10, 20, 50, 100};


        for (int i : denominations) {
            if (number_of_bills.get(i) < 20) {
                restock_bills.put(i, 20 - number_of_bills.get(i));
            } else {
                restock_bills.put(i, 0);
            }
        }

        return restock_bills;
    }

    public void restock_machine() {
        HashMap<Integer, Integer> cur_map = has_enough_cash();

        int[] denominations = {5, 10, 20, 50, 100};

        for (int i : denominations) {
            restock_bill(i, cur_map.get(i));

        }
    }
    public void update_users(){
        for (User u : manager.all_users){
            all_users.add(u);
        }
    }

    public User username_exists(String username){
        for (User u : all_users){
            if (u.get_user_id().equals(username)) return u;
        }
        return null;
    }

    public BankManager getManager(){
        return manager;
    }

}
