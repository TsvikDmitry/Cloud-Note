package com.tsvikdima.cloudnote;

import android.util.Log;

import com.tsvikdima.cloudnote.view.list.Fragment_View_List;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_add;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_edit;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_add;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_edit;

public class ClassViewTask {
	/********************* Fragment_View_Task **************************/
	static void vFragment_View_Task(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewTask, Main.IconTask);
		android.app.Fragment fragment = new Fragment_View_Task();
		int container = R.id.container;
		boolean is = isFragmentViewTask(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentViewTask(String index) {
		boolean s;
		Fragment_View_Task fragments = (Fragment_View_Task) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* Fragmen View Task Add **************************/
	static void vFragment_View_Task_add(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewTaskAdd, Main.IconTaskAdd);
		android.app.Fragment fragment = new Fragment_View_Task_add();
		int container = R.id.container;
		boolean is = isFragmentViewTaskAdd(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentViewTaskAdd(String index) {
		boolean s;
		Fragment_View_Task_add fragments = (Fragment_View_Task_add) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* Fragment View Task Edit **************************/
	static void vFragment_View_Task_edit(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewTaskEdit, Main.IconTaskEdit);
		android.app.Fragment fragment = new Fragment_View_Task_edit();
		int container = R.id.container;
		boolean is = isFragmentViewTaskEdit(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentViewTaskEdit(String index) {
		boolean s;
		Fragment_View_Task_edit fragments = (Fragment_View_Task_edit) Main.myFragmentManager.findFragmentByTag(index);
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
