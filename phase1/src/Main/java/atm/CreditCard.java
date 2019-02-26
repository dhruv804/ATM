package atm;

public class CreditCard extends Debt{

    public CreditCard(User u) {
        super(u);
    }

    @Override
    public boolean transfer_out(double amount) {
        return false;
    }


}
