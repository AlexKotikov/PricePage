package org.kotikov.PriceView;

import java.util.List;

import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DostupKBazeResumeOffer {

	private WebDriver driver;
	DostupKBazeResumeOffer(WebDriver d) { this.driver  = d;}
	
	//Общие XPath для всех 
		//Тайтл оффера
		private static final String titleID = 		"div[@class='price-resume-access__period-title']";
		private              String title;
	    
		//Дискрипшен оффера
		private static final String periodDescID =  "div[@class='price-resume-access__period-desc']";
		private              String periodDesc;
	
		//Список чекбоксов для каждого оффера
		private static final String checkboxesID = 	"label[@class='price-resume-access__part-item']";
		private       List<WebElement> checkboxesList;
	
		//Текст чекбокса - например: "период 1 день"
		private static final String checkboxID = "input[@class='HH-Price-ResumeAccess-PeriodCheckbox']";
		private       String checkbox;
		
		//Стоимость напротив чек бокса
		private static final String costID = "span[@class='price-resume-access__item-cost']";
		private       String cost;		

		
	//Оффер 1	
		//Это рут тайтла  
		private static final String root1 = "//div[contains(@class, 'HH-Price-ResumeAccess-Period-Group_short')]";
		
		//Рут чекбоксов
		private static final String  offer1_r= "//div[@class='HH-Price-ResumeAccess-Period_short']";
	
	
	//Оффер 2 
		//Рут тайтла	
		private static final String root2 = "//div[contains(@class, 'HH-Price-ResumeAccess-Period-Group_medium')]";
		
		//Рут чекбоксов
		private static final String offer2_r= "//div[@class='HH-Price-ResumeAccess-Period_medium']";
		
	
	//Оффер 3
		//Рут тайтла
		private static final String root3 = "//div[contains(@class, 'HH-Price-ResumeAccess-Period-Group_long')]";
		
		//Рут чекбоксов
		private static final String offer3_r = "//div[@class='HH-Price-ResumeAccess-Period_long']";
		
		
	//Поля объекта оффер
		private  String XPathOfCurrentOffer;
		private  String rootElment;
	
		
	//Инициализация офферов	
	private void init() throws Exception {
		//Получить заголовок и описание оффера
		
		
		this.title 		   =  PriceUtils.getText(driver, rootElment+"/"+titleID);
		this.periodDesc    =  PriceUtils.getText(driver, rootElment+"/"+periodDescID);
		this.checkboxesList = PriceUtils.getList(driver, rootElment+"/div/"+checkboxesID) ;
		
		if (checkboxesList.isEmpty()) {
			Exception e = new Exception("Не найдено ни одной радиокнопки");
			e.printStackTrace();
			throw e;
		}
	}	
		/**
		Узнать сколько чекбоксов у каждого оффера
		 */
	public int getCountOfCheckboxes() throws Exception {
		if (checkboxesList == null) {
			Exception e = new Exception("Вы не проинициализировали оффер");
			e.printStackTrace();
			throw e;
			}
		return checkboxesList.size();
	}
	
	
	public DostupKBazeResumeOffer getFirst() throws Exception  {
		this.XPathOfCurrentOffer= this.offer1_r;
		this.rootElment =  this.root1;
		init();
		return this;
	}

	public DostupKBazeResumeOffer getSecond() throws Exception  {
		this.XPathOfCurrentOffer= this.offer2_r;
		this.rootElment =  this.root2;
		init();
		return this;
	}
	
	
	public DostupKBazeResumeOffer getThird() throws Exception  {
		this.XPathOfCurrentOffer= this.offer3_r;
		this.rootElment =  this.root3;
		init();
		return this;
	}
	
	
	private String getCheckboxXPATHbyIndex(int index) {
		String checkbox = this.XPathOfCurrentOffer+"/" + this.checkboxesID + "["+ (index) +"]" ;
		return checkbox;
	}
	
	/**
	 * Выбрать вариант предложения на странице
	 */
	public void clickRadio(int index) {
		PriceUtils.click(driver, getCheckboxXPATHbyIndex(index) + "/" + this.checkboxID);
	}

	public String getRadioText(int index) {
		return	PriceUtils.rmTags(
				PriceUtils.getAttr(driver, getCheckboxXPATHbyIndex(index), "innerHTML")
				).trim();
	}
	
	public String getRadioCost(int index) {
	return	PriceUtils.parsCurrency(
			PriceUtils.getText(driver, getCheckboxXPATHbyIndex(index)+ "/" + this.costID));
		
	}
 
	public String getTitleOfOffer() {
		return title;
	}
	
	public String getDescrOfOffer() {
		return periodDesc;
		
	}
	
}

