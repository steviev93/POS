package Models;

public class ItemSold {
    private int SellId;
    private String Name;
    private double Price;
    private int OrderId;

    public ItemSold(int sellId, String name, double price, int orderId) {
        SellId = sellId;
        Name = name;
        Price = price;
        OrderId = orderId;
    }

    public int getSellId() {
        return SellId;
    }

    public void setSellId(int sellId) {
        SellId = sellId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }

    public int getOrderId() {
        return OrderId;
    }

    public void setOrderId(int orderId) {
        OrderId = orderId;
    }
}
