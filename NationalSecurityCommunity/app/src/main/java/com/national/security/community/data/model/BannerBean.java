package com.national.security.community.data.model;

public class BannerBean {

    private String id;
    private String advertModel;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAdvertModel() {
        return advertModel;
    }

    public void setAdvertModel(String advertModel) {
        this.advertModel = advertModel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String name;
    private String picture;
    private String url;

}
