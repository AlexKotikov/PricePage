package org.kotikov.PriceView;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RecomenduemoeOffer {
	
	
	private static final String ADD_TO_CART_BUTTON = "/form/div/button[@class=\"HH-Price-SpecialOffer-AddToCartButton\"]";
	private static final String PRICE_SPOFFERS_OLD_PRICE = "/span[@class=\"price-spoffers__old-price\"]";
	private static final String PRICE_SPOFFERS_SPECIAL_OFFER_PLUS = "/div[@class=\"price-spoffers__special-offer-plus\"]";
	private static final String PRICE_SPOFFERS_GIFT_DESC = "/div[@class=\"price-spoffers__gift-desc\"]";
	private static final String PRICE_SPOFFERS_GIFT_TITLE = "/div[@class=\"price-spoffers__gift-title\"]";
	private static final String PRICE_SPOFFERS_ACTUAL_PRICE = "/span[@class=\"price-spoffers__actual-price\"]";
	private static final String SPECIAL_OFFER_TITLE = "/div[@class=\"price-spoffers__special-offer-title\"]";
	
	//Общий икспас для всех офферов на странице
	private static final String offerStart =  "//div[@class=\"g-col";
	private static final String offerEnd   =  " m-colspan2\"]"; 
	
	
	private String actualPrice;
	private String giftDesc;
	private String giftTitle;
	private	String specialOfferTitle;
	private	String oldPrice;
	private String specialOfferPlus;
	private boolean hasOldPrice = true; 
	
	
	private WebDriver driver;
	private String  offerXPath ;
	//Номер оффера
	private Integer currentOfferId;
	
	//Айдишники офферов
	private static final int offerTwoID = 3;
	private static final int offerOneID = 1;
	

    public RecomenduemoeOffer(WebDriver d){
	this.driver = d ; 
    } 

	 
	public RecomenduemoeOffer prepareOfferOne() throws Exception {
		currentOfferId =offerOneID;
		return  this;
	}
	
	public RecomenduemoeOffer prepareOfferTwo() throws Exception {
		currentOfferId =offerTwoID;
		return  this;
	}
	
	public RecomenduemoeOffer getOfferFromPage() throws Exception {
			if (currentOfferId.equals(null)) throw new Exception ("Сначала нужно проинициализировать оффер");
			   
			offerXPath = new StringBuilder(offerStart + Integer.toString(currentOfferId) + offerEnd).toString();
			   
			specialOfferTitle =  driver.findElement(By.xpath(offerXPath+ SPECIAL_OFFER_TITLE)).getText();
			actualPrice = driver.findElement(By.xpath(offerXPath+ PRICE_SPOFFERS_ACTUAL_PRICE)).getText();
			giftTitle = driver.findElement(By.xpath(offerXPath+ PRICE_SPOFFERS_GIFT_TITLE)).getText();
			giftDesc =  driver.findElement(By.xpath(offerXPath+ PRICE_SPOFFERS_GIFT_DESC)).getText();
			specialOfferPlus =  driver.findElement(By.xpath(offerXPath+ PRICE_SPOFFERS_SPECIAL_OFFER_PLUS)).getText();
			
			if (hasOldPrice)  
			    oldPrice =  driver.findElement(By.xpath(offerXPath+ PRICE_SPOFFERS_OLD_PRICE)).getText();
		   
		return  this;
	}
	
	
	public void addToChart(){
		driver.findElement(By.xpath( offerXPath +ADD_TO_CART_BUTTON)).click();
		
	}
	
	 
	public RecomenduemoeOffer setNoOldPrice(){
		this.hasOldPrice = false;
		return  this;
	}

	
	
	@Override
	public String toString() {
		return "RecomenduemoeOffer [actualPrice=" + actualPrice + ", giftDesc="
				+ giftDesc + ", giftTitle=" + giftTitle
				+ ", specialOfferTitle=" + specialOfferTitle + ", oldPrice="
				+ oldPrice + ", hasOldPrice="+ hasOldPrice +", SpecialOfferPlus=" + specialOfferPlus + "]";
	}

 
	public String getActualPrice() {
		return actualPrice;
	}


	public String getGiftDesc() {
		return giftDesc;
	}


	public String getGiftTitle() {
		return giftTitle;
	}


	public String getSpecialOfferTitle() {
		return specialOfferTitle;
	}


	public String getOldPrice() {
		if (!hasOldPrice) return ""; 
		return oldPrice;
	}

	public String getSpecialOfferPlus() {
		return specialOfferPlus;
	}
	
	
}
