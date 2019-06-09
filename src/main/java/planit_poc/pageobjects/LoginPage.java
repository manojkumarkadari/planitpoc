package planit_poc.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import planit_poc.helpers.PageObject;
import planit_poc.helpers.Settings;



public class LoginPage extends PageObject {

	// WebDriver driver;

		public LoginPage(WebDriver driver) {           
			//this.driver = driver;
			super(driver);
			PageFactory.initElements(driver, this);
			}
	//Page Elements	
		@FindBy(xpath = "//a[@class='ico-login' and text()='Log in']")
		public WebElement loginbtn;
		@FindBy(xpath = "//div[@class='page-title']/child::h1")
		public WebElement loginpagetitle;
		@FindBy(css = "input#Email")
		public WebElement emailid;
		@FindBy(css = "input#Password")
		public WebElement password;
		@FindBy(xpath = "//input[@class ='button-1 login-button' and @value='Log in']")
		public WebElement loginsubmitbtn;
		
		public String loginButton="//a[@class='ico-login' and text()='Log in']";
		
		public void navigateToLogin() {
			driver.get(Settings.getProperty("url"));
		}
		
		public void validatePageLoaded() {
			assertExists(loginButton);
		}
		public void clickLogin() {
			loginbtn.click();
		}
		public String getPageTitle() {
			checkVisible(loginpagetitle);
			return loginpagetitle.getText();
		}
		
		public void doLogin(String userName,String passWord) {
			emailid.sendKeys(userName);
			password.sendKeys(passWord);
			loginsubmitbtn.submit();
			
		}
		
		
		
		
}
