package com.example.jean.retrofitexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.example.jean.retrofitexample.Model.RestResponses;
import com.example.jean.retrofitexample.Presenter.CountryPresenter;

public class MainActivity extends AppCompatActivity implements CountryPresenter.CountryPresenterListener {

    private CountryPresenter countryPresenter;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button_retry);
        countryPresenter = new CountryPresenter(this, this);
        countryPresenter.getCountries();
    }

    @Override
    public void countriesReady(RestResponses restResponses) {
        Log.i("Message = ", restResponses.RestResponse.messages.get(0));
    }

    public void setButtonText() {
        button.setText("HAHAHA");
    }
}
