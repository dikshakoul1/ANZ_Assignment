package com.anz.utilities;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtility {
    
    public static String currentDate(String format)
    {
        try {
            DateFormat df=new SimpleDateFormat(format);
            Date date=new Date();
            String currDate=df.format(date);
            return currDate;
        }catch(Exception e)
        {
            System.out.println("Current date is not generated "+e.getMessage());
        }
        return format;
    }
    
    public static WebElement locateElement(HashMap<String,String> data, WebDriver driver)
    {
        String locatorType= null;
        String locaterValue= null;
        for(Entry<String, String> entry: data.entrySet()) {
            locatorType= entry.getKey();
            locaterValue= entry.getValue();
        }
        WebElement element=null;
        try {
            if(locatorType.equalsIgnoreCase("ID"))
            {
                element= driver.findElement(By.id(locaterValue));
            }else if(locatorType.equalsIgnoreCase("ClassName"))
            {
                element= driver.findElement(By.className(locaterValue));
            }else if(locatorType.equalsIgnoreCase("LinkText"))
            {
                element= driver.findElement(By.linkText(locaterValue));
            }else if(locatorType.equalsIgnoreCase("Name"))
            {
                element= driver.findElement(By.name(locaterValue));
            }else if(locatorType.equalsIgnoreCase("TagName"))
            {
                element= driver.findElement(By.tagName(locaterValue));
            }else if(locatorType.equalsIgnoreCase("Partial Link Text"))
            {
                element= driver.findElement(By.partialLinkText(locaterValue));
            }else if(locatorType.equalsIgnoreCase("XPath"))
            {
                element= driver.findElement(By.xpath(locaterValue));
            }else
            {
                element=driver.findElement(By.cssSelector(locaterValue));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return element;
    }
    
    public static List<WebElement> locateElements(HashMap<String,String> data, WebDriver driver)
    {
        String locatorType= null;
        String locaterValue= null;
        for(Entry<String, String> entry: data.entrySet()) {
            locatorType= entry.getKey();
            locaterValue= entry.getValue();
        }
        List<WebElement> element=null;
        try {
            if(locatorType.equalsIgnoreCase("ID"))
            {
                element= driver.findElements(By.id(locaterValue));
            }else if(locatorType.equalsIgnoreCase("ClassName"))
            {
                element= driver.findElements(By.className(locaterValue));
            }else if(locatorType.equalsIgnoreCase("LinkText"))
            {
                element= driver.findElements(By.linkText(locaterValue));
            }else if(locatorType.equalsIgnoreCase("Name"))
            {
                element= driver.findElements(By.name(locaterValue));
            }else if(locatorType.equalsIgnoreCase("TagName"))
            {
                element= driver.findElements(By.tagName(locaterValue));
            }else if(locatorType.equalsIgnoreCase("Partial Link Text"))
            {
                element= driver.findElements(By.partialLinkText(locaterValue));
            }else if(locatorType.equalsIgnoreCase("XPath"))
            {
                element= driver.findElements(By.xpath(locaterValue));
            }else
            {
                element=driver.findElements(By.cssSelector(locaterValue));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return element;
    }
    
    public static void clickOnWebelement(WebElement element)
    {
        try {
            element.click();
        }
        catch(Exception e)
        {
            System.out.println("Element is not clickable "+e.getMessage());
        }
    }
    
    public static void inputData(WebElement element, String text)
    {
        try {
            element.click();
            element.clear();
            element.sendKeys(text);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getText(WebElement element)
    {
    	String value= null;
        try {
            value= element.getText();
        }catch(Exception e) {
            e.printStackTrace();
        }
        return value;
    }
    
    public static void captureScreen(WebDriver driver, String fileName)
    {
        try {
            fileName= fileName+"_"+currentDate("dd-M-yyyy hh:mm:ss");
            File source=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            File target=new File("Test Output\\Screenshots\\"+ fileName+".png");
            FileHandler.copy(source,target);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void selectByValue(WebElement element, String value)
    {
        Select select=new Select(element);
        select.selectByValue(value);
    }

    public static void selectByVisibleText(WebElement element, String value)
    {
        Select select=new Select(element);
        select.selectByVisibleText(value);
    }

    public static void selectByIndex(WebElement element, int indexvalue)
    {
        Select select=new Select(element);
        select.selectByIndex(indexvalue);
    }
	
    public static void expWait(WebDriver driver, WebElement element, String condition)
    {
        try {
            int time =30;   
            WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(time));
            if(condition.equalsIgnoreCase("Clickable")) {
                wait.until(ExpectedConditions.elementToBeClickable(element));
            }else if(condition.equalsIgnoreCase("Selected")) {
                wait.until(ExpectedConditions.elementToBeSelected(element));
            }else if(condition.equalsIgnoreCase("Visible")) {
                wait.until(ExpectedConditions.visibilityOf(element));
            }else if(condition.equalsIgnoreCase("alert")) {
                wait.until(ExpectedConditions.alertIsPresent());
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
}