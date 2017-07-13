package com.example.nayan.swiperefresh.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.nayan.swiperefresh.R;
import com.example.nayan.swiperefresh.adapter.AdapterList;
import com.example.nayan.swiperefresh.model.MList;
import com.example.nayan.swiperefresh.utils.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Nayan on 7/13/2017.
 */
public class GetDataFromDatabaseActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private DatabaseHelper db;
    private AdapterList adapterList;
    private ArrayList<MList> listArrayList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.get_data_activity);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        db = new DatabaseHelper(this);
        listArrayList = new ArrayList<>();
        adapterList = new AdapterList(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listArrayList = db.getContuctFromList();
        adapterList.addAll(listArrayList);
        recyclerView.setAdapter(adapterList);

    }
}
