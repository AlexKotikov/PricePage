package org.kotikov.framework;

 

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


/**
 * Вспомогательный класс.
 * Содержит часто используемые методы обертки
 */
public class PriceUtils {

   
 public static String parsCurrency(String in) {
     
     in = new String(in.replaceAll("руб.",""));
     in = new String(in.replaceAll(" ",""));
  return in;
 }
   
 public static String rmTags(String in) {
     
     in = new String(in.replaceAll("<input[^>]*>",""));
     in = new String(in.replaceAll("<span.*>",""));  
     in = new String(in.replaceAll("\n",""));
     return in;
 }
 
 public static String getText(WebDriver driver, String in) {
     return driver.findElement(By.xpath(in)).getText();
 }
 
 public static String getTextByCSS(WebDriver driver, String in) {
     return driver.findElement(By.cssSelector(in)).getText();
 }
 
 public static  WebElement getElement(WebDriver driver, String in) {
     return driver.findElement(By.xpath(in)) ;
 }
 
 public static List<WebElement> getList(WebDriver driver, String in) {
     return driver.findElements(By.xpath(in));
 }
 
 public static void click(WebDriver driver, String in) {
          driver.findElement(By.xpath(in)).click();
    }

 public static String getAttr(WebDriver driver, String in ,String attr) {
     return   driver.findElement(By.xpath(in) ).getAttribute(attr) .trim();
    }

public static void clickCSS(WebDriver driver, String in) {
    driver.findElement(By.cssSelector(in)).click();
   
}
 
 
 
}
