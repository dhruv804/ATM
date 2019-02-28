package atm;

import java.util.HashMap;
import java.util.Map;

public class Machine {
    public Map<Integer, Integer> number_of_bills = new HashMap<Integer, Integer>();

    public Machine() {
        number_of_bills.put(5, 0);
        number_of_bills.put(10, 0);
        number_of_bills.put(20, 0);
        number_of_bills.put(50, 0);
        number_of_bills.put(100, 0);
    }

    public int get_number_of(int denom){
        try {
            return number_of_bills.get(denom);
        } catch (NullPointerException n){
            return -1;
        }
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
        if (cash < 5) {
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

}
