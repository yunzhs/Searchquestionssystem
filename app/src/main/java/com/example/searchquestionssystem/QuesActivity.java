package com.example.searchquestionssystem;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class QuesActivity extends AppCompatActivity {

    private Button homeButton;
    private TextView quetitle;
    private TextView quecontent;
    private TextView queans;
    private List<inventory> quelist;
    private String queId;
    private String quename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ques);
        homeButton=(Button)findViewById(R.id.button_home);
        quetitle=(TextView)findViewById(R.id.title_que);
        quecontent=(TextView)findViewById(R.id.content_que);
        queans=(TextView)findViewById(R.id.ans_que);
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(QuesActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        quename = getIntent().getStringExtra("queName");
        quelist = DataSupport.where("titlename = ?",quename).find(inventory.class);
        for (inventory inven : quelist) {
            quetitle.setText(inven.getTitlename());
            quetitle.setTextSize(18);
            quecontent.setText(inven.getContent());
            quecontent.setTextSize(18);
            queans.setText(inven.getAnswer());
            queans.setTextSize(18);

        }


    }
}
