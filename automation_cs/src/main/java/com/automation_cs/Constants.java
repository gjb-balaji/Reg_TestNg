package com.automation_cs;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Constants {

	public static WebDriver driver;
	public static ChromeOptions options;
	
	public static Properties OR = new Properties();
	//public static Properties OR;
	public static File ORPath = null;
	public static FileReader filereader;
	public static String Col_Environment_UAT;
	public static String Col_Environment_SIT;
	public static String Col_Environment_PROD;
	public static String Col_Env;
	public static boolean bResult;
	public static boolean bResume;
	public static String vActRes = "PASS";
	public static String vExpRes = "PASS";
	public static String Time = "100";
}
