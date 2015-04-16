package com.example.drey.testforpromua;

import android.app.Application;

import com.example.drey.testforpromua.dataobjects.Feed;
import com.example.drey.testforpromua.orm.HelperFactory;

public class MyApplication extends Application {

   @Override
   public void onCreate() {
       super.onCreate();
       HelperFactory.setHelper(getApplicationContext());
   }
   @Override
   public void onTerminate() {
       HelperFactory.releaseHelper();
       super.onTerminate();
   }
}