package com.example.gryphus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Random;

public class SearchFragment extends Fragment {
    private ArrayList<Filter> filterList;
    private RecyclerView recyclerView;
    private Random rand = new Random();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_productrecycler, container,false);
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recycler);
        filterList = new ArrayList<>();
        setFilterInfo();
        setAdapter();


    }

    private void setAdapter() {
        FilterAdapter adapter = new FilterAdapter(filterList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setFilterInfo() {
        filterList.add(new Filter("CPU", getContext().getDrawable(R.drawable.ic_settings)));
        filterList.add(new Filter("HHD", getContext().getDrawable(R.drawable.ic_search)));
        filterList.add(new Filter("REM", getContext().getDrawable(R.drawable.ic_home)));
    }


}
