package com.grp.ln.web.model;

import java.util.Date;

public class Appversions {
    private String resId;

    private String name;

    private String appversions;

    private Date dateTime;

    private Integer status;

    public String getResId() {
        return resId;
    }

    public void setResId(String resId) {
        this.resId = resId == null ? null : resId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAppversions() {
        return appversions;
    }

    public void setAppversions(String appversions) {
        this.appversions = appversions == null ? null : appversions.trim();
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}