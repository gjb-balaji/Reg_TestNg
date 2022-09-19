package com.automation_cs.driverEngine;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.InetAddress;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.automation_cs.Constants;
import com.automation_cs.Control;
import com.google.common.net.InetAddresses;
import org.testng.Reporter;

public class Keywords extends Driver {

	public static void click(String Object) {
		/*
		 * *****************************************************************************
		 * ********************************************************** Purpose : To
		 * "CLICK" on the WebElement mentioned in Object (Xpath)
		 *
		 * Parameters : Object - (Property file or Xpath of the Webelement to be
		 * clicked).
		 *
		 * *****************************************************************************
		 */
		try {
			WebElement webElement = (new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Constants.Time))))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.OR.getProperty(Object))));

			webElement.click();

			Constants.vActRes = Constants.vExpRes;
			Constants.bResult = true;
			Reporter.log("User is able to click the button");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot Click " + e.getMessage());
			Constants.vActRes = "Clicking Error";
			Constants.bResult = false;
			Reporter.log("User is not able to click the button");
		}
	}

	public static void setOfElementsclick(String Object) {
		/*
		 * *****************************************************************************
		 * ********************************************************** Purpose : To
		 * "CLICK" on the WebElement mentioned in Object (Xpath)
		 *
		 * Parameters : Object - (Property file or Xpath of the Webelement to be
		 * clicked).
		 *
		 * *****************************************************************************
		 */
		try {
			// Thread.sleep(1000);
			// String e = "";
			WebElement webElement = (new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Constants.Time))))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.OR.getProperty(Object))));
			List<WebElement> li1 = driver.findElements(By.xpath(Constants.OR.getProperty(Object)));
			String e = "";
			int i = 1;
			JavascriptExecutor js = (JavascriptExecutor) driver;
			for (WebElement ee : li1) {
				if (i > 2 && i < 6) {
					js.executeScript("arguments[0].scrollIntoView(true);", ee);
					js.executeScript("arguments[0].click();", ee);
				}
				i++;
				if (i > 6) {
					break;
				}
			}
			Constants.vActRes = Constants.vExpRes;
			Constants.bResult = true;
			Reporter.log("User is able to add 3 items to the Cart page by clicking the selected items");
		} catch (Exception e) {
			e.printStackTrace();
			Constants.vActRes = "Clicking Error";
			Constants.bResult = false;
			Reporter.log("User is able to add 3 items to the Cart page by clicking the selected items" + e.getMessage());
		}
	}

	public static void enterText(String Object, String data1) {
		/*
		 * *****************************************************************************
		 * ********************************************************** Purpose : To
		 * "Enter Text" in the Text Field or WebElement mentioned in Object (Xpath).
		 *
		 * Parameters : Object - (Property file or Xpath of the WebElement) data1 -
		 * (String or Number to be Entered. If data1 is "NULL", skip entering value).
		 * *****************************************************************************
		 * **********************************************************
		 */
		String original = Constants.OR.getProperty(Object);
		try {
			WebElement webElement = driver.findElement(By.xpath(original));
			//((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);

			webElement.clear();
			//webElement.click();
			webElement.sendKeys(Constants.OR.getProperty(data1));
			Constants.vActRes = data1 + " is entered";
			Constants.bResult = true;
			Reporter.log("User is able to enter the " + Constants.OR.getProperty(data1) + " to the text box");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable  enter text : " + e.getMessage());
			Constants.vActRes = "Unable  enter text : " + data1;
			Constants.bResult = false;
			Reporter.log("User is able to enter the " + Constants.OR.getProperty(data1) + " to the text box" + e.getMessage());
		}
	}

	public static void closeBrowser() {
		/*
		 * *****************************************************************************
		 * ********************************************************** Purpose : To close
		 * browser Parameters : None
		 * *****************************************************************************
		 * **********************************************************
		 */
		try {
			driver.close();
			driver.quit();
			Constants.bResult = true;
			Constants.vActRes = "Browser is closed";
			Reporter.log("Browser is closed");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			Constants.bResult = false;
			Constants.vActRes = "Browser is NOT closed";
			Reporter.log("Browser is NOT closed");
		}
	}

	public static String verifyElement_Present(String Object) {
		/*
		 * *****************************************************************************
		 * ********************************************************** Purpose : To
		 * Verify the WebElement is displayed or not
		 *
		 * Parameters : Object - Xpath of WebElement to be verified data1 *
		 * *****************************************************************************
		 * **********************************************************
		 */
		String original = Constants.OR.getProperty(Object);
		try {

			WebElement webElement = (new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Constants.Time))))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.OR.getProperty(Object))));

			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].scrollIntoView(true);", webElement);

			System.out.println("The text value is = " + webElement.getText());
			String a = webElement.getText();

			Constants.vActRes = Constants.vExpRes;
			Constants.bResult = true;
			Reporter.log("The text is - " + a);
			return a;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Cannot Click " + e.getMessage());
			Constants.vActRes = "Error in getting the text";
			Constants.bResult = false;
			Reporter.log("Error in getting the text");
			return null;
		}
	}
	
	public static void actionMoveToElement(String Object) {
		/*
		 * *****************************************************************************
		 * ********************************************************** Purpose : To mouse
		 * hover on the WebElement
		 *
		 * Parameters : Object - Property file or Xpath of the WebElement 		 *
		 * *****************************************************************************
		 * **********************************************************
		 */
		try {
			String original = Constants.OR.getProperty(Object);

			WebElement webElement = (new WebDriverWait(driver, Duration.ofSeconds(Integer.parseInt(Constants.Time))))
					.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(original)));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", webElement);
			Actions action = new Actions(driver);
			action.moveToElement(webElement).build().perform();

			Constants.vActRes = "Mouse hover performed on the element" + Object;
			Constants.bResult = true;
			Reporter.log("Mouse hover performed on the element" + Object);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Unable mouse hover on the element " + Object + "due to " + e.getMessage());
			Constants.bResult = false;
			Reporter.log("Unable mouse hover on the element " + Object + "due to " + e.getMessage());
		}
	}

	public static void SelectDropDownValue(String Object, String data1) throws Exception {
		/*
		 * *****************************************************************************
		 * ********************************************************** Purpose : To
		 * select the DropDown mentioned as WebElement (Object)
		 *
		 * Parameters : Object - WebElement for which text needs to be captured data1 -
		 * Value to replace "%s" if the Object's Xpath needs to be dynamically
		 * generated. Else, data1 is blank
		 *
		 * *****************************************************************************
		 * **********************************************************
		 */
		try {
			// JavascriptExecutor je = (JavascriptExecutor) driver;
			WebElement DropDownValue = (new WebDriverWait(driver, Duration.ofSeconds(30)))
					.until(ExpectedConditions.elementToBeClickable(By.xpath(Constants.OR.getProperty(Object))));
			Select sel = new Select(DropDownValue);
			sel.selectByVisibleText(Constants.OR.getProperty(data1));
			Reporter.log("Selected the value as " + data1 + "from the drop down");
		} catch (Exception e) {
			e.printStackTrace();
			Constants.bResult = false;
			Constants.vActRes = "DropDown value is not selected";
			Reporter.log("Not able to select the value as " + data1 + "from the drop down");
		}
	}

}
