package atm;

import com.sun.tools.hat.internal.model.HackJavaValue;

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
     * how to give give the cash to the consumer.
     *
     * @param cash: The amount to be withdrawn from the ATM
     * @return true if can be done, false if not
     */
    public boolean withdraw(int cash){
        return true; //TODO: Someone implement this pls
    }

    /**
     * @return This method will return A map of denominations to how many bills must be inserted for the number of bills
     * to be restocked to 20. If a bill denomination already has more than 20 bills, the denomination is mapped to 0
     */
    public HashMap<Integer, Integer> has_enough_cash(){
        return new HashMap<Integer, Integer>(); //TODO: Someone implement this pls
    }

}
