package com.ccsu.bookshop.bean;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class BookComment {
    private Integer id;
    private Integer dadId;
    private Integer uid;
    private String uname;
    private String content;
    private Integer star;
    private String pic;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    private Integer praise;
    private String icon;
    private String likeMan;
    public String getLikeMan() {
        return likeMan;
    }
    public void setLikeMan(String likeMan) {
        this.likeMan = likeMan;
    }
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDadId() {
        return dadId;
    }

    public void setDadId(Integer dadId) {
        this.dadId = dadId;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }
}
