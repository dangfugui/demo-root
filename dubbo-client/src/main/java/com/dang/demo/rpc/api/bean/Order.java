package com.dang.demo.rpc.api.bean;

import java.io.Serializable;

/**
 * @Date Create in 2018/1/18
 */
public class Order implements Serializable {


    private Integer id;
    private String buyName;
    private transient String goodsName;     // transient  不序列化这个字段
    private Double price;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBuyName() {
        return buyName;
    }

    public void setBuyName(String buyName) {
        this.buyName = buyName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Order{"
                + "id=" + id
                + ", buyName='" + buyName + '\''
                + ", goodsName='" + goodsName + '\''
                + ", price=" + price
                + ", address='" + address + '\''
                + '}';
    }
}
