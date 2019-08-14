package com.ccsu.bookshop.bean;

public class BookCar {

    private Integer id;
    private Integer userId;//用户id
    private Integer bookId;//书籍id
    private String title;//书名
    private String picUrl;//图片URL
    private float price;//单价
    private Integer number;//数量
    private String bookTypeBig;//大分类
    private int status;//状态
    private String createTime;//购买时间

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBookTypeBig() {
        return bookTypeBig;
    }

    public void setBookTypeBig(String bookTypeBig) {
        this.bookTypeBig = bookTypeBig;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}
