
package com.example.odk41.foodname_search.foodIngredients;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FoodIngredients {

    @SerializedName("secificIngredient")
    @Expose
    private String secificIngredient;
    @SerializedName("organicAmount")
    @Expose
    private String organicAmount;
    @SerializedName("foodMaterials")
    @Expose
    private List<FoodMaterial> foodMaterials = null;
    @SerializedName("allergyIngredientContent")
    @Expose
    private String allergyIngredientContent;

    public String getSecificIngredient() {
        return secificIngredient;
    }

    public void setSecificIngredient(String secificIngredient) {
        this.secificIngredient = secificIngredient;
    }

    public String getOrganicAmount() {
        return organicAmount;
    }

    public void setOrganicAmount(String organicAmount) {
        this.organicAmount = organicAmount;
    }

    public List<FoodMaterial> getFoodMaterials() {
        return foodMaterials;
    }

    public void setFoodMaterials(List<FoodMaterial> foodMaterials) {
        this.foodMaterials = foodMaterials;
    }

    public String getAllergyIngredientContent() {
        return allergyIngredientContent;
    }

    public void setAllergyIngredientContent(String allergyIngredientContent) {
        this.allergyIngredientContent = allergyIngredientContent;
    }

}
