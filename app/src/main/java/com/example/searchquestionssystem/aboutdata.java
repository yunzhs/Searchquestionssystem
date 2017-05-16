package com.example.searchquestionssystem;

/**
 * Created by hasee on 2017/4/22.
 */

public class aboutdata {
    public inventory db1,db2;
    public student s1,s2;
    public void add_data(){
        db1=new inventory();
        db1.setTitlename("一个几何问题");
        db1.setContent("直线AB、CO、EF、相交于点O,EF垂直于AB,OG为角COF的平分线,OH为角DOG的平分线,若角AOC:角COG=4:7,求角DOF和角DOH的度数.");
        db1.setAnswer("下面三个大写字母都表示角：\n" +
                "AOC=90÷（4+7+7）×4=20度\n" +
                "所以DOF=90+20=110度\n" +
                "角COG=90÷（4+7+7）×7=35度\n" +
                "所以DOH=1/2×(180-COG)\n" +
                "=1/2×（180-35）\n" +
                "=72.5度");
        db1.setType("数学");
        db1.setFinished("true");
        db1.save();
        db2=new inventory();
        db2.setTitlename("高中立体几何问题");
        db2.setContent("已知一个平放的正四面体的各棱长均为4，其内有一轻质小球（不计重量），现从正四面体顶端向内注水，球慢慢上浮，当球与正四面体各侧面均相切（与水面也相切）时，若注入的水体积是正四面体体积的7/8，则球的表面积等于多少？");
        db2.setType("数学");
        db2.setFinished("false");
        db2.save();
    }
    public void add_data1(){
        s1=new student();
        s1.setAnsid("224");
        s1.setGrade("大一");
        s1.setName("李华");
        s1.setPoints(20);
        s1.setQueid("223");
        s1.setAccount("admin");
        s1.setPassword("000");
        s1.save();
    }
}
