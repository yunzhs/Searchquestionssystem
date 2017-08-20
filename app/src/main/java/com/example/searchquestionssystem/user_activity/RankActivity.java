package com.example.searchquestionssystem.user_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class RankActivity extends AppCompatActivity {

    private ListView listView,listView1;
    private List<student> stulist,stulist1,stulist2;
    private TextView textView;
    private String UserId;
    private ArrayAdapter<String> adapter,adapter1;
    private Button backButton;
    private List<String> dataList = new ArrayList<>();
    private List<String> dataList1 = new ArrayList<>();
    private Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        textView=(TextView)findViewById(R.id.inte);
        homeButton=(Button)findViewById(R.id.button_home3) ;
        UserId=getIntent().getStringExtra("UserId");

        stulist = DataSupport.where("id = ? ",UserId).find(student.class);
        for (student stu : stulist) {
               textView.setText(String.valueOf(stu.getPoints()));
            }
        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RankActivity.this,MainActivity.class);
                UserId=getIntent().getStringExtra("UserId");
                intent.putExtra("UserId",UserId);
                startActivity(intent);
            }
        });
        listView = (ListView)findViewById(R.id.point);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        listView1 = (ListView)findViewById(R.id.name);
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList1);
        listView1.setAdapter(adapter1);
        stulist1 = DataSupport.where("grade = ?", "大一").order("points desc").find(student.class);
        for (student stu : stulist1) {
            int i=1;
            dataList.add(String.valueOf(stu.getPoints()));
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            dataList1.add( dataList.size()+"、"+stu.getName());
            i=i+1;
            adapter1.notifyDataSetChanged();
            listView1.setSelection(0);

        }
    }
}
