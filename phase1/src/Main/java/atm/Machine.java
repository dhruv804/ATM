package atm;

import sun.util.resources.cldr.ebu.CalendarData_ebu_KE;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Machine implements Serializable{

    public Map<Integer, Integer> number_of_bills = new HashMap<Integer, Integer>();
    public int total_cash = 0;
    private ArrayList<User> all_users = new ArrayList<>();
    private BankManager manager;
    private static int serialVersionUID = 75857858;
    private Date date;

    /**
     *
     * This method is used to initialize the initial bank notes of denominations 5,10,20,50 and 100 to be 0 in the ATM.
     *
     * @param manager The main bank manager
     * @param date The date of operation
     */

    public Machine(BankManager manager, Date date) {
        number_of_bills.put(5, 0);
        number_of_bills.put(10, 0);
        number_of_bills.put(20, 0);
        number_of_bills.put(50, 0);
        number_of_bills.put(100, 0);
        this.manager = manager;
        this.date = date;
    }

    /**
     *
     * This method is used to return the total cash remaining in the ATM machine.
     *
     * @return the added up money from bills in the ATM
     */

    public int get_total_cash(){
        for(int i: number_of_bills.keySet()){
            total_cash += number_of_bills.get(i) * i;
        }
        return total_cash;
    }

    /**
     *
     * This method returns the number of bills of denom in the ATM or -1 for a NullPointer Exception
     *
     * @param denom the denomination of the bill to be accounted for
     * @return either the number of bills of denom or the -1 for a NullPointer Exception
     */

    public int get_number_of(int denom){
        try {
            return number_of_bills.get(denom);
        } catch (NullPointerException n){
            return -1;
        }
    }

    /**
     *
     * This boolean method returns the need for restocking of bills by checking if any bill denomination is less than
     * 20 bills in the ATM.
     *
     * @return true if any bill in the ATM is less than 20 or else returns falls fall enough bills in the machine
     */

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


    /**
     *
     * This method is used to restock a specific bill denom by a specific number amount.
     *
     * @param denom the denomination of the bill
     * @param amount the number of bills to be restocked of denom
     */

    // Changed return type from boolean to void for bank manager restock method
    public void restock_bill(int denom, int amount){
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
                restock_bill(100, -1);
                num_100_taken += 1;
                money_taken += 100;
            } else if (cash - money_taken >= 50 && this.get_number_of(50) > 0){
                restock_bill(50, -1);
                num_50_taken += 1;
                money_taken += 50;
            } else if (cash - money_taken >= 20 && this.get_number_of(20) > 0){
                restock_bill(20, -1);
                num_20_taken += 1;
                money_taken += 20;
            } else if (cash - money_taken >= 10 && this.get_number_of(10) > 0){
                restock_bill(10, -1);
                num_10_taken += 1;
                money_taken += 10;
            } else if (cash - money_taken >= 5 && this.get_number_of(5) > 0){
                restock_bill(5, -1);
                num_5_taken += 1;
                money_taken += 5;
            } else {
                break;
            }
        }

        if (money_taken != cash) {
            this.restock_bill(5, num_5_taken);
            this.restock_bill(10, num_10_taken);
            this.restock_bill(20, num_20_taken);
            this.restock_bill(50, num_50_taken);
            this.restock_bill(100, num_100_taken);
            return false;
        }
        else {
            return true;
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

    /**
     *This method is used to check whether any bill of any specific denomination requires restocking.
     */

    public void restock_machine() {
        HashMap<Integer, Integer> cur_map = has_enough_cash();

        int[] denominations = {5, 10, 20, 50, 100};

        for (int i : denominations) {
            restock_bill(i, cur_map.get(i));

        }
    }

    /**
     * This method is used by manger to add a user into the ATM database.
     */

    public void update_users(){
        for (User u : manager.all_users){
            all_users.add(u);
        }
    }

    /**
     *
     * This method is used to determine whether the user with a username exists or not.
     *
     * @param username username of the user
     * @return returns true if the user exists or null if it doesn't.
     */

    public User username_exists(String username){
        for (User u : all_users){
            if (u.get_user_id().equals(username)) return u;
        }
        return null;
    }

    /**
     * This method is used to return the current bank manager.
     * @return bank manager information
     */

    public BankManager getManager(){
        return manager;
    }

    /**
     * This method is used to process the next day on an ATM.
     */

    public void nextDay(){
        this.date = new Date(this.date.getTime() + (1000*60*60*24));
    }

    /**
     * This method is used to return the current date of operation.
     * @return the date of operation
     */

    public String getDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        return  dateFormat.format(date);
    }


    /**
     * deposits interest and withdraws fees from foing over the transaction limit at the beginning of every month
     */
    public void depositInterest(){
        update_users();
        if (date.getDate() == 1){
            for (User u : all_users){
                for (Account a : u.account_list){
                    a.interest_deposit();
                    System.out.println(getDate());
                    System.out.println("Interest Deposited");

                    if(a instanceof Chequing) {
                        if(a instanceof Student) {
                            if(a.getBalance() >= 4000) {
                                ((Student) a).remove_transaction_fee();
                            }
                        }
                        if(((Chequing) a).over_transaction_limit() > 0) {
                            a.withdraw(((Chequing) a).over_transaction_limit()*((Chequing) a).getTransaction_fee());
                            ((Chequing) a).reset_transaction_count();
                        }
                    } else if(a instanceof Savings) {
                        if(((Savings) a).over_transaction_limit() > 0) {
                            a.withdraw(((Savings) a).over_transaction_limit()*((Savings) a).getTransaction_fee());
                            ((Savings) a).reset_transaction_count();
                        }
                    }
                    System.out.println(getDate());
                    System.out.println("Transaction Fees Withdrawn");
                }
            }
        }

        StockPortfolio.update_price();
    }

}
