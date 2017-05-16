package com.example.searchquestionssystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class MyqueActivity extends AppCompatActivity {

    private inventory inven;
    private String t;
    Spinner spinner;
    EditText edtitlel,edcontent;
    Button back_btn,submit_btn;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myque);
        edtitlel=(EditText)findViewById(R.id.edit_title) ;
        edcontent=(EditText)findViewById(R.id.edit_content) ;
        back_btn=(Button) findViewById(R.id.button_home);
        submit_btn=(Button) findViewById(R.id.submit);
        spinner = (Spinner) findViewById(R.id.spinner);

        back_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyqueActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        submit_btn.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v) {
                if(edtitlel.getText().toString().length()==0) {
                    new AlertDialog.Builder(v.getContext())
                            .setTitle("警告")
                            .setMessage("请输入标题")
                            .setPositiveButton("确定", null)
                            .show();
                }
                else {
                    new  AlertDialog.Builder(v.getContext())
                        .setTitle("确认" )
                        .setMessage("确定吗？")
                        .setPositiveButton("是" , new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                inven=new inventory();
                                inven.setTitlename(edtitlel.getText().toString());
                                inven.setContent(edcontent.getText().toString());
                                inven.setType(t);
                                inven.setFinished("false");
                                inven.save();
                                new AlertDialog.Builder(MyqueActivity.this)
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

        String[] mItems = getResources().getStringArray(R.array.type);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.spiner_text_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] type = getResources().getStringArray(R.array.type);
                t=type[pos];
                //inven.setType(type[pos]);
                /*Toast.makeText(MyqueActivity.this, "你点击的是:"+languages[pos], 2000).show();*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

    }


}

