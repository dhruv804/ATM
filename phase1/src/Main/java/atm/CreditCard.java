package atm;

public class CreditCard extends Debt{

    public CreditCard(User u) {
        super(u);
    }

    @Override
    public boolean withdraw(double amount) {
        balance += amount;
        return true;
    }
}
