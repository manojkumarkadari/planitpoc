package planit_poc.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import planit_poc.helpers.PageObject;

public class HomePage extends PageObject{

	 WebDriver driver;


		public  HomePage(WebDriver driver) {           
			super(driver); 
			PageFactory.initElements(driver, this);
			}
		
		//@FindBy(xpath = "//*[contains(@class, 'ico-login')]")
		//public WebElement loginbtn;
		
		@FindBy(xpath = "//li[@id='topcartlink']/child::a[@href='/cart']")
		public WebElement shoppingcartbtn;
		@FindBy(xpath="//a[@href ='/cart']/child::span[@class='cart-qty']")
		public WebElement shoppingcartqty;
		@FindBy(xpath = "//div[@class='header-links']//a[@href='/customer/info']")
		public WebElement accountid;
		
		@FindBy(xpath = "//a[contains(text(), 'Log out')]")
		public WebElement logout;
		@FindBy(xpath = "//ul[@class='top-menu']/child::li/child::a[@href ='/books']")
		public WebElement booksmenu;
		
		public String accountId="//div[@class='header-links']//a[@href='/customer/info']";
		
		
		
		public void validateAccountIdExists() {
			assertExists(accountId);
		}
		
		public void clickShoppingCart() {
			waitForEnabled(shoppingcartbtn);
			shoppingcartbtn.click();
		}
		
		public boolean isItemsExistInCart() {
			String quantityDisplayed=shoppingcartqty.getText();
			int quantity =Integer.valueOf(quantityDisplayed.replaceAll("[^0-9]",""));
			if(quantity>0)
				return true;
			else
				return false;

		}
		public void clickBooksCategory() {
			booksmenu.click();
		}
		
		
}
