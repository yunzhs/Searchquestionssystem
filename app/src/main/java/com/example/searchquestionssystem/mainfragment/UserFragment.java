package com.example.searchquestionssystem.mainfragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.db.inventory;
import com.example.searchquestionssystem.db.student;
import com.example.searchquestionssystem.user_activity.DisansActivity;
import com.example.searchquestionssystem.user_activity.DisqueActivity;
import com.example.searchquestionssystem.user_activity.InfoActivity;
import com.example.searchquestionssystem.user_activity.MyProActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.user_activity.RankActivity;

import org.litepal.crud.DataSupport;

import java.util.List;


public class UserFragment extends Fragment {

    private TextView username;
    private List<student> stulist;
    private String t,n;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment,container,false);
        username=(TextView)view.findViewById(R.id.username);
        Bundle bundle1 = getArguments();
        t=bundle1.getString("id");
        //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
        stulist= DataSupport.where("id= ?",t).find(student.class);
        for (student stu : stulist){
            n=stu.getName();
        }
        username.setText(n);


        ImageButton bt=(ImageButton)view.findViewById(R.id.imageButton_que);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                if (getActivity() instanceof MainActivity) {
                    Intent intent = new Intent(getActivity(),DisqueActivity.class);
                    intent.putExtra("UserId", t);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        ImageButton bt1=(ImageButton)view.findViewById(R.id.imageButton_ans);
        bt1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                if (getActivity() instanceof MainActivity) {
                    Intent intent = new Intent(getActivity(),DisansActivity.class);
                    intent.putExtra("UserId", t);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        ImageButton bt2=(ImageButton)view.findViewById(R.id.imageButton_set);
        bt2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                if (getActivity() instanceof MainActivity) {
                    Intent intent = new Intent(getActivity(),RankActivity.class);
                    intent.putExtra("UserId", t);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });
        ImageButton bt3=(ImageButton)view.findViewById(R.id.imageButton_info);
        bt3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
                if (getActivity() instanceof MainActivity) {
                    Intent intent = new Intent(getActivity(),InfoActivity.class);
                    intent.putExtra("UserId", t);
                    startActivity(intent);
                    getActivity().finish();
                }
            }
        });


        return view;
    }

}
