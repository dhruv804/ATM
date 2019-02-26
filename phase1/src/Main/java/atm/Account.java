package atm;

public abstract class Account {
    private static int account_count = 0;
    private int account_num;
    private User user;


    public Account(User user){
        account_count++;
        account_num = account_count;
        this.user = user;
    }

    public int getAccount_num() {
        return account_num;
    }

    public abstract boolean withdraw(double amount);

    public abstract void deposit(double amount);

    public void interest_deposit(){
        ;
    }

    /**
     *
     * @param other_account Payee Account
     * @param amount Amount to be transferred
     * @return true if successful, false if not
     */
    public boolean transfer(Account other_account, double amount){
        return false;
        //TODO
    }

}