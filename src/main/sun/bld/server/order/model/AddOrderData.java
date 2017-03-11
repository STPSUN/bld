package main.sun.bld.server.order.model;

/**
 * Created by SUN on 2017/3/11.
 */
public class AddOrderData {
    private int buyNumber;
    private int productID;

    public int getBuyNumber() {
        return buyNumber;
    }

    public void setBuyNumber(int buyNumber) {
        this.buyNumber = buyNumber;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }
}
