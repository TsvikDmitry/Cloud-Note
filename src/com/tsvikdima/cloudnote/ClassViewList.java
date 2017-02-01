package com.tsvikdima.cloudnote;

import android.util.Log;

import com.tsvikdima.cloudnote.view.list.Fragment_View_List;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_add;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_edit;

public class ClassViewList {
	/********************* Fragment_View_List **************************/
	static void vFragment_View_List(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewList, Main.IconList);
		android.app.Fragment fragment = new Fragment_View_List();
		int container = R.id.container;
		boolean is = isFragmentViewList(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentViewList(String index) {
		boolean s;
		Fragment_View_List fragments = (Fragment_View_List) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* Fragmen View List Add **************************/
	static void vFragment_View_List_add(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewListAdd, Main.IconListAdd);
		android.app.Fragment fragment = new Fragment_View_List_add();
		int container = R.id.container;
		boolean is = isFragmentViewListAdd(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentViewListAdd(String index) {
		boolean s;
		Fragment_View_List_add fragments = (Fragment_View_List_add) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* Fragment View List Edit **************************/
	static void vFragment_View_List_edit(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewListEdit, Main.IconListEdit);
		android.app.Fragment fragment = new Fragment_View_List_edit();
		int container = R.id.container;
		boolean is = isFragmentViewListEdit(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentViewListEdit(String index) {
		boolean s;
		Fragment_View_List_edit fragments = (Fragment_View_List_edit) Main.myFragmentManager.findFragmentByTag(index);
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
		String s1 = "Fragment_View_List_add";
		Log.d(TAG, s1 + " - " + s2);

	}
}
