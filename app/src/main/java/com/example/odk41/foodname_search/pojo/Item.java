
package com.example.odk41.foodname_search.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

    @SerializedName("foodId")
    @Expose
    private String foodId;
    @SerializedName("mainNutrientServingMeasureAmount")
    @Expose
    private String mainNutrientServingMeasureAmount;
    @SerializedName("volumeUnit")
    @Expose
    private String volumeUnit;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;
    @SerializedName("registerDate")
    @Expose
    private String registerDate;
    @SerializedName("mainNutrientServingMeasureUnit")
    @Expose
    private String mainNutrientServingMeasureUnit;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("foodType")
    @Expose
    private String foodType;
    @SerializedName("barcode")
    @Expose
    private String barcode;
    @SerializedName("mainNutrients")
    @Expose
    private List<MainNutrient> mainNutrients = null;
    @SerializedName("vendors")
    @Expose
    private String vendors;
    @SerializedName("foodName")
    @Expose
    private String foodName;

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public String getMainNutrientServingMeasureAmount() {
        return mainNutrientServingMeasureAmount;
    }

    public void setMainNutrientServingMeasureAmount(String mainNutrientServingMeasureAmount) {
        this.mainNutrientServingMeasureAmount = mainNutrientServingMeasureAmount;
    }

    public String getVolumeUnit() {
        return volumeUnit;
    }

    public void setVolumeUnit(String volumeUnit) {
        this.volumeUnit = volumeUnit;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getMainNutrientServingMeasureUnit() {
        return mainNutrientServingMeasureUnit;
    }

    public void setMainNutrientServingMeasureUnit(String mainNutrientServingMeasureUnit) {
        this.mainNutrientServingMeasureUnit = mainNutrientServingMeasureUnit;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public List<MainNutrient> getMainNutrients() {
        return mainNutrients;
    }

    public void setMainNutrients(List<MainNutrient> mainNutrients) {
        this.mainNutrients = mainNutrients;
    }

    public String getVendors() {
        return vendors;
    }

    public void setVendors(String vendors) {
        this.vendors = vendors;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

}
