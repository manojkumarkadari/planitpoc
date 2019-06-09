package planit_poc.helpers;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import planit_poc.helpers.Settings;

public final class DriverProperties {

	static String driverPath =  Settings.getProperty("driver_path");
	public static WebDriver intializeWebdriver()
	{
		switch (Settings.getProperty("browser").toUpperCase()) {
		case "CHROME":
			return initializeChromeDriver();
		case "IE":
			return initializeIeDriver();
		default:
			return	initializeChromeDriver();
			
		}
	}
	
	private static WebDriver initializeChromeDriver()
	{
		System.setProperty("webdriver.chrome.driver",driverPath+File.separator+"chromedriver.exe");
		
		 WebDriver chromeDriver = new ChromeDriver();
		 chromeDriver.manage().deleteAllCookies();
		 chromeDriver.manage().window().maximize();
		 return chromeDriver;
		
	}
	public static WebDriver initializeIeDriver()
	{
		System.setProperty("webdriver.ie.driver", driverPath+File.separator+"IEDriverServer.exe");
		 
		 return new InternetExplorerDriver();
		
	}

}
