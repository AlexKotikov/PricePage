package org.kotikov.PriceView;

import java.util.List;

import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ShoppingCart {
	
	private static final String CART_NOT_EMPTY_SIGN = "//div[@class=\"HH-PriceCart\"]/div[@class=\"price-cart__title\"]";
	private static final String CART_EMPTY_SIGN = "//div[contains(@class, 'HH-PriceCart-Empty')]/div[@class=\"price-cart__title\"]";
	private static final String CART_TOTAL_COST_ACTUAL = "//span[@class=\"HH-PriceCart-TotalCost-Actual\"]";


	final WebDriver driver;
	WebElement cartTitle;
	
	
	public ShoppingCart(WebDriver driver){
	  this.driver = driver;
	}
	
	
	public String getTitle (){
		WebElement a= 	driver.findElement(By.xpath(CART_EMPTY_SIGN));	
		if (a.isDisplayed())  return a.getText();
		else 
			return driver.findElement(By.xpath(CART_NOT_EMPTY_SIGN)).getText();
			}
	
	
	
	public boolean isEmpty(){
		WebElement a= 	driver.findElement(By.xpath(CART_EMPTY_SIGN));	
		if (a.isDisplayed()) return true;
		return false;
		}
	
	
	public String getTotal(){
	  return PriceUtils.parsCurrency(driver.findElement(By.xpath(CART_TOTAL_COST_ACTUAL)).getText());
	}
	
	public ShoppingCartItem getCartItems(){
			return PageFactory.initElements(driver, ShoppingCartItem.class);
		}
	 
	 
	 
	public ShoppingCartItemsList getItemsFromPage() {
		ShoppingCartItemsList list = PageFactory.initElements(driver, ShoppingCartItemsList.class);
		list.buildList();
		return list ;
	}
	
	
	public void isButtonGoToPayHere(){}
}
