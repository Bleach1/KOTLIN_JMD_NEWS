package com.national.security.community.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class BannerBean implements Parcelable {

    private String id;
    private String advertModel;
    private HomeBean testBean;
    private ArrayList<HomeBean> testBeans;

    public HomeBean getTestBean() {
        return testBean;
    }

    public void setTestBean(HomeBean testBean) {
        this.testBean = testBean;
    }

    public List<HomeBean> getTestBeans() {
        return testBeans;
    }

    public void setTestBeans(ArrayList<HomeBean> testBeans) {
        this.testBeans = testBeans;
    }

    public BannerBean(Parcel source) {
        id = source.readString();
        advertModel = source.readString();
        name = source.readString();
        picture = source.readString();
        url = source.readString();
        testBean = source.readParcelable(HomeBean.class.getClassLoader());
        testBeans = source.readArrayList(HomeBean.class.getClassLoader());

    }

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

    //备选名字
    @SerializedName(value = "userName", alternate = {"user_name", "Name"})
    // 序列化 反序列化 生效
    @Expose(serialize = true, deserialize = true)
    private String name;
    private String picture;
    private String url;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(advertModel);
        dest.writeString(name);
        dest.writeString(picture);
        dest.writeString(url);
        // dest.writeParcelable(testBean,flags);
        //dest.writeList(testBeans);
    }

    public static final Creator<BannerBean> CREATOR = new Creator<BannerBean>() {
        @Override
        public BannerBean createFromParcel(Parcel source) {
            return new BannerBean(source);
        }

        @Override
        public BannerBean[] newArray(int size) {
            return new BannerBean[size];
        }
    };

}
