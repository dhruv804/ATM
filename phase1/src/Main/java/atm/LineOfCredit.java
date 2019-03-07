package atm;

public class LineOfCredit extends Debt {

    public LineOfCredit() {
        super();
    }

    @Override
    public boolean transfer_out(double amount) {
        return this.withdraw(amount);
    }

    @Override
    public void transfer_in(double amount) {
        this.deposit(amount);
    }

    @Override
    public String toString(){
        return "Line of Credit"+this.getAccount_num();
    }

    public String get_account_details(){
        String l1 = "Account Type: Line of Credit\n";
        String l2 = "Balance: " + this.getBalance() + "\n";
        return l1+l2;
    }
}
