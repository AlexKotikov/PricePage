package org.kotikov.PriceView;

import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.WebDriver;

public class RecomenduemoeOffer {
	
	//Общий икспас для всех офферов на странице
		private static final String offerStart =  "//div[@class=\"g-col";
		private static final String offerEnd   =  " m-colspan2\"]"; 
	
	//Айдишники полей оффера 
	private static final String ADD_TO_CART_BUTTON = "/form/div/button[@class=\"HH-Price-SpecialOffer-AddToCartButton\"]";
	private static final String PRICE_SPOFFERS_OLD_PRICE = "/span[@class=\"price-spoffers__old-price\"]";
	private static final String PRICE_SPOFFERS_SPECIAL_OFFER_PLUS = "/div[@class=\"price-spoffers__special-offer-plus\"]";
	private static final String PRICE_SPOFFERS_GIFT_DESC = "/div[@class=\"price-spoffers__gift-desc\"]";
	private static final String PRICE_SPOFFERS_GIFT_TITLE = "/div[@class=\"price-spoffers__gift-title\"]";
	private static final String PRICE_SPOFFERS_ACTUAL_PRICE = "/span[@class=\"price-spoffers__actual-price\"]";
	private static final String SPECIAL_OFFER_TITLE = "/div[@class=\"price-spoffers__special-offer-title\"]";

	//Поля объекта
	private String actualPrice;
	private String giftDesc;
	private String giftTitle;
	private	String specialOfferTitle;
	private	String oldPrice;
	private String specialOfferPlus;
	private boolean hasOldPrice = true; 
	
	
	private WebDriver driver;
	
	//Здесь будет корневой путь от начала до конечного искомого элемента
	private String  offerXPath ;
	
	//Номер оффера
	private Integer currentOfferId;
	
	//Айдишники офферов на странице
	private static final int offerTwoID = 3;
	private static final int offerOneID = 1;
	

    public RecomenduemoeOffer(WebDriver d){ this.driver = d ;  } 

	 
	public RecomenduemoeOffer prepareOfferOne() throws Exception {
		currentOfferId =offerOneID;
		return  this;
	}
	
	public RecomenduemoeOffer prepareOfferTwo() throws Exception {
		currentOfferId =offerTwoID;
		return  this;
	}
	
	public RecomenduemoeOffer getOfferFromPage()  throws Exception {
			if (currentOfferId.equals(null)) {
				Exception e = new Exception("Сначала нужно проинициализировать оффер");
				e.printStackTrace();
				throw e ;
			}
			
			offerXPath = new StringBuilder(offerStart + Integer.toString(currentOfferId) + offerEnd).toString();
			   
			specialOfferTitle =  PriceUtils.getText(driver, offerXPath+ SPECIAL_OFFER_TITLE) ;
			actualPrice 	  =  PriceUtils.getText(driver, offerXPath+ PRICE_SPOFFERS_ACTUAL_PRICE) ;
			giftTitle 		  =  PriceUtils.getText(driver, offerXPath+ PRICE_SPOFFERS_GIFT_TITLE) ;
			giftDesc 		  =  PriceUtils.getText(driver, offerXPath+ PRICE_SPOFFERS_GIFT_DESC) ;
			specialOfferPlus  =  PriceUtils.getText(driver, offerXPath+ PRICE_SPOFFERS_SPECIAL_OFFER_PLUS);
			
			if (hasOldPrice)  
			    oldPrice =  	 PriceUtils.getText(driver, offerXPath + PRICE_SPOFFERS_OLD_PRICE) ;
		   
		return  this;
	}
	
	
	public void addToChart()
		{  
		PriceUtils.click(driver, offerXPath +ADD_TO_CART_BUTTON) ; }
	
	 
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
		return PriceUtils.parsCurrency(actualPrice);
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
