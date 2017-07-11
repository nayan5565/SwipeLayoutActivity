package com.example.nayan.swiperefresh;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Nayan on 7/11/2017.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtPass, edtUser;
    String pass, user;
    Button login, btnData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        edtPass = (EditText) findViewById(R.id.pass);
        edtUser = (EditText) findViewById(R.id.userName);
        login = (Button) findViewById(R.id.btn);
        btnData = (Button) findViewById(R.id.btn2);
        login.setOnClickListener(this);
        btnData.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btn){
            user = edtUser.getText().toString().trim();
            pass = edtPass.getText().toString().trim();

            if (user.equals("nayan") && pass.equals("555965")) {
                Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
            }
        }

        else if (v.getId()==R.id.btn2){
            user = edtUser.getText().toString().trim();
            pass = edtPass.getText().toString().trim();

            if (user.equals("nayan") && pass.equals("555965")) {
                Toast.makeText(this, "correct", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginActivity.this, DataListActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
            }
        }

    }
}
