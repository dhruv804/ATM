package atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by vedantshah on 2019-02-25.
 */
public class CreditCard_Test {
    private CreditCard a, b;
    //private User u1, u2;

    @BeforeEach
    void setUp() {
        //u1 = new User("John");
        //u2 = new User("Ben");
        a = new CreditCard();
        b = new CreditCard();
    }

    @Test
    public void withdraw_deposit_test() {
        a.withdraw(500);
        assertTrue(a.getBalance() == 500);

        a.deposit(200);
        assertTrue(a.getBalance() == 300);

        a.deposit(500);
        assertTrue(a.getBalance() == -200);
    }

    @Test
    public void transfer_test(){
        assertFalse(a.transfer_out(500));
        assertTrue(a.getBalance() == 0);
    }
}
