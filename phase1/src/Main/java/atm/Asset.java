package atm;

public abstract class Asset extends Account{

    protected double balance;

    public double getBalance() {
        return balance;
    }

    public Asset(User u){
        super(u);
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

        if (this.withdraw(amount)){
            other_account.deposit(amount);
            return true;
        } else {
            return false;
        }

    }
}
