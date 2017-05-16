package com.example.searchquestionssystem;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;



public class SearFragment extends Fragment {

    private ImageButton imagebtn;
    private EditText editText;
    private ListView lv;
    private inventory selectedQue;
    private List<inventory> quelist;
    private ArrayAdapter<String> adapter;
    private List<String> dataList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sear_fragment,container,false);

        imagebtn=(ImageButton) view.findViewById(R.id.ib_search);
        editText=(EditText) view.findViewById(R.id.et_search);
        lv=(ListView)view.findViewById(R.id.lv_search) ;
        adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, dataList);
        lv.setAdapter(adapter);

        imagebtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                quelist=DataSupport.where("titlename like ?","%"+editText.getText().toString()+"%").find(inventory.class);
                if (quelist.size() > 0) {
                    dataList.clear();
                    for (inventory inven : quelist) {
                        dataList.add(inven.getTitlename());
                    }
                    adapter.notifyDataSetChanged();
                    lv.setSelection(0);
                }
            }
        });

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedQue= quelist.get(position);

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

                selectedQue = quelist.get(position);
                String quename = quelist.get(position).getTitlename();
                String finished = quelist.get(position).getFinished();
                if (getActivity() instanceof MainActivity) {
                    if (getActivity() instanceof MainActivity) {
                        if ("true".equals(finished)) {
                            Intent intent = new Intent(getActivity(), QuesActivity.class);
                            intent.putExtra("queName", quename);
                            startActivity(intent);
                            getActivity().finish();
                        } else {
                            Intent intent = new Intent(getActivity(), SloveActivity.class);
                            intent.putExtra("queName", quename);
                            startActivity(intent);
                            getActivity().finish();
                        }
                    }

                }
            }});
    }
}
