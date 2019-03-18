package atm;

import org.junit.jupiter.api.BeforeEach;

public class Manager_Test {
    BankManager m1;
    User u1;
    Chequing c1;
    Savings s1;

    @BeforeEach
    public void setUp(){
        m1 = new BankManager();
        u1 = new User("Ben", "ben", "pass");
        c1 = new Chequing();
        s1 = new Savings();
    }

}
