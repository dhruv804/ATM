package atm;

import java.io.Serializable;
import java.security.cert.TrustAnchor;

public abstract class Asset extends Account implements Serializable {

    protected double balance;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Asset(){
        super();
        this.balance = 0;
    }

    /**
     * This method removed a specified amount of money from the balance
     * @param amount: the amount to withdraw from account
     * @return true if successful, false if not
     */

    @Override
    public boolean withdraw(double amount) {
        balance -= amount;
        return true;
    }


    /**
     * This method adds a specified amount of money to the balance
     * @param amount: the amount to deposit to account
     */

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    /**
     * This method transfers money from one account to another, if possible
     * @param other_account Payee Account
     * @param amount Amount to be transferred
     * @return true if successful, false if not
     */

    @Override
    public boolean transfer(Account other_account, double amount) {
        if (this.transfer_out(amount)){
            other_account.deposit(amount);
            return true;
        } else {
            return false;
        }
    }

    public abstract boolean transfer_out(double amount);

}
