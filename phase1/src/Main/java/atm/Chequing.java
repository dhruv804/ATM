package atm;

public class Chequing extends Asset{

    private boolean default_chequing;

    public Chequing(User u) {
        super(u);
        this.default_chequing = false;
    }

    public void setDefault_chequing(boolean default_chequing) {
        this.default_chequing = default_chequing;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance-amount >= -100 && balance >= 0) {
            return super.withdraw(amount);
        } else {
            //System.out.printf("Savings Account does not have enough balance to withdraw $%f.", amount);
            return false;
        }
    }


}
