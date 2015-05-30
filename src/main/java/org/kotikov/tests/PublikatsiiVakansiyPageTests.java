package org.kotikov.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.kotikov.PriceView.PublikatsiiVakansiyOffer;
import org.kotikov.PriceView.PublikatsiiVakansiyTab;
import org.kotikov.PriceView.ShoppingCart;
import org.kotikov.PriceView.ShoppingCartItemsList;
import org.kotikov.framework.TestCommander;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

public class PublikatsiiVakansiyPageTests extends TestCommander{
   
   
    private static  PublikatsiiVakansiyTab   publ; 
    private static  ShoppingCart              cart;
   
   
  @Before
     public   void pageSetUP() throws Exception {
              publ = page.getPublikatsiiVakansiyTab();
            cart = page.getShoppingCart();
  }
 
  @BeforeClass
     public static void   beforeClass() throws Exception {
         page.ClickPublikatsiiVakansiyTab();
}
   
  @AfterClass 
      public static void end() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " всё");
        if (!TestCommander.isSuiteModeTrue())
               TestCommander.tearDown(); 
       
       
      }
 
  @After  //Почистить за собой
  public    void emptyCart() throws Exception {
      if (!cart.isEmpty())  cart.deleteAll();
     
  }
 
 
  /**
   * Standart
   * Проверить, что цена дисконта на странице и в корзине отображается одинаково.
   */
  @Test
    public void testStandart() throws Exception {
      PublikatsiiVakansiyOffer  offer = null;
      boolean flag = false;  
     
       offer = publ.getStandart();
      
      
      for (int i=1; i<=offer.getCountOfDiscountRates(); i++) {
          flag = true;
         
          offer.clickDiscountRate(i);
          offer.addToCart();
          assertEquals(cart.get(1).getItemActualCost(),  offer.getCost());
          cart.deleteItem(1);
      }
      
      if (!flag) fail("Не было ни одной итерации цикла");
  }
 
 
  /**
   * StandartPlus
   * Проверить, что цена дисконта на странице и в корзине отображается одинаково.
   */
  @Test
    public void testStandartPlus() throws Exception {
      PublikatsiiVakansiyOffer  offer = null;
      ShoppingCartItemsList     items = null;
      boolean flag = false;  
     
       offer = publ.getStandartPlus();
      
      for (int i=1; i<=offer.getCountOfDiscountRates(); i++) {
          flag = true;
         
          offer.clickDiscountRate(i);
          offer.addToCart();
          items = cart.getItemsFromPage();
          assertEquals(items.get(1).getItemActualCost(),  offer.getCost());
          cart.deleteItem(1);
      }
      
      if (!flag) fail("Не было ни одной итерации цикла");
  }
 
 
  /**
   * Premium
   * Проверить, что цена дисконта на странице и в корзине отображается одинаково.
   */
  @Test
    public void testPremium() throws Exception {
      PublikatsiiVakansiyOffer  offer = null;
      ShoppingCartItemsList     items = null;
      boolean flag = false;  
     
       offer = publ.getPremium();
      
      for (int i=1; i<=offer.getCountOfDiscountRates(); i++) {
          flag = true;
         
          offer.clickDiscountRate(i);
          offer.addToCart();
          items = cart.getItemsFromPage();
          assertEquals(items.get(1).getItemActualCost(),  offer.getCost());
          cart.deleteItem(1);
      }
      
      if (!flag) fail("Не было ни одной итерации цикла");
  }
 
 
  /**
   * Anonim
   * Проверить, что цена дисконта на странице и в корзине отображается одинаково.
   */
  @Test
    public void testAnonim() throws Exception {
      PublikatsiiVakansiyOffer  offer = null;
      ShoppingCartItemsList     items = null;
      boolean flag = false;  
     
       offer = publ.getAnonim();
      
      for (int i=1; i<=offer.getCountOfDiscountRates(); i++) {
          flag = true;
         
          offer.clickDiscountRate(i);
          offer.addToCart();
          items = cart.getItemsFromPage();
          assertEquals(items.get(1).getItemActualCost(),  offer.getCost());
          cart.deleteItem(1);
      }
      
      if (!flag) fail("Не было ни одной итерации цикла");
  }
 
 
 
  /**
   * Проверить что данные из дискаунтов отображаются правильно на странице.
   */
 
  @Test
    public void testDataOFDiscouns() throws Exception {
      PublikatsiiVakansiyOffer  offer = null;
     
        //1
        offer = publ.getStandart();
        offer.clickDiscountRate(1);
        assertEquals("3250", offer.getCost());
       
        offer.clickDiscountRate(2);
        assertEquals("6000", offer.getCost());
       
        offer.clickDiscountRate(3);
        assertEquals("25000", offer.getCost());
       
        assertEquals(3, offer.getCountOfDiscountRates());
       
        //2
        offer = publ.getStandartPlus();
        offer.clickDiscountRate(1);
        assertEquals("12000", offer.getCost());
       
        offer.clickDiscountRate(2);
        assertEquals("22000", offer.getCost());
       
        offer.clickDiscountRate(3);
        assertEquals("90000", offer.getCost());
       
        assertEquals(3, offer.getCountOfDiscountRates());
       
        //3
        offer = publ.getPremium();
        offer.clickDiscountRate(1);
        assertEquals("35000", offer.getCost());
       
        offer.clickDiscountRate(2);
        assertEquals("60000", offer.getCost());
       
        offer.clickDiscountRate(3);
        assertEquals("250000", offer.getCost());
 
        assertEquals(3, offer.getCountOfDiscountRates());
       
        //4
        offer = publ.getAnonim();
        offer.clickDiscountRate(1);
        assertEquals("13750", offer.getCost());
       
        offer.clickDiscountRate(2);
        assertEquals("25000", offer.getCost());
       
        offer.clickDiscountRate(3);
        assertEquals("100000", offer.getCost());
       
        assertEquals(3, offer.getCountOfDiscountRates());
       
         
  }
  /***
   * Добавить все предложения и удалить в обратном порядке
   */
  @Test
    public void testDelete() throws Exception {
      PublikatsiiVakansiyOffer  offer = null;
     
        //1
        offer = publ.getStandart();
        offer.clickDiscountRate(1);
        offer.addToCart();
       
       
        //2
        offer = publ.getStandartPlus();
        offer.clickDiscountRate(1);
         offer.addToCart();
        //3
        offer = publ.getPremium();
        offer.clickDiscountRate(1);
         offer.addToCart();
        //4
        offer = publ.getAnonim();
        offer.clickDiscountRate(1);
         offer.addToCart();
       
         assertThat(cart.get(4).getItemSpecialOfferTitle(), containsString("5 Анонимная"));
        cart.deleteItem(4);
        assertThat(cart.get(3).getItemSpecialOfferTitle(), containsString("5 вакансии Премиум"));
        cart.deleteItem(3);
        assertThat(cart.get(2).getItemSpecialOfferTitle(), containsString("5 вакансии Стандарт плюс по"));
        cart.deleteItem(2);
        assertThat(cart.get(1).getItemSpecialOfferTitle(), containsString("5 вакансии Стандарт по"));
        cart.deleteItem(1);
       
       
       
       
  }
 
  /**
   * Добавить все 4 оффера на страницу и убедиться, что сумма в корзине
   * посчитана правильно.
   * Проверятся самые дорогие варианты.
   */
  @Test
     public void testTitlesOfOffers() throws Exception {
      PublikatsiiVakansiyOffer first  = publ.getStandart();
      PublikatsiiVakansiyOffer second = publ.getStandartPlus();
      PublikatsiiVakansiyOffer third  = publ.getPremium();
      PublikatsiiVakansiyOffer fourth = publ.getAnonim();

       first.clickDiscountRate(3);
       first.addToCart();
     
       second.clickDiscountRate(3);
       second.addToCart();
      
       third.clickDiscountRate(3);
       third.addToCart();
      
       fourth.clickDiscountRate(3);
       fourth.addToCart();
      
     
       assertEquals("465000", cart.getTotal()); 
  }

}