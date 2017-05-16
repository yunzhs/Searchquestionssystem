package com.example.searchquestionssystem;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import static com.example.searchquestionssystem.R.layout.que_fragment;


public class QueFragment extends Fragment{


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(que_fragment,container,false);
        SimpleDateFormat formatter    =new SimpleDateFormat("yyyy年MM月dd日    HH:mm:ss ");
        Date curDate    =   new    Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);
        Button button=(Button)view.findViewById(R.id.myque) ;
        button.setOnClickListener(new View.OnClickListener(){
            public void  onClick(View v){
                Intent intent = new Intent(getActivity(), MyqueActivity.class);
                startActivity(intent);
            }
        });
       /* TextView th=(TextView)view.findViewById(R.id.textView4);
        th.setText(str);*/
        return view;
    }
}
