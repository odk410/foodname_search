package com.example.odk41.foodname_search;

import com.example.odk41.foodname_search.pojo.Item;
import com.example.odk41.foodname_search.pojo.Repo;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by odk41 on 2018-02-26.
 */

public interface ApiService {
    //베이스 URL
    static final String BASEURL = "https://apis.eatsight.com/foodinfo/1.0/ ";
//    static final String APPKEY = YelpToken;

    //get 메소드를 통한 http rest api 통신
    @GET("foods")
    Call<Repo>getInfo(@Query("DS-ApplicationKey")String appKey, @Query("foodType") String foodType,
                      @Query("searchField") String searchField, @Query("searchValue")String searchValue, @Query("offset") int offset, @Query("limit") int limit);
}
