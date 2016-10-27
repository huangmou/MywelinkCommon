package com.welink.mywelinkcommon;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.jason.feick.LogActivity;
import com.jason.feick.net.ResponseCallBack;
import com.jason.feick.task.MyTask;
import com.jason.feick.task.TaskManager;
import com.jason.feick.util.DisplayUtil;
import com.jason.feick.util.JsonParseHelper;
import com.jason.feick.util.LogUtil;
import com.jason.feick.util.MyJsonMap;
import com.jason.feick.util.StringUtil;
import com.jason.feick.util.ToastUtil;
import com.welink.mywelinkcommon.receiver.NetStateReceiver;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * @author  xiaoxiangtata#gmail.com
 *	UI基类
 *  功能：标题栏、无网提示、展示开发模式、后台接口数据回调、推送数据处理
 */
public abstract class MyActivity extends LogActivity implements NetStateReceiver.NetStateChangeListener {

	protected boolean hasStoreKey = false;

	protected TextView titleRight;

	protected TextView titleLeft;

	protected TextView titleTextView;

	protected ImageView backImageView;

	protected ImageView rightTextView;

	protected TextView dev_modeTextView;

	public TextView noNetTextView;

	public MyApp application;

	public static final int handler_net_yes = 0;
	public static final int handler_net_no = 1;
	public static final int handler_upload_address = 3;
	protected Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
				case handler_net_yes:
				case handler_net_no:
					if (task != null && task.isAlive()) {
						task.stop();
						task = null;
					}
					break;
			}
			super.handleMessage(msg);
		}

	};

	public ConnectionChangeReceiver connectionChangeReceiver = new ConnectionChangeReceiver();

	public ResponseCallBack callBack = new ResponseCallBack() {

		@Override
		public void response(String message, int what, int index) {
			loadingToast.dismiss();
			LogUtil.Log(getApplicationContext(), tag, "index = " + index
					+ ", what = " + what + "\nmessage=" + message);
			if (-1 != index) {
				setData(what, false, null, 0);
				return;
			} else {
				boolean success = false;
				if (message == null || message.equalsIgnoreCase("")) {
					return;
				}
				MyJsonMap result = JsonParseHelper
						.getJsonMap(message);
				int errorCode = result.getInt(API.errcode);
				if (result.getStringNoNull(API.success).equalsIgnoreCase(
						API.successFalse)) {
					success = false;
					if (errorCode == API.errcode_20026
							|| errorCode == API.errcode_20027) {
						return;
					}
					setData(what, success, result, result.getInt(API.errcode));
				} else {
					success = true;
					setData(what, success, result, result.getInt(API.errcode));
				}

				if (errorCode == API.errcode_10000
						&& (!result.getStringNoNull(API.message)
						.equalsIgnoreCase(API.ok))) {
				}
			}

			if (task != null) {
				task = null;
			}

		}
	};

	public abstract void setData(int what, boolean success,
								 MyJsonMap data, int errorCode);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//initScreen();
		//showStatusBar();
		IntentFilter filter2 = new IntentFilter();
		filter2.addAction("android.net.conn.CONNECTIVITY_CHANGE");
		registerReceiver(connectionChangeReceiver, filter2);


	}

	@Override
	public void noNetWork() {
		ToastUtil.initToast(this).showToast("我们没有网！");
	}

	@Override
	public void hasNetWork() {
		ToastUtil.initToast(this).showToast("我们有网！");
	}

	private void initScreen(){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			//透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			//透明导航栏
			//getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		}

		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

	}

	private void hideStatusBar() {
		WindowManager.LayoutParams attrs = getWindow().getAttributes();
		attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
		getWindow().setAttributes(attrs);
	}

	private void showStatusBar() {
		WindowManager.LayoutParams attrs = getWindow().getAttributes();
		attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
		getWindow().setAttributes(attrs);
	}

	@Override
	protected void onDestroy() {
		// unregisterReceiver(receiver);
		loadingToast.dismiss();
		unregisterReceiver(connectionChangeReceiver);
		super.onDestroy();
	}

	protected void setTitle(String text) {
		initTitle();
		application = (MyApp) getApplication();
		titleTextView.setText(text);
	}

	public void initTitle() {
		titleLeft = (TextView) findViewById(R.id.title_left);
		titleRight = (TextView) findViewById(R.id.title_right);
		titleTextView = (TextView) findViewById(R.id.title);
		backImageView = (ImageView) findViewById(R.id.back);
		rightTextView = (ImageView) findViewById(R.id.right);
		dev_modeTextView = (TextView) findViewById(R.id.dev_mode);
		noNetTextView = (TextView) findViewById(R.id.no_net);

		backImageView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
				overridePendingTransition(R.anim.push_right_in,
						R.anim.push_right_out);
			}
		});

		// 版本提示
		switch (MyApp.devMode) {
			case 0:
				dev_modeTextView.setVisibility(View.VISIBLE);
				dev_modeTextView.setText("开发版");
				break;
			case 1:
				dev_modeTextView.setVisibility(View.VISIBLE);
				dev_modeTextView.setText("测试版");
				break;
		}
		noNetTextView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onClickNoNet(v);
			}
		});
	}

	public volatile static MyTask task;

	public synchronized MyTask getData(String method,
									   HashMap<String, Object> par, int mark) {
		loadingToast.show();
		task = TaskManager.getConfigure(getApplicationContext(), callBack,
				method, addLocalInfo(par), mark,MyApp.token,MyApp.user_id);
		return task;
	}


	@Override
	protected void onPause() {
		super.onPause();
//		StatService.onPause(this);百度统计
	}

	@Override
	protected void onResume() {
		toast.setGravity(Gravity.TOP | Gravity.CENTER, 0,
				DisplayUtil.getScreenHeight(MyActivity.this) / 2);
		super.onResume();
//		StatService.onResume(this);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	private void showNetState() {
		// 没有网络
		if ("-1".equalsIgnoreCase(StringUtil.detectionMesh(this))) {
			noNetTextView.setVisibility(View.VISIBLE);
		} else {
			noNetTextView.setVisibility(View.GONE);
		}
	}


	public HashMap<String, Object> addLocalInfo(
			HashMap<String, Object> paramHashMap) {
		JSONObject appVersionObject = new JSONObject();
		try {
			appVersionObject.put("app_version", MyApp.appVersion);
//			appVersionObject.put("app_udid",
//					AndroidUtils.getDeviceID(getApplicationContext()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		paramHashMap.put("app_stats", appVersionObject);
		return paramHashMap;
	}

	public void onClickNoNet(View view) {
		startActivity(new Intent(Settings.ACTION_SETTINGS));
	}

	public class ConnectionChangeReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
		}
	}

	private void handleReceive(Context context) {
		if ("-1".equalsIgnoreCase(StringUtil.detectionMesh(context))) {
			noNetTextView.setVisibility(View.VISIBLE);
			handler.sendEmptyMessage(handler_net_no);
		} else {
			noNetTextView.setVisibility(View.GONE);
			handler.sendEmptyMessage(handler_net_yes);
		}
	}

	private volatile int number = 0;
	private volatile int seconds = 0;

	public class MyBroadcastReceiver extends BroadcastReceiver {

		@Override
		public void onReceive(Context context, Intent intent) {
			String msg = intent.getStringExtra("push");
			MyJsonMap messageJsonMap = JsonParseHelper
					.getJsonMap(msg);
			setPushMessages(messageJsonMap);
		}

	}



	public abstract void setPushMessages(MyJsonMap messageJsonMap);

	public void forward(Context formActivity, Class toActivity, int enterAnim,
						int exitAnim, Bundle bundle) {
		Intent intent = new Intent(formActivity, toActivity);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivity(intent);
		overridePendingTransition(enterAnim, exitAnim);
	}

	public void forwardActivityWithResult(Context formActivity,
										  Class toActivity, int enterAnim, int exitAnim, Bundle bundle,
										  int requestCode) {
		Intent intent = new Intent(formActivity, toActivity);
		if (bundle != null) {
			intent.putExtras(bundle);
		}
		startActivityForResult(intent, requestCode);
		overridePendingTransition(enterAnim, exitAnim);
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//			if (backImageView.isShown()) {
//				backImageView.performClick();
//				return true;
//			}
//		}
//		return super.onKeyDown(keyCode, event);
//	}

	@Override
	public void onTrimMemory(int level) {
		LogUtil.Log(tag, "onTrimMemory " + this + ": " + level);
		super.onTrimMemory(level);
	}

	public void addFragment(Fragment fragment, int containerId)
	{
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(containerId, fragment).commitAllowingStateLoss();
	}


	public void replaceFragment(Fragment fragment, int containerId)
	{
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(containerId, fragment).commitAllowingStateLoss();
	}

	public void removeFragment(Fragment fragment){
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.remove(fragment);
	}

	public void detachFragment(Fragment fragment){
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.detach(fragment);
	}

	public void showFragment(Fragment fragment)
	{
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.show(fragment).commitAllowingStateLoss();
	}

	public void hideFragment(Fragment fragment)
	{
		android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//		transaction.setCustomAnimations(R.anim.slide_in_from_top, R.anim.slide_in_from_bottom);
		transaction.hide(fragment).commitAllowingStateLoss();
	}
}
