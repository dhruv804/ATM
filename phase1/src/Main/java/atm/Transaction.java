package atm;

import java.io.Serializable;

/**
 * Created by vedantshah on 2019-03-29.
 */
public class Transaction implements Serializable{
    Account account_from;
    Account account_to;
    double amount;

    /**
     * This method is used to initialize a new transaction. It determines the account_from from which an amount
     * is deposited to an account account_to
     *
     * @param account_from initiating account
     * @param account_to deposited account
     * @param amount transfer amount
     */

    public Transaction(Account account_from, Account account_to, double amount){
        this.account_from = account_from;
        this.account_to = account_to;
        this.amount = amount;
    }

    /**
     * This method is used to undo a transaction.
     */

    public void undo(){
        if (account_from != null){
            account_from.deposit(amount);
        }

        if (account_to != null){
            account_to.withdraw(amount);
        }
    }

    /**
     * This method is used to return the transaction details of a specific transaction in a string format. It returns
     * the account from which a transaction is initiated and the account to which the amount is deposited to.
     *
     * @return account_from + account_to + amount
     */

    public String getInfo(){
        String u;

        String from;
        if (account_from != null) {
            from = "Money withdrawn from: " + account_from.toString();
        } else {
            from = "Money withdrawn from: " + "Non-bank account";
        }

        String to;
        if (account_to != null) {
            to = "Money deposited to: " + account_to.toString();
        } else {
            to = "Money deposited to: " + "Non-bank account";
        }

        String amt = "Amount moved: " + String.valueOf(amount);

        return from + "\n" +to + "\n" + amt;

    }

    /**
     * The converts the transaction amount into a string.
     *
     * @return string format of transaction amount
     */

    public String toString(){
        return String.valueOf(amount);
    }

}
