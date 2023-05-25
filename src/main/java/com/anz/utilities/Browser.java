package com.anz.utilities;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser {
	static WebDriver driver=null;
	@SuppressWarnings("deprecation")
	public static WebDriver launchBrowser(String browserType,String url)
	{
		try {
			if(browserType.equalsIgnoreCase("Chrome"))
			{
				System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\Drivers\\chromedriver.exe");
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--remote-allow-origins=*");
				driver=new ChromeDriver(options);
			}else if(browserType.equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.ie.driver",System.getProperty("user.dir")+"Drivers\\IEDriverServer.exe");
				driver=new InternetExplorerDriver();
			}else if(browserType.equalsIgnoreCase("Edge"))
			{
				System.setProperty("webdriver.safari.driver",System.getProperty("user.dir")+"Drivers\\msedgedriver.exe");
				driver=new EdgeDriver();
			}else
			{
				System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir")+"Drivers\\geckodriver.exe");
				driver=new FirefoxDriver();
			}
			driver.get(url);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return driver;
	}

	public static void browserClose()
	{
		driver.close();
	}

	public static void browserQuit()
	{
		driver.quit();
	}

	public static WebDriver getDriver()
	{
		return driver;
	}
}