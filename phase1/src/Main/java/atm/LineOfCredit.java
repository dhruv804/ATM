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
}
