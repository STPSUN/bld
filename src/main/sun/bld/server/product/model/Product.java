package main.sun.bld.server.product.model;

/**
 * Created by SUN on 2017/2/18.
 */
public class Product {
    private Integer productID;
    private String categorys;
    private String productName;
    private Double price;
    private Integer productNumber;
    private String productDetail;
    private String productImg;
    private String productState;
    private String categorysImg;

    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public String getCategorys() {
        return categorys;
    }

    public void setCategorys(String categorys) {
        this.categorys = categorys;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(String productDetail) {
        this.productDetail = productDetail;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductState() {
        return productState;
    }

    public void setProductState(String productState) {
        this.productState = productState;
    }

    public String getCategorysImg() {
        return categorysImg;
    }

    public void setCategorysImg(String categorysImg) {
        this.categorysImg = categorysImg;
    }
}
