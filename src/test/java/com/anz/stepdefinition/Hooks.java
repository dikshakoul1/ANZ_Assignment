package com.anz.stepdefinition;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.anz.utilities.Browser;
import com.anz.utilities.Parameterization;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
	Map<String,HashMap<String,String>> data= Parameterization.ExcelObjectReader("ANZ_Test");
	Map<String,String> environmentValues= Parameterization.getEnvDetails();
	WebDriver driver;

	@Before
	public void launch_the_Browser(Scenario scenario) throws InterruptedException
	{
		try {
			if(!(scenario.getName().contains("API"))) {
				driver= Browser.launchBrowser(environmentValues.get("Browser"), environmentValues.get("URL"));	
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@AfterStep
	public void addScreenshot(Scenario scenario){
		if(scenario.isFailed() && (!(scenario.getName().contains("API")))) {
			final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "image");
			//Browser.browserQuit();
		}
	}

	@After
	public void closeBrowser(Scenario scenario)
	{ 
		if(!(scenario.isFailed()) && (!(scenario.getName().contains("API")))) {
			Browser.browserClose();
		}
	}
}
