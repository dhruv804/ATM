package atm;

public class LineOfCredit extends Debt {

    public LineOfCredit() {
        super();
    }

    @Override
    public boolean transfer_out(double amount) {
        return this.withdraw(amount);
    }
}
