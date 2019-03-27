package atm;

import java.io.Serializable;

public abstract class Account implements Serializable {
    private static int account_count = 0;
    private int account_num;
    protected double balance;
    //private User user;

    /**
     * This method is used for getting balances of initial accounts for testing purposes for different account types.
     */

    public abstract double getBalance();

    /**
     *
     * This method is only used for setting up initial accounts for testing purposes for different account types.
     *
     * @param balance balance of the account
     */

    public abstract void setBalance(double balance);

    /**
     * Creates a new account number of the user's account and sets default account balance to 0.
     */

    public Account(){
        account_count++;
        account_num = account_count;
        this.balance = 0;
        //this.user = user;
    }

    /**
     *
     * @return account number of the user
     */

    public int getAccount_num() {
        return account_num;
    }

    /**
     * @param amount The amount to be withdrawn from an account
     * This abstract method is used for withdrawing the 'amount' from different account types.
     */

    public abstract boolean withdraw(double amount);

    /**
     * @param amount The amount to be deposited to an account
     * This abstract method is used for depositing the 'amount' to different account types.
     */

    public abstract void deposit(double amount);

    /**
     * This method is used for getting balances of initial accounts for testing purposes for different account types.
     */

    public void interest_deposit(){
        ;
    }

    /**
     *
     * @param other_account Payee Account
     * @param amount Amount to be transferred
     * @return true if successful, false if not
     */
    public boolean transfer(Account other_account, double amount){
        if (this.withdraw(amount)){
            other_account.deposit(amount);
            return true;
        } else {
            return false;
        }
    }

    /**
     * This method is used to give the type of the account and the balance in a string format.
     */

    public abstract String get_account_details();
}