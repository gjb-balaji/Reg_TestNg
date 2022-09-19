package com.automation_cs;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.automation_cs.driverEngine.Driver;
import com.automation_cs.pom_steps.HomePage;

public class TestDriver {

	static HomePage homepage = new HomePage();
	@BeforeTest
	public static void InitScript() throws Exception {
		try {
			homepage.propsInit();
			homepage.navigate((homepage.OR).getProperty("browser_name"), (homepage.OR).getProperty("url_qa"));
			System.out.println(homepage.OR);
			Reporter.log("We used Google Chrome Ver 80 for this test");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public static void test () throws Exception {
		homepage.languageSelection();
		homepage.itemSelectionfromSolidCat();
		homepage.checkOutFromShoppingBag();
		String a = homepage.FreeItemMissingMsg();
		a = a.replace("\n"," ");
		System.out.println(a.trim().toString());
		Assert.assertEquals("Don't forget to add your free item! Buy 3 Get 1 Free for Colors, Glitters, Designs and French".trim(), a.trim().toString());
		homepage.checkOutAsGuestUser();
		homepage.findAndSelectStylistWithZipCode();
		homepage.enterAddressForStylist();
	}
	
	@AfterTest
	public static void tearDown () throws Exception{
		homepage.closeBrowser();
		homepage.filereader.close();
	}
	
}
