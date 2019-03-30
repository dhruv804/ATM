package atm;

import java.io.Serializable;

public class Student extends Chequing implements Serializable {

    public Student() {
        super();
    }


    public void remove_transaction_fee() {
        transaction_limit = this.getTransaction_count() + 1;
    }
}
