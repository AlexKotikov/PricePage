package org.kotikov.framework;


/**
 * Тут будет все, что связанно с модификацией данных
 */
public class PriceUtils {

	
 public static String parsCurrency(String in) {
	 
	 in = new String(in.replaceAll("руб.",""));
	 in = new String(in.replaceAll(" ",""));
  return in;
 }
	
}
