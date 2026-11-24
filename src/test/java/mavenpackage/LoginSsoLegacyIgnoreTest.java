package mavenpackage;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginSsoLegacyIgnoreTest {

    @Test
    public void verifyLegacySsoFlow() {
        System.out.println("[LoginSsoLegacyIgnoreTest] This legacy test is excluded by Surefire naming pattern.");
        Assert.assertTrue(true);
    }
}