package atm;

import java.io.Serializable;

public abstract class Debt extends Account implements Serializable {

    protected double balance;
    protected int limit;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Debt(){
        super();
        this.balance = 0;
        this.limit = 1000;
    }

    @Override
    public boolean withdraw(double amount) {
        if(amount <= this.limit) {
            balance += amount;
            this.limit -= amount;
            return true;
        }
        return false;
    }

    @Override
    public void deposit(double amount) {
        balance -= amount;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public abstract boolean transfer_out(double amount);

    public abstract void transfer_in(double amount);


}
