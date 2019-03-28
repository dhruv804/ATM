package atm;

import java.io.Serializable;

public class Chequing extends Asset implements Serializable {

    protected boolean default_chequing;
    private int transaction_count;
    private int transaction_limit;
    private double transaction_fee;

    /**
     * This method sets default transaction count to 0, transaction limit to 50 and transaction fee to 0.5.
     */

    public Chequing() {
        super();
        this.default_chequing = true;
        transaction_count = 0;
        transaction_limit = 50;
        transaction_fee = 0.5;
    }

    /**
     * This method checks and sets the defaults values to a chequing account.
     * @param default_chequing boolean that checks for default values for a chequing account.
     */

    public void setDefault_chequing(boolean default_chequing) {
        this.default_chequing = default_chequing;
    }

    /**
     * This method updates the trasaction count by increasing it by 1.
     *
     * @param amount withdrawal amount
     * @return checks success of a withdrawl of an amount
     *
     * The method also checks whether a withdrawal of amount is successful or not.
     */

    public boolean transfer_out(double amount){
        transaction_count++;
        return withdraw(amount);
    }

    /**
     *
     * This method overrides and it is used for withdrawing the 'amount' from a chequing account. The method enforces a
     * one-time overdraft limit of -100 for a withdrawal for an initial positive account balance.
     *
     * @param amount The amount to be withdrawn from an account
     * @return true for successful withdraw and false for an unsuccessful one.
     *
     * The method also returns the success/failure of a withdrawal from a chequing account.
     */

    @Override
    public boolean withdraw(double amount) {
        if (balance-amount >= -100 && balance >= 0) {
            transaction_count++;
            return super.withdraw(amount);
        } else {
            //System.out.printf("Savings Account does not have enough balance to withdraw $%f.", amount);
            return false;
        }
    }

    /**
     * @return used to get account number in chequing account number format
     *
     * This method returns account number appended to the string "Chequing"
     */

     @Override
    public String toString(){
        return "Chequing"+this.getAccount_num();
    }

    /**
     * @return account type is concatenated to account balance
     *
     * This method returns account type along with the chequing account balance
     */

    public String get_account_details(){
        String l1 = "Account Type: Chequing\n";
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
     * This method is used to reset the transaction fee for the chequing account.
     */

    public double getTransaction_fee() {
        return transaction_fee;
    }
}
