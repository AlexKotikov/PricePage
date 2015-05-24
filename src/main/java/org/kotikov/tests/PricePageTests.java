package org.kotikov.tests;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.kotikov.framework.TestCommander;


public class PricePageTests  extends TestCommander{


   /**
    * Проверить, что страница Price отображается пользователю
    */
	@Test
	public void testPricePage() {
		
	String  actual = page.getTitle();
	String expected = "Покупка услуг (демо)";
	assertEquals(expected, actual);
	
	actual = page.getLinkRecomenduemoe();
	expected = "Рекомендуемое";
	assertEquals(expected, actual);
	
	actual = page.getLinkDostupKBazeResume();
	expected = "Доступ к базе резюме";
	assertEquals(expected, actual);
	
	actual = page.getLinkPublikatsiiVakansiy();
	expected = "Публикации вакансий";
	assertEquals(expected, actual);
	
	actual = page.getLinkDopolnitelnieUslugi();
	expected = "Дополнительные услуги";
	assertEquals(expected, actual);
	
	}
	
	@AfterClass
	  public static void end() throws Exception {
		System.out.println(Thread.currentThread().getStackTrace()[1].getClassName() + " всё");
		if (!TestCommander.isSuiteModeTrue())
		TestCommander.tearDown();
		  
	  }
	
}
