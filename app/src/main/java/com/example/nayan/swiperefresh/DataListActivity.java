package com.example.nayan.swiperefresh;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Nayan on 7/12/2017.
 */
public class DataListActivity extends AppCompatActivity implements View.OnClickListener {
    DatabaseHelper db;
    TextView txt, txt_lst;
    Button btn_add, btn_update, btn_delete, btn_show;
    EditText edt_id, edt_name;
    MList mList;
    RecyclerView recyclerView;
    ArrayList<MList> mLists;
    AdapterList adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.data_list);
        init();
        prepareDisplay();
//        getData();
    }

    private void init() {
        adapterList = new AdapterList(this);
        mLists = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.rec);
        mList = new MList();
        db = new DatabaseHelper(DataListActivity.this);

        txt = (TextView) findViewById(R.id.txt_op);

        txt_lst = (TextView) findViewById(R.id.txt_list);

        edt_id = (EditText) findViewById(R.id.edt_id);

        edt_name = (EditText) findViewById(R.id.edt_name);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_add.setOnClickListener(this);

        btn_update = (Button) findViewById(R.id.btn_update);
        btn_update.setOnClickListener(this);

        btn_delete = (Button) findViewById(R.id.btn_delete);
        btn_delete.setOnClickListener(this);

        btn_show = (Button) findViewById(R.id.btn_show);
        btn_show.setOnClickListener(this);
//        getData();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_add) {
            addContact();

        } else if (v.getId() == R.id.btn_update) {
            updateContact();


        } else if (v.getId() == R.id.btn_delete) {
            deleteContact();

        } else if (v.getId() == R.id.btn_show) {
            getData();

        }

    }

    private void addContact() {
        long result;
        String value = edt_id.getText().toString();
        int id = Integer.valueOf(value);
        String name = edt_name.getText().toString();
//        result = db.addContuct(id, name, DatabaseHelper.TABLE_FRIENDS);
        mList.setRank(id);
        mList.setTitle(name);

        db.addContuctToList(mList, DatabaseHelper.TABLE_FRIENDS);


//        if (result < 0) {
//            Toast.makeText(DataListActivity.this, "error " + result, Toast.LENGTH_SHORT).show();
//
//        } else {
//            Toast.makeText(DataListActivity.this, "success", Toast.LENGTH_SHORT).show();
//            getData();
//        }
    }

    private void updateContact() {
        long result;
        String value = edt_id.getText().toString();
        int id = Integer.valueOf(value);
        String name = edt_name.getText().toString();
        result = db.updateContuct(id, name);
        if (result < 0) {
            Toast.makeText(DataListActivity.this, "error", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(DataListActivity.this, "succesfully added", Toast.LENGTH_SHORT).show();
            getData();
        }
    }

    private void deleteContact() {
        int result;
        String val = edt_id.getText().toString();
        int id = Integer.valueOf(val);
        result = db.deleteCotact(id);
        if (result > 0) {
            Toast.makeText(DataListActivity.this, "deleted", Toast.LENGTH_SHORT).show();
            getData();
        } else {
            Toast.makeText(DataListActivity.this, "did not found data", Toast.LENGTH_SHORT).show();

        }
    }


    private void getData() {
//        txt_lst.setText(db.getContuct());
        mLists=db.getContuctFromList();
        adapterList.addAll(mLists);

    }

    private void prepareDisplay() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapterList);
    }
}
