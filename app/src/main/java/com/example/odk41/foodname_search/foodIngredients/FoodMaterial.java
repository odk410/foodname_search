
package com.example.odk41.foodname_search.foodIngredients;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodMaterial {

    @SerializedName("baseMaterialId")
    @Expose
    private Integer baseMaterialId;
    @SerializedName("materialId")
    @Expose
    private Integer materialId;
    @SerializedName("materialName")
    @Expose
    private String materialName;
    @SerializedName("materialStructure")
    @Expose
    private String materialStructure;

    public Integer getBaseMaterialId() {
        return baseMaterialId;
    }

    public void setBaseMaterialId(Integer baseMaterialId) {
        this.baseMaterialId = baseMaterialId;
    }

    public Integer getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Integer materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialStructure() {
        return materialStructure;
    }

    public void setMaterialStructure(String materialStructure) {
        this.materialStructure = materialStructure;
    }

}
