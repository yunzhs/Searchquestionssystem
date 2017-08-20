package com.example.searchquestionssystem.mainfragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.QuesActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.db.inventory;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import static com.example.searchquestionssystem.R.layout.ans_fragment;


public class AnsFragment extends Fragment{

    private List<inventory> quelist;
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private Button backButton;
    private inventory selectedQue;
    private  String t;
    private List<String> dataList = new ArrayList<>();

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(ans_fragment,container,false);
        /*inventory db=DataSupport.findFirst(inventory.class);
        TextView test=(TextView)view.findViewById(R.id.textView4);
        String T=db.getTitlename();
        test.setText(T);
        test.setTextSize(18);*/
        listView = (ListView) view.findViewById(R.id.list_view);
        Bundle bundle1 = getArguments();
        t=bundle1.getString("id");
        /*backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               display();
            }
        });*/
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        listView.setAdapter(adapter);
        display();



        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedQue= quelist.get(position);
               /* displayQue();*/
                String quename = quelist.get(position).getTitlename();
                if (getActivity() instanceof MainActivity) {
                    Intent intent = new Intent(getActivity(), QuesActivity.class);
                    intent.putExtra("queName", quename);
                    intent.putExtra("UserId", t);
                    startActivity(intent);
                    getActivity().finish();
                }

            }
        });
    }

    private void display() {
        quelist = DataSupport.where("finished = ?","true").find(inventory.class);
        if (quelist.size() > 0) {
            dataList.clear();
            for (inventory inven : quelist) {
                dataList.add(inven.getTitlename());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);

        }
    }

    /*private void displayQue() {
        titleText.setText("待回答的问题");
        backButton.setVisibility(View.VISIBLE);
        quelist= DataSupport.where("titlename = ?", String.valueOf(selectedQue.getTitlename())).find(inventory.class);
        if (quelist.size() > 0) {
            dataList.clear();
            for (inventory inven : quelist) {
                dataList.add(inven.getTitlename());
                dataList.add(inven.getContent());
                dataList.add(inven.getAnswer());
            }
            adapter.notifyDataSetChanged();
            listView.setSelection(0);

        }
    }*/


}