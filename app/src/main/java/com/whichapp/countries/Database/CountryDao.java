package com.whichapp.countries.Database;

import android.arch.persistence.room.Dao;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.whichapp.countries.Model.Country;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

@Dao
public interface CountryDao {

    @Query("select * from Country")
    LiveData<List<Country>> getAllCountryItems();

    @Query("select Count(iso) from Country where iso = :iso")
    int getItemByIso(String iso);

    @Insert(onConflict = REPLACE)
    void addCountry(Country countryModel);



}
