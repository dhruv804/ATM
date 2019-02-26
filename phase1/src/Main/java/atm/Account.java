package atm;

public abstract class Account {
    private static int account_count = 0;
    private int account_num;


    public Account(){
        account_count++;
        account_num = account_count;
    }

    public int getAccount_num() {
        return account_num;
    }

    public abstract void withdraw(double amount);

    public abstract void deposit(double amount);

}