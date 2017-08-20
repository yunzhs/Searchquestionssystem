package com.example.searchquestionssystem.db;

import org.litepal.crud.DataSupport;

/**
 * Created by hasee on 2017/4/27.
 */

public class student extends DataSupport {
    int id;
    String iden;//身份
    String account;//账号
    String password;//密码
    String name;
    String grade;//年级
    String queid;//问题id
    String ansid;//回答id
    int points;
    public int getPoints() {
        return points;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getQueid() {
        return queid;
    }

    public void setQueid(String queid) {
        this.queid = queid;
    }

    public String getAnsid() {
        return ansid;
    }

    public void setAnsid(String ansid) {
        this.ansid = ansid;
    }


    public String getIden() {
        return iden;
    }

    public void setIden(String iden) {
        this.iden = iden;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
