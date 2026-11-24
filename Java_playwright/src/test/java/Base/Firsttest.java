package Base;



import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class Firsttest extends basetest {
	@Test
	public void verifytitle() {
		page.navigate("https://demoqa.com/");
		String title=page.title();
		System.out.println(title);
		page.locator("//h5[contains(text(),'Elements')]").click();
		
		
		
		
	}
	

	
}

