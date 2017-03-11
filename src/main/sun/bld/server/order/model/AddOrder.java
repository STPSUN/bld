package main.sun.bld.server.order.model;

import java.util.List;

/**
 * Created by SUN on 2017/3/11.
 */
public class AddOrder {
    private String userName;
    private int addressID;
    private List<AddOrderData> addOrderData;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getAddressID() {
        return addressID;
    }

    public void setAddressID(int addressID) {
        this.addressID = addressID;
    }

    public List<AddOrderData> getAddOrderData() {
        return addOrderData;
    }

    public void setAddOrderData(List<AddOrderData> addOrderData) {
        this.addOrderData = addOrderData;
    }
}
