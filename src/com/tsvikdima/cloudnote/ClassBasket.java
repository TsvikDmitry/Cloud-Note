package com.tsvikdima.cloudnote;

import android.util.Log;





import com.tsvikdima.cloudnote.view.basket.Fragment_Basket;
import com.tsvikdima.cloudnote.view.basket.Fragment_Recovery_List;
import com.tsvikdima.cloudnote.view.basket.Fragment_Recovery_Task;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_add;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_edit;

public class ClassBasket {
	/********************* Fragment_View_Basket **************************/
	static void vFragment_Basket(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleBasket, Main.IconBasket);
		android.app.Fragment fragment = new Fragment_Basket();
		int container = R.id.container;
		boolean is = isFragmentViewBasket(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentViewBasket(String index) {
		boolean s;
		Fragment_Basket fragments = (Fragment_Basket) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* vFragment_Recovery_List **************************/
	static void vFragment_Recovery_Task(String index) {
		Main.ActionBar(Main.TitleRecoveryTask, Main.IconTask);
		android.app.Fragment fragment = new Fragment_Recovery_Task();
		int container = R.id.container;
		boolean is = isFragmentRecoveryTask(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentRecoveryTask(String index) {
		boolean s;
		Fragment_Recovery_Task fragments = (Fragment_Recovery_Task) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}

	/********************* vFragment_Recovery_List **************************/
	static void vFragment_Recovery_List(String index) {
		Main.ActionBar(Main.TitleRecoveryList, Main.IconList);
		android.app.Fragment fragment = new Fragment_Recovery_List();
		int container = R.id.container;
		boolean is = isFragmentRecoveryList(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentRecoveryList(String index) {
		boolean s;
		Fragment_Recovery_List fragments = (Fragment_Recovery_List) Main.myFragmentManager.findFragmentByTag(index);
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
