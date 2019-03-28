package atm;

import java.io.Serializable;

public abstract class Debt extends Account implements Serializable {

    protected double balance;
    protected int limit;

    /**
     * This inherited method is used for returning balances of debt accounts.
     * @return the debt balance of the debt account
     */

    public double getBalance() {
        return balance;
    }

    /**
     * This inherited method is used to set balance of debt accounts.
     * @param balance the account balance
     */

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * This method is used to set the default balance of a debt account to 0 and the maximum debt limit of a debt
     * account to 1000.
     */

    public Debt(){
        super();
        this.balance = 0;
        this.limit = 1000;
    }

    /**
     *
     * This boolean method overrides and is used to accept a withdrawal amount and determine the success of a
     * withdrawal attempt.
     *
     * @param amount The amount to be withdrawn from an account
     * @return true for successful debt withdrawal and false for an unsuccessful attempt.
     */

    @Override
    public boolean withdraw(double amount) {
        if(amount <= this.limit) {
            balance += amount;
            this.limit -= amount;
            return true;
        }
        return false;
    }

    /**
     *
     * This method overrides and deposits the amount into a debt account.
     *
     * @param amount The amount to be deposited back into a debt account.
     */

    @Override
    public void deposit(double amount) {
        balance -= amount;
    }

    /**
     *
     * This method accepts the debt account limit and sets it for the current debt account.
     *
     * @param limit debt account upper limit
     */

    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     *
     * This boolean abstract method is used to determine the success of a withdrawal.
     *
     * @param amount the amount to be transferred out of the debt account
     * @return true for successful and false for unsuccessful
     */

    public abstract boolean transfer_out(double amount);

    /**
     *
     * This boolean abstract method is used to determine the success of a deposit.
     *
     * @param amount the amount to be deposited into the debt account
     */

    public abstract void transfer_in(double amount);


}
