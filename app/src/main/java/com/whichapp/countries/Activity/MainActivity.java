package com.whichapp.countries.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.whichapp.countries.Adapter.CountryAdapter;
import com.whichapp.countries.Adapter.SectionedRecyclerViewAdapter;
import com.whichapp.countries.Model.Country;
import com.whichapp.countries.Model.SectionModel;
import com.whichapp.countries.Presenter.GetDataContract;
import com.whichapp.countries.Presenter.Presenter;
import com.whichapp.countries.Presenter.CountryViewModel;
import com.whichapp.countries.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements GetDataContract.View {

    @BindView(R.id.recycler)
    RecyclerView recyclerView;

    //Header Section
    @BindString(R.string.section_header_label)
    String sectionHeader;

    //Footer Section
    @BindString(R.string.section_footer_label)
    String sectionFooter;

    private Presenter presenter;
    private LinearLayoutManager linearLayoutManager;
    private SectionedRecyclerViewAdapter adapter;
    private CountryViewModel countryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


        presenter = new Presenter(this);
        presenter.getDataFromURL(getApplicationContext(), "");
        countryViewModel = ViewModelProviders.of(this).get(CountryViewModel.class);

        setUpRecyclerView();
        populateRecyclerView();

        
        countryViewModel.getCountryList().observe(MainActivity.this, new Observer<List<Country>>() {
            @Override
            public void onChanged(@Nullable List<Country> countryList) {
                //reading data from database to show in recyclerview
                adapter.showItems(countryList);
            }
        });
    }

    
    //setup recycler view
    private void setUpRecyclerView() {

        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void populateRecyclerView(){
        adapter = new SectionedRecyclerViewAdapter(this,  new SectionModel(sectionHeader, sectionFooter, new ArrayList<Country>()));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onGetDataSuccess(String message, List<Country> allCountriesData) {
        //save data in database
        countryViewModel.saveCountry(allCountriesData);
    }

    @Override
    public void onGetDataFailure(String message) {
        Log.d("Status", message);
    }

}
