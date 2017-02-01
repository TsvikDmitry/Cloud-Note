package com.tsvikdima.cloudnote.dropbox;

import android.util.Log;

import com.dropbox.sync.android.DbxAccount;
import com.dropbox.sync.android.DbxAccountInfo;
import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxAccountManager.AccountListener;
import com.dropbox.sync.android.DbxDatastore;
import com.dropbox.sync.android.DbxException;
import com.tsvikdima.cloudnote.Main;

public class DropboxSunc {
	// переменные Dropbox
	public static String APP_KEY = "eq0dlios8bsdyp7";
	public static String APP_SECRET = "505xqy5hw2ktlye";
	public static String TAG = "Note";
	public static int REQUEST_LINK_TO_DBX = 0;
	public static int RESULT_OK = 0;
	public static DbxAccountManager mDbxAcctMgr;
	public static DbxAccountInfo mDbxInfo;
	public static DbxDatastore mDatastore;
	public static Table mTable;
	public static DbxAccount mDbxAccount;

	

	public static void handleException(DbxException e) {
		e.printStackTrace();
		Main.Toast("handleException Ошибка DropBox");
		
	}

	public static void showTasksView() {
		Log(" showTasksView");
		try {
			if (null == mDatastore) {
				mDatastore = DbxDatastore.openDefault(mDbxAcctMgr.getLinkedAccount());
				//Log("null == mDatastore");
			}
			mTable = new Table(mDatastore);
			mDatastore.addSyncStatusListener(mDatastoreListener);
			mDatastore.sync();
			//Log("mDatastore");
			//Activity_Main.update();
			//mDatastore.sync();
		} catch (DbxException e) {
			handleException(e);
		}

	}

	void onPause() {
		if (mDatastore != null) {
			mDatastore.removeSyncStatusListener(mDatastoreListener);
			mDatastore.close();
			mDatastore = null;
			Log("mDatastore != null");
		}
		
	}


	
	public static DbxAccountManager.AccountListener mDatastoreListener1 = new DbxAccountManager.AccountListener() {

		@Override
		public void onLinkedAccountChange(DbxAccountManager arg0,	DbxAccount arg1) {
		
			try {
				mDatastore.sync();
			} catch (DbxException e) {
				handleException(e);
			}
		}
		
	};
		
	
	
	public static DbxDatastore.SyncStatusListener mDatastoreListener = new DbxDatastore.SyncStatusListener() {
		@Override
		public void onDatastoreStatusChange(DbxDatastore ds) {
			//Log.d(TAG, "DbxDatastore.SyncStatusListener");
			if (ds.getSyncStatus().hasIncoming) {
				try {
					mDatastore.sync();
					//Log("SyncStatusListener");

				} catch (DbxException e) {
					handleException(e);
				}
			}
			Main.update();
			
		}

	};

	static void Log(String s2) {
		String TAG = "Note";
		String s1 = "DropboxSunc";
		Log.d(TAG, s1 + "  " + s2);
	}



}
