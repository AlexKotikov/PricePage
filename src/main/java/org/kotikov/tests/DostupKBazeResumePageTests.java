package org.kotikov.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kotikov.PriceView.DostupKBazeResumeOffer;
import org.kotikov.PriceView.DostupKBazeResumeTab;
import org.kotikov.PriceView.ShoppingCart;
import org.kotikov.PriceView.ShoppingCartItemsList;
import org.kotikov.framework.TestCommander;

public class DostupKBazeResumePageTests extends TestCommander{
   
   
    private static  DostupKBazeResumeTab   dost; 
    private static  ShoppingCart            cart;
   
   
  @Before
     public  void setUpPage() throws Exception {
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
           TestCommander.tearDown(); else refresh ();
      }
 
  @After  //Почистить за собой
  public    void emptyCart() throws Exception {
         if (!cart.isEmpty())  cart.deleteAll();
     
  }
 
 
  /**
   * Проверить что данные из офферов присутствуют на странице.
   */
  @Test
     public void testTitlesOfOffers() throws Exception {
      DostupKBazeResumeOffer offer = null;

       offer = dost.getOffers().getFirst();
       assertEquals (offer.getTitleOfOffer(), "Экспресс-подбор");
       assertEquals (offer.getDescrOfOffer(), "Для разового закрытия одной или нескольких вакансий.");
     
       offer = dost.getOffers().getSecond();
       assertEquals (offer.getTitleOfOffer(), "Поиск специалиста");
       assertEquals (offer.getDescrOfOffer(), "Для активного подбора нескольких специалистов");
      
       offer = dost.getOffers().getThird();
       assertEquals (offer.getTitleOfOffer(), "Регулярный подбор");
       assertEquals (offer.getDescrOfOffer(), "Компаниям, которые ведут постоянный набор сотрудников.");
      
      
  }
 
  /**
   * Выборочная проверка радиокнопок из офферов.
   * Проверяется цена и название
   */
  @Test
     public void testSomeRadioButtons() throws Exception {
      DostupKBazeResumeOffer offer = null;

       offer = dost.getOffers().getFirst();
       assertEquals (offer.getRadioText(2),          "7 дней");
       assertEquals (offer.getRadioCost(3),          "16500");
     
       offer = dost.getOffers().getSecond();
       offer.clickRadio(1);
       assertEquals (offer.getRadioText(1),         "30 дней");
       assertEquals (offer.getRadioCost(1),           "29000");
      
       offer = dost.getOffers().getThird();
       assertEquals (offer.getRadioText(1),           "3 месяца");
       assertEquals (offer.getRadioCost(3),          "251700");
  }
 
  /**
   * Проверить, что отображается верное количество опций для каждого оффера
   */
  @Test
     public void testRadioOfEachOffer() throws Exception {
        DostupKBazeResumeOffer offer = null;

        offer = dost.getOffers().getFirst();
        assertEquals (offer.getCountOfCheckboxes(), 3);
       
        offer = dost.getOffers().getSecond();
       assertEquals (offer.getCountOfCheckboxes(), 1);
      
       offer = dost.getOffers().getThird();
        assertEquals (offer.getCountOfCheckboxes(), 3);
       
  }
 
  /**
   *Проверить что все офферы и их опции можно добавлять в корзину без удаления, кликом.
   *Проверить что их цена отображается в корзине так же как и на странице.
   */
  @Test
    public void testFIRSTradio() throws Exception {
      boolean flag= false;
     
       DostupKBazeResumeOffer 
       //Со страницы получить первый оффер
       offer = dost.getOffers().getFirst();
      
       //Пройтись по всем радиокнопкам
     for (int radio=1; radio <=offer.getCountOfCheckboxes(); radio++) {
           flag =true;
           offer.clickRadio(radio);
           dost.addToCart();
           assertEquals (offer.getRadioCost(radio),  cart.getTotal());
          }
     if (!flag) fail("Не было ни одной итерации цикла");
  }
 
 
  @Test
     public void testSECONDradio() throws Exception {
      boolean flag= false;
     
       DostupKBazeResumeOffer 
       //Со страницы получить первый оффер
       offer = dost.getOffers().getSecond();
      
       //Пройтись по всем радиокнопкам
     for (int radio=1; radio <=offer.getCountOfCheckboxes(); radio++) {
           flag =true;
           offer.clickRadio(radio);
           dost.addToCart();
           assertEquals (offer.getRadioCost(radio),  cart.getTotal());
         }
     if (!flag) fail("Не было ни одной итерации цикла");
 }
     
  @Test
    public void testTHIRDradio() throws Exception {
      boolean flag= false;
     
       DostupKBazeResumeOffer 
       //Со страницы получить первый оффер
       offer = dost.getOffers().getThird();
      
       //Пройтись по всем радиокнопкам
     for (int radio=1; radio <=offer.getCountOfCheckboxes(); radio++) {
           flag =true;
           offer.clickRadio(radio);
           dost.addToCart();
           assertEquals (offer.getRadioCost(radio),  cart.getTotal());
        }
     if (!flag) fail("Не было ни одной итерации цикла");
}
   
  /**
   * Проверить что тайтл  и описание присутствуют на странице
   */
    @Test
    public void testDostupKBazePage() {
       
    String  actual = dost.getTitle();
    String expected = "Доступ к базе резюме";
    assertEquals( actual,expected);
   
    actual = dost.getResumeAccessGift();
    expected = "Вакансия Стандарт+";
    assertThat(actual, containsString(expected));
   
    }

}
