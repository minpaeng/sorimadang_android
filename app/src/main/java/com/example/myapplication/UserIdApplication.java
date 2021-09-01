package com.example.myapplication;

import android.app.Application;

public class UserIdApplication extends Application {
    /*
    Application을 상속받고 멤버변수를 생성함
    getter/setter를 이용하여 멤버함수도 생성함
    * */
    private String id;

    public String getId() {
        return id;
    }

    public void setId( String id ) {
        this.id = id;
    }

}
