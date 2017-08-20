package com.example.searchquestionssystem;

import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.searchquestionssystem.mainfragment.AnsFragment;
import com.example.searchquestionssystem.mainfragment.NansFragment;
import com.example.searchquestionssystem.mainfragment.QueFragment;
import com.example.searchquestionssystem.mainfragment.SearFragment;
import com.example.searchquestionssystem.mainfragment.UserFragment;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView topBar;
    private TextView tabSre;
    private TextView tabQue;
    private TextView tabAns;
    private TextView tabNan;
    private TextView tabUser;


    private FrameLayout ly_content;

    private SearFragment f1;
    private QueFragment f2;
    private AnsFragment f3;
    private UserFragment f4;
    private NansFragment f5;
    private String UserId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //LitePal.getDatabase();
        UserId = getIntent().getStringExtra("UserId");
        //Toast.makeText(MainActivity.this, UserId, Toast.LENGTH_SHORT).show();
        bindView();
    }

    //UI组件初始化与事件绑定
    private void bindView() {
        topBar = (TextView)this.findViewById(R.id.txt_top);
        tabSre = (TextView)this.findViewById(R.id.txt_sre);
        tabQue = (TextView)this.findViewById(R.id.txt_que);
        tabAns = (TextView)this.findViewById(R.id.txt_ans);
        tabNan = (TextView)this.findViewById(R.id.txt_nan);
        tabUser = (TextView)this.findViewById(R.id.txt_user);
        ly_content = (FrameLayout) findViewById(R.id.fragment_container);

        tabSre.setOnClickListener(this);
        tabQue.setOnClickListener(this);
        tabAns.setOnClickListener(this);
        tabNan.setOnClickListener(this);
        tabUser.setOnClickListener(this);


    }

    //重置所有文本的选中状态
    public void selected(){
        tabSre.setSelected(false);
        tabQue.setSelected(false);
        tabAns.setSelected(false);
        tabNan.setSelected(false);
        tabUser.setSelected(false);

    }

    //隐藏所有Fragment
    public void hideAllFragment(FragmentTransaction transaction){
        if(f1!=null){
            transaction.hide(f1);
        }
        if(f2!=null){
            transaction.hide(f2);
        }
        if(f3!=null){
            transaction.hide(f3);
        }
        if(f4!=null){
            transaction.hide(f4);
        }
        if(f5!=null){
            transaction.hide(f5);
        }
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch(v.getId()){
            case R.id.txt_sre:
                selected();
                tabSre.setSelected(true);
                if(f1==null){
                    f1 = new SearFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", UserId);
                    f1.setArguments(bundle1);
                    transaction.add(R.id.fragment_container,f1);
                }else{
                    transaction.show(f1);
                }
                break;

            case R.id.txt_que:
                selected();
                tabQue.setSelected(true);
                if(f2==null){
                    f2 = new QueFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", UserId);
                    f2.setArguments(bundle1);
                    transaction.add(R.id.fragment_container,f2);
                }else{
                    transaction.show(f2);
                }
                break;

            case R.id.txt_nan:
                selected();
                tabNan.setSelected(true);
                if(f5==null){
                    f5 = new NansFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", UserId);
                    f5.setArguments(bundle1);
                    transaction.add(R.id.fragment_container,f5);
                }else{
                    transaction.show(f5);
                }
                break;

            case R.id.txt_ans:
                selected();
                tabAns.setSelected(true);
                if(f3==null){
                    f3 = new AnsFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", UserId);
                    f3.setArguments(bundle1);
                    transaction.add(R.id.fragment_container,f3);
                }else{
                    transaction.show(f3);
                }
                break;


               case R.id.txt_user:
                selected();
                tabUser.setSelected(true);
                if(f4==null){
                    f4 = new UserFragment();
                    Bundle bundle1 = new Bundle();
                    bundle1.putString("id", UserId);
                    f4.setArguments(bundle1);
                    transaction.add(R.id.fragment_container,f4);
                }else{
                    transaction.show(f4);
                }

                break;
        }

        transaction.commit();
    }
}