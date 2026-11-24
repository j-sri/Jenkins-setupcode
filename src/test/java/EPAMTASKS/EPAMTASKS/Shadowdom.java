package EPAMTASKS.EPAMTASKS;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
public class Shadowdom {
	
	public WebDriver driver;
	private JavascriptExecutor js;
	protected static ExtentReports extent;
@BeforeTest
public void launch_url() {
	extent = ExtentReportManager.getReportInstance();
	driver=new ChromeDriver();
	driver.get("https://the-internet.herokuapp.com/shadowdom");
	driver.manage().window().maximize();
	
}
@Test

public void shadowdom() {
	ExtentTest test = extent.createTest("shadow dom");
	 test.log(Status.INFO, "Starting login test");

     // Your Selenium automation code here
     test.log(Status.INFO, "Opening application");
	WebElement shadowHost = driver.findElement(By.cssSelector("my-paragraph"));

    //SearchContext shadowRoot = shadowHost.getShadowRoot();
    WebElement slottedSpan = shadowHost.findElement(By.cssSelector("span[slot='my-text']"));
    String slottedText = slottedSpan.getText().trim();
    System.out.println("Slotted span text: " + slottedText);
    Assert.assertEquals(slottedText, "Let's have some different text!");
    test.log(Status.PASS, "Login test passed");
}
@Test
public void testShadowDomWithJsExecutor() {
	js = (JavascriptExecutor) driver;
    WebElement shadowHost = driver.findElement(By.cssSelector("my-paragraph"));
   WebElement shadowParagraph = (WebElement) js.executeScript(
            "return arguments[0].shadowRoot.querySelector('p');",
            shadowHost
    );
    Assert.assertNotNull(shadowParagraph, "Shadow <p> element should be present");
    String paragraphText = (String) js.executeScript(
            "return arguments[0].textContent;",
            shadowParagraph
    );
    System.out.println("Shadow <p> textContent: " + paragraphText);
    String slottedText = (String) js.executeScript(
            "const slot = arguments[0].shadowRoot.querySelector(\"slot[name='my-text']\");" +
            "return slot.assignedNodes({flatten: true}).map(n => n.textContent).join('').trim();",
            shadowHost
    );
    Assert.assertEquals(slottedText, "Let's have some different text!");
    js.executeScript("arguments[0].style.border='2px solid red';", shadowParagraph);
}
@AfterTest
public void closebrowser() {
	driver.close();
	extent.flush();
}
}
