package com.tsvikdima.cloudnote;

import android.util.Log;

import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_List_add;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_List_edit;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_Task_add;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_Task_edit;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_View;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_add;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_edit;

public class ClassNotebook {
	/********************* Fragment_View_Task **************************/
	static void vFragment_Notebook(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleNotebook, Main.IconNotebook);
		android.app.Fragment fragment = new Fragment_Notebook();
		int container = R.id.container;
		boolean is = isFragmentNotebook(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentNotebook(String index) {
		boolean s;
		Fragment_Notebook fragments = (Fragment_Notebook) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	/********************* vFragment_Notebook_add **************************/
	static void vFragment_Notebook_add(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleNotebookAdd, Main.IconNotebookAdd);
		android.app.Fragment fragment = new Fragment_Notebook_add();
		int container = R.id.container;
		boolean is = isFragmentNotebookAdd(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentNotebookAdd(String index) {
		boolean s;
		Fragment_Notebook_add fragments = (Fragment_Notebook_add) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* vFragment_Notebook_edit **************************/
	static void vFragment_Notebook_edit(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleNoteBookEdit, Main.IconNotebookEdit);
		android.app.Fragment fragment = new Fragment_Notebook_edit();
		int container = R.id.container;
		boolean is = isFragmentNotebookEdit(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentNotebookEdit(String index) {
		boolean s;
		Fragment_Notebook_edit fragments = (Fragment_Notebook_edit) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	/********************* vFragment_Notebook_View **************************/
	static void vFragment_Notebook_View(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleNotebook, Main.IconNotebook);
		android.app.Fragment fragment = new Fragment_Notebook_View();
		int container = R.id.container;
		boolean is = isFragmentNotebookView(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentNotebookView(String index) {
		boolean s;
		Fragment_Notebook_View fragments = (Fragment_Notebook_View) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	

	/********************* vFragment_Notebook_List_add **************************/
	static void vFragment_Notebook_List_add(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewListAdd, Main.IconListAdd);
		android.app.Fragment fragment = new Fragment_Notebook_List_add();
		int container = R.id.container;
		boolean is = isFragmentNotebookListAdd(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentNotebookListAdd(String index) {
		boolean s;
		Fragment_Notebook_List_add fragments = (Fragment_Notebook_List_add) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* Fragment Note List Edit **************************/
	static void vFragment_Notebook_List_edit(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewListEdit, Main.IconListEdit);
		android.app.Fragment fragment = new Fragment_Notebook_List_edit();
		int container = R.id.container;
		boolean is = isFragmentNotebookListEdit(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}


	static boolean isFragmentNotebookListEdit(String index) {
		boolean s;
		Fragment_Notebook_List_edit fragments = (Fragment_Notebook_List_edit) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	

	/********************* Fragmen Note Task Add **************************/
	static void vFragment_Notebook_Task_add(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewTaskAdd, Main.IconTaskAdd);
		android.app.Fragment fragment = new Fragment_Notebook_Task_add();
		int container = R.id.container;
		boolean is = isFragmentNotebookTaskAdd(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}

	static boolean isFragmentNotebookTaskAdd(String index) {
		boolean s;
		Fragment_Notebook_Task_add fragments = (Fragment_Notebook_Task_add) Main.myFragmentManager.findFragmentByTag(index);
		if (fragments == null) {
			s = false;
		} else {
			s = true;
		}
		return s;

	}
	
	/********************* Fragment Note Task Edit **************************/
	static void vFragment_Notebook_Task_edit(String index) {
		Log("фрагмент  = " + index);
		Main.ActionBar(Main.TitleViewTaskEdit, Main.IconTaskEdit);
		android.app.Fragment fragment = new Fragment_Notebook_Task_edit();
		int container = R.id.container;
		boolean is = isFragmentNotebookTaskEdit(index);

		if (!is) {
			Log(index + " : Фрагмент не загружен, загружаем");
			Main.myFragmentManager.beginTransaction()
					.replace(container, fragment, index).commit();
		} else {
			Log(index + " : Фрагмент УЖЕ загружен");

		}
	}
	
	static boolean isFragmentNotebookTaskEdit(String index) {
		boolean s;
		Fragment_Notebook_Task_edit fragments = (Fragment_Notebook_Task_edit) Main.myFragmentManager.findFragmentByTag(index);
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
		String s1 = "Fragment_notebook";
		Log.d(TAG, s1 + " - " + s2);

	}
}
