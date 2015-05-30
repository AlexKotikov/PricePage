package org.kotikov.framework;

import java.io.FileReader;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;

public class XMLloader {

	private static final String RESOURCES_XML_COMMON_PATH = "./src/test/resources/";
	//Потом эту строку можно вынести в коллекцию Properties
	
	private static final String RECOMENDUEMOE_PAGE_OFFERS_XML = "RecomenduemoePageOffers.xml";

	
	
	private static Offers loadXML(String forwhatPage) throws IOException {
		XStream stream = new XStream();
		
		stream.processAnnotations(Offers.class);
		//stream.processAnnotations(Offer.class);
		
		FileReader reader = new FileReader(RESOURCES_XML_COMMON_PATH+ forwhatPage);  

     	Offers 	resultList   =   (Offers ) stream.fromXML(reader) ;
		 reader.close();
		 return resultList;
	}
	
	
	public static Offers getRecomenduemoePageOffersXML() throws IOException {
		return loadXML(RECOMENDUEMOE_PAGE_OFFERS_XML);
	}
}
