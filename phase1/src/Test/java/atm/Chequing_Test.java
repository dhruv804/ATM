package atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by vedantshah on 2019-02-25.
 */
public class Chequing_Test {
    private Chequing a, b;
    //private User u1, u2;

    @BeforeEach
    public void setUp() {
        //u1 = new User("Johnny");
        //u2 = new User("Ben");
        a = new Chequing();
        b = new Chequing();
    }

    @Test
    public void deposit_test() {
        a.deposit(500);
        b.deposit(200);
        assertTrue(a.getBalance() == 500);
        assertTrue(b.getBalance() == 200);
    }

    @Test
    public void withdraw_test() {
        a.deposit(200);

        b.deposit(50);

        assertTrue(a.withdraw(50));
        assertTrue(a.getBalance() == 150);

        assertFalse(b.withdraw(200));
        assertTrue(b.getBalance() == 50);

        assertTrue(b.withdraw(100));
        assertTrue(b.getBalance() == -50);

        assertFalse(b.withdraw(1));
        assertTrue(b.getBalance() == -50);

    }

    @Test
    void interest_payment_test() {
        a.deposit(1000);
        a.interest_deposit();
        assertTrue(a.getBalance() == 1000);
    }
}
