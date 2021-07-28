package com.wind.addressdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddActivity extends Activity {
    private TextView textView;
    private EditText shouhuoren;
    private EditText mobile;
    private EditText address;
    private Button save;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_activity);
        textView=findViewById(R.id.goods);
        textView.setText("新增收货地址");
        shouhuoren=findViewById(R.id.et_shouhuoren);
        mobile=findViewById(R.id.et_Mobile);
        address=findViewById(R.id.et_xiangxidizhi);
        save=findViewById(R.id.btn_save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRess();
            }
        });

    }

    private void addRess() {
        Intent intent=new Intent(this,MainActivity.class);
boolean flag=true;
        Bundle bundle=new Bundle();

        String name=shouhuoren.getText().toString().trim();
         bundle.putString("aname",name);
        String number=mobile.getText().toString().trim();
        bundle.putString("anumber",number);
        String maddress=address.getText().toString().trim();

        bundle.putString("aaddress",maddress);
        bundle.putBoolean("flag",flag);

        intent.putExtras(bundle);
        startActivity(intent);

    }
}
