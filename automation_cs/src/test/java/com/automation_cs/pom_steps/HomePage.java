package com.automation_cs.pom_steps;

import com.automation_cs.Constants;
import com.automation_cs.driverEngine.Keywords;

public class HomePage extends Keywords  {
	
	public static void languageSelection () throws Exception {		
		SelectDropDownValue("Country_DD", "country_input");
		click("Submit_Btn");		
	}
	
	public static void itemSelectionfromSolidCat () throws Exception {		
		actionMoveToElement("Shop_Lbl");
		click("Solid_Colors_ListOption");	
		setOfElementsclick("items_selection");
	}
	
	public static void checkOutFromShoppingBag () throws Exception {		
		click("shopping_Bag_Btn");
		click("CheckOut_Btn");		
	}
	
	public static String FreeItemMissingMsg () {	
		return verifyElement_Present("Dont_Forget_Lbl");			
	}
	
	public static void checkOutAsGuestUser () throws Exception {
		Thread.sleep(500);
		click("CheckOut_WithOut_Free_Item_Btn");
		click("Continue_As_Guest_Btn");
	}
	
	public static void findAndSelectStylistWithZipCode () throws Exception {
		enterText("ZipCode_Txt", "postalCode_input");
		click("Search_Btn");
		click("Stylish_Btn");
	}
	
	public static void enterAddressForStylist () throws Exception {
		enterText("FirstName_Txt", "firstNme_input");
		enterText("LastName_Txt", "lastNme_input");
		enterText("StreetAddress1_Txt", "address1_input");
		enterText("StreetAddress2_Txt", "address2_input");
		enterText("City_Txt", "city_input");		
		SelectDropDownValue("Province_DD", "province_input");
		enterText("ZipCodeAdd_Txt", "postalCode_input");
		enterText("Email_Txt", "email_input");
		enterText("PhoneNumber_Txt", "phone_input");
		Thread.sleep(10);
		click("ShipToThisAddress_Btn");
		click("Continue_Btn");
	}
}
