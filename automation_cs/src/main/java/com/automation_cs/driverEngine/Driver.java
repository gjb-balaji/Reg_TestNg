
package com.automation_cs.driverEngine;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.automation_cs.*;

public class Driver extends Constants {
	
//	public static WebDriver driver;
//	public static ChromeOptions options;

	//browser stack setups
	public static String USERNAME = Constants.OR.getProperty("BrowserStack_UserName");
	public static String ACCESSKEY = Constants.OR.getProperty("BrowserStack_ACCESSKEY");;
	public static String URL = "https://" + USERNAME + ":" + ACCESSKEY + "@hub-cloud.browserstack.com/wd/hub";

	public static WebDriver navigate(String data1, String env) throws Exception {
		/*
		 * ***************************************************************************************************************************************
		 * Purpose		:	To launch the url in a specific OS and browser version
		 *
		 * Parameters	:   data1	-	OS Name, OS Version, Browser Name separated by ; which should be from properites file
		 * 					env	-	URL to navigate the browsers from properites file

		 * ***************************************************************************************************************************************
		 */
		String[] OS_Browser_Details = data1.split(";");
		String  Browser_Name;
		Browser_Name = OS_Browser_Details[2].toUpperCase().trim();
		
			String value;
			String CurDir = System.getProperty("user.dir");
			try {
				if (Browser_Name.equalsIgnoreCase("Firefox")) {
					value = CurDir + "\\GridConfiguration";
					System.setProperty("webdriver.gecko.driver", value + "\\geckodriver.exe");
					driver = new FirefoxDriver();

				}  else if (Browser_Name.equalsIgnoreCase("Chrome")) {
					value = CurDir + "\\GridConfiguration";
					System.setProperty("webdriver.chrome.driver", value + "\\chromedriver.exe");
					
					options = new ChromeOptions();
					options.addArguments("disable-infobars");
					Map<String, Object> prefs = new HashMap<String, Object>();
					prefs.put("credentials_enable_service", false);
					prefs.put("password_manager_enabled", false);
					options.setExperimentalOption("prefs", prefs);
					driver = new ChromeDriver(options);
					//driver = new ChromeDriver();
				} else if (Browser_Name.equalsIgnoreCase("edge")) {
					value = CurDir + "\\GridConfiguration";
					System.setProperty("webdriver.edge.driver", value + "\\MicrosoftWebDriver.exe");
					driver = new EdgeDriver();
				}
			} catch (Exception e) {
				Constants.bResult = false;
				Constants.bResume = false;
				System.out.println("Unable to Navigate " + e.getMessage());
				int len = e.getMessage().length();
				String Err = e.getMessage().substring(0, 20);
				if (len > 255) {
					Constants.vActRes = Browser_Name + "-" + Err;
				} else {
					Constants.vActRes = "Unable to Navigate " + e.getMessage();
					;
				}
				System.out.println("ActRes -" + Constants.vActRes);
			}		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// To set the driver for functions in different classes
		LocateDriver ld = new LocateDriver();
		ld.setDriver(driver);

		driver.get(env);
		return driver;
	}
	
	public static void propsInit () throws Exception {
		Control.getObjects();
	}

}