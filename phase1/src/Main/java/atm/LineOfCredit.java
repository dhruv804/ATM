package atm;

import java.io.Serializable;

public class LineOfCredit extends Debt implements Serializable {

    public LineOfCredit() {
        super();
    }

    /**
     *
     * This method overrides and returns the success of a LineOfCredit amount withdrawal attempt
     *
     * @param amount the amount to be transferred out from a LineOfCredit account
     * @return true for successful transfer and false for unsuccessful
     */

    @Override
    public boolean transfer_out(double amount) {
        return this.withdraw(amount);
    }

    /**
     *
     * This method overrides and facilitates a deposit of an amount to a LineOfCredit account
     *
     * @param amount the amount to be transferred into a LineOfCredit account
     */

    @Override
    public void transfer_in(double amount) {
        this.deposit(amount);
    }

    /**
     * @return used to get account number in Line of Credit account number format
     *
     * This method overrides returns account number appended to the string "Line of Credit"
     */


    @Override
    public String toString(){
        return "Line of Credit"+this.getAccount_num();
    }

    /**
     * @return LineOfCredit account type is concatenated to the Line of Credit account balance
     *
     * This method returns account type along with the LineOfCredit account balance
     */

    public String get_account_details(){
        String l1 = "Account Type: Line of Credit\n";
        String l2 = "Balance: " + this.getBalance() + "\n";
        return l1+l2;
    }
}
