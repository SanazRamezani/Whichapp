package com.whichapp.countries.Model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

public interface AllCountriesResponse {
    @GET("countries")
    Call<List<Country>> getCountry();
}
