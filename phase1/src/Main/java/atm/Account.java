package atm;

public abstract class Account {
    private static int account_count = 0;
    private int account_num;
    protected double balance;
    //private User user;

    public abstract double getBalance();

    public abstract void setBalance(double balance);

    public Account(){
        account_count++;
        account_num = account_count;
        this.balance = 0;
        //this.user = user;
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
        if (this.withdraw(amount)){
            other_account.deposit(amount);
            return true;
        } else {
            return false;
        }
    }

    public String get_account_details(){
        String l1 = "Account Type: Chequing\n";
        String l2 = "Balance: " + "0" + "\n";
        return l1+l2; //TODO: Implement this as abstact, override in all accounts
    }
}