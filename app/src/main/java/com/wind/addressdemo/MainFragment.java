package com.wind.addressdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainFragment extends BaseFragment {

    private SlideListView address_pager;
    protected ArrayList<Person> persons=new ArrayList<>();
    private int id;


    @Override
    public View initView() {
//       View addressView=LayoutInflater.from(context).inflate(R.layout.address_pager,null);
            View      addressView=View.inflate(context,R.layout.address_pager,null);
        address_pager=addressView.findViewById(R.id.address_pager);


        return addressView;

    }

    private Handler handler=new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(persons!=null&&persons.size()>0){
                address_pager.setAdapter(new AddressAdapter());
                addAddress();

            }
        }
    };

    class AddressAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return persons.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder;
            if(convertView==null){
//                convertView=LayoutInflater.from(context).inflate(R.layout.address_pager_item,null);
                convertView=View.inflate(context,R.layout.address_pager_item,null);
                viewHolder=new ViewHolder();
                viewHolder.tv_name=convertView.findViewById(R.id.tv_name);
                viewHolder.tv_phone=convertView.findViewById(R.id.tv_phone);
                viewHolder.tv_address=convertView.findViewById(R.id.address);
                viewHolder.right_menu=convertView.findViewById(R.id.item_right_menu);
                viewHolder.edit_address=convertView.findViewById(R.id.edit);
                convertView.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) convertView.getTag();
            }
            final Person person=persons.get(position);
            viewHolder.tv_name.setText(person.getName());
            viewHolder.tv_phone.setText(person.getPhone());
            viewHolder.tv_address.setText(person.getAddress());

            viewHolder.right_menu.getChildAt(0).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   Person temp= persons.get(position);
                   persons.remove(position);
                   persons.add(0,temp);
                   notifyDataSetChanged();
                   address_pager.turnToNormal();

                }
            });
            viewHolder.right_menu.getChildAt(1).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    persons.remove(position);
                    notifyDataSetChanged();
                    address_pager.turnToNormal();
                }
            });
            viewHolder.edit_address.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    id=position;
                    Intent intent=new Intent(getActivity(),editActivity.class);
                    Bundle bundle=new Bundle();
                    bundle.putInt("id",position);
                    bundle.putString("name",persons.get(position).getName());
                    bundle.putString("phone",persons.get(position).getPhone());
                    bundle.putString("address",persons.get(position).getAddress());
                    intent.putExtras(bundle);
                    startActivityForResult(intent,1);


                }
            });




            return convertView;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){

           persons.get(id).setName(data.getStringExtra("sshou"));
           persons.get(id).setPhone(data.getStringExtra("smobile"));
           persons.get(id).setAddress(data.getStringExtra("saddress"));


        }
    }

    @Override
    public void initData() {
        new Thread(){
            @Override
            public void run() {
                super.run();


                DBHelper helper=new DBHelper(context,"test.db",null,1);

                SQLiteDatabase db=helper.getWritableDatabase();



                Cursor cursor=db.rawQuery("select * from address",null);
                if(cursor!=null){
                    while (cursor.moveToNext()){
                        Person person=new Person();
                        int id=cursor.getInt(0);
                        person.setId(id);
                        String name=cursor.getString(1);
                        person.setName(name);
                        String phone=cursor.getString(2);
                        person.setPhone(phone);
                        String address=cursor.getString(3);
                        person.setAddress(address);
                        persons.add(person);
                    }
                    cursor.close();
                }
                handler.sendEmptyMessage(0);


            }
        }.start();


    }

    private void addAddress() {
        Intent intent=getActivity().getIntent();
        boolean flag=intent.getBooleanExtra("flag",false);
        Person person1=new Person();
        String aname=intent.getStringExtra("aname");
        person1.setName(aname);
        String anumber=intent.getStringExtra("anumber");
        person1.setPhone(anumber);
        String aaddress=intent.getStringExtra("aaddress");
        person1.setAddress(aaddress);
        if(flag){
            persons.add(person1);
        }else {

        }

    }

    static class ViewHolder{
        TextView tv_name;
        TextView tv_phone;
        TextView tv_address;
        ViewGroup right_menu;
        ImageView edit_address;
    }
}
