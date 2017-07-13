package com.example.nayan.swiperefresh.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nayan.swiperefresh.adapter.AdapterList;
import com.example.nayan.swiperefresh.model.MList;
import com.example.nayan.swiperefresh.R;
import com.google.gson.Gson;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    RecyclerView listView;
    ArrayList<MList> list;
    AdapterList adapterList;
    private MList mList;
    Gson gson;
    private int size = 0;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Only ever call `setContentView` once right at the top
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.lvItems);
        gson = new Gson();
        list = new ArrayList();
        mList = new MList();
        adapterList = new AdapterList(this);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapterList);

//        display();
//        adapterList.addAll(list);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync();

                swipeContainer.setRefreshing(false);
//                mList = new MList();
//                mList.setTitle("c");
//                list.add(mList);


            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void fetchTimelineAsync() {
        Log.e("step", " one");
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        size = list.size();
        AsyncHttpClient client = new AsyncHttpClient();
        Log.e("step", " two");
        client.post("http://api.androidhive.info/json/imdb_top_250.php?offset=" + size, new JsonHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);

                MList[] movieList = gson.fromJson(response.toString(), MList[].class);

                ArrayList<MList> movies=new ArrayList<MList>(Arrays.asList(movieList));
               for (int i=0;i<movies.size();i++){
                   list.add(movies.get(i));
               }

                adapterList.addAll(list);
                Log.e("step", " one" + response);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                Log.e("step", " four");
            }
        });
    }

//    private void display() {
//        mList = new MList();
//        mList.setTitle("a");
//        list.add(mList);
//        mList = new MList();
//        mList.setTitle("b");
//        list.add(mList);
//
//        listView.setLayoutManager(new LinearLayoutManager(this));
//        listView.setAdapter(adapterList);
//
//    }


}
