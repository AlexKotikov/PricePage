package org.kotikov.PriceView;

import org.kotikov.framework.PriceUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
 
/**
 * Класс представляет собой один элемент корзины
 * @author ak
 *
 */
 

public class ShoppingCartItem {
   

    private WebDriver driver;
    public ShoppingCartItem(WebDriver d){ this. driver =d;}
   
    //Общие айдишники
    final static private String labels       = "/label/span[1]";
    final static private String oldPrices    = "/label/span[2]";
    final static private String actualPrices = "/label/span[3]";
    final static private String deleteLink   = "/small";
   
    //Поля объекта
    private String itemTitle;
    private String itemOldPrice;
    private String itemActualCost;


   
    public ShoppingCartItem init(int number){
        this.itemTitle           = PriceUtils.getText(driver, new StringBuilder(ShoppingCartItemsList.itemsInCart+"[" +(number) +"]" + labels       ).toString()) ;
        this.itemOldPrice       = PriceUtils.getText(driver, new StringBuilder(ShoppingCartItemsList.itemsInCart+"[" +(number) +"]" + oldPrices    ).toString()) ;
        this.itemActualCost   = PriceUtils.getText(driver, new StringBuilder(ShoppingCartItemsList.itemsInCart+"[" +(number) +"]" + actualPrices ).toString()) ;
    return this;
    }   
   
   
    public String getItemSpecialOfferTitle() {
        return itemTitle ;
    }

    public String getItemOldPrice() {
        return PriceUtils.parsCurrency(itemOldPrice);
    }

    public String getItemActualCost() {
        return PriceUtils.parsCurrency(itemActualCost);
    }

    public void print() {
        System.out.println(toString());
    }

    public void delete(int i) {
        PriceUtils.click(driver,  new StringBuilder(ShoppingCartItemsList.itemsInCart+"[" +(i) +"]" + deleteLink ).toString());
    }
   
    @Override
    public String toString() {
        return "ShoppingCartItem [itemTitle=" + itemTitle + ", itemOldPrice="
                + itemOldPrice + ", itemActualCost=" + itemActualCost + "]";
    }

}
