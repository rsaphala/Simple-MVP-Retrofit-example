package com.example.jean.retrofitexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TestingActivity extends AppCompatActivity {

    private TextView textViewQuoteOfTheDay;
    private Button buttonRetry;

    private static final String TAG = "MainActivity";
    private QuoteOfTheDayRestService service;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewQuoteOfTheDay = (TextView) findViewById(R.id.text_view_quote);
        buttonRetry = (Button) findViewById(R.id.button_retry);
        buttonRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuoteOfTheDay();
            }
        });

        OkHttpClient client = new OkHttpClient();
        // client.interceptors().add(new LoggingInterceptor());
        retrofit = new Retrofit.Builder()
                .baseUrl(QuoteOfTheDayConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        service = retrofit.create(QuoteOfTheDayRestService.class);
        getQuoteOfTheDay();

    }


    private void getQuoteOfTheDay() {
        Call<ResponseBody> call =
                service.getQuoteOfTheDay();

        call.enqueue(new Callback<ResponseBody>() {

            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    try {
                        String json = response.body().string();
                        JSONObject jsonObject = new JSONObject(json);
                        JSONObject contents = jsonObject.getJSONObject("contents");
                        JSONArray quotes = contents.getJSONArray("quotes");
                        JSONObject firstElemet = quotes.getJSONObject(0);
                        String quote = firstElemet.getString("quote");
                        textViewQuoteOfTheDay.setText(quote);
                        Log.i("response: ", quote);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                //Transport level errors such as no internet etc.
            }
        });


    }

    private void showRetry(String error) {
        textViewQuoteOfTheDay.setText(error);
        buttonRetry.setVisibility(View.VISIBLE);

    }
}
