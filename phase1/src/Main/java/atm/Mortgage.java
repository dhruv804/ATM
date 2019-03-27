package atm;

import java.io.Serializable;

public class Mortgage extends Debt implements Serializable {

    double initial_amount;
    double interest_rate;

    public Mortgage(double initial_amount, double interest_rate) {
        super();
        this.initial_amount = -initial_amount;
        this.interest_rate = interest_rate;
    }

    @Override
    public boolean transfer_out(double amount) {
        return this.withdraw(amount);
    }

    @Override
    public void transfer_in(double amount) {
        this.deposit(amount);
    }

    @Override
    public String toString(){
        return "Mortgage: "+this.getAccount_num();
    }

    public String get_account_details(){
        String l1 = "Account Type: Mortgage\n";
        String l2 = "Balance: " + this.getBalance() + "\n";
        return l1+l2;
    }
}
