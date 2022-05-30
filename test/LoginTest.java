import be.Usertypes.Admin;
import be.Usertypes.User;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import dal.dao.IUserDAO;
import dal.dao.UserDAO;
import org.junit.jupiter.api.*;

import java.io.IOException;

public class LoginTest {
    private static final String CORRECT_USERNAME = "testadmin";
    private static final String CORRECT_PASSWORD = "adminPassword123";

    private static final String INCORRECT_USERNAME = "testeradmin";
    private static final String INCORRECT_PASSWORD1 = "adminpassword123"; //Wrong capitalization
    private static final String INCORRECT_PASSWORD2 = "123Wrongpassword";

    private static User user;


    private static IUserDAO userDAO;


    @BeforeAll
    static void setup() throws IOException {
        userDAO = new UserDAO();

        user = new Admin(-1, CORRECT_USERNAME, 1);

        userDAO.createUser(user, CORRECT_PASSWORD);
    }

    @AfterAll
    static void done(){
        userDAO.deleteUser(user.getId());
    }

    @Test
    @DisplayName("Correct login credentials")
    void loginValid() throws SQLServerException {

        User actualUser = userDAO.checkLogin(CORRECT_USERNAME, CORRECT_PASSWORD);

        User expectedUser = user;

        Assertions.assertEquals(expectedUser.getId(), actualUser.getId());
    }

    @Test
    @DisplayName("Incorrectly capitalized password")
    void loginInvalid1() throws SQLServerException {

        User actualUser = userDAO.checkLogin(CORRECT_USERNAME, INCORRECT_PASSWORD1);

        User expectedUser = null;

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Incorrect password")
    void loginInvalid2() throws SQLServerException {

        User actualUser = userDAO.checkLogin(CORRECT_USERNAME, INCORRECT_PASSWORD2);

        User expectedUser = null;

        Assertions.assertEquals(expectedUser, actualUser);
    }

    @Test
    @DisplayName("Incorrect username")
    void loginInvalid3() throws SQLServerException {

        User actualUser = userDAO.checkLogin(INCORRECT_USERNAME, CORRECT_PASSWORD);

        User expectedUser = null;

        Assertions.assertEquals(expectedUser, actualUser);
    }
}
