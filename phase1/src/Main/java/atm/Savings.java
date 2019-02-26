package atm;

public class Savings extends Asset{

    static private double interest_rate = 0.001;

    public Savings(User u) {
        super(u);
    }


    /**
     *
     * @param amount the amount to withdraw (amount >= 0)
     */
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
        balance = balance * (1 + interest_rate); //TODO: Make this better
    }
}
