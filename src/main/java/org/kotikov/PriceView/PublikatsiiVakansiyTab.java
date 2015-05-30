package org.kotikov.PriceView;

import org.openqa.selenium.WebDriver;

public class PublikatsiiVakansiyTab {
	private WebDriver driver;

	PublikatsiiVakansiyTab(WebDriver d) {this.driver= d;}
	
	PublikatsiiVakansiyOffer standart;
	PublikatsiiVakansiyOffer standartPlus;
	PublikatsiiVakansiyOffer premium;
	PublikatsiiVakansiyOffer anonim;
	
	
	/**
	 * Получить стандартное предложение (оффер)
	 */
	public PublikatsiiVakansiyOffer getStandart() throws Exception{
		PublikatsiiVakansiyOffer  offer= new PublikatsiiVakansiyOffer(driver,1);
		return offer.init();
	} 
	
	public PublikatsiiVakansiyOffer getStandartPlus() throws Exception{
		PublikatsiiVakansiyOffer  offer= new PublikatsiiVakansiyOffer(driver,2);
		return offer.init();
	} 
	
	public PublikatsiiVakansiyOffer getPremium() throws Exception{
		PublikatsiiVakansiyOffer  offer= new PublikatsiiVakansiyOffer(driver,3);
		return offer.init();
	} 
	
	public PublikatsiiVakansiyOffer getAnonim() throws Exception{
		PublikatsiiVakansiyOffer  offer= new PublikatsiiVakansiyOffer(driver,4);
		return offer.init();
	} 
}
