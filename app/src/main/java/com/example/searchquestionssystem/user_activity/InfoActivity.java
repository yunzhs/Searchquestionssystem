package com.example.searchquestionssystem.user_activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.db.inventory;
import com.example.searchquestionssystem.db.student;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class InfoActivity extends AppCompatActivity {

    private List<inventory> quelist;
    private List<inventory> quelist1;
    private TextView textView,textView1;
    private String UserId;
    private Button backButton;
    private List<String> dataList = new ArrayList<>();
    private Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_);
        textView=(TextView)findViewById(R.id.into1);
        textView1=(TextView)findViewById(R.id.into2);
        homeButton=(Button)findViewById(R.id.button_info) ;
        UserId=getIntent().getStringExtra("UserId");

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoActivity.this,MainActivity.class);
                UserId=getIntent().getStringExtra("UserId");
                intent.putExtra("UserId",UserId);
                startActivity(intent);
            }
        });
        quelist = DataSupport.where("useid = ? and finished = ? ",UserId,"false").find(inventory.class);
        quelist1 = DataSupport.where("ansid = ? and finished = ? ",UserId,"false").find(inventory.class);
        for (inventory inven : quelist) {
            dataList.add(inven.getTitlename());
            textView.setText(String.valueOf(dataList.size()));
        }
        for (inventory inven : quelist1) {
            dataList.clear();
            dataList.add(inven.getTitlename());
            textView1.setText(String.valueOf(dataList.size()));
        }
    }
}