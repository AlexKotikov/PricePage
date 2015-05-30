package org.kotikov.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kotikov.PriceView.RecomenduemoeOffer;
import org.kotikov.PriceView.RecomenduemoeTab;
import org.kotikov.PriceView.ShoppingCart;
import org.kotikov.framework.Offers;
import org.kotikov.framework.TestCommander;
import org.kotikov.framework.XMLloader;

public class RecomenduemoePageTests  extends TestCommander{

    //Представления страницы, корзины, офферов
    private static  RecomenduemoeTab   recPage; 
    private static  ShoppingCart        cart;
    //private static  RecomenduemoeOffer offerOneActual;
    //private static  RecomenduemoeOffer offerTwoActrual;
   
       //Ожидаемый результат
      private static  Offers.RecomOffer  offerOneExpected;
      private static  Offers.RecomOffer  offerTwoExpected;
     
 
 @BeforeClass  //Получить тестовые данные из XML
   public static void start() throws Exception {
        Offers o =  XMLloader.getRecomenduemoePageOffersXML();
         
        //Ожидаемые данные, которые будут сверяться с актуальными (то что на есть на стр).
        offerOneExpected =  o.get(1);
        offerTwoExpected =  o.get(2);
     }
   
   
     @Before
     public void setUpReocmenduemoeTab() throws Exception {
         recPage = page.getRecomenduemoeTab();
            cart = page.getShoppingCart();
       
           
           
            page.clickRecomenduemoeTab();
     }
     
      @After  //Почистить за собой
      public   void emptyCart() throws Exception {
          if (!cart.isEmpty())
          {
                  for(int i=0; i < cart.getCount() ;i++){
                     cart.deleteItem(1);
                  }
          }
         
      }
     
     
      @AfterClass //Закрыть браузер если надо и написать, что тесты прошли
      public static void end() throws Exception {
        System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " всё");
        if (!TestCommander.isSuiteModeTrue()) {
           TestCommander.tearDown();}
      }
   
     /**
       * Проверить что вкладка "Рекомендуемое" представлена на странице
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
       * Проверить что корзина представлена на странице
       */
       @Test
      public void testCartOnRecomTab() {
          assertEquals(cart.getTitle(), "Корзина пуста");
      }
     
     
      /**
       * Проверить, что оба предложения присутствуют на странице еще до добавления в корзину
       *
       * Первое предложение на странице.
       */
       @Test
      public void testOfferOneOnPageV2() throws Exception {
           RecomenduemoeOffer   offerOneActual = recPage.getOffers().prepareOfferOne().getOfferFromPage();
          
          assertEquals(offerOneActual.getGiftDesc(),            "при покупке вакансии");
          assertEquals(offerOneActual.getActualPrice(),            "2100");
          assertEquals(offerOneActual.getGiftTitle(),            "День доступа к базе резюме бесплатно");
          assertEquals(offerOneActual.getOldPrice(),            "3000");
          assertEquals(offerOneActual.getSpecialOfferPlus(),    "+День доступа к базе резюме бесплатно");
          assertEquals(offerOneActual.getSpecialOfferTitle(),   "Вакансия Стандарт+");
      }
     
       /**
        * Первое предложение на странице.
        * Тест аналогичен предыдущему, только данные берутся из XML.
        * (Оставил оба теста для наглядности)
        */
       @Test
          public void testOfferOneOnPage() throws Exception {
           RecomenduemoeOffer    offerOneActual = recPage.getOffers().prepareOfferOne().getOfferFromPage();
          
              assertEquals(offerOneActual.getActualPrice(),       offerOneExpected.getActualPrice());
              assertEquals(offerOneActual.getGiftDesc(),           offerOneExpected.getGiftDesc());
              assertEquals(offerOneActual.getGiftTitle(),          offerOneExpected.getGiftTitle());
              assertEquals(offerOneActual.getOldPrice(),          offerOneExpected.getOldPrice());
              assertEquals(offerOneActual.getSpecialOfferPlus(),  offerOneExpected.getSpecialOfferPlus());
              assertEquals(offerOneActual.getSpecialOfferTitle(), offerOneExpected.getSpecialOfferTitle());
          }
      
      
       /**
        * Убедиться что оффер2 подгрузился на страницу тоже
        */
       @Test
      public void testOfferTwoOnPage() throws Exception {                                
           RecomenduemoeOffer offerTwoActrual = recPage.getOffers().prepareOfferTwo().setNoOldPrice().getOfferFromPage();
          
          assertEquals(offerTwoActrual.getActualPrice(),       offerTwoExpected.getActualPrice());       
          assertEquals(offerTwoActrual.getGiftDesc(),           offerTwoExpected.getGiftDesc());          
          assertEquals(offerTwoActrual.getGiftTitle(),           offerTwoExpected.getGiftTitle());         
          assertEquals(offerTwoActrual.getOldPrice(),           offerTwoExpected.getOldPrice());          
          assertEquals(offerTwoActrual.getSpecialOfferPlus(),  offerTwoExpected.getSpecialOfferPlus());  
          assertEquals(offerTwoActrual.getSpecialOfferTitle(), offerTwoExpected.getSpecialOfferTitle()); 
      }                                                                                               
     
     
     
      /**
       * Тестовый сценарий.
       * Проверить что оба оффера можно добавить и затем удалить.
       *
       * Оффер в корзине и на странице имеют разные title поэтому приходится использовать
       * статический метод containsString() и использовать метод getSpecialOfferTitleSHORT,
       * чтобы сверить оффер по куску строки, а не по всей.
       */
        @Test
        public void addOffersToCartAndDeleteThemTest() throws Exception {
            RecomenduemoeOffer offerOneActual = recPage.getOffers().prepareOfferOne().getOfferFromPage();
            RecomenduemoeOffer offerTwoActrual = recPage.getOffers().prepareOfferTwo().setNoOldPrice().getOfferFromPage();
             
             
                //Добавить первый в корзину и извлечь из корзины
             offerOneActual.addToChart();
                assertThat(cart.get(1).getItemSpecialOfferTitle() , containsString (offerOneExpected.getSpecialOfferTitle())) ;
           
                //Второй тоже добавить в корзину
             offerTwoActrual.addToChart();
                assertThat(cart.get(2).getItemSpecialOfferTitle(),containsString (offerTwoExpected.getSpecialOfferTitleSHORT()))  ;
           
                //Удалить второй из корзины а первый там должен остаться
             cart.deleteItem(2) ;
             assertThat(cart.get(1).getItemSpecialOfferTitle() , containsString (offerOneExpected.getSpecialOfferTitle())) ;
            
                //Удалить первый. В корзине должно быть пусто
             cart.deleteItem(1);
             assertEquals(cart.getTitle(), "Корзина пуста");
          }

       
        /**
           * Тестовый сценарий.
           * Проверить что оба оффера можно добавить и затем удалить, а затем снова добавить
           */
        @Test
        public void addOffersToCartDeleteAndAddAgainTest() throws Exception {
            RecomenduemoeOffer offerOneActual = recPage.getOffers().prepareOfferOne().getOfferFromPage();
            RecomenduemoeOffer offerTwoActrual = recPage.getOffers().prepareOfferTwo().setNoOldPrice().getOfferFromPage();
             
           
             //Добавить в корзину
             offerOneActual.addToChart();
             offerTwoActrual.addToChart();
             
           
             //Убедиться что они оба там
             assertThat(cart.get(1).getItemSpecialOfferTitle() , containsString (offerOneExpected.getSpecialOfferTitle()));
             assertThat(cart.get(2).getItemSpecialOfferTitle() , containsString (offerTwoExpected.getSpecialOfferTitleSHORT()));
            
             //Удалить оба. В корзине должно быть пусто
             cart.deleteItem(2);
             cart.deleteItem(1);
            
             assertEquals(cart.getTitle(), "Корзина пуста");
           
             //Добавить в корзину опять их.
             offerOneActual.addToChart();
             offerTwoActrual.addToChart();
           
            //Убедиться что они оба там
             assertThat(cart.get(1).getItemSpecialOfferTitle() , containsString (offerOneExpected.getSpecialOfferTitle()));
             assertThat(cart.get(2).getItemSpecialOfferTitle() , containsString (offerOneExpected.getSpecialOfferTitleSHORT()));
        }
       
        /**
           * Тестовый сценарий.
           * Добавлять офферы и проверить, что цена отображается в корзине как ожидается
           */
        @Test
        public void calcPriceTest() throws Exception {
             RecomenduemoeOffer offerOneActual = recPage.getOffers().prepareOfferOne().getOfferFromPage();
             RecomenduemoeOffer offerTwoActual = recPage.getOffers().prepareOfferTwo().setNoOldPrice().getOfferFromPage();
             
             
             //Добавить в корзину 1
             offerOneActual.addToChart();
             assertThat( cart.getTotal(), equalTo(offerOneExpected.getActualPrice()));  
             cart.deleteItem(1);
             
            //Добавить в корзину 2
             offerTwoActual.addToChart();
             assertThat(cart.getTotal() ,   equalTo(offerTwoExpected.getActualPrice()));  
             cart.deleteItem(1);
            
             //Добавить в корзину опять их.
             offerOneActual.addToChart();
             offerTwoActual.addToChart();
             
             int expected = Integer.parseInt(offerOneExpected.getActualPrice()) + Integer.parseInt(offerTwoExpected.getActualPrice() );
             
             assertThat(cart.getTotal() ,  equalTo(Integer.toString(expected)));
        }
       
}
