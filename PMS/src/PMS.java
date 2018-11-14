import buyer_pages.*;
import front_end.*;
import views.*;

public class PMS {
	public static void main(String[] args) {
		View v1 = new LoginView();
		View v2 = new OperatorView();
		View v3 = new BuyerView();
		View v4 = new BuyerView();
		v4.setContentPane(new RegisteredBuyerHomePage());
		View v5 = new BuyerView();
		v5.setContentPane(new PaymentInfoPage());
		View v6 = new BuyerView();
		v6.setContentPane(new CheckoutPage());
		View v7 = new BuyerView();
		v7.setContentPane(new PromotionsPage());
	}
}
