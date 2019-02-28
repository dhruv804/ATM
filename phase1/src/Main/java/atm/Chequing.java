package atm;

public class Chequing extends Asset{

    private boolean default_chequing;

    public Chequing() {
        super();
        this.default_chequing = true;
    }

    public void setDefault_chequing(boolean default_chequing) {
        this.default_chequing = default_chequing;
    }

    public boolean transfer_out(double amount){
        return withdraw(amount);
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
