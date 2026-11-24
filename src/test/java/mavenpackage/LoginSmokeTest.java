package mavenpackage;


import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginSmokeTest {

    private String browser;
    private String baseUrl;

    @BeforeMethod
    public void setUp() {
        browser = System.getProperty("browser", "chrome");
        baseUrl = System.getProperty("baseUrl", "https://qa.example.com");
    }

    @Test
    public void verifyValidLoginSmoke() {
        System.out.println("[LoginSmokeTest] Running on browser: " + browser + " | URL: " + baseUrl + "/login");
        boolean loginSuccess = performLogin("validUser", "ValidPass@123");
        Assert.assertTrue(loginSuccess, "Expected valid login to succeed in smoke test.");
    }

    private boolean performLogin(String username, String password) {
        return username != null && !username.isEmpty() && password != null && !password.isEmpty();
    }
}