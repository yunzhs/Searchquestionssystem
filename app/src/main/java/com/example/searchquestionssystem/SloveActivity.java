package com.example.searchquestionssystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.litepal.crud.DataSupport;

import java.util.List;

public class SloveActivity extends AppCompatActivity {

    private Button homeButton,btn_slove;
    private TextView quetitle;
    private TextView quecontent;
    private EditText editslove;
    private List<inventory> quelist;
    private String queId;
    private String quename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slove);
        homeButton=(Button)findViewById(R.id.button_home_s);
        btn_slove=(Button)findViewById(R.id.btn_slove);
        quetitle=(TextView)findViewById(R.id.title_que_s);
        quecontent=(TextView)findViewById(R.id.content_que_s);
        editslove=(EditText)findViewById(R.id.edit_solve) ;

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SloveActivity.this,MainActivity.class);
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

        }
        btn_slove.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                if(editslove.getText().toString().length()==0) {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("警告")
                            .setMessage("请输入内容")
                            .setPositiveButton("确定", null)
                            .show();
                }
                else {
                    new  AlertDialog.Builder(v.getContext())
                            .setTitle("确认" )
                            .setMessage("确定吗？")
                            .setPositiveButton("是" ,  new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    for (inventory inven : quelist) {
                                    inven.setAnswer(editslove.getText().toString());
                                    inven.setFinished("true");
                                    inven.save();
                                    }
                                    //提交回答的相关监听

                                    new AlertDialog.Builder(SloveActivity.this)
                                            .setTitle("")
                                            .setMessage("提交成功")
                                            .setPositiveButton("确定", null)
                                            .show();
                                }
                            })
                            .setNegativeButton("否" , null)
                            .show();

                }
            }
        });


    }
}