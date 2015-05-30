package org.kotikov.tests;
 
import static org.junit.Assert.assertEquals;
 
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.kotikov.PriceView.DostupKBazeResumeOffer;
import org.kotikov.PriceView.DostupKBazeResumeTab;
import org.kotikov.PriceView.PublikatsiiVakansiyOffer;
import org.kotikov.PriceView.PublikatsiiVakansiyTab;
import org.kotikov.PriceView.RecomenduemoeOffer;
import org.kotikov.PriceView.RecomenduemoeTab;
import org.kotikov.PriceView.ShoppingCart;
import org.kotikov.PriceView.ShoppingCartItemsList;
import org.kotikov.framework.TestCommander;
 
public class AddAllOffersTests  extends TestCommander{
 
//корзина
private static  ShoppingCart   cart;
 
 
//страницы
private static  RecomenduemoeTab        recom;
private static  DostupKBazeResumeTab    dost;
private static  PublikatsiiVakansiyTab  publ; 
 
 
@Before
public  void pageSetUP() throws Exception {
recom = page.getRecomenduemoeTab();
  publ = page.getPublikatsiiVakansiyTab();
  dost = page.getDostupKBazeResumeTab();
 
  cart = page.getShoppingCart();
}
 
 
@After  //Почистить за собой
  public    void emptyCart() throws Exception {
  if (!cart.isEmpty())  cart.deleteAll();
 
 
  }
 
  @AfterClass //Закрыть браузер если надо и написать, что тесты прошли
  public  static void end() throws Exception {
System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " всё");
if (!TestCommander.isSuiteModeTrue())
TestCommander.tearDown();
 
 
  }
 
 
 
  /**
  * Тестовый сценарий.
  * Проверить  что можно добавить все офферы с нескольких страниц.
  * Убедиться, что количество офферов и их цены соответствуют ожидаемым.
  *
  * Добавляются:
  * Стр рекомендуемое:
  * два оффера
  *
  * Стр Доступ к базе резюме
  *  первый оффер и первый дискаунт у него
  * 
  * Стр Публикации вакансий
  *  все 4 оффера и второй дискаунт у каждого 
  */
    @Test
public void addOffersToCartAndDeleteThemTest() throws Exception {
       
    RecomenduemoeOffer offerOneActual  = null;
    RecomenduemoeOffer offerTwoActual  = null;
    DostupKBazeResumeOffer    offerP  = null;
    PublikatsiiVakansiyOffer  offer1  = null;
 
    //Переходить на каждую страницу и добавлять офферы
   
    page.clickRecomenduemoeTab();
              offerOneActual = recom.getOffers().prepareOfferOne().getOfferFromPage();
      offerTwoActual = recom.getOffers().prepareOfferTwo().setNoOldPrice().getOfferFromPage();
   
      offerOneActual.addToChart();
      offerTwoActual.addToChart();
 
   
    page.ClickDostupKBazeResumeTab();
      offerP = dost.getOffers().getFirst();
      offerP.clickRadio(1);
      dost.addToCart();
   
     
    //кликнуть по второму дискаунту для всех офферов
    page.ClickPublikatsiiVakansiyTab();
     
      offer1 = publ.getStandart();
      offer1.clickDiscountRate(2);
      offer1.addToCart();
     
      offer1 = publ.getStandartPlus();
      offer1.clickDiscountRate(2);
      offer1.addToCart();
   
      offer1 = publ.getPremium();
      offer1.clickDiscountRate(2);
      offer1.addToCart();
   
      offer1 = publ.getAnonim();
      offer1.clickDiscountRate(2);
      offer1.addToCart();
   
   
   
    //Проверить что количество элементов и их цена соответствует ожидаемой
    assertEquals("7", Integer.toString(cart.getCount()));
    assertEquals("127100", cart.getTotal());
  }
 
 
   
   
    /**
  * Тестовый сценарий:
  * Выбрать несколько офферов со страницы
  * Добавить их в корзину
  * Проверить результат
  * Удалить из корзины те, что были добавлены.
  * Добавить те, которые не были добавлены до этого.
  * Проверить результат
  *
  *
  * Добавляются:
  * Стр. рекомендуемое
  * оба оффера
  *
  * Стр Доступ к базе резюме
  * Второй вариант, чекбокс 1, затем 3 вариант и чек бокс 3
  *
  * Стр Публикации вакансий
  * Все 4 оффера, радиокнопка (3 везде)
  */
    @Test
public void addPartThenRestOfThem() throws Exception {
    RecomenduemoeOffer offerOneActual  = null;
    RecomenduemoeOffer offerTwoActual  = null;
    DostupKBazeResumeOffer    offerP  = null;
    PublikatsiiVakansiyOffer  offer1  = null;
   
   
    page.clickRecomenduemoeTab();
    offerTwoActual = recom.getOffers().prepareOfferTwo().setNoOldPrice().getOfferFromPage();
      offerTwoActual.addToChart();
     
    page.ClickDostupKBazeResumeTab();
      offerP = dost.getOffers().getSecond();
      offerP.clickRadio(1);
      dost.addToCart();
   
    page.ClickPublikatsiiVakansiyTab();
      offer1 = publ.getStandart();
      offer1.clickDiscountRate(3);
      offer1.addToCart();
     
      offer1 = publ.getStandartPlus();
      offer1.clickDiscountRate(3);
      offer1.addToCart();
     
   
    //Проверить что количество элементов и их цена соответствует ожидаемой
    assertEquals("4", Integer.toString(cart.getCount()));
    assertEquals("153000", cart.getTotal()); 
   
   
          //Удалить все
    		cart.deleteAll();
 
   
    		page.clickRecomenduemoeTab();
              offerOneActual = recom.getOffers().prepareOfferOne().getOfferFromPage();  
              offerTwoActual.addToChart();
             
             
            page.ClickDostupKBazeResumeTab();
                offerP = dost.getOffers().getThird();
            	offerP.clickRadio(3);
            	dost.addToCart();
 
   
            page.ClickPublikatsiiVakansiyTab();
               offer1 = publ.getPremium();
               offer1.clickDiscountRate(3);
               offer1.addToCart();
   
   
               	offer1 = publ.getAnonim();
               	offer1.clickDiscountRate(3);
               	offer1.addToCart();
   
   
  //Проверить что количество элементов и их цена соответствует ожидаемой
    assertEquals("4", Integer.toString(cart.getCount()));
    assertEquals("580165", cart.getTotal()); 
    }
   
   
}
 