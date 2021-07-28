package com.wind.addressdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class editActivity extends Activity {
    private EditText shouhuoren;
    private EditText mobile;
    private EditText address;
    private Button save;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        shouhuoren=findViewById(R.id.et_shouhuoren);
        mobile=findViewById(R.id.et_Mobile);
        address=findViewById(R.id.et_xiangxidizhi);
        save=findViewById(R.id.btn_save);

        final Intent intent=getIntent();
        shouhuoren.setText(intent.getStringExtra("name"));
        mobile.setText(intent.getStringExtra("phone"));
        address.setText(intent.getStringExtra("address"));
        final int position=intent.getIntExtra("id",0);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rshouhuoren=shouhuoren.getText().toString().trim();
                String rmobile=mobile.getText().toString().trim();
                String raddress=address.getText().toString().trim();
                intent.putExtra("sshou",rshouhuoren);
                intent.putExtra("smobile",rmobile);
                intent.putExtra("saddress",raddress);
                intent.putExtra("id",position);
                setResult(1,intent);

                finish();

            }
        });

    }
}
