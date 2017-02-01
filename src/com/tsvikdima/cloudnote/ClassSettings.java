package com.tsvikdima.cloudnote;

import android.util.Log;

import com.tsvikdima.cloudnote.dropbox.DropboxSunc;
import com.tsvikdima.cloudnote.dropbox.Fragment_Auth;
import com.tsvikdima.cloudnote.password.Fragment_Password;
import com.tsvikdima.cloudnote.password.Fragment_Password_Save;
import com.tsvikdima.cloudnote.settings.Fragment_Settings;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_add;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_edit;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_add;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_edit;

public class ClassSettings {

	/******************** Auth ***************************/
	static void vFragment_Auth(String index) {
		Main.ActionBar(Main.TitleAuth, Main.IconAuth);
		android.app.Fragment fragment = new Fragment_Auth();
		int container = R.id.container;
		boolean is = isFragmentAuth(index);

		if (!is) {
			Log(index + ": Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + ": Фрагмент УЖЕ загружен");
			Fragment_Auth.onUpdate();
		}
	}

	static boolean isFragmentAuth(String index) {
		boolean s;
		Fragment_Auth fragments = (Fragment_Auth) Main.myFragmentManager
				.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}




	/*********************** Settings ************************/
	static void vFragment_Settings(String index) {
		Main.ActionBar(Main.TitleSettings, Main.IconSettings);
		android.app.Fragment fragment = new Fragment_Settings();
		int container = R.id.container;
		boolean is = isFragmentSettings(index);

		if (!is) {
			Log(index + ": Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + ": Фрагмент УЖЕ загружен");
			Fragment_Settings.onUpdate();

		}
	}

	static boolean isFragmentSettings(String index) {
		boolean s;
		Fragment_Settings fragments = (Fragment_Settings) Main.myFragmentManager
				.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	/*********************** password ************************/
	static void vFragment_Password_Save(String index) {
		Main.ActionBar(Main.TitlePasswordSave, Main.IconPasswordSave);
		android.app.Fragment fragment = new Fragment_Password_Save();
		int container = R.id.container;
		boolean is = isFragmentPasswordSave(index);

		if (!is) {
			Log(index + ": Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + ": Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentPasswordSave(String index) {
		boolean s;
		Fragment_Password_Save fragments = (Fragment_Password_Save) Main.myFragmentManager
				.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	/*********************** password ************************/
	static void vFragment_Password(String index) {
		Main.ActionBar(Main.TitlePassword, Main.IconPassword);
		android.app.Fragment fragment = new Fragment_Password();
		int container = R.id.container;
		boolean is = isFragmentPassword(index);

		if (!is) {
			Log(index + ": Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + ": Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentPassword(String index) {
		boolean s;
		Fragment_Password fragments = (Fragment_Password) Main.myFragmentManager
				.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	

	static void Log(String s2) {
		// Log("onCreate");
		String TAG = "Note";
		String s1 = "Fragment_View_Task";
		Log.d(TAG, s1 + " - " + s2);

	}
}
