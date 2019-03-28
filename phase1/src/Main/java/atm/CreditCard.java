package atm;

import java.io.Serializable;

public class CreditCard extends Debt implements Serializable {

    /**
     * The CreditCard abstract class inherits the Debt super class features and initializes them by default.
     */

    public CreditCard() {
        super();
    }

    /**
     *
     * This method overrides and returns the success of a CreditCard purchase of amount
     *
     * @param amount the amount to be transferred out from a CreditCard account
     * @return true for successful transfer and false for unsuccessful
     */

    @Override
    public boolean transfer_out(double amount) {
        return this.withdraw(amount);
    }


    /**
     *
     * This method overrides and facilitates a CreditCard balance deposit of an amount
     *
     * @param amount the amount to be transferred into a CreditCard account
     */

    @Override
    public void transfer_in(double amount) {
        this.deposit(amount);
    }

    /**
     * @return used to get account number in Credit Card account number format
     *
     * This method overrides returns account number appended to the string "Credit Card"
     */

    @Override
    public String toString(){
        return "Credit Card"+this.getAccount_num();
    }

    /**
     * @return CreditCard account type is concatenated to the Credit Card account balance
     *
     * This method returns account type along with the CreditCard account balance
     */

    public String get_account_details(){
        String l1 = "Account Type: Credit Card\n";
        String l2 = "Balance: " + this.getBalance() + "\n";
        return l1+l2;
    }


}
