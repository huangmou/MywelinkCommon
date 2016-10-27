package com.welink.mywelinkcommon;

import android.os.Bundle;

import com.jason.feick.util.MyJsonMap;

public class MainActivity extends MyActivity {

    @Override
    public void setData(int what, boolean success, MyJsonMap data, int errorCode) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setPushMessages(MyJsonMap messageJsonMap) {

    }
}
