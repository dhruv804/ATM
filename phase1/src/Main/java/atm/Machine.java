package atm;

import com.sun.xml.internal.bind.v2.TODO;

import java.util.HashMap;
import java.util.Map;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Machine {
    public Map<Integer, Integer> number_of_bills = new HashMap<Integer, Integer>();
    public int total_cash = 0;
    private String path = "phase1/alerts.txt";

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
        FileWriter write = new FileWriter(path, true);
        PrintWriter print_alert = new PrintWriter(write);

        print_alert.printf("Machine has less than 20 %f", str(denom));
        print_alert.close();

    }

    public boolean restock_bill(int denom, int amount){
        int prev = get_number_of(denom);
        if (prev >= 0){
            number_of_bills.put(denom, amount + prev);
            return true;
        } else {
            return false;
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
                    // TODO
//                    if (get_number_of(prev) < 20){
//                        create_alert(prev);
//                    }
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

}
