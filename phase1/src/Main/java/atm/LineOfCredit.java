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
    public String get_account_type(){
        return "Line of Credit";
    }
}
