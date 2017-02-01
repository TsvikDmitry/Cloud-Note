package com.tsvikdima.cloudnote.dropbox;

import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;
import com.tsvikdima.cloudnote.R.id;
import com.tsvikdima.cloudnote.R.layout;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public  class Fragment_Auth extends Fragment {
	static Main mActivity_Main;

	
    public Fragment_Auth() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        Log("onCreateView");

        View rootView = inflater.inflate(R.layout.dropbox_auth, container, false);
      
        
		LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.auth_dropbox);
		layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Log("layout");
				DropboxSunc.mDbxAcctMgr.startLink((Activity) getActivity(),DropboxSunc.REQUEST_LINK_TO_DBX);
				
			}
		});  
        
        return rootView;
    }
  
	public void onStart() {
        super.onStart();
		Log("onStart");

    }
    
    public void onResume() {
    	super.onResume();
        Log("onResume");
        
    }

    public void onDestroy() {
    	super.onDestroy();
        Log("onDestroy");

    }
    
    public void onPause() {
    	super.onPause();
        Log("onPause");
        
    }
    
    public static void onUpdate() {
        Log("onUpdate");
        
    }
   
    static void Log(String s2){
    	//Log("onCreate");
    	String TAG = "Note";
    	String s1 = "Fragment_Auth";
    	Log.d(TAG, s1+ " - " +s2);

    }
    
}