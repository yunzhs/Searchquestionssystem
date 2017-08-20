package com.example.searchquestionssystem.user_activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.db.inventory;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class DisansActivity extends AppCompatActivity {

    private List<inventory> quelist;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button backButton;
    private inventory selectedQue;
    private String UserId;
    private List<String> dataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disans);
        listView = (ListView)findViewById(R.id.myans);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        UserId = getIntent().getStringExtra("UserId");
        //Toast.makeText(DisqueActivity.this, UserId, Toast.LENGTH_SHORT).show();
        quelist = DataSupport.where("ansid = ? ",UserId).find(inventory.class);
        if (quelist.size() > 0) {
            dataList.clear();
            for (inventory inven : quelist) {
                dataList.add(inven.getTitlename());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedQue= quelist.get(position);
                //Toast.makeText(DisansActivity.this, position, Toast.LENGTH_SHORT).show();
                String quename = quelist.get(position).getTitlename();
                String userid = quelist.get(position).getAnsid();
                Intent intent = new Intent(DisansActivity.this, MyansActivity.class);
                intent.putExtra("queName", quename);
                intent.putExtra("UserId", userid);
                //Toast.makeText(DisansActivity.this, userid, Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        });
    }
}
