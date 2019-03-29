package atm;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by vedantshah on 2019-02-25.
 */
public class Account_Test {
    private Account a, b;
    //private User u1, u2;

    @BeforeEach
    public void setUp() {
        //u1 = new User("Johnny");
        //u2 = new User("Ben");
        a = new Savings();
        b = new Savings();
    }

    @Test
    public void account_number_test() {
        assertTrue(a.getAccount_num() == 1);
        assertTrue(b.getAccount_num() == 2);
    }


}
