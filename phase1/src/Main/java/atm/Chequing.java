package atm;

import java.io.Serializable;

public class Chequing extends Asset implements Serializable {

    protected boolean default_chequing;
    private int transaction_count;
    private int transaction_limit;
    private double transaction_fee;

    public Chequing() {
        super();
        this.default_chequing = true;
        transaction_count = 0;
        transaction_limit = 50;
        transaction_fee = 0.5;
    }

    public void setDefault_chequing(boolean default_chequing) {
        this.default_chequing = default_chequing;
    }

    public boolean transfer_out(double amount){
        transaction_count++;
        return withdraw(amount);
    }

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

    @Override
    public String toString(){
        return "Chequing"+this.getAccount_num();
    }

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

    public void reset_transaction_count() {
        transaction_count = 0;
    }

    public double getTransaction_fee() {
        return transaction_fee;
    }
}
