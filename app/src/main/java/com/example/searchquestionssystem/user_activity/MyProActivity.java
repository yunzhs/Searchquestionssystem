package com.example.searchquestionssystem.user_activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchquestionssystem.LoginActivity;
import com.example.searchquestionssystem.MainActivity;
import com.example.searchquestionssystem.R;
import com.example.searchquestionssystem.db.inventory;
import com.example.searchquestionssystem.db.student;

import org.litepal.crud.DataSupport;

import java.util.List;

public class MyProActivity extends AppCompatActivity {

    private Button homeButton,con;
    private TextView quetitle;
    private TextView quecontent,bs;
    private TextView ans1,ans2;
    private EditText editslove;
    private List<inventory> quelist;
    private List<student> stulist;
    private String queId;
    private String UserId;
    private String titlename;
    private RadioGroup r;
    private String finish;
    private RelativeLayout r1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_pro);
        r1=(RelativeLayout) findViewById(R.id.r1);
        bs=(TextView)findViewById(R.id.bs) ;
        homeButton=(Button)findViewById(R.id.button_home_p) ;
        quetitle=(TextView)findViewById(R.id.title_pro);
        quecontent=(TextView)findViewById(R.id.content_pro);
        ans1=(TextView)findViewById(R.id.ans_pro1);
        ans2=(TextView)findViewById(R.id.ans_pro2);
        con=(Button)findViewById(R.id.button_pro);
        r=(RadioGroup) findViewById(R.id.radioGroup);




        homeButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MyProActivity.this,MainActivity.class);
                UserId=getIntent().getStringExtra("UserId");
                intent.putExtra("UserId",UserId);
                startActivity(intent);
            }
        });
        titlename = getIntent().getStringExtra("queName");
        quelist = DataSupport.where("titlename = ? ",titlename).find(inventory.class);
        for (inventory inven : quelist) {
            stulist = DataSupport.where("id = ? ",inven.getAnsid()).find(student.class);
            if(inven.getFinished().equals("true")){
                r1.setVisibility(View.GONE);
            }
            else {
                bs.setVisibility(View.GONE);
            }
            quetitle.setText(inven.getTitlename());
            quetitle.setTextSize(18);
            quecontent.setText(inven.getContent());
            quecontent.setTextSize(18);
            ans1.setText(inven.getAnswer());
            ans1.setTextSize(18);
            ans2.setText(inven.getAnswer1());
            ans2.setTextSize(18);

        }
        con.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int radioButtonId = r.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)MyProActivity.this.findViewById(radioButtonId);
                //Toast.makeText(MyProActivity.this,rb.getText() ,Toast.LENGTH_SHORT).show();
                String str=rb.getText().toString();

                for (inventory inven : quelist) {
                    for (student stu : stulist) {
                        stu.setPoints(Integer.valueOf(inven.getPoint()).intValue()+stu.getPoints());
                    }

                if(str.equals("采纳回答1")){
                    //Toast.makeText(MyProActivity.this,str ,Toast.LENGTH_SHORT).show();
                    inven.setFinished("true");
                    inven.save();
                }
                else
                {
                    //Toast.makeText(MyProActivity.this,str,Toast.LENGTH_SHORT).show();
                    String t=inven.getAnswer();
                    inven.setAnswer(inven.getAnswer1());
                    inven.setAnswer1(t);
                    inven.setFinished("true");
                    inven.save();
                }
                    new AlertDialog.Builder(MyProActivity.this)
                            .setTitle("")
                            .setMessage("采纳成功")
                            .setPositiveButton("确定",new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent=new Intent(MyProActivity.this,MainActivity.class);
                                UserId=getIntent().getStringExtra("UserId");
                                intent.putExtra("UserId",UserId);
                                startActivity(intent);
                            }
                         })
                            .show();
            }}
        });
    }
}
