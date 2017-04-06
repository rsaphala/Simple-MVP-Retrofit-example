package com.example.jean.retrofitexample.Presenter;

import android.content.Context;
import android.util.Log;

import com.example.jean.retrofitexample.Model.RestResponses;
import com.example.jean.retrofitexample.Service.CountryService;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jean on 29/07/16.
 */

public class CountryPresenter {
    private final Context context;
    private final CountryPresenterListener mListener;
    private final CountryService countryService;

    public interface CountryPresenterListener{
        void countriesReady(RestResponses restResponses);
    }

    public CountryPresenter(CountryPresenterListener listener, Context context){
        this.mListener = listener;
        this.context = context;
        this.countryService = new CountryService();
    }

    public void getCountries(){
        countryService
                .getAPI()
                .getResults()
                .enqueue(new Callback<RestResponses>() {
                    @Override
                    public void onResponse(Call<RestResponses> call, Response<RestResponses> response) {
                        if (response.isSuccessful()) {
                            RestResponses restResponses = response.body();
                            mListener.countriesReady(restResponses);
                        }
                    }

                    @Override
                    public void onFailure(Call<RestResponses> call, Throwable t) {

                    }
                });
    }
}
