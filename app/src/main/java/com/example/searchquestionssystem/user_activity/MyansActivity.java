package com.example.searchquestionssystem.user_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.db.inventory;

import org.litepal.crud.DataSupport;

import java.util.List;

public class MyansActivity extends AppCompatActivity {

    private Button homeButton;
    private TextView quetitle;
    private TextView quecontent;
    private TextView ans1,ans2;
    private LinearLayout l1;
    private EditText editslove;
    private List<inventory> quelist;
    private String queId;
    private String UserId;
    private String titlename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myans);
        homeButton=(Button)findViewById(R.id.button_home_a) ;
        quetitle=(TextView)findViewById(R.id.title_ans);
        quecontent=(TextView)findViewById(R.id.content_ans);
        l1=(LinearLayout)findViewById(R.id.ansl);
        ans1=(TextView)findViewById(R.id.ans_ans1);
        ans2=(TextView)findViewById(R.id.ans_ans2);

        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyansActivity.this,MainActivity.class);
                UserId=getIntent().getStringExtra("UserId");
                intent.putExtra("UserId",UserId);
                startActivity(intent);
            }
        });
        titlename = getIntent().getStringExtra("queName");
        quelist = DataSupport.where("titlename = ? ",titlename).find(inventory.class);
        for (inventory inven : quelist) {
            quetitle.setText(inven.getTitlename());
            quetitle.setTextSize(18);
            quecontent.setText(inven.getContent());
            quecontent.setTextSize(18);
            ans1.setText(inven.getAnswer());
            ans1.setTextSize(18);
            if (inven.getAnswer1()==null){
                l1.setVisibility(View.GONE);
            }
            else {ans2.setText(inven.getAnswer1());
            ans2.setTextSize(18);}

        }
    }
}
