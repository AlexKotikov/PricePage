package org.kotikov.PriceView;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartItemsList {

	@FindAll({	@FindBy(xpath = "//li[@class=\"price-cart__item\"]/label/span[1]")})
	List<WebElement> names = new ArrayList<WebElement>();
	
	@FindAll({@FindBy(xpath = "//li[@class=\"price-cart__item\"]/label/span[2]")})
	List<WebElement> oldPrice = new ArrayList<WebElement>();
	
	@FindAll({@FindBy(xpath = "//li[@class=\"price-cart__item\"]/label/span[3]")})
	List<WebElement> actualPrice = new ArrayList<WebElement>();
	
	//Хранилищие найденных объектов
	List<ShoppingCartItem> ShoppingCartItems = new ArrayList<ShoppingCartItem>();
	
	private WebDriver driver;

	public ShoppingCartItemsList(WebDriver d) {
		this.driver = d;
		
	}

	public ShoppingCartItem get(int i) {
		return ShoppingCartItems.get(i-1);
	}
	 /**
	  * Удалить элемент по его порядковому номеру нахождения в корзине
	  * @throws Exception
	  */
	public void deleteItem (int i) throws Exception {
		int todelete = i-1;
	
		if (todelete>=ShoppingCartItems.size()) 
			throw new Exception("Вы пытаетесь удалить больше чем есть в списке");
		
		driver.findElement(By.xpath(ShoppingCartItems.get(todelete).getPlace())).click();
	}
	
	public int getCount(){
		return ShoppingCartItems.size();
	} 
	
	public void printAll(){
		 
		for (int i=0;i<ShoppingCartItems.size(); i++) {
			System.out.print(ShoppingCartItems.get(i).getItemTitle() +"   ");
			System.out.print(ShoppingCartItems.get(i).getItemActualCost() +"   ");
			System.out.print(ShoppingCartItems.get(i).getItemOldPrice() +"   ");
			System.out.print(ShoppingCartItems.get(i).getPlace() +"   ");
			System.out.println();
		}
	}

	protected void buildList() {
		for (int i=0;i<names.size(); i++) {
			ShoppingCartItems.add(new ShoppingCartItem(names.get(i).getText(),oldPrice.get(i).getText(),
					actualPrice.get(i).getText(), i));
		}
		
	}
	
}
