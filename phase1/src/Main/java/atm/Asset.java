package atm;

public abstract class Asset extends Account{

    protected double balance;

    public double getBalance() {
        return balance;
    }

    public Asset(){
        super();
        this.balance = 0;
    }

    @Override
    public boolean withdraw(double amount) {
        balance -= amount;
        return true;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }


    @Override
    public boolean transfer(Account other_account, double amount) {
        if (this.transfer_out(amount)){
            other_account.deposit(amount);
            return true;
        } else {
            return false;
        }
        //TODO: Should be in User Class
    }

    public abstract boolean transfer_out(double amount);
}
