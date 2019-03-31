package atm;

import java.io.Serializable;

public class Student extends Chequing implements Serializable {

    public Student() {
        super();
    }

    /**
     * @return used to get account number in chequing account number format
     *
     * This method returns account number appended to the string "Student"
     */

    @Override
    public String toString(){
        return "Student"+this.getAccount_num();
    }

    public void remove_transaction_fee() {
        transaction_limit = this.getTransaction_count() + 1;
    }
}
