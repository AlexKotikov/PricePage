package org.kotikov.framework;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.kotikov.PriceView.PriceSPAApp;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class TestCommander {
 

  private static String baseUrl = "http://hh.ru/price/";	
 
  
  public  static PriceSPAApp page;
  
  private static WebDriver driver;
  private static boolean acceptNextAlert = true;
  private static StringBuffer verificationErrors = new StringBuffer();
  private static boolean runAsSuite = false;

   @BeforeClass
  public static void setUp() throws Exception {
	       if (page == null) {
	    	   System.out.println("Готовлю драйвер");
	    	   driver = new FirefoxDriver();
		       driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		       driver.get(baseUrl);
	    	   
		       System.out.println("Готовлю тестируемое приложение");
		       page = PageFactory.initElements(driver, PriceSPAApp.class);
     	       }
  }

 

   public static void suiteTrue(){
	   runAsSuite = true;
	   System.out.println("Работаю в режиме сьюта");
   }
   
   public static boolean isSuiteModeTrue() {
	   return runAsSuite;
   }
   
   
   
   public static void  tearDown() {
	   driver.quit();
  	    String verificationErrorString = verificationErrors.toString();
  	    if (!"".equals(verificationErrorString)) {
  	      fail(verificationErrorString); }
   }
   
   
   public static void  go(String url) {
	   driver.get(url);
   }
   
 
  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  } 
}

