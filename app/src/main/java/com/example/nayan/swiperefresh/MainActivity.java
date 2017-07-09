package com.example.nayan.swiperefresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {
    RecyclerView listView;
    ArrayList<MList> list;
    AdapterList adapterList;
    private MList mList;

    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Only ever call `setContentView` once right at the top
        setContentView(R.layout.activity_main);
        listView = (RecyclerView) findViewById(R.id.lvItems);

        list = new ArrayList();
        mList = new MList();
        adapterList = new AdapterList(this);
        display();
        adapterList.addAll(list);
        // Lookup the swipe container view
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchTimelineAsync(0);

                swipeContainer.setRefreshing(false);
                mList=new MList();
                mList.setTeam("c");
                list.add(mList);

                adapterList.addAll(list);

            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
    }

    public void fetchTimelineAsync(int page) {
        // Send the network request to fetch the updated data
        // `client` here is an instance of Android Async HTTP
        // getHomeTimeline is an example endpoint.
        AsyncHttpClient client = new AsyncHttpClient();
        client.post("", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                adapterList.addAll(list);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }

    private void display() {
        mList = new MList();
        mList.setTeam("a");
        list.add(mList);
        mList = new MList();
        mList.setTeam("b");
        list.add(mList);

        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapterList);

    }


}
