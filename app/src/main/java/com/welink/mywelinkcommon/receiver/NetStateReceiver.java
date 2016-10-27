package com.welink.mywelinkcommon.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2016/1/6.
 */

public class NetStateReceiver extends BroadcastReceiver {
    private String Tag="NetStateReceiver";
    public static List<NetStateChangeListener> netStateChangeListeners=new ArrayList<NetStateChangeListener>();

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if (activeInfo==null){
            Toast.makeText(context,"无可用网络",Toast.LENGTH_SHORT).show();
            if (netStateChangeListeners!=null){
                for (NetStateChangeListener listener:netStateChangeListeners){
                    listener.noNetWork();
                }
            }
            Log.e("GetData", "无可用网络");
        }else{
            if (mobileInfo.isConnectedOrConnecting()){
                for (NetStateChangeListener listener:netStateChangeListeners){
                    listener.hasNetWork();
                }
                Log.e("GetData","正在使用手机移动网络");
            }
            if(wifiInfo.isConnectedOrConnecting()){
                for (NetStateChangeListener listener:netStateChangeListeners){
                    listener.hasNetWork();
                }
                Log.e("GetData","正在使用手机WIFI网络");
            }
        }
    }

    public static void setNetStateChangeListener(NetStateChangeListener netStateChangeListener) {
        netStateChangeListeners.add(netStateChangeListener);
    }

    public interface NetStateChangeListener{
        void noNetWork();
        void hasNetWork();
    }
}
