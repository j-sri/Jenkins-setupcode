package EPAMTASKS.EPAMTASKS;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class InfiniteScroll {
	private static WebDriver driver;
	JavascriptExecutor js=(JavascriptExecutor)driver;
	@Test
	public void scroll() throws TimeoutException {

		WebDriver driver = new ChromeDriver();
int currentcount=0;
int maxretries=100;
while(currentcount<maxretries) {
	List<WebElement>input=driver.findElements(By.xpath(""));
	currentcount=input.size();
	js.executeScript("window.scrollTo(0,document.body.scrollHeight);");
}
       

        } 
    }