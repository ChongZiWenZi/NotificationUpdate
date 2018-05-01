package com.example.notificationupdate;

import android.app.Application;

/**
 * Created by Administrator on 2018/5/1.
 */

public class UpdateApp extends Application{
    public boolean isDownload() {
        return isDownload;
    }

    public void setDownload(boolean download) {
        isDownload = download;
    }

    private boolean isDownload;

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        isDownload = false;
    }

  private static volatile UpdateApp instance = null;

	    public static UpdateApp getInstance() {
	           if (instance == null) {
	             synchronized (UpdateApp.class) {
	                if (instance == null) {
	                   instance = new UpdateApp();
	                }
	             }
	           }
	           return instance;
	    }
}
