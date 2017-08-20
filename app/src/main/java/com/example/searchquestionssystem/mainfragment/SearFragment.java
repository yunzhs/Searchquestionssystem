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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.QuesActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.SloveActivity;
import com.example.searchquestionssystem.db.inventory;
import com.example.searchquestionssystem.db.keyword;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;



public class SearFragment extends Fragment {

    private ImageButton imagebtn;
    private EditText editText;
    private ListView lv;
    private List<keyword> keylist;
    private List<inventory> quelist,conlist,addlist,addlist1;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();
    private List<String> dataList1 = new ArrayList<>();
    private List<String> dataList2= new ArrayList<>();
    Spinner spinner;
    private String t;
    private String id;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sear_fragment,container,false);

        imagebtn=(ImageButton) view.findViewById(R.id.ib_search);
        editText=(EditText) view.findViewById(R.id.et_search);
        lv=(ListView)view.findViewById(R.id.lv_search) ;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        lv.setAdapter(adapter);
        spinner = (Spinner) view.findViewById(R.id.spinner1);
        Bundle bundle1 = getArguments();
        id =bundle1.getString("id");
        /*quelist=DataSupport.where("titlename like ? ","%"+editText.getText().toString()+"%").find(inventory.class);
        for (inventory inven : quelist) {
            dataList.add(inven.getTitlename());
        }
        adapter.notifyDataSetChanged();
        lv.setSelection(0);*/

        imagebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (t.equals("不限")){
                    quelist=DataSupport.where("titlename like ? ","%"+editText.getText().toString()+"%").find(inventory.class);
                   // Toast.makeText(getActivity(), quelist.size(),Toast.LENGTH_SHORT).show();
                    conlist=DataSupport.where("content like ?","%"+editText.getText().toString()+"%").find(inventory.class);
                }
                else {quelist=DataSupport.where("titlename like ? and type = ?","%"+
                        editText.getText().toString()+"%",t).find(inventory.class);
                        conlist=DataSupport.where("content like ?","%"+editText.getText().toString()+"%").find(inventory.class);
                    }
                    dataList.clear();
                    for (inventory inven : quelist) {
                        dataList.add(inven.getTitlename());//理由
                        dataList1.add(inven.getTitlename());
                    }
                if(dataList1.size()<3) {
                if (t.equals("不限")){
                    conlist=DataSupport.where("content like ?","%"+editText.getText().toString()+"%").find(inventory.class);
                }
                else {
                    conlist=DataSupport.where("content like ?","%"+editText.getText().toString()+"%").find(inventory.class);
                }
                   for (inventory con : conlist) {
                        dataList.add(con.getTitlename());
                       dataList2.add(con.getTitlename());
                   }}
                   if(dataList.size()<3){
                        String str=editText.getText().toString();
                       //乱七八糟的方法
                        keylist=DataSupport.findAll(keyword.class);
                       for (keyword key : keylist) {
                           int i = str.indexOf(key.getWord());
                           /*String s = String.valueOf(i);
                           Toast.makeText(getActivity(),s,Toast.LENGTH_SHORT).show();*/
                           if(i!=-1){
                           addlist=DataSupport.where("answer like ? or content like ? or titlename like ?","%"+key.getWord()+"%","%"+key.getWord()+"%","%"+key.getWord()+"%").find(inventory.class);
                           for (inventory add : addlist) {
                               dataList.add(add.getTitlename());
                           }
                           }
                       }
                     }
                    adapter.notifyDataSetChanged();
                    lv.setSelection(0);

            }
        });
        String[] mItems = getResources().getStringArray(R.array.findtype);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getContext(),R.layout.spiner_text_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                String[] type = getResources().getStringArray(R.array.findtype);
                t=type[pos];
                //inven.setType(type[pos]);
                /*Toast.makeText(MyqueActivity.this, "你点击的是:"+languages[pos], 2000).show();*/
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Another interface callback
            }
        });

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                String quename = quelist.get(position).getTitlename();
                String finished=quelist.get(position).getFinished();

                if (getActivity() instanceof MainActivity) {
                    if(finished=="true") {
                        Intent intent = new Intent(getActivity(), QuesActivity.class);
                        intent.putExtra("queName", quename);
                        startActivity(intent);
                        getActivity().finish();
                    }
                    else  {
                        Intent intent = new Intent(getActivity(), SloveActivity.class);
                        intent.putExtra("queName", quename);
                        startActivity(intent);
                        getActivity().finish();
                    }
                }
            }
        });*/

        return view;
    }
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String finished;
                String quename;
                Bundle bundle1 = getArguments();
                String userid =bundle1.getString("id");
                if(dataList1.size()!=0) {
                    quename = quelist.get(position).getTitlename();
                    finished = quelist.get(position).getFinished();
                }
                else if(dataList2.size()!=0){
                    quename = conlist.get(position).getTitlename();
                    finished = conlist.get(position).getFinished();

                }
                else {
                    quename = addlist.get(position).getTitlename();
                    finished = addlist.get(position).getFinished();
                }
                    if (getActivity() instanceof MainActivity) {
                        if ("true".equals(finished)) {
                            Intent intent = new Intent(getActivity(), QuesActivity.class);
                            intent.putExtra("UserId",userid);
                            intent.putExtra("queName", quename);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Intent intent = new Intent(getActivity(), SloveActivity.class);
                            intent.putExtra("queName", quename);
                            intent.putExtra("UserId",userid);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }

            }});
    }
}
