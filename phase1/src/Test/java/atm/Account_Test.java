package atm;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by vedantshah on 2019-02-25.
 */
public class Account_Test {
    private Account a, b;

    @BeforeEach
    public void setUp() {
        a = new Savings();
        b = new Savings();
    }

    @Test
    public void account_number_test() {
        assertTrue(a.getAccount_num() == 1);
        assertTrue(b.getAccount_num() == 2);
    }
}
