package atm;

public abstract class Debt extends Account{

    protected double balance;

    public Debt(){
        super();
        this.balance = 0;
    }

    @Override
    public void withdraw(double amount) {
        balance += amount;
    }

    @Override
    public void deposit(double amount) {
        balance -= amount;
    }
}
