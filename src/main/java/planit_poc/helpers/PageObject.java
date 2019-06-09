package planit_poc.helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageObject {

	// The default framework timeout is 60 seconds.
		public int defaultTimeout = 60;

	protected WebDriver driver;
	
	public PageObject(WebDriver driver) {
	this.driver = driver;	
	}
	
	public boolean waitForEnabled(WebElement element) {
		return this.waitForEnabled(element, defaultTimeout);
	}
	public boolean waitForEnabled(WebElement element, int timeout)
			 {
		//String message = "Wait for element selected " + by.toString() + " to be enabled.";

		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.elementToBeClickable(element));
			
			return true;

		} catch (Throwable t) {
			String error = "waitForClickable: Element selected by " + element.toString() + " is not enabled after "
					+ Integer.toString(timeout) + " second(s).";
		}
			return false;
		}
	public boolean waitForVisible(By by, int timeout) throws Exception
			 {
		String message = "[waitForVisible] Wait for element selected " + by.toString() + " to be visible.";

		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
			
			return true;

		} catch (Throwable t) {
			String error = "waitForVisible: Element selected by " + by.toString() + " is not visible after "
					+ Integer.toString(timeout) + " second(s).";
							throw new Exception(message);
			
			//return false;
		}
	}


	public boolean checkVisible(WebElement element) {
		return this.checkVisible(element, defaultTimeout);
	}

	public boolean checkVisible(WebElement element, int timeout) {
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.visibilityOf(element));
			return true;

		} catch (Throwable t) {
			return false;
		}
		
		
	}
	public void assertExists(String element) {
		this.assertExists(element, defaultTimeout);
	}

	public void assertExists(String element, int timeout) {
		String message = "[assertExists] Assert that the element selected by '" + element.toString() + "' exists on screen";
		try {
			new WebDriverWait(driver, timeout).until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));

		} catch (TimeoutException t) {
			String error = "assertExists: Element selected by " + element.toString() + " could not be found on screen.";
			throw new RuntimeException(error);
		}
		
	}
//TODO  check
public String replaceWithValue(String actual,String... parameters) {
	for(String parameter : parameters) {
		actual=actual.replaceFirst("\\?", parameter);
	}
	return actual;
	
}

}

	
	
	

