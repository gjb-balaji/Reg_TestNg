package com.automation_cs.driverEngine;

import org.openqa.selenium.WebDriver;

public class LocateDriver {
	
	public static WebDriver driver;

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		LocateDriver.driver = driver;
	}

}