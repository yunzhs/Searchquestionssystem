package com.example.searchquestionssystem.user_activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.SloveActivity;
import com.example.searchquestionssystem.db.inventory;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class DisqueActivity extends AppCompatActivity {

    private List<inventory> quelist,quelist1;
    private ListView listView,listView1;
    private ArrayAdapter<String> adapter,adapter1;
    private Button backButton;
    private String UserId;
    private List<String> dataList = new ArrayList<>();
    private List<String> dataList1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disque);
        listView = (ListView)findViewById(R.id.myque);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        listView1 = (ListView)findViewById(R.id.myque1);
        adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dataList1);
        listView1.setAdapter(adapter1);
        UserId = getIntent().getStringExtra("UserId");
        //Toast.makeText(DisqueActivity.this, UserId, Toast.LENGTH_SHORT).show();
        quelist = DataSupport.where("useid = ? and finished = ?",UserId,"true").find(inventory.class);
        if (quelist.size() > 0) {
            dataList.clear();
            for (inventory inven : quelist) {
                    dataList.add(inven.getTitlename());
            }
        }
        quelist1 = DataSupport.where("useid = ? and finished = ?",UserId,"false").find(inventory.class);
            if (quelist1.size() > 0) {
                dataList1.clear();
                for (inventory inven : quelist1) {
                    dataList1.add(inven.getTitlename());
                }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);
            adapter1.notifyDataSetChanged();
            listView1.setSelection(0);

        }
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String quename = quelist.get(position).getTitlename();
                String userid = quelist.get(position).getUseid();
                Intent intent = new Intent(DisqueActivity.this, MyProActivity.class);
                //Toast.makeText(DisqueActivity.this, quename, Toast.LENGTH_SHORT).show();
                intent.putExtra("queName", quename);
                intent.putExtra("UserId", userid);
                startActivity(intent);
            }
        });
        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String quename = quelist1.get(position).getTitlename();
                String userid = quelist1.get(position).getUseid();
                Intent intent = new Intent(DisqueActivity.this, MyProActivity.class);
                //Toast.makeText(DisqueActivity.this, quename, Toast.LENGTH_SHORT).show();
                intent.putExtra("queName", quename);
                intent.putExtra("UserId", userid);
                startActivity(intent);
            }
        });
    }




}
