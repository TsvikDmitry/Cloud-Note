package com.tsvikdima.cloudnote.settings;

import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;
import com.tsvikdima.cloudnote.R.id;
import com.tsvikdima.cloudnote.R.layout;
import com.tsvikdima.cloudnote.dropbox.DropboxSunc;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Fragment_Settings extends Fragment {
	SharedPreferences sp;
	TextView tvInfo;

	ListView lvActionMode, lvMain;
	static TableLayout table, tableNote_2, tableNote_3, tableNote_4,
			tableNote_5;
	TableRow row;
	Context MyContext;
	LayoutInflater ltInflater;
	static LayoutInflater mInflater;
	Button button, button1;
	static Context context, context2;

	public Fragment_Settings() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");

		/*
		 * DropboxSunc.mDbxAcctMgr.unlink(); Main.Auth();
		 * Main.OpenFragment(Main.IndexPasswordSave);
		 */

		View view = inflater.inflate(R.layout.fragment_settings, container,
				false);
		context2 = getActivity();
		context = getActivity().getApplicationContext();
		mInflater = LayoutInflater.from(context);
		table = (TableLayout) view.findViewById(R.id.Table);
		

		
		Row();
		return view;
	}

	void Row(){
		table.removeAllViews();
		TableRow row = (TableRow) mInflater.inflate(R.layout.row_setting, null);
		table.addView(row);
		TextView name = (TextView) row.findViewById(R.id.name);
		name.setText("Настройка синхронизации");
		TextView het = (TextView) row.findViewById(R.id.het);
		het.setText("Отсоединить аккаунт");
		LinearLayout idLayout = (LinearLayout) row.findViewById(R.id.lay1);
		idLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				DropboxSunc.mDbxAcctMgr.unlink();
				Main.Auth();
				Main.setPasswordChek(false);
			}
		});
		
		
		if (Main.getPasswordChek()) {
			TableRow row1 = (TableRow) mInflater.inflate(R.layout.row_setting,null);
			table.addView(row1);
			TextView name1 = (TextView) row1.findViewById(R.id.name);
			name1.setText("Поменять пароль");
			TextView het1 = (TextView) row1.findViewById(R.id.het);
			het1.setText("Смена существуюего пароля");
			LinearLayout idLayout1 = (LinearLayout) row1.findViewById(R.id.lay1);
			idLayout1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					Main.OpenFragment(Main.IndexPasswordSave);
					
				}
			});
			
			
			TableRow row2 = (TableRow) mInflater.inflate(R.layout.row_setting,null);
			table.addView(row2);
			TextView name2 = (TextView) row2.findViewById(R.id.name);
			name2.setText("Удалить пароль");
			TextView het2 = (TextView) row2.findViewById(R.id.het);
			het2.setText("Приложение не будет требовать пароль при выходе.");
			LinearLayout idLayout2 = (LinearLayout) row2.findViewById(R.id.lay1);
			idLayout2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Main.setPasswordChek(false);
					Row();
					
				}
			});
		
		} else {
			TableRow row1 = (TableRow) mInflater.inflate(R.layout.row_setting,null);
			table.addView(row1);
			TextView name1 = (TextView) row1.findViewById(R.id.name);
			name1.setText("Задать пароль");
			TextView het1 = (TextView) row1.findViewById(R.id.het);
			het1.setText("Пароль для безопасность приложения");
			LinearLayout idLayout1 = (LinearLayout) row1.findViewById(R.id.lay1);
			idLayout1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					
					Main.OpenFragment(Main.IndexPasswordSave);
					
				}
			});
			
		}
		
	}

	public void onStart() {
		super.onStart();
		Log("onStart");

	}

	public void onResume() {
		super.onResume();
		Log("onResume");
		Main.setSetting(Main.ID_FRAGMENT, Main.IndexSettings);

	}

	public void onDestroy() {
		super.onDestroy();
		Log("onDestroy");

	}

	public void onPause() {
		super.onPause();
		Log("onPause");

	}

	void Log(String s2) {
		// Log("onCreate");
		String TAG = "Note";
		String s1 = "Fragment_Note";
		Log.d(TAG, s1 + " - " + s2);

	}

	public static void onUpdate() {
		// TODO Auto-generated method stub

	}

}