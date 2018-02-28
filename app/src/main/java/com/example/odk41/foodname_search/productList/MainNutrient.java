
package com.example.odk41.foodname_search.productList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainNutrient {

    @SerializedName("servingAmount")
    @Expose
    private String servingAmount;
    @SerializedName("servingAmountUnit")
    @Expose
    private String servingAmountUnit;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("nutrientName")
    @Expose
    private String nutrientName;
    @SerializedName("nutrientId")
    @Expose
    private String nutrientId;

    public String getServingAmount() {
        return servingAmount;
    }

    public void setServingAmount(String servingAmount) {
        this.servingAmount = servingAmount;
    }

    public String getServingAmountUnit() {
        return servingAmountUnit;
    }

    public void setServingAmountUnit(String servingAmountUnit) {
        this.servingAmountUnit = servingAmountUnit;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public String getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(String nutrientId) {
        this.nutrientId = nutrientId;
    }

}
