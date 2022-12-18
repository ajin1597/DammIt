package com.example.dammit;

public class ResponseData {
    private String waterLevel;
    private String light;
    private String workNmpr;
    private String updtDt;
    private String damName;

    public String getDamName() {
        return damName;
    }

    public void setDamName(String damName) {
        this.damName = damName;
    }

    public String getWaterLevel() {
        return waterLevel;
    }

    public void setWaterLevel(String waterLevel) {
        this.waterLevel = waterLevel;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public String getWorkNmpr() {
        return workNmpr;
    }

    public void setWorkNmpr(String workNmpr) {
        this.workNmpr = workNmpr;
    }

    public String getUpdtDt() {
        return updtDt;
    }

    public void setUpdtDt(String updtDt) {
        this.updtDt = updtDt;
    }
}
