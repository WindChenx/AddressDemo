package com.wind.addressdemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView add_address;
    private Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add_address=findViewById(R.id.add_address);

        replaceFragment();
        add_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addaddress();
            }
        });
    }

    private void addaddress() {
        Intent intent=new Intent(this,AddActivity.class);
        startActivity(intent);
    }

    private void replaceFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.address, new MainFragment());
        transaction.commit();
    }





}
