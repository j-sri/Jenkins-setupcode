package Base;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Download;
import com.microsoft.playwright.FrameLocator;

public class Epam_tasks extends basetest{

	
	@Test
	public void Frame_interaction() {
		page.navigate("https://the-internet.herokuapp.com/iframe");
		FrameLocator frame=page.frameLocator("#mce_0_ifr");
		frame.locator("#tinymce").click();
        frame.locator("#tinymce").press("Control+A");
        frame.locator("#tinymce").press("Backspace");
        frame.locator("#tinymce").fill("Hello from Playwright Java!");
        String text = frame.locator("#tinymce").textContent();
        Assert.assertEquals(text, "Hello from Playwright Java!");
        
        //Interact with an element outside the iframe in the main document
        String headertext=page.locator("h3").textContent();
        Assert.assertEquals(headertext, "An iFrame containing the TinyMCE WYSIWYG Editor");
		
	}
	
	@Test
	public void fileupload() {
		page.navigate("https://the-internet.herokuapp.com/upload");
	    page.locator("#file-upload")
	        .setInputFiles(Paths.get("C:\\Users\\ADMIN\\Documents\\Selenium Automation\\selenium-practice\\Playwright_java\\Java_playwright\\src\\test\\java\\Resources\\config.txt"));
	    page.locator("#file-submit").click();

	   
	    String actualMessage = page.locator("//h3[contains(text(),'File Uploaded!')]")
	                                .textContent();

	    Assert.assertTrue(actualMessage.contains("File Uploaded!"),
	                      "File upload failed!");

	    
	    String uploadedFile = page.locator("#uploaded-files").textContent();

	    Assert.assertEquals(uploadedFile.trim(), "config.txt");
		
	}
	@Test
	public void download() {
		page.navigate("https://the-internet.herokuapp.com/download");
		Download download=page.waitForDownload(() -> {
            page.locator(".example a").first().click();
        });
		Path targetPath = Paths.get("downloads/" + download.suggestedFilename());
        download.saveAs(targetPath);
		
        Assert.assertTrue(Files.exists(targetPath), "Downloaded file does not exist locally!");
		
		
	}
}
