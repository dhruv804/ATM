package atm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vedantshah on 2019-02-25.
 */
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

    public boolean insert_bill(int denom, int amount){
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
     * how to give give the cash to the consumer.
     *
     * @param cash: The amount to be withdrawn from the ATM
     * @return true if can be done, false if not
     */
    public boolean withdraw(int cash){
        return true;
    }

    public static void main(String[] args) {
        Machine m = new Machine();
        m.insert_bill(21, 5);
        m.insert_bill(20, 4);
        System.out.println(m.get_number_of(21));

    }
}
