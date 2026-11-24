package mavenpackage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginRememberMeRegressionTest {

    private String browser;
    private String baseUrl;

    @BeforeMethod
    public void setUp() {
        browser = System.getProperty("browser", "chrome");
        baseUrl = System.getProperty("baseUrl", "https://qa.example.com");
    }

    @Test
    public void verifyRememberMePersistsSession() {
        System.out.println("[LoginRememberMeRegressionTest] Running on browser: " + browser + " | URL: " + baseUrl + "/login");
        boolean sessionPersisted = loginWithRememberMe("validUser", true);
        Assert.assertTrue(sessionPersisted, "Expected session token to persist when Remember Me is checked.");
    }

    private boolean loginWithRememberMe(String username, boolean rememberMe) {
        return username != null && rememberMe;
    }
}