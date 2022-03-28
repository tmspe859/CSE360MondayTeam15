import java.util.ArrayList;

public class Customer extends User {

    private DeliveryInfo deliveryInfo;
    private CardInfo cardInfo;
    private ArrayList<Coupon> coupons;
    private ArrayList<Order> pastOrders;
    private Order currentOrder;

    public Customer() {
        super();
        this.deliveryInfo = null;
        this.cardInfo = null;
        this.coupons = new ArrayList<Coupon>();
        this.pastOrders = new ArrayList<Order>();
        this.currentOrder = null;
    }

    public Customer(String firstName, String lastName, String userName, String password, String email, Integer accountID) {
        super(firstName, lastName, userName, password, email, accountID);
        this.deliveryInfo = null;
        this.cardInfo = null;
        this.coupons = new ArrayList<Coupon>();
        this.pastOrders = new ArrayList<Order>();
        this.currentOrder = null;
    }

    public void setDeliveryInfo(DeliveryInfo deliveryInfo) { this.deliveryInfo = deliveryInfo; }
    public DeliveryInfo getDeliveryInfo() { return this.deliveryInfo; }

    public void setPaymentInfo(CardInfo cardInfo) { this.cardInfo = cardInfo; }
    public CardInfo getPaymentInfo() { return this.cardInfo; }
    
    
}
