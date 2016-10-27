package com.welink.mywelinkcommon;

import android.os.Bundle;

import com.jason.feick.util.MyJsonMap;

/**
 * @author xiaoxiangtata#gmail.com
 * 登录页面中接口处理示例
 */
public class LoginByMessageActivity extends MyActivity {


	@Override
	public void onCreate(Bundle save){
		super.onCreate(save);
		setContentView(R.layout.titlebar);
	}

	/* (non-Javadoc)
	 * @see com.welink.sxtx.MyActivity#setData(int, boolean, com.jason.feick.util.MyJsonMap, int)
	 * 处理登录接口返回的数据
	 */
	@Override
	public void setData(int what, boolean success,
						MyJsonMap data, int errorCode) {
		switch (what) {
		case API.channel_asset_income_mark:
			if (success) {
				setResult(1);
				finish();
			}
			break;
		}
	}

	/* (non-Javadoc)
	 * @see com.welink.sxtx.MyActivity#setPushMessages(com.jason.feick.util.MyJsonMap)
	 * 处理消息推送的数据，暂时不管
	 */
	@Override
	public void setPushMessages(MyJsonMap messageJsonMap) {

	}

}
