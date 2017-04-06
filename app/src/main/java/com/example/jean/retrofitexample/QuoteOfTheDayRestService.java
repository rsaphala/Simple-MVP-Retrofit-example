package com.example.jean.retrofitexample;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by rks on 4/6/17.
 */

public interface QuoteOfTheDayRestService {

    @GET("/qod.json")
    Call<ResponseBody> getQuoteOfTheDay();

}