package org.kotikov.PriceView;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class RecomenduemoeTab {
	private WebDriver driver;
	
	//Две надписи вверху
	@FindBy(css="div[class='form-box-flagged__flag-comment']")
	WebElement flagComment;

	@FindBy(css="div[class='form-box-flagged__flag-title']")
	private WebElement flagTitle;
	
	//надпись внизу 
	@FindBy(css="div[class='price-spoffers__gifts-cost-info']")
	private WebElement  giftsCostInfo;
	
	
	public String  getFlagComment(){ return flagComment.getText(); };
	
	public String  getFlagTitle(){ return flagTitle.getText(); };
	
	public String  getGiftsCostInfo(){ return giftsCostInfo.getText(); };
	
	
	public void isContactUsHere(){};

	public RecomenduemoeTab(WebDriver d) {
		this.driver=d; 
	}


	@Override
	public String toString() {
		return "RecomenduemoeTab [flagComment=" + flagComment.getText()
				+ ", giftsCostInfo=" + giftsCostInfo.getText() + ", flagTitle="
				+ flagTitle.getText() + "]";
	}

	
	public RecomenduemoeOffer getOffers(){
		return PageFactory.initElements(driver, RecomenduemoeOffer.class);
	}
	
}
