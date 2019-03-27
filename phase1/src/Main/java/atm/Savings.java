package atm;

import java.io.Serializable;

public class Savings extends Asset implements Serializable {

    static private double interest_rate = 0.001;
    private int transaction_count;
    private int transaction_limit;
    private double transaction_fee;

    public Savings() {
        super();
    }

    public boolean transfer_out(double amount){
        return withdraw(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance-amount >= 0) {
            return super.withdraw(amount);
        } else {
            //System.out.printf("Savings Account does not have enough balance to withdraw $%f.", amount);
            return false;
        }
    }


    public void interest_deposit(){
        balance = balance * (1 + interest_rate);
        balance = (double) Math.round(balance * 100)/100;
    }

    @Override
    public String toString(){
        return "Savings" + this.getAccount_num();
    }

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

    public void reset_transaction_count() {
        transaction_count = 0;
    }

    public double getTransaction_fee() {
        return transaction_fee;
    }
}
