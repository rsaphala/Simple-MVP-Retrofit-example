package com.example.jean.retrofitexample;

import android.widget.Button;

import com.example.jean.retrofitexample.Model.RestResponses;
import com.example.jean.retrofitexample.Service.CountryService;

import org.junit.Test;

import java.io.IOException;

import retrofit2.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void testRetrofit() {
        CountryService countryService = new CountryService();
        try {
            Response<RestResponses> response = countryService
                    .getAPI()
                    .getResults()
                    .execute();
            RestResponses restResponses = response.body();
            assertTrue(response.isSuccessful() && restResponses.RestResponse.messages.size() == (2));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}