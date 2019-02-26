package atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.lang.Math.abs;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class Savings_Test {

    private Savings a, b;
    private User u1, u2;

    @BeforeEach
    public void setUp() {
        u1 = new User("Johnny");
        u2 = new User("Ben");
        a = new Savings(u1);
        b = new Savings(u2);
    }

    @Test
    public void deposit_test() {
        a.deposit(500);
        b.deposit(200);
        assertTrue(a.getBalance() == 500);
        assertTrue(b.getBalance() == 200);
    }

    @Test
    public void withdrawal_test() {
        a.deposit(0);
        a.withdraw(100);
        b.deposit(200);
        b.withdraw(50);

        assertFalse(a.withdraw(100));
        assertTrue(a.getBalance() == 0);
        assertTrue(b.getBalance() == 150);
    }

    @Test
    public void interest_payment_test() {
        a.deposit(1000);
        a.interest_deposit();
        assertTrue(a.getBalance() - 1001 == 0);
    }

}
