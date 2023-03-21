package com.example.xml_parser_test_withretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContextAPI {

    @GET("getCountryBasicList")
    Call<dataOPOJ> getData(
            @Query("serviceKey") String serviceKey,
            @Query("numOfRows") String numOfRows,
            @Query("pageNo") String pageNo,
            @Query("countryName") String countryName
    );

}
