package org.kotikov.PriceView;

import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
/***
 * Класс представляет интерфейс тестировщику для работы с корзиной
 */

public class ShoppingCart {
	
	private static final String CART_EMPTY_SIGN = "//div[contains(@class, 'HH-PriceCart-Empty')]/div[@class=\"price-cart__title\"]";
	private static final String CART_TOTAL_COST_ACTUAL = "//span[@class=\"HH-PriceCart-TotalCost-Actual\"]";

	//Получить Title корзины
	private static final String cartTitle ="//div[contains(@class,'HH-PriceCart')  and not(contains(@class,'hidden')) ]/div[@class='price-cart__title']";
	private  WebDriver driver;
	
	
	public ShoppingCart(WebDriver driver){
	  this.driver = driver;
	}
	
	/**
	 * Узнать заголовок корзины
	 */
	public String getTitle (){
	   return	PriceUtils.getText(driver, cartTitle);
	}
	
	/**
	 * Узнать пуста ли корзина
	 */
	public boolean isEmpty(){
		WebElement a= 	PriceUtils.getElement(driver, CART_EMPTY_SIGN) ;	
		if (a.isDisplayed()) return true;
		return false;
		}
	
	/**
	 * Узнать общую сумму товаров в корзине
	 */
	public String getTotal(){
	   String a = PriceUtils.parsCurrency(PriceUtils.getText(driver, CART_TOTAL_COST_ACTUAL ));
		 
	   if (a.equals("")) return "Значения нет";
	   
	   return  a;
	}
	
	 
	/**
	  * Удалить элемент на странице по его порядковому номеру нахождения в корзине
	  * @throws Exception
	  */
	public void deleteItem (int i ) throws Exception {
		int toDelete = i-1;
		int count = getItemsFromPage().getCount();
		
		if (toDelete> count) {
			Exception e = new Exception("Вы пытаетесь удалить больше чем есть в списке");
			e.printStackTrace();
			throw e ;
		}
		 new ShoppingCartItem(driver).delete(i);
	} 
	
	public void deleteAll (  ) throws Exception {
		while (getItemsFromPage().getCount() > 0) 
		 { this.deleteItem(1);
		 } 
	} 
	
	
	public ShoppingCartItemsList getItemsFromPage() {
		    return  new ShoppingCartItemsList(driver).init();
	}
	
	public int getCount() {
		 return getItemsFromPage().init().getCount();
		
	}
	
	public ShoppingCartItem get(int i) throws Exception {
		 return getItemsFromPage().init().get(i);
		
	}
	
}
