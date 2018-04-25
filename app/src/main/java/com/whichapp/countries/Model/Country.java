package com.whichapp.countries.Model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.util.Date;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

@Entity
public class Country {
    @PrimaryKey(autoGenerate = true)
    public int id;
    private String iso;
    private String name;
    private String phone;

    public Country(String iso, String name, String phone) {
        this.iso = iso;
        this.name = name;
        this.phone = phone;
    }

    public String getIso() {
        return iso;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

}
