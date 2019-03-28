package atm;

import java.io.Serializable;

public class Savings extends Asset implements Serializable {

    static private double interest_rate = 0.001;
    private int transaction_count;
    private int transaction_limit;
    private double transaction_fee;


    /**
     * This method initializes a new Savings account and it is initialized by the default by the Asset class.
     */

    public Savings() {
        super();
    }

    /**
     * This boolean method returns the success of transfer of money out from a savings account.
     *
     * @param amount withdrawal amount
     * @return checks success of a withdrawal of an amount
     *
     */

    public boolean transfer_out(double amount){
        return withdraw(amount);
    }

    /**
     *
     * This boolean method overrides and it is used for withdrawing the 'amount' from a savings account.
     *
     * @param amount The amount to be withdrawn from an account
     * @return true for successful withdraw and false for an unsuccessful one.
     *
     * The method also returns the success/failure of a withdrawal from a savings account.
     */

    @Override
    public boolean withdraw(double amount) {
        if (balance-amount >= 0) {
            return super.withdraw(amount);
        } else {
            //System.out.printf("Savings Account does not have enough balance to withdraw $%f.", amount);
            return false;
        }
    }


    /**
     * This method updates the balance of the savings amount based on the interest rate.
     */

    public void interest_deposit(){
        balance = balance * (1 + interest_rate);
        balance = (double) Math.round(balance * 100)/100;
    }


    /**
     * @return used to get account number in Savings account number format
     *
     * This method overrides returns account number appended to the string "Savings"
     */

    @Override
    public String toString(){
        return "Savings" + this.getAccount_num();
    }

    /**
     * @return Savings account type is concatenated to the Savings account balance
     *
     * This method returns account type along with the Savings account balance
     */

    public String get_account_details(){
        String l1 = "Account Type: Savings\n";
        String l2 = "Balance: " + this.getBalance() + "\n";
        return l1+l2;
    }

    /**
     * if the number of transactions is under the limit, will return a negative number
     * if it is over, it will return the number of transactions over the limit
     * @return the number of transactions over the monthly limit
     */
    public int over_transaction_limit() {
        return transaction_count-transaction_limit;
    }

    /**
     * This method is used to reset the transaction counter to 0.
     */

    public void reset_transaction_count() {
        transaction_count = 0;
    }

    /**
     * This method is used to return the transaction fee incurred for the savings account.
     */

    public double getTransaction_fee() {
        return transaction_fee;
    }
}
