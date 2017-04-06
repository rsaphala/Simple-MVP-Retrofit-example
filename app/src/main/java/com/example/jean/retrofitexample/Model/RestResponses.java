package com.example.jean.retrofitexample.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jean on 29/07/16.
 */

public class RestResponses implements Serializable{
    public RestResponse RestResponse;

    public class RestResponse {
        public List<String> messages;
        public List<Country> result;
        public List<String> getMessages(){
            return messages;
        }

        public class Country {
            private String name;

            private String alpha2_code;

            private String alpha3_code;


            public String getName() {
                return name;
            }

            public String getAlphaCode2() {
                return alpha2_code;
            }

            public String getAlphaCode3() {
                return alpha3_code;
            }
        }
        public List<Country> getResult(){
            return result;
        }
    }
}