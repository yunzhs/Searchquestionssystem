package com.example.searchquestionssystem.db;

import com.example.searchquestionssystem.db.inventory;
import com.example.searchquestionssystem.db.student;

/**
 * Created by hasee on 2017/4/22.
 */

public class aboutdata {
    public inventory db1,db2;
    public student s1,s2;
    public keyword k;
    public void add_data(){
        db1=new inventory();
        db1.setAnsid("6");
        db1.setTitlename("椭圆定义，性质是什么？");
        db1.setContent("请说出你的理由");
        db1.setAnswer("平面内的点到两定点间的距离之和是一个定值，则由这些点构成的图形就是椭圆");
        db1.setAnswer1("椭圆是一种圆锥曲线（也有人叫圆锥截线的），现在高中教材上有两种定义： \n" +
                "1、平面上到两点距离之和为定值的点的集合（该定值大于两点间距离）（这两个定点也称为椭圆的焦点，焦点之间的距离叫做焦距）； \n" +
                "2、平面上到定点距离与到定直线间距离之比为常数的点的集合（定点不在定直线上，该常数为小于1的正数）（该定点为椭圆的焦点，该直线称为椭圆的准线）。这两个定义是等价的 ");
        db1.setType("数学");
        db1.setFinished("false");
        db1.setPoint("5");
        db1.setUseid("6");
        db1.save();
        /*db1=new inventory();
        db1.setAnsid("6");
        db1.setTitlename("鸡和蛋哪个先有？");
        db1.setContent("请说出你的理由");
        db1.setAnswer("是先有蛋的，根据生物的进化原理和遗传变异原理");
        db1.setAnswer1("鸡蛋鸡蛋，当然是鸡喽");
        db1.setType("哲学");
        db1.setFinished("false");
        db1.setPoint("5");
        db1.setUseid("6");
        db1.save();*/
       /* db2=new inventory();
        db2.setTitlename("高中立体几何问题");
        db2.setContent("已知一个平放的正四面体的各棱长均为4，其内有一轻质小球（不计重量），现从正四面体顶端向内注水，球慢慢上浮，当球与正四面体各侧面均相切（与水面也相切）时，若注入的水体积是正四面体体积的7/8，则球的表面积等于多少？");
        db2.setType("数学");
        db2.setFinished("false");
        db2.save();*/
    }
    public void add_data1(){
        s1=new student();
        s1.setAnsid("213");
        s1.setGrade("大一");
        s1.setName("布隆");
        s1.setPoints(15);
        s1.setQueid("214");
        s1.setAccount("admin2");
        s1.setPassword("000");
        s1.save();
    }
    public void add_data2(){
        k= new keyword();
        k.setWord("遗传");
        k.save();
    }
}
