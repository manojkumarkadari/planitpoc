package planit_poc.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import planit_poc.helpers.PageObject;

public class CheckOutPage extends PageObject {

	
	public CheckOutPage(WebDriver driver) {           
		super(driver);
		PageFactory.initElements(driver, this);
		}
	
	private String labelcheckout = "//h1[text()='Checkout']";
	
	@FindBy(xpath="//*[@id='billing-address-select']")
	public WebElement billingAddress;
	@FindBy(xpath="//h2[text()='%s']//following::input[@type='button'][1]")
	public WebElement continueBtn;
	@FindBy(xpath="//*[@id='shipping-address-select']")
	public WebElement shippingAddress;
	@FindBy(xpath="//label[text()='%s']//preceding-sibling::input")
	public WebElement shippingmethod;
	@FindBy(xpath="//label[text()='%s']//preceding-sibling::input")
	public WebElement paymentmethod;
	@FindBy(xpath="//*[@id='confirm-order-buttons-container']/input")
	public WebElement confirmorderBtn;
	@FindBy(xpath="//div[@class='title']//strong")
	public WebElement orderconfirmation;
	@FindBy(xpath="//a[text()='Click here for order details.']")
	public WebElement orderdetailslink;
	@FindBy(xpath="//ul[@class='details']//li[1]")
	public WebElement ordernumber;
	@FindBy(xpath="//input[contains(@class,'order-completed-continue-button')]")
	public WebElement ordercomplteBtn;
	@FindBy(xpath="//a[@class='ico-logout']")
	public WebElement logoutBtn;
	
	
	public void validatePageLoaded() {
		assertExists(labelcheckout);
	}
	

}
