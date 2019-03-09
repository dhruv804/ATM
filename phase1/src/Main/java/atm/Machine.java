package atm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Machine {
    public Map<Integer, Integer> number_of_bills = new HashMap<Integer, Integer>();
    public int total_cash = 0;
    private String path = "group_0315/phase1/alerts.txt";
    private ArrayList<User> all_users = new ArrayList<>();

    public Machine() {
        number_of_bills.put(5, 0);
        number_of_bills.put(10, 0);
        number_of_bills.put(20, 0);
        number_of_bills.put(50, 0);
        number_of_bills.put(100, 0);
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

        int prev = 5;

        while(cash > 0) {
            for (int i : number_of_bills.keySet()) {
                if (i > cash) {
                    cash -= prev;
                    number_of_bills.replace(prev, number_of_bills.get(prev) - 1);
                    prev = 5;
                } else {
                    prev = i;
                }
            }
        }
        return true;
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

    public boolean username_exists(String username){
        for (User u : all_users){
            if (u.get_user_id().equals(username)) return true;
        }
        return false;
    }

}
