package com.automation_cs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class Control {
	
	static Map<String, String> dictionary = new HashMap<String, String>();

	public static void getObjects() throws Exception {
		// Loading Objects
		final File ORPath = new File(System.getProperty("user.dir") + "/src/test/java/objectRepository");
		readPropertyFiles(ORPath);
	}

	//verify whether the file is exists
	public static String getFile(String fileName) {
		String value;
		String CurDir = System.getProperty("user.dir");
		System.out.println("Current Directory :" + CurDir);
		value = CurDir + "/src/test/java/objectRepository/" + fileName + ".properties";
		System.out.println("Test case file path: " + value);
		File file = new File(value);
		if (file.exists()) {
			return value;
		} else {
			System.out.println("File does not Exists");
			return null;
		}
	}

	public static void readPropertyFiles(final File ORPath) throws IOException {
		File[] listOfFiles = ORPath.listFiles();
		for (final File fileEntry : listOfFiles) {
			if (fileEntry.isFile()) {
				readAPropertyFile(fileEntry);
				System.out.println("File name: "+fileEntry.getName());
				System.out.println("File path: "+fileEntry.getAbsolutePath());
				FileInputStream fs = new FileInputStream(fileEntry.getAbsolutePath());
				Constants.OR.load(fs);
			}			
		}
	}

	public static void readAPropertyFile(final File fileName) throws IOException {
		Constants.filereader = new FileReader(fileName);
		(Constants.OR).load(Constants.filereader);
		System.out.println(Constants.OR);
		
		// Constants.OR = System.getProperties();
//		Set set = (Constants.OR).entrySet();
//		Map<String, String> data = new HashMap<String, String>();
//
//		Iterator itr = set.iterator();
//		while (itr.hasNext()) {
//			Map.Entry entry = (Map.Entry) itr.next();
//			data.put((String) entry.getKey(), (String) entry.getValue());
//			System.out.println(entry.getKey() + " = " + entry.getValue());
//		}
		//(Constants.filereader).close();
		//return OR;
	}
	
	public static void storeData(String Varname,String value) {
//		Map<String, String> dictionary = new HashMap();
		if (dictionary.containsKey(Varname)){
//			System.out.println(dictionary.get(value));
//			dictionary.remove(Varname);
			dictionary.clear();
		}
			dictionary.put(Varname, value);
//			System.out.println("Values from dictionary :" + dictionary.keySet() + dictionary.get(Varname));
	}

}
