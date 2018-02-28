package com.example.odk41.foodname_search;

import com.example.odk41.foodname_search.foodIngredients.FoodIngredients;
import com.example.odk41.foodname_search.productList.Repo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by odk41 on 2018-02-26.
 */

public interface ApiService {
    //베이스 URL
    static final String BASEURL = "https://apis.eatsight.com/foodinfo/1.0/ ";

    //get 메소드를 통한 http rest api 통신
    //식품 id, 식품 명, thumbnail 이미지
    @GET("foods")
    Call<Repo>getInfo(@Query("DS-ApplicationKey")String appKey, @Query("foodType") String foodType,
                      @Query("searchField") String searchField, @Query("searchValue")String searchValue, @Query("offset") int offset, @Query("limit") int limit);

    //식품 id로 식품에 대한 상세 정보 얻어오기 (알러지)
    @GET("foods/{foodId}/materials")
    Call<FoodIngredients> getDetailInfo(@Path("foodId") String foodId, @Query("DS-ApplicationKey") String appKey);
}
