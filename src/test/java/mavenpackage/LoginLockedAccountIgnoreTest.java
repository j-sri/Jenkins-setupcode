package mavenpackage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginLockedAccountIgnoreTest {

    @Test
    public void verifyLockedAccountMessage() {
        System.out.println("[LoginLockedAccountIgnoreTest] This test is excluded by Surefire naming pattern.");
        Assert.assertTrue(true);
    }
}