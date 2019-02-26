package atm;

public class LineOfCredit extends Debt {

    public LineOfCredit(User u) {
        super(u);
    }

    @Override
    public boolean transfer_out(double amount) {
        return this.withdraw(amount);
    }
}
