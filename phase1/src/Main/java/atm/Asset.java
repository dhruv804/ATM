package atm;

public abstract class Asset extends Account{

    protected double balance;

    public Asset(){
        super();
        this.balance = 0;
    }

    @Override
    public void withdraw(double amount) {
        balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }
}
