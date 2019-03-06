package atm;

public class CreditCard extends Debt{

    public CreditCard() {
        super();
    }

    @Override
    public boolean transfer_out(double amount) {
        return false;
    }

    @Override
    public String get_account_type(){
        return "Credit Card";
    }


}
