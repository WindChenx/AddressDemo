package com.wind.pageselect;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView pager_left;
    private ImageView pager_right;
    private TextView textView;
    private int pager_normal=R.drawable.normal;
    private int suo=R.drawable.suo;
    private ArrayList<Button> buttons;
    private int[] numbers=new int[20];
    private LinearLayout pager_group;
    private int length=20;
    int temp=4;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private TextView button8;
    private Button button9;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pager_left=findViewById(R.id.page_left);
        pager_right=findViewById(R.id.pager_right);
        button1=findViewById(R.id.button1);
        button2=findViewById(R.id.button2);
        button3=findViewById(R.id.button3);
        button4=findViewById(R.id.button4);
        button5=findViewById(R.id.button5);
        button6=findViewById(R.id.button6);
        button7=findViewById(R.id.button7);
        button8=findViewById(R.id.button8);
        button9=findViewById(R.id.button9);
        textView=findViewById(R.id.my_text);
        buttons=new ArrayList<>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);
        buttons.add(button5);
        buttons.add(button6);
        buttons.add(button7);
        button1.setBackgroundResource(R.color.pressed);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("当前页码为："+1);


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("当前页码为："+2);
                button2.setBackgroundResource(R.color.pressed);
                button1.setBackgroundResource(R.drawable.normal);
                button3.setBackgroundResource(R.drawable.normal);
                button4.setBackgroundResource(R.drawable.normal);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("当前页码为："+3);
                button3.setBackgroundResource(R.color.pressed);
                button1.setBackgroundResource(R.drawable.normal);
                button2.setBackgroundResource(R.drawable.normal);
                button4.setBackgroundResource(R.drawable.normal);

            }
        });

        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView.setText("当前页码为："+4);
                button4.setBackgroundResource(R.color.pressed);
                button1.setBackgroundResource(R.drawable.normal);
                button2.setBackgroundResource(R.drawable.normal);
                button3.setBackgroundResource(R.drawable.normal);

            }
        });


        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                button2.setBackgroundResource(suo);
                button5.setBackgroundResource(R.color.pressed);
                button4.setBackgroundResource(R.drawable.normal);
                buttons.get(1).setText("");
                temp++;
                update();


            }
        });


        pager_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp--;
                update();
            }
        });

        pager_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp++;
                update();
            }
        });






    }







    private void update() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        int m=temp;
                        for(int i=2;i<7;i++) {
                            buttons.get(i).setText(String.valueOf(m - 2));
                            int te=m-4;
                            textView.setText("当前页码为："+te);
                            m++;


                        }


                    }
                });
            }
        }).start();

    }






}
