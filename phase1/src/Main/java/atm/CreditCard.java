package atm;

public class CreditCard extends Debt{

    public CreditCard() {
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
    public String get_account_type(){
        return "Credit Card";
    }


}
