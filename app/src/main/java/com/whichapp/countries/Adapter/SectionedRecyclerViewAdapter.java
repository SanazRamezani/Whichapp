package com.whichapp.countries.Adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.whichapp.countries.Model.Country;
import com.whichapp.countries.Model.SectionModel;
import com.whichapp.countries.R;
import com.whichapp.countries.Utility.Invarients;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Sa.Ramezani on 24/04/2018.
 */

public class SectionedRecyclerViewAdapter extends RecyclerView.Adapter<SectionedRecyclerViewAdapter.SectionViewHolder> {

    class SectionViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.section_header_label) TextView sectionHeaderLabel;
        @BindView(R.id.section_footer_label) TextView sectionFooterLabel;
        @BindView(R.id.item_recycler_view) RecyclerView itemRecyclerView;

        public SectionViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    private Context context;
    private SectionModel sectionModelArrayList;
    private List<Country> list = new ArrayList<>();

    public SectionedRecyclerViewAdapter(Context context, SectionModel sectionModelArrayList) {
        this.context = context;
        this.sectionModelArrayList = sectionModelArrayList;
        this.list = sectionModelArrayList.getCountryList();
    }

    @Override
    public SectionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.section_custom_row_layout, parent, false);
        return new SectionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SectionViewHolder holder, int position) {

        final SectionModel sectionModel = sectionModelArrayList;
        holder.sectionHeaderLabel.setText(sectionModel.getSectionHeaderLabel());
        holder.sectionFooterLabel.setText(sectionModel.getSectionFooterLabel());

        //recycler view for items
        holder.itemRecyclerView.setHasFixedSize(true);
        holder.itemRecyclerView.setNestedScrollingEnabled(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.itemRecyclerView.setLayoutManager(linearLayoutManager);

        final CountryAdapter adapter = new CountryAdapter(context, list);
        holder.itemRecyclerView.setAdapter(adapter);
        if(list.size() > 0){
            adapter.setCount(Invarients.RECYCLER_COUNT_LIMIT);
        }

        //show See more..
        holder.sectionFooterLabel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapter.getItemCount()+ Invarients.RECYCLER_COUNT_LIMIT >= list.size()){
                    adapter.setCount(list.size());
                } else {
                    adapter.setCount(adapter.getItemCount()+Invarients.RECYCLER_COUNT_LIMIT);
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public void showItems(List<Country> countryList) {
        this.list = countryList;
        notifyDataSetChanged();
    }

}
