package org.kotikov.tests;

import static org.junit.Assert.assertEquals;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kotikov.PriceView.RecomenduemoeOffer;
import org.kotikov.PriceView.RecomenduemoeTab;
import org.kotikov.PriceView.ShoppingCart;
import org.kotikov.PriceView.ShoppingCartItemsList;
import org.kotikov.framework.TestCommander;
import org.openqa.selenium.WebElement;

import  static org.hamcrest.Matchers.containsString;
import  static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class RecomenduemoePageTests  extends TestCommander{

	private static  RecomenduemoeTab   recPage;
	private static  ShoppingCart 	   cart;
	private static  RecomenduemoeOffer offerOne;
	private static  RecomenduemoeOffer offerTwo;
	
	 @Before
	 public void setUpReocmenduemoeTab() throws Exception {
		 recPage = page.getRecomenduemoeTab();
		    cart = page.getShoppingCart();
		
		    offerOne = recPage.getOffers().prepareOfferOne().getOfferFromPage();
		    offerTwo = recPage.getOffers().prepareOfferTwo().setNoOldPrice().getOfferFromPage();
		    
		    page.clickRecomenduemoeTab();
	 }
	 
	  @After  //Почистить за собой
	  public   void emptyCart() throws Exception {
		 
		  if (!cart.isEmpty()) {
			  ShoppingCartItemsList 
			  items = cart.getItemsFromPage();
		
			  for(int i=0; i < items.getCount() ;i++){
			  items.deleteItem(1);
			  	}
		  }
	  }
	  @AfterClass //Закрыть браузер если надо и написать, что готово
	  public static void end() throws Exception {
		System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " всё");
		if (!TestCommander.isSuiteModeTrue())
		TestCommander.tearDown();
		  
	  }
	
	 /**
	   * Проверить что вкладка "Рекомендуемое" представленна на странице
	   */
	   @Test
	  public void testRecomenduemoeTab() {
		  
		     
		   
		  String  actual =recPage.getFlagComment();
		  String  expected = "ТОЛЬКО ОДИН РАЗ";
			      assertEquals(expected, actual);
		  
			      actual = recPage.getFlagTitle();			                    
			      expected = "Подарки при первой покупке";
			      assertEquals(expected, actual);
		  
		  		  actual = recPage.getGiftsCostInfo();
		  		  expected = "Стоимость подарков входит в стоимость предлагаемых выше услуг.";
		  		  assertEquals(expected, actual);
		  
	  }
	  
	  /**
	   * Проверить что корзина представленна на странице
	   */
	   @Test
	  public void testCartOnRecomTab() { 
		  assertEquals(cart.getTitle(), "Корзина пуста");
	  }
	  
	  
	  /**
	   * Проверить что оба предложения присутствуют на странице
	   */
	   @Test
	  public void testOfferOneOnPage() throws Exception {
		  assertEquals(offerOne.getActualPrice(),"2100 руб.");
		  assertEquals(offerOne.getGiftDesc(),"при покупке вакансии");
		  assertEquals(offerOne.getGiftTitle(),"День доступа к базе резюме бесплатно");
		  assertEquals(offerOne.getOldPrice(),"3000");
		  assertEquals(offerOne.getSpecialOfferPlus(),"+День доступа к базе резюме бесплатно");
		  assertEquals(offerOne.getSpecialOfferTitle(),"Вакансия Стандарт+");
	  }
	  
	   @Test
	  public void testOfferTwoOnPage() throws Exception {
		  assertEquals(offerTwo.getActualPrice(),"9000 руб.");
		  assertEquals(offerTwo.getGiftDesc(),"при покупке доступа к базе резюме");
		  assertEquals(offerTwo.getGiftTitle(),"Вакансия Стандарт+ бесплатно");
		  assertEquals(offerTwo.getOldPrice(),"");
		  assertEquals(offerTwo.getSpecialOfferPlus(),"+Вакансия Стандарт+ бесплатно");
		  assertEquals(offerTwo.getSpecialOfferTitle(),"Неделя доступа к резюме в регионе: Санкт-Петербург и Ленинградская область");
	  }
	  
	  
	 
	  /**
	   * Тестовый сценарий.
	   * Проверить что оба оффера можно добавить и затем удалить 
	   */
	    @Test
		public void addOffersToCartAndDeleteThemTest() throws Exception {
	    
	    	 //Добавить первый в корзину и получить  данные из корзины 
	    	 offerOne.addToChart();
		     ShoppingCartItemsList 
		     items = cart.getItemsFromPage();
	       	 assertThat(items.get(1).getItemTitle() , containsString ("Вакансия Стандарт+")) ;
	    	
	    	 //Второй тоже добавить в корзину
	    	 offerTwo.addToChart();
	    	 items = cart.getItemsFromPage();
   	    	 assertThat(items.get(2).getItemTitle(),containsString ("Неделя доступа к"))  ;
		    
		     //Удалить второй из корзины а первый там должен остаться
		     items.deleteItem(2) ;
		     items = cart.getItemsFromPage();
		     assertThat(items.get(1).getItemTitle() , containsString ("Вакансия Стандарт+")) ;
		     
		     //Удалить первый. В корзине должно быть пусто
		     items.deleteItem(1);
		     items = cart.getItemsFromPage();
		     assertEquals(cart.getTitle(), "Корзина пуста");
		  }

	    
	    /**
		   * Тестовый сценарий.
		   * Проверить что оба оффера можно добавить и затем удалить, а затем снова добавить
		   */
	    @Test
		public void addOffersToCartDeleteAndAddAgainTest() throws Exception { 
	    	
	    	 //Добавить в корзину
	    	 offerOne.addToChart();
	    	 offerTwo.addToChart();
	    	 
		     ShoppingCartItemsList 
		     items = cart.getItemsFromPage();
	    	
		     //Убедиться что они оба там
		     items = cart.getItemsFromPage();
		     assertThat(items.get(1).getItemTitle() , containsString ("Вакансия Стандарт+"));
		     assertThat(items.get(2).getItemTitle() , containsString ("Неделя доступа к"));
		     
		     //Удалить оба. В корзине должно быть пусто
		     items.deleteItem(2);
		     items.deleteItem(1);
		     
		     items = cart.getItemsFromPage();
		     assertEquals(cart.getTitle(), "Корзина пуста");
	    	
		     //Добавить в корзину опять их.
	    	 offerOne.addToChart();
	    	 offerTwo.addToChart();
	    	
	    	//Убедиться что они оба там
	    	 items = cart.getItemsFromPage();
	    	 assertThat(items.get(1).getItemTitle() , containsString ("Вакансия Стандарт+"));
		     assertThat(items.get(2).getItemTitle() , containsString ("Неделя доступа к"));
	    }
	    
	    /**
		   * Тестовый сценарий.
		   * Проверить, что цена отображется в корзине как ожидается
		   */
	    @Test
		public void calcPriceTest() throws Exception { 
	    	ShoppingCartItemsList items = null;
	    	
		    
	    	 //Добавить в корзину 1
	    	 offerOne.addToChart();
	    	 items = cart.getItemsFromPage();
	    	 assertThat(cart.getTotal() ,  equalTo("2100"));   
	    	 items.deleteItem(1);
	    	 
	    	//Добавить в корзину 2
	    	 offerTwo.addToChart();
		     items = cart.getItemsFromPage();
		     assertThat(cart.getTotal() ,   equalTo("9000"));   
		     items.deleteItem(1);
		     
		     //Добавить в корзину опять их.
	    	 offerOne.addToChart();
	    	 offerTwo.addToChart();
	    	 
	    	 items = cart.getItemsFromPage();
	    	 assertThat(cart.getTotal() ,  equalTo("11100"));  
	    }
	    
		
		
		
		
		
		
}
