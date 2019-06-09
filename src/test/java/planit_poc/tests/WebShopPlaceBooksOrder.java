package planit_poc.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.codoid.products.exception.FilloException;

import planit_poc.helpers.DriverProperties;
import planit_poc.helpers.FileUtils;
import planit_poc.helpers.Settings;
import planit_poc.pageobjects.BooksPage;
import planit_poc.pageobjects.HomePage;
import planit_poc.pageobjects.LoginPage;
import planit_poc.pageobjects.ShoppingCartPage;

/**
 * Author: Prashanth Jain Engoli Description: .
 */
public class WebShopPlaceBooksOrder {
	//public WebDriver driver;

	@BeforeTest
	public void beforeMethod() throws IOException {
		Settings.loadProperties();

	}

	@DataProvider
	public Object[][] getInputData() throws FilloException {

		return FileUtils.readInputExcelData(Settings.getProperty("input_path") + File.separator + "OrderDetails.xlsx");

	}

	@Test(testName = "Validate Placing Book Order functionalty", dataProvider = "getInputData")
	public void ValidatePlacingBooksOrderFunctionality(HashMap<String, String> inputData) throws Exception {
		// Constants here
		double expSubTotal,actSubTotal;
		String userName = inputData.get("UserName").trim();
		String password = inputData.get("Password").trim();
		String bookName = inputData.get("ItemToBeSelected").trim();
		String quantity = inputData.get("Quantity").trim();
		

		// TO validate
		String expWelcomeMessage = "Welcome, Please Sign In!";
		String expSuccessmsg = "The product has been added to your shopping cart";
		
		WebDriver driver = DriverProperties.intializeWebdriver();

		// Initialization of pages
		LoginPage loginPage = new LoginPage(driver);
		HomePage homePage = new HomePage(driver);
		BooksPage booksPage = new BooksPage(driver);
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);

		// 1. Login And validate the PageLoad
		loginPage.navigateToLogin();
		loginPage.validatePageLoaded();

		// 2.Click on Login And verify the Page Title
		loginPage.clickLogin();
		// 3. Validate expWelcomeMessage
		Assert.assertEquals(expWelcomeMessage, loginPage.getPageTitle().trim());

		// 4.Login with credentials
		loginPage.doLogin(userName, password);
		// 5.validate AccountId exists on top right of the page
		homePage.validateAccountIdExists();
		// 6.Clear the shopping cart if items exist in the cart
		if (homePage.isItemsExistInCart()) {
			homePage.clickShoppingCart();
			shoppingCartPage.validatePageLoaded();
			shoppingCartPage.clearShoppingCart();
			shoppingCartPage.validateShoppingCartEmptyMessgDisplayed();
		}
		// 7.select books from categories
		homePage.clickBooksCategory();
		// 8.Select Book 'Fiction' from the list
		booksPage.validatePageLoaded();
		booksPage.selectBook(bookName);
		booksPage.selectedBookDisplayed(bookName);
		// 9.Get the price details and enter more than one in quantity
		String price = booksPage.getPriceValue();
		booksPage.clearAndEnterQuantity(quantity);
		//10.click on add cart
		booksPage.clickAddToCart();
		// 11.validate the success message
		Assert.assertEquals(booksPage.getSucssesMessage().trim(), expSuccessmsg);
		//12. click on shoppingcart
		homePage.clickShoppingCart();
		shoppingCartPage.validatePageLoaded();
		//Validate the subtotal of selectedItem
		expSubTotal=Double.valueOf(price)*Double.valueOf(quantity);
		actSubTotal=Double.valueOf(shoppingCartPage.getSubTotalPrice());
		Assert.assertEquals(actSubTotal, expSubTotal, 0);
		//13.click on checkout
		shoppingCartPage.clickCheckOutButton();
		driver.quit();

	}

	@AfterTest
	public void afterTest() {
	//	driver.quit();
	}

}
