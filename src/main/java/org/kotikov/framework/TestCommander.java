package org.kotikov.framework;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.BeforeClass;
import org.kotikov.PriceView.PriceSPAApp;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

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
   
   public static void  refresh () {
	   driver.navigate().refresh();
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

