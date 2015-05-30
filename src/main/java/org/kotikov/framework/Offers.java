package org.kotikov.framework;

import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * XML Представление оффера на странице Рекомендуемое
 * @author ak
 *
 */
@XStreamAlias("offersList")
public class Offers {
	@XStreamImplicit
	@XStreamAlias("offers")
	private List<RecomOffer> ofList = new ArrayList<RecomOffer>();
	
	
	/**
	 * Получить оффер из XML.
	 * Офферы начинаются с единицы.
	 */
	public  RecomOffer  get(int index) {
		return  ofList.get(index-1);
	}

	 

	@Override
	public String toString() {
		return "Offers [albums=" + ofList + "]";
	}

	

	

@XStreamAlias("offerRecomenduemoePage")
public class RecomOffer {

		
		  String  actualPrice;
		  String  giftDesc  ;
		  String  giftTitle ;
		  String  oldPrice  ;      
		  String  specialOfferPlus ;
		  String  specialOfferTitle ;
		  String  specialOfferTitleSHORT;
		  
		  public void setActualPrice(String actualPrice) {
				this.actualPrice = actualPrice;
			}

			public void setGiftDesc(String giftDesc) {
				this.giftDesc = giftDesc;
			}

			public void setGiftTitle(String giftTitle) {
				this.giftTitle = giftTitle;
			}

			public String getActualPrice() {
				return PriceUtils.parsCurrency(actualPrice);
			}

			public String getGiftDesc() {
				return giftDesc;
			}

			public String getGiftTitle() {
				return giftTitle;
			}

			public String getOldPrice() {
				return oldPrice;
			}

			public String getSpecialOfferPlus() {
				return specialOfferPlus;
			}

			public String getSpecialOfferTitle() {
				return specialOfferTitle;
			}

			public String getSpecialOfferTitleSHORT() {
				return specialOfferTitleSHORT;
			}

			public void setOldPrice(String oldPrice) {
				this.oldPrice = oldPrice;
			}

			public void setSpecialOfferPlus(String specialOfferPlus) {
				this.specialOfferPlus = specialOfferPlus;
			}

			public void setSpecialOfferTitle(String specialOfferTitle) {
				this.specialOfferTitle = specialOfferTitle;
			}

			public void setSpecialOfferTitleSHORT(String specialOfferTitleSHORT) {
				this.specialOfferTitleSHORT = specialOfferTitleSHORT;
			}
			
			@Override
			public String toString() {
				return "RecomenduemoeOfferStructure [actualPrice=" + actualPrice
						+ ", giftDesc=" + giftDesc + ", giftTitle=" + giftTitle
						+ ", oldPrice=" + oldPrice + ", specialOfferPlus="
						+ specialOfferPlus + ", specialOfferTitle=" + specialOfferTitle
						+ ", specialOfferTitleSHORT=" + specialOfferTitleSHORT + "]";
			}
		 
		  
		}	
	
	 
}


  