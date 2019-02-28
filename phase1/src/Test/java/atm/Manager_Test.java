package atm;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Manager_Test {
    BankManager m1;
    User u1;
    Chequing c1;
    Savings s1;

    @BeforeEach
    public void setUp(){
        m1 = new BankManager();
        u1 = new User("Ben");
        c1 = new Chequing();
        s1 = new Savings();
    }

    @Test
    public void request_test(){
        m1.request_account(u1, c1);
        m1.request_account(u1, s1);
        assertTrue(m1.pending_requests.size() == 2);
    }
}
