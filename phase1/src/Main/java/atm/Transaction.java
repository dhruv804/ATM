package atm;

import java.io.Serializable;

/**
 * Created by vedantshah on 2019-03-29.
 */
public class Transaction implements Serializable{
    Account account_from;
    Account account_to;
    double amount;

    public Transaction(Account account_from, Account account_to, double amount){
        this.account_from = account_from;
        this.account_to = account_to;
        this.amount = amount;
    }

    public void undo(){
        if (account_from != null){
            account_from.deposit(amount);
        }

        if (account_to != null){
            account_to.withdraw(amount);
        }
    }

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

    public String toString(){
        return String.valueOf(amount);
    }

}
