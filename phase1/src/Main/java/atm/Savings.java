package atm;

public class Savings extends Asset{

    static private double interest_rate = 0.001;

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
}
