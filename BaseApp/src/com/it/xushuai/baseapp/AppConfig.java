package com.it.xushuai.baseapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidClassException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Properties;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;

public class AppConfig {
	
	private static AppConfig appConfig;
	private Context mContext;
	public final static String CONF_APP_UNIQUEID = "APP_UNIQUEID";
	private final static String APP_CONFIG = "config";
	public final static String CONF_VOICE = "perf_voice";
	public final static String CONF_LOAD_IMAGE = "perf_loadimage";
	public final static String CONF_CHECKUP = "perf_checkup";
	
	
	public static AppConfig getAppConfig(Context context)
	{
		if(appConfig == null){
			appConfig = new AppConfig();
			appConfig.mContext = context;
		}
		return appConfig;
	}
	

	public static SharedPreferences getSharedPreferences(Context context){
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
	/**
	 * �Ƿ������ʾ����ͼƬ
	 */
	public static boolean isLoadImage(Context context)
	{
		return getSharedPreferences(context)
				.getBoolean(CONF_LOAD_IMAGE, true);
	}	
	
	public String get(String key)
	{
		Properties props = get();
		return (props!=null)?props.getProperty(key):null;
	}
	
	public Properties get() {
		FileInputStream fis = null;
		Properties props = new Properties();
		try{
			//��ȡfilesĿ¼�µ�config
			//fis = activity.openFileInput(APP_CONFIG);
			
			//��ȡapp_configĿ¼�µ�config
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			fis = new FileInputStream(dirConf.getPath() + File.separator + APP_CONFIG);
			
			props.load(fis);
		}catch(Exception e){
		}finally{
			try {
				fis.close();
			} catch (Exception e) {}
		}
		return props;
	}
	
	private void setProps(Properties p) {
		FileOutputStream fos = null;
		try{
			//��config����filesĿ¼��
			//fos = activity.openFileOutput(APP_CONFIG, Context.MODE_PRIVATE);
			
			//��config����(�Զ���)app_config��Ŀ¼��
			File dirConf = mContext.getDir(APP_CONFIG, Context.MODE_PRIVATE);
			File conf = new File(dirConf, APP_CONFIG);
			fos = new FileOutputStream(conf);
			
			p.store(fos, null);
			fos.flush();
		}catch(Exception e){	
			e.printStackTrace();
		}finally{
			try {
				fos.close();
			} catch (Exception e) {}
		}
	}
	
	public void set(Properties ps)
	{
		Properties props = get();
		props.putAll(ps);
		setProps(props);
	}
	
	public void set(String key,String value)
	{
		Properties props = get();
		props.setProperty(key, value);
		setProps(props);
	}
	
	public void remove(String...key)
	{
		Properties props = get();
		for(String k : key)
			props.remove(k);
		setProps(props);
	}
}
