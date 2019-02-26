package atm;

public abstract class Debt extends Account{

    protected double balance;

    public double getBalance() {
        return balance;
    }

    public Debt(User u){
        super(u);
        this.balance = 0;
    }

    @Override
    public boolean withdraw(double amount) {
        balance += amount;
        return true;
    }

    @Override
    public void deposit(double amount) {
        balance -= amount;
    }

    public abstract boolean transfer_out(double amount);
}
