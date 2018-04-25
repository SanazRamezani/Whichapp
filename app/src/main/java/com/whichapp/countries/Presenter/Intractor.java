package com.whichapp.countries.Presenter;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.whichapp.countries.Model.AllCountriesResponse;
import com.whichapp.countries.Model.Country;
import com.whichapp.countries.Utility.Invarients;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

public class Intractor implements GetDataContract.Interactor{

    private GetDataContract.onGetDataListener mOnGetDataListener;
    List<Country> allcountry = new ArrayList<>();

    public Intractor(GetDataContract.onGetDataListener mOnGetDatalistener){
        this.mOnGetDataListener = mOnGetDatalistener;
    }

    @Override
    public void initRetrofitCall(Context context, String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Invarients.URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        AllCountriesResponse request = retrofit.create(AllCountriesResponse.class);
        Call<List<Country>> call = request.getCountry();
        call.enqueue(new retrofit2.Callback<List<Country>>() {
            @Override
            public void onResponse(retrofit2.Call<List<Country>> call, retrofit2.Response<List<Country>> response) {
                List<Country> jsonResponse = response.body();

                for(int i=0;i<jsonResponse.size();i++){
                    allcountry.add(jsonResponse.get(i));

                }

                Log.d("Data", "Refreshed");
                mOnGetDataListener.onSuccess("List Size: " + allcountry.size(), allcountry);



            }

            @Override
            public void onFailure(retrofit2.Call<List<Country>> call, Throwable t) {
                Log.d("Error",t.getMessage());
                mOnGetDataListener.onFailure(t.getMessage());
            }
        });
    }

}
