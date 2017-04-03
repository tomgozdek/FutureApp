package com.tomekgozdek.futureapp.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Model class representing FutureItem object retrieved from API.
 *
 * Created by TomekG on 2017-04-02.
 */

public class FutureItem extends RealmObject{

    private String title;
    private String description;
    @PrimaryKey
    private int orderId;
    private String url;
    @SerializedName("image_url")
    private String imageUrl;
    private Date modificationDate;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Date getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(Date date) {
        this.modificationDate = date;
    }

    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("ORDER_ID: ").append(getOrderId()).append("\n");
        buffer.append("TITLE: ").append(getTitle()).append("\n");
        buffer.append("DESC: ").append(getDescription() != null ? getDescription().substring(0, 40) : getDescription()).append("\n");
        buffer.append("URL: ").append(getUrl()).append("\n");

        return buffer.toString();
    }
}
