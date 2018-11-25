package data;

public class PaymentInformation 
{
	private String cardType;
	private int cardNumber;
	
	public PaymentInformation(String cardType, int cardNumber)
	{
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
