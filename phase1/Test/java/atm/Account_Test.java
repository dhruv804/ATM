package java.atm;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by vedantshah on 2019-02-25.
 */
public class Account_Test {
    Account a, b;

    @BeforeAll
    void setUp() {
        a = new Account();
        b = new Account();
    }

    @Test
    void account_number_test() {
        assertTrue(a.getAccount_num() == 1);
        assertTrue(b.getAccount_num() == 2);
    }
}
