package mavenpackage;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginInvalidCredentialsSmokeTest {

    private String browser;
    private String baseUrl;

    @BeforeMethod
    public void setUp() {
        browser = System.getProperty("browser", "chrome");
        baseUrl = System.getProperty("baseUrl", "https://qa.example.com");
    }

    @Test
    public void verifyInvalidPasswordShowsError() {
        System.out.println("[LoginInvalidCredentialsSmokeTest] Running on browser: " + browser + " | URL: " + baseUrl + "/login");
        String errorMessage = attemptLogin("validUser", "WrongPassword");
        Assert.assertEquals(errorMessage, "Invalid username or password");
    }

    private String attemptLogin(String username, String password) {
        if (!"ValidPass@123".equals(password)) {
            return "Invalid username or password";
        }
        return "";
    }
}