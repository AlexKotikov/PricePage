package org.kotikov.PriceView;

import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.WebDriver;

  /**
   * Этот класс представляет список объектов в корзине.
   * Эмулирует работу аррэйлиста, а на самом деле он генерит элементы на лету.
   * @author ak
   *
   */

public class ShoppingCartItemsList {

    final static protected String itemsInCart ="(//div[@class='price-cart__contents'  and  not(ancestor::*[contains(@class,'hidden')])]/ol/descendant::li[@class='price-cart__item'  and not(ancestor::ol[contains(@class,'hidden')])  ])";

   
    //Количество записей в корзине
    private int ShoppingCartItems =0;
   
    private WebDriver driver;

    public ShoppingCartItemsList(WebDriver d) {
        this.driver = d;
       
    }
   
    /**
     * Выбрать элемент в корзине. Отсчет от единицы
     * @throws Exception
     */
    public ShoppingCartItem get(int i) throws Exception {
          int toGet = i -1;
       
          if (i < 1) {
                Exception e = new Exception("В корзине элементы начинаются с 1. " + (i));
                e.printStackTrace();
                throw e ;
                } 
         
         
        if (ShoppingCartItems==0) {
            Exception e = new Exception("В корзине пусто. Нельзя вызвать get(). Возможно стоит вызвать getItemsFromPage() вначале");
            e.printStackTrace();
            throw e ;
            }
       
        if (toGet > ShoppingCartItems) {
            Exception e = new Exception("Вы пытаетесь запросить get() больше чем есть в корзине.  " + (i));
            e.printStackTrace();
            throw e ;
        }
       
        //Вернуть запрашиваемый элемент
        return new ShoppingCartItem(driver).init(i);
      }
   
   
   
    /**
     * Узнать сколько элементов лежит в корзине на странице
     */
    public int getCount(){
         
        return ShoppingCartItems ;
    }
   
    protected ShoppingCartItemsList init() {
        ShoppingCartItems = PriceUtils.getList(driver, itemsInCart).size();
      return this;   
    }
       
   
}
