package com.it.xushuai.baseapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

public class BaseBroadCastReceiver extends BroadcastReceiver{

	/**
	 * ����״̬����Ĺ㲥
	 */
	@Override
	public void onReceive(Context context, Intent intent) {
		 if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent.getAction())) {
	            AppContext.mNetWorkState = ((AppContext)context.getApplicationContext()).getNetworkType();
	    }
	}
	
}
