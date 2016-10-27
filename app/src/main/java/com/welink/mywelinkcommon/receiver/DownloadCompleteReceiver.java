package com.welink.mywelinkcommon.receiver;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by dell on 2016/4/7.
 */
public class DownloadCompleteReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //android版本超过5.0可能会出现找不到contex的问题，只能手动安装，其他版本可以自动安装
        if (intent.getAction().equals(DownloadManager.ACTION_DOWNLOAD_COMPLETE)) {
            if (Integer.parseInt(android.os.Build.VERSION.SDK) < 21) {
                try {
                    long downId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
                    String serviceString = Context.DOWNLOAD_SERVICE;
                    DownloadManager dManager = (DownloadManager) context.getSystemService(serviceString);
                    Intent install = new Intent(Intent.ACTION_VIEW);
                    Uri downloadFileUri = dManager.getUriForDownloadedFile(downId);
                    install.setDataAndType(downloadFileUri, "application/vnd.android.package-archive");
                    install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(install);
                }catch (Exception e){
                    Log.i("DownloadCompletReceiver",e.getMessage());
                }
            }
        }
    }


}
