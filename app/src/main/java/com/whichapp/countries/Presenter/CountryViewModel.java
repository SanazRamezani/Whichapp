package com.whichapp.countries.Presenter;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;

import com.whichapp.countries.Database.AppDatabase;
import com.whichapp.countries.Model.Country;

import java.util.List;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

public class CountryViewModel extends AndroidViewModel {

    private AppDatabase appDatabase;
    private final LiveData<List<Country>> countryList;

    public CountryViewModel(Application application) {
        super(application);

        appDatabase = AppDatabase.getDatabase(this.getApplication());

        countryList = appDatabase.countryModel().getAllCountryItems();
    }

    public void saveCountry(final List<Country> countryModel) {
        new saveAsyncTask(appDatabase).execute(countryModel);
    }

    public LiveData<List<Country>> getCountryList() {
        return countryList;
    }

    //Saving to database (Preventing Duplication)
    private static class saveAsyncTask extends AsyncTask<List<Country>, Void, Void> {

        private AppDatabase db;

        saveAsyncTask(AppDatabase appDatabase) {
            db = appDatabase;
        }

        @Override
        protected Void doInBackground(final List<Country>... params) {
            for(Country country : params[0]){
                if(db.countryModel().getItemByIso(country.getIso()) == 0){
                    db.countryModel().addCountry(country);
                }
            }

            return null;
        }

    }
}