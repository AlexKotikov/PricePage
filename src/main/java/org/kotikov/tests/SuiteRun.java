package org.kotikov.tests;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.kotikov.framework.TestCommander;



@RunWith(Suite.class)
@SuiteClasses({ 
						PricePageTests.class,
						DostupKBazeResumePageParamTests.class,
						PublikatsiiVakansiyPageTests.class,
						AddAllOffersTests.class,
						RecomenduemoePageTests.class,
						DostupKBazeResumePageTests.class
						
						
})

/**
 * Запуск всех сьютов приложения Price
 * @author ak
 *
 */
public class SuiteRun {
	 
	 
	    @BeforeClass
	    public static void setUp() {
	      System.out.println("Запуск сьютов");
	      TestCommander.suiteTrue();
	    }

	    @AfterClass
	    public static void tearDown() {
	    	TestCommander.tearDown();
	    	System.out.println("Все сьюты прошли. Смотрите результаты");
	    	
	    }
}
