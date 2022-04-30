package com.example.gryphus;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import java.util.ArrayList;
import java.util.Random;

public class ProductFragment extends Fragment {

    public static ArrayList<Product> productList;
    private RecyclerView recyclerView;
    private Random rand = new Random();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_productrecycler, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = getView().findViewById(R.id.recycler);
        productList = new ArrayList<>();
        setProductInfo();
        setAdapter();
    }

    private void setAdapter() {
        productAdapter adapter = new productAdapter(productList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setProductInfo() {
        productList.add(new Product("Test Product 1", getContext().getDrawable(R.drawable.ic_settings), 1, true));
        productList.add(new Product("Test Product 2", getContext().getDrawable(R.drawable.ic_settings),2, true));
        productList.add(new Product("Test Product 3", getContext().getDrawable(R.drawable.ic_settings),3, false));
        productList.add(new Product("Test Product 4", getContext().getDrawable(R.drawable.ic_settings),4, false));
        productList.add(new Product("Test Product 5", getContext().getDrawable(R.drawable.ic_settings),5, true));
        productList.add(new Product("Test Product 6", getContext().getDrawable(R.drawable.ic_aboutus),6, true));
        productList.add(new Product("Test Product 7", getContext().getDrawable(R.drawable.ic_home),7, false));
        productList.add(new Product("Test Product 8", getContext().getDrawable(R.drawable.ic_search),8, false));
        productList.add(new Product("Test Product 9", getContext().getDrawable(R.drawable.ic_login),9, true));
        productList.add(new Product("Test Product 10",getContext().getDrawable(R.drawable.ic_settings) ,10, true));
        productList.add(new Product("Test Product 11",getContext().getDrawable(R.drawable.ic_settings) ,11, true));
        productList.add(new Product("Test Product 12",getContext().getDrawable(R.drawable.ic_settings) ,12, false));
        productList.add(new Product("Test Product 13",getContext().getDrawable(R.drawable.ic_settings) ,13, true));
        productList.add(new Product("Test Product 14",getContext().getDrawable(R.drawable.ic_settings) ,14, true));
        productList.add(new Product("Test Product 15",getContext().getDrawable(R.drawable.ic_settings) ,15, true));
        productList.add(new Product("Test Product 16",getContext().getDrawable(R.drawable.ic_settings) ,16, true));
    }

    private void setProductID() {
        for (Product products:
             productList) {
            int id = 0;
            for (int i = 0; i < 6; i++) {
                id += rand.nextInt(8)+1;
                id *= 10;
            }
            products.setItemID(id);
        }
    }
}