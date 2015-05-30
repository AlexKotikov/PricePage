package org.kotikov.PriceView;

 
import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DostupKBazeResumeTab {
 
	private WebDriver driver;
	public   DostupKBazeResumeTab(WebDriver d) {this.driver =d;}
	
	@FindBy(css="div[class='price-resume-access__title']")
	WebElement title;
	
	@FindBy(css="div[class='price-resume-access-gift HH-Price-ResumeAccess-Gift']")
	WebElement resumeAccessGift;
	
	//Представление суммы на странице
	private final String costOnPage = "span[class='HH-Price-ResumeAccess-Cost']";
	
	//Кнопка добавить в корзину
	private  final String addToCartBtn ="button[class='HH-Price-ResumeAccess-AddToCartButton']";
	
	//чекбокс С безлимитными публикациями вакансий «Стандарт»  
	private  final String linkSbezlimitnimiPublikatsiyami = "//input[contains(@class,'HH-Price-ResumeAccess-Unlimited-Checkbox')]";
	
	
	public String getTitle() {
		//Получить заголовок страницы
		return  title.getText() ;
	}
	
	public String getResumeAccessGift() {
		//Получить описание подарка на странице
		return 	  resumeAccessGift.getText() ;
	}
	
	
	
	public String getCostOnPage() {
		//Получить заголовок и описание оффера
		return 	 PriceUtils.parsCurrency(PriceUtils.getTextByCSS(driver, costOnPage)) ;
	}
	
	public void addToCart() {
		PriceUtils.clickCSS(driver, addToCartBtn);
	}
		
	public void clickLinkSbezlimitnimiPublikatsiyami() {
		PriceUtils.click(driver,linkSbezlimitnimiPublikatsiyami) ;
		
	}
	
	public DostupKBazeResumeOffer getOffers (){
		return  new DostupKBazeResumeOffer(driver);
	}
	
}
