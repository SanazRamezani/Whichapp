package com.whichapp.countries.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.whichapp.countries.Model.Country;
import com.whichapp.countries.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.MyViewHolder> {
    private Context context;
    private List<Country> list = new ArrayList<>();
    private int count;

    public CountryAdapter(Context context, List<Country> list){
        this.context = context;
        this.list = list;
    }
    @Override
    public CountryAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.card_item, parent, false));
    }

    @Override
    public void onBindViewHolder(CountryAdapter.MyViewHolder holder, int position) {
        if(position < list.size()){
            Country country = list.get(position);
            holder.tvCountryName.setText(country.getIso()+" : "+country.getName());
            holder.tvCountryPhone.setText("+"+country.getPhone());
        }

    }

    @Override
    public int getItemCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        notifyDataSetChanged();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_country_name) TextView tvCountryName;
        @BindView(R.id.tv_country_phone) TextView tvCountryPhone;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

}