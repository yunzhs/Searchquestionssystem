package com.example.searchquestionssystem;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.List;


public class LoginActivity extends AppCompatActivity {
    private student stu;
    private EditText account;
    private EditText password;
    private Button btnlogin;
    private List<student> stulist;
    private List<inventory> quelist;
    public aboutdata db;

    private String account1,password1,password2;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        account=(EditText) findViewById(R.id.account);
        password=(EditText) findViewById(R.id.password);
        btnlogin=(Button)findViewById(R.id.bt_go);
        LitePal.getDatabase();
        /*quelist= DataSupport.where("answer is not null").find(inventory.class);
        for (inventory inven : quelist) {
            inven.setFinished("true");
            inven.save();
        }*/
        //DataSupport.deleteAll(inventory.class, "finished = ?","false");
        //DataSupport.deleteAll(student.class);
        //db=new aboutdata();
        //db.add_data();
        //db.add_data1();


        btnlogin.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v) {
                    if(account.getText().toString().length()==0) {
                        new AlertDialog.Builder(v.getContext())
                                .setTitle("警告")
                                .setMessage("账号不能为空")
                                .setPositiveButton("确定", null)
                                .show();
                    }
                    else{
                        stu=new student();
                        account1=account.getText().toString();
                        password1=password.getText().toString();
                        /*Toast.makeText(LoginActivity.this, account1, Toast.LENGTH_SHORT).show();*/
                        stulist= DataSupport.where("account = ?",account1).find(student.class);
                        /*stulist=DataSupport.where("account like ?","%"+account.getText().toString()+"%").find(student.class);*/
                        if(stulist.size()>0){
                            for (student stu : stulist) {
                                password2=stu.getPassword();
                                int userid=stu.getId();
                                final String s = String.valueOf(userid);
                                //Toast.makeText(LoginActivity.this, password2, Toast.LENGTH_SHORT).show();
                                if(password1.equals(password2)){
                                    new AlertDialog.Builder(LoginActivity.this)
                                            .setTitle("")
                                            .setMessage("登陆成功")
                                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                                    intent.putExtra("UserId", s);
                                                    startActivity(intent);
                                                }
                                            })
                                            .show();

                                }
                                else{
                                    new AlertDialog.Builder(LoginActivity.this)
                                            .setTitle("")
                                            .setMessage("密码输入错误")
                                            .setPositiveButton("确定", null)
                                            .show();
                                }
                            }
                        }

                        else{
                            new AlertDialog.Builder(v.getContext())
                                    .setTitle("警告")
                                    .setMessage("账号输入错误或不存在相应的账号")
                                    .setPositiveButton("确定", null)
                                    .show();
                        }
                    }

            }
        });
    }
}
