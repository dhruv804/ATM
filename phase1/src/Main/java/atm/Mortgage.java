package atm;

import java.io.Serializable;

public class Mortgage extends Debt implements Serializable {

    double balance;
    double interest_rate;

    public Mortgage(double initial_amount, double interest_rate) {
        super();
        this.balance = -initial_amount;
        this.interest_rate = interest_rate;
    }

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

    @Override
    public String toString(){
        return "Mortgage: "+this.getAccount_num();
    }

    public void interest_deposit() {
        balance = balance * (1 + interest_rate);
    }

    public String get_account_details(){
        String l1 = "Account Type: Mortgage\n";
        String l2 = "Balance: " + this.getBalance() + "\n";
        return l1+l2;
    }
}
