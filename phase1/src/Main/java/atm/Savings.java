package atm;

public class Savings extends Asset{

    public Savings() {
        super();
    }

    /**
     *
     * @param amount the amount to withdraw (amount >= 0)
     */
    @Override
    public void withdraw(double amount) {
        if (balance-amount >= 0) {
            super.withdraw(amount);
        } else {
            System.out.printf("Savings Account does not have enough balance to withdraw $%f." +
                    "Current Balance at $%f", amount, balance);
        }
    }

    /**
     *
     * @param amount the amount to deposit (amount >= 0)
     */
    @Override
    public void deposit(double amount) {
        super.deposit(amount);
    }
}
