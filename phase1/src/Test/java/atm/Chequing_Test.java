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
    private User u1, u2;

    @BeforeEach
    public void setUp() {
        u1 = new User("Johnny");
        u2 = new User("Ben");
        a = new Chequing(u1);
        b = new Chequing(u2);
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
}
