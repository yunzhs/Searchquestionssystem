package com.example.searchquestionssystem;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


public class UserFragment extends Fragment {

    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.user_fragment,container,false);
        Bundle bundle1 = getArguments();
        final String t=bundle1.getString("id");

        ImageButton bt=(ImageButton)view.findViewById(R.id.imageButton_info);
        bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), t, Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}
