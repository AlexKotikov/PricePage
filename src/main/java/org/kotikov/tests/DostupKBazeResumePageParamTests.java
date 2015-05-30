package org.kotikov.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kotikov.PriceView.DostupKBazeResumeOffer;
import org.kotikov.PriceView.DostupKBazeResumeTab;
import org.kotikov.PriceView.ShoppingCart;
import org.kotikov.PriceView.ShoppingCartItemsList;
import org.kotikov.framework.TestCommander;

@RunWith(JUnitParamsRunner.class)
public class DostupKBazeResumePageParamTests extends TestCommander{
   
   
    private static  DostupKBazeResumeTab   dost; 
    private static  ShoppingCart            cart;
   
   
  @Before
     public   void setUpPage() throws Exception {
              dost = page.getDostupKBazeResumeTab();
            cart = page.getShoppingCart();
  }
 
  @BeforeClass
     public static void   beforeClass() throws Exception {
         page.ClickDostupKBazeResumeTab();
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
   * Проверить, что все опции первого оффера можно добавить в корзину
   * а  затем все удалить из нее.
   */
 
 
 
  /**
   * Тестировать оффер 1.
   * Проверить, что все доступные варианты отображаются на странице.
   * Проверить, что в корзине и на странице цена отображается одинаково.
   * Проверить, что элементы можно удалять из корзины.
   */
   @Test
   @Parameters({"1","2","3"})
    public void testOffer1variants(int radio) throws Exception {
      DostupKBazeResumeOffer   first = null;

      //Со страницы получить все офферы,а из них первый
       first = dost.getOffers().getFirst();
      
      
       //Кликнуть радиокнопку
       first.clickRadio(radio);
      
       //Убедиться что цены у кнопки и на странице соответствуют друг другу
       assertEquals(first.getRadioCost(radio),dost.getCostOnPage());
      
       //Получить содержимое корзины
       dost.addToCart();
      
       assertEquals(dost.getCostOnPage(),     cart.get(1).getItemActualCost());
     
  }
 
  /**
   * Тестировать оффер 2 с одной радиокнопкой.
   * Проверить, что все доступные  варианты отображаются на странице.
   * Проверить, что в корзине и на странице цена отображается одинаково.
   * В будущем радиокнопок может станет больше.
   * Проверить, что элементы можно удалять из корзины.
   */
     @Test
     @Parameters({"1"})
    public void testOffer2variants(int radio) throws Exception {
      DostupKBazeResumeOffer   second = null;

      //Со страницы получить второй оффер
       second = dost.getOffers().getSecond();
      
       //Кликнуть радиокнопку
       second.clickRadio(radio);
      
       //Убедиться что цены у кнопки и на странице соответствуют друг другу
       assertEquals(second.getRadioCost(radio),    dost.getCostOnPage());
      
       //Получить содержимое корзины
       dost.addToCart();
      
       //Проверить что цены сходятся
       assertEquals(dost.getCostOnPage(),    cart.get(1).getItemActualCost());
      
       //Удалить элемент и проверить что в корзине пусто
       cart.deleteItem(1);
       assertTrue(cart.isEmpty());
  }
 
 
  /**
   * Тестировать оффер 3.
   * Проверить, что все доступные  варианты отображаются на странице.
   * Проверить, что в корзине и на странице цена отображается одинаково.
   * Проверить, что элементы можно удалять из корзины.
   */
  @Test
  @Parameters({"1","2","3"})
    public void testOffer3variants(int radio) throws Exception {
      DostupKBazeResumeOffer   third = null;

      //Со страницы получить третий оффер
      third = dost.getOffers().getThird();
      
       //Кликнуть радиокнопку
      third.clickRadio(radio);
      
       //Убедиться что цены у кнопки и на странице соответствуют друг другу
       assertEquals(third.getRadioCost(radio),    dost.getCostOnPage());
      
       //Получить содержимое корзины
       dost.addToCart();
       assertEquals(dost.getCostOnPage(),    cart.get(1).getItemActualCost());
      
       //Удалить элемент и проверить что в корзине пусто
       cart.deleteItem(1);
       assertTrue(cart.isEmpty());
  }
 

}
