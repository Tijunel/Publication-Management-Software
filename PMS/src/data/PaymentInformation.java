package data;

import java.io.Serializable;

public class PaymentInformation implements Serializable{
	private static final long serialVersionUID = 1L;
	private String cardType;
	private int cardNumber;
	
	public PaymentInformation(String cardType, int cardNumber){
		this.setCardType(cardType);
		this.setCardNumber(cardNumber);
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}
}
