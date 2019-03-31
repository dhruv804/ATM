package atm;

import java.io.Serializable;

public class Mortgage extends Debt implements Serializable {

    protected double interest_rate;

    /**
     * The Mortgage abstract class inherits the Debt super class features. Additionally, it sets the initial balance to
     * negative initial_amount and passes in the interest_rate.
     *
     * @param initial_amount the absolute balance of mortgage
     * @param interest_rate the interest rate on mortgage account
     *
     */

    public Mortgage() {
        super();
        this.balance = 500000;
        this.interest_rate = 0.04;
        this.limit = 1000000;
    }

    /**
     *
     * This method overrides and returns the success of a Mortgage amount withdrawal attempt
     *
     * @param amount the amount to be transferred out from a Mortgage account
     * @return true for successful transfer and false for unsuccessful
     */

    @Override
    public boolean transfer_out(double amount) {
        return this.withdraw(amount);
    }

    /**
     * pays off the mortgage account on a monthly basis
     * @param amount amount to pay per month
     */

    @Override
    public void transfer_in(double amount) {
        this.deposit(amount);
    }

    /**
     * @return used to get account number in Mortgage account number format
     *
     * This method overrides returns account number appended to the string "Mortgage"
     */

    @Override
    public String toString(){
        return "Mortgage"+this.getAccount_num();
    }

    /**
     * This method updates the balance of the mortgage amount based on the interest rate.
     */

    public void interest_deposit() {
        balance = balance * (1 + interest_rate);
    }

    /**
     * @return Mortgage account type is concatenated to the Mortgage account balance
     *
     * This method returns account type along with the Mortgage account balance
     */

    public String get_account_details(){
        String l1 = "Account Type: Mortgage\n";
        String l2 = "Balance: " + this.getBalance() + "\n";
        return l1+l2;
    }
}
