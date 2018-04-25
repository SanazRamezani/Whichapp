package com.whichapp.countries.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

public class SectionModel {
    private String sectionHeaderLabel;
    private String sectionFooterLabel;
    private List<Country> countryList;

    public SectionModel(String sectionHeaderLabel, String sectionFooterLabel, List<Country> countryList) {
        this.sectionHeaderLabel = sectionHeaderLabel;
        this.sectionFooterLabel = sectionFooterLabel;
        this.countryList = countryList;
    }

    public String getSectionHeaderLabel() {
        return sectionHeaderLabel;
    }

    public void setSectionHeaderLabel(String sectionHeaderLabel) {
        this.sectionHeaderLabel = sectionHeaderLabel;
    }

    public String getSectionFooterLabel() {
        return sectionFooterLabel;
    }

    public void setSectionFooterLabel(String sectionFooterLabel) {
        this.sectionFooterLabel = sectionFooterLabel;
    }

    public List<Country> getCountryList() {
        return countryList;
    }

    public void setCountryList(List<Country> countryList) {
        this.countryList = countryList;
    }
}
