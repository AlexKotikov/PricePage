package org.kotikov.PriceView;

import java.util.List;

import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PublikatsiiVakansiyOffer {

	PublikatsiiVakansiyOffer(WebDriver d, int i) {this.driver = d; this.IDofOffer =i; }
	
	private WebDriver driver;
	
	
 //Общие айдишники для офферов
	
	//рут
	private final static String rootID ="//div[@class='price-countable-service']";
	
	//заголовок
	private final static String titleID =   "/h2";
	private WebElement  title;
	
	//добавить в корзину или пересчитать
	private final static String vKorzinyLinkID =  "/div/" + "a[contains (@class,'HH-Price-CountableService-AddToCartButton price-countable-service__submit')]";
	private WebElement vKorzinyLink;
	
	//Пересчитать
	//private final static String  vKorzinyLinkAdded = "/div/" + "a[@class='HH-Price-CountableService-AddToCartButton price-countable-service__submit price-countable-service__submit_recalc']";
	
	//Общий кост для оффера (жирные буквы)
	private final static String costID =    "/span/span[@class='HH-Price-CountableService-TotalCostLabel price-countable-service__cost-amount']";
	private WebElement cost;
	
	//Узнать тайтл дисконта
	final static String discountRateID =     "/div/span[@class='price-countable-service__rate-amount']";
	private List<WebElement> discountRates;
	
	//Узнать цену дисконта
	final static String discountCostsID =    "/div/span[@class='price-countable-service__rate-cost']";
	private List<WebElement> discountCosts;
	
	//строка дисконта по которой кликает пользователь (корень двух предыдущех тегов)
	final static String rateAndCostTagID ="/div[@class='price-countable-service__rate HH-Price-CountableService-DiscountRate']";
	
	
	 //Поля объекта	
		//Офферы отличаются индексом который лежит здесь. Передается конструктору при создании объекта.
		private int 	  IDofOffer;
		
		//Сюда запишется полный путь с корневого элемента, по нему можно найти все остальные
		private String 	  path;

	
	//Инициализация офферов	по айдишнику который передается еще в конструкторе
	public PublikatsiiVakansiyOffer init() throws Exception {
		int index = IDofOffer;
		
		 path = new StringBuilder(rootID+"["+(index)+"]"+"/div").toString();

		this.title 		  = PriceUtils.getElement(driver, path  + titleID) ;
		this.vKorzinyLink = PriceUtils.getElement(driver, path  + vKorzinyLinkID) ;
		this.cost 		  = PriceUtils.getElement(driver, path  + costID) ;
		
		this.discountRates = PriceUtils.getList(driver,   path + discountRateID);
		this.discountCosts = PriceUtils.getList(driver,   path + discountCostsID);
		
		if (discountRates.isEmpty()) {
			Exception e = new Exception("Не найдено ни одной ссылки дискаунта (rate-amount)");
			e.printStackTrace();
			throw e;
		}
		
		if (discountCosts.isEmpty()) {
			Exception e = new Exception("Не найдено ни одной цены дискаунта (rate-cost)");
			e.printStackTrace();
			throw e;
		}
		
		//Проверка на случай если не все предложения имеют цены или наборот
		if (discountCosts.size() !=  discountRates.size()) {
			Exception e = new Exception("discountCosts.size != discountRates.size ");
			e.printStackTrace();
			throw e;
		}
		
		
		return this;
	}
	
	
	public String getTitle() {
		return title.getText();
	}
	
	/**
	 * Узнать общую стоимость на странице оффера до добавления его в корзину
	 */
	public String getCost() {
		return PriceUtils.parsCurrency(cost.getText());
	}

	public String getDiscountRate(int index) {
		return discountRates.get(index-1).getText();
	}
	
	public String getDiscountCost(int index) {
		return  PriceUtils.parsCurrency(discountCosts.get(index-1).getText());
	}
	
	/**
	 * Узнать сколько дискаунтов у оффера
	 */
	public int getCountOfDiscountRates() {
		return discountRates.size();
	}
	
	
	/**
	 * Добавить элемент в корзину
	 */
	public void addToCart(){
		PriceUtils.click(driver, path  + vKorzinyLinkID) ;
	}
	
	/**
	 * Кликнуть  дискаунт у оффера чтобы его выбрать.
	 */
	public void clickDiscountRate(int index) throws Exception{
		
		if (discountRates.size() <  index) {
			Exception e = new Exception("В списке меньше элементов чем " + (index));
			e.printStackTrace();
			throw e;
		}
		
		PriceUtils.click(driver, path  + rateAndCostTagID + "["+ (index)+ "]" ) ;
		}
	
}
