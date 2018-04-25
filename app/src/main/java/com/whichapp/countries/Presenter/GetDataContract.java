package com.whichapp.countries.Presenter;

import android.content.Context;

import com.whichapp.countries.Model.Country;

import java.util.List;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

public interface GetDataContract {
    interface View{
        void onGetDataSuccess(String message, List<Country> list);
        void onGetDataFailure(String message);
    }
    interface Presenter{
        void getDataFromURL(Context context, String url);
    }
    interface Interactor{
        void initRetrofitCall(Context context, String url);

    }
    interface onGetDataListener{
        void onSuccess(String message, List<Country> list);
        void onFailure(String message);
    }
}
