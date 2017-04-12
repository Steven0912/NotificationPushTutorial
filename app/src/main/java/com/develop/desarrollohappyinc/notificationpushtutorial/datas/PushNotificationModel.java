package com.develop.desarrollohappyinc.notificationpushtutorial.datas;

import java.util.UUID;

/**
 * Created by DESARROLLO HAPPY INC on 24/03/2017.
 */

public class PushNotificationModel {
    private String id;
    private String mTitle;
    private String mDescription;
    private String mExpiryDate;
    private float mDiscount;

    public PushNotificationModel() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public String getExpiryDate() {
        return mExpiryDate;
    }

    public void setExpiryDate(String mExpiryDate) {
        this.mExpiryDate = mExpiryDate;
    }

    public float getDiscount() {
        return mDiscount;
    }

    public void setDiscount(float mDiscount) {
        this.mDiscount = mDiscount;
    }
}
