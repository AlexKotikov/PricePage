package org.kotikov.PriceView;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PriceSPAApp {
	
	final WebDriver driver;
	
	
	
	@FindBy(tagName  = "H1") 
	private WebElement  title;
	
	@FindBy(css="a[href='#recommended']")
	WebElement linkRecomenduemoe;

	@FindBy(css="a[href='#dbaccess']")
	private WebElement linkDostupKBazeResume;
	
	@FindBy(css="a[href='#publications']")
	private WebElement linkPublikatsiiVakansiy;
	
	@FindBy(css="a[href='#additional']")
	private WebElement linkDopolnitelnieUslugi;
	

	
	public String getTitle() {
		return title.getText();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
	
	
	
	public	PriceSPAApp(WebDriver d ){
		this.driver = d;
	}
	public RecomenduemoeTab getRecomenduemoeTab(){
		return PageFactory.initElements(driver, RecomenduemoeTab.class);
	}
	
	public DostupKBazeResumeTab getDostupKBazeResumeTab(){
		return PageFactory.initElements(driver, DostupKBazeResumeTab.class);
	}
	
	public PublikatsiiVakansiyTab getPublikatsiiVakansiyTab(){
		//return PageFactory.initElements(driver, PublikatsiiVakansiyTab.class);
		return new PublikatsiiVakansiyTab(driver);
		
	}
	public DopolnitelnieUslugiTab getDopolnitelnieUslugiTab(){
		return PageFactory.initElements(driver, DopolnitelnieUslugiTab.class);
	}
	public ShoppingCart getShoppingCart(){
		return PageFactory.initElements(driver, ShoppingCart.class);
	}
	
	public String getLinkRecomenduemoe() {
		return linkRecomenduemoe.getText();
	}
	public String getLinkDostupKBazeResume() {
		return linkDostupKBazeResume.getText();
	}
	public String getLinkPublikatsiiVakansiy() {
		return linkPublikatsiiVakansiy.getText();
	}
	public String getLinkDopolnitelnieUslugi() {
		return linkDopolnitelnieUslugi.getText();
	}
	
	
	public void clickRecomenduemoeTab() {
		  linkRecomenduemoe.click() ;
	}
	public void ClickDostupKBazeResumeTab() {
		  linkDostupKBazeResume.click();
	}
	public void ClickPublikatsiiVakansiyTab() {
		  linkPublikatsiiVakansiy.click();
	}
	public void ClickDopolnitelnieUslugiTab() {
		  linkDopolnitelnieUslugi.click();
	}
	
	
}
