package com.tsvikdima.cloudnote;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.sync.android.DbxAccountManager;
import com.dropbox.sync.android.DbxException;
import com.tsvikdima.cloudnote.dropbox.DropboxSunc;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_List_add;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_List_edit;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_Task_add;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_Task_edit;
import com.tsvikdima.cloudnote.v.notebook.Fragment_Notebook_View;
import com.tsvikdima.cloudnote.view.basket.Fragment_Basket;
import com.tsvikdima.cloudnote.view.basket.Fragment_Recovery_List;
import com.tsvikdima.cloudnote.view.basket.Fragment_Recovery_Task;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_add;
import com.tsvikdima.cloudnote.view.list.Fragment_View_List_edit;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_add;
import com.tsvikdima.cloudnote.view.task.Fragment_View_Task_edit;

public class Main extends Activity {
	// объ€вление классов
	static FragmentManager myFragmentManager;

	
	//public static InputMethodManager keyboard;
	
	/***************** View List *****************************/
	//Title
	public static int TitleViewList = R.string.title_view_list;
	public static int TitleViewListEdit = R.string.title_view_list_edit;
	public static int TitleViewListAdd = R.string.title_view_list_add;
	//Title
	public static String IndexViewList = "IndexViewList";
	public static String IndexViewListEdit = "IndexViewListEdit";
	public static String IndexViewListAdd = "IndexViewListAdd";
	//Icon
	public static int IconList = R.drawable.cold_list;
	public static int IconListAdd = R.drawable.cold_list_add;
	public static int IconListEdit = R.drawable.cold_list_add;
	
	
	/***************** View Task *****************************/
	//Title
	public static int TitleViewTask = R.string.title_view_task;
	public static int TitleViewTaskEdit = R.string.title_view_task_edit;
	public static int TitleViewTaskAdd = R.string.title_view_task_add;
	//Title
	public static String IndexViewTask = "IndexViewTask";
	public static String IndexViewTaskEdit = "IndexViewTaskEdit";
	public static String IndexViewTaskAdd = "IndexViewTaskAdd";
	//Icon
	public static int IconTask = R.drawable.cold_task;
	public static int IconTaskAdd = R.drawable.cold_task_add;
	public static int IconTaskEdit = R.drawable.cold_task_add;
	
	/***************** IndexBasket *****************************/
	
	public static int TitleBasket = R.string.title_basket;
	public static int TitleRecoveryTask = R.string.title_recovery_task;
	public static int TitleRecoveryList = R.string.title_recovery_list;
	
	public static String IndexBasket = "IndexBasket";
	public static String IndexRecoveryList = "IndexRecoveryList";
	public static String IndexRecoveryTask = "IndexRecoveryTask";

	public static int IconBasket = R.drawable.cold_brakset;

	/***************** Auth *****************************/
	public static String IndexAuth = "IndexAuth";
	
	public static int TitleAuth = R.string.title_auth;
	
	public static int IconAuth = R.drawable.icon_settings;

	/***************** Settings *****************************/
	public static final String Password = "Password";
	public static final String PasswordChek = "PasswordChek";
	public static final String PasswordCount = "PasswordCount";
	
	public static String IndexSettings = "IndexSettings";
	public static String IndexPassword  = "IndexPassword";
	public static String IndexPasswordSave  = "IndexPasswordSave";
	
	public static int TitleSettings = R.string.title_settings;
	public static int TitlePassword = R.string.title_password;
	public static int TitlePasswordSave = R.string.title_password_save;
	
	public static int IconPassword = R.drawable.icon_settings;
	public static int IconPasswordSave = R.drawable.icon_settings;
	public static int IconSettings = R.drawable.icon_settings;
	
	/***************** Drawer *****************************/

	public static int TitleDrawer = R.string.title_drawer;
	public static int IconDrawer = R.drawable.cold_icon;
	public static String IndexDrawer = "IndexDrawer";


	/*********************** Note ***********************************/
	public static int IconNotebook = R.drawable.cold_note;
	public static int IconNotebookAdd = R.drawable.cold_note_add;
	public static int IconNotebookEdit = R.drawable.cold_note_add;

	// Note
	public static int TitleNotebook = R.string.title_notes;
	public static int TitleNotebookAdd = R.string.title_note_add;
	public static int TitleNoteBookEdit = R.string.title_note_edit;
	public static int TitleNotebookView = R.string.title_note;
	public static int TitleNotebookTaskEdit = R.string.title_view_task_edit;
	public static int TitleNotebookdTaskAdd = R.string.title_view_task_add;
	public static int TitleNotebookdListEdit = R.string.title_view_list_edit;
	public static int TitleNotebookListAdd = R.string.title_view_list_add;

	public static String IndexNotebook = "IndexNotebook";
	public static String IndexNotebookAdd = "IndexNotebookAdd";
	public static String IndexNotebookEdit = "IndexNotebookEdit";
	public static String IndexNotebookView = "IndexNotebookView";
	public static String IndexNotebookTaskEdit = "IndexNotebookTaskEdit";
	public static String IndexNotebookTaskAdd = "IndexNotebookTaskAdd";
	public static String IndexNotebookListEdit = "IndexNotebookListEdit";
	public static String IndexNotebookListAdd = "IndexNotebookListAdd";

	/************************Favorit**********************************/

	public static int TitleFavorit = R.string.title_favorit;
	public static String IndexFavorit = "IndexFavorit";
	/**********************************************************/


	public static final String ID_FRAGMENT = "FRAGMENT_ID";
	public static final String ID_NOTE = "NOTE_ID";
	public static final String ID_TASK = "TASK_ID";


	/* переменные Drawer */
	public static int mActionBarTitle;
	public static int mActionBarIcon;
	public static DrawerLayout mDrawerLayout,mDrawerLayout1;
	public static TableLayout TableDrawer, TableDrawer_two;
	public static ActionBarDrawerToggle mDrawerToggle,mDrawerToggle1;
	public static ActionBar mActionBar;
	public static RelativeLayout LayoutDrawer, LayoutDrawer2;

	// переменные
	public static boolean mDrawer = false;
	public static boolean mDrawer2 = false;

	boolean TableRow = true;
	public static SharedPreferences mSettings;
	public ActionBar actionBar;
	public static Context context;

	// параметры дл€ проверки размера экрана
	public static DisplayMetrics displaymetrics;
	public static DisplayMetrics metricsB;
	public static boolean DISPLYA = false;
	private static boolean isTabletModeDetermined = false;
	private static boolean isTabletMode = false;
	public static int pass_tima = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// getActionBar().setDisplayHomeAsUpEnabled(true);
		Log("onCreate");

		context = this;

		/** DropboxSunc **/
		DropboxSunc.mDbxAcctMgr = DbxAccountManager.getInstance(
				getApplicationContext(), DropboxSunc.APP_KEY,
				DropboxSunc.APP_SECRET);

		/* объ€вление полей дл€ сохранение в xml */
		mSettings = getSharedPreferences(ID_FRAGMENT, Context.MODE_PRIVATE);
		mSettings = getSharedPreferences(ID_NOTE, Context.MODE_PRIVATE);
		mSettings = getSharedPreferences(ID_TASK, Context.MODE_PRIVATE);
		mSettings = getSharedPreferences(Password, Context.MODE_PRIVATE);
		mSettings = getSharedPreferences(PasswordChek, Context.MODE_PRIVATE);
		mSettings = getSharedPreferences(PasswordCount, Context.MODE_PRIVATE);
		
		mActionBar = getActionBar();
		myFragmentManager = getFragmentManager();
		
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
		//mDrawerLayout1 = (DrawerLayout) findViewById(R.id.drawer_layout);
		//mDrawerLayout1.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.END);
		LayoutDrawer = (RelativeLayout) findViewById(R.id.layout_drawer);
		//LayoutDrawer2 = (RelativeLayout) findViewById(R.id.layout_drawer2);

		mDrawerLayoutLift();
		//mDrawerLayoutLift2();
		
		// дл€ заркыти€ клавиатуры принудительно
		//keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

		// ”знаем размеры экрана из ресурсов
		displaymetrics = getResources().getDisplayMetrics();
		DISPLYA = isTablet(Main.this);

	}

	/******************** mDrawer ***************************/

	
	
	void mDrawerLayoutLift() {
		Log("mDrawer");

		
		mActionBarIcon = IconNotebook;
		mActionBarTitle = TitleNotebook;
		


		/* Button */

		ImageButton but_note_add_dw = (ImageButton) findViewById(R.id.button_note_add_dw);
		but_note_add_dw.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				OpenFragment(IndexNotebookAdd);
				mDrawerLayout.closeDrawer(LayoutDrawer);
			}
		});

		ImageButton but_task_add_dw = (ImageButton) findViewById(R.id.button_task_add_dw);
		but_task_add_dw.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				OpenFragment(IndexViewTaskAdd);
				mDrawerLayout.closeDrawer(LayoutDrawer);

			}
		});

		ImageButton but_list_add_dw = (ImageButton) findViewById(R.id.button_list_add_dw);
		but_list_add_dw.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				OpenFragment(IndexViewListAdd);
				mDrawerLayout.closeDrawer(LayoutDrawer);
			}
		});
		/* mDrawer_Table */

		TableDrawer = (TableLayout) findViewById(R.id.table_drawer);
		TableDrawer.removeAllViews();

		//note
		mDrawer_Row(TableDrawer, R.layout.drawer_row, R.id.drawer_text,
				TitleNotebook, IndexNotebook, R.id.drawer_layout_2, IconNotebook);
		//tak
		mDrawer_Row(TableDrawer, R.layout.drawer_row, R.id.drawer_text,
				TitleViewTask, IndexViewTask, R.id.drawer_layout_2, IconTask);
		//list
		mDrawer_Row(TableDrawer, R.layout.drawer_row, R.id.drawer_text,
				TitleViewList, IndexViewList, R.id.drawer_layout_2, IconList);
		//Basket
		mDrawer_Row(TableDrawer, R.layout.drawer_row, R.id.drawer_text,
				TitleBasket, IndexBasket, R.id.drawer_layout_2, IconBasket);
		//setting
		mDrawer_Row(TableDrawer, R.layout.drawer_row, R.id.drawer_text,
				TitleSettings, IndexSettings, R.id.drawer_layout_2,
				IconSettings);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
				R.drawable.ic_drawer, R.string.drawer_open,
				R.string.drawer_close) {
			public void onDrawerClosed(View view) {
				mActionBar.setTitle(mActionBarTitle);
				mActionBar.setIcon(mActionBarIcon);
				invalidateOptionsMenu();
				mDrawer = false;
			}

			public void onDrawerOpened(View drawerView) {
				mActionBar.setTitle(R.string.title_drawer);
				mActionBar.setIcon(IconDrawer);
				invalidateOptionsMenu();
				mDrawer = true;
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

	}

	/******************** mDrawer_Row ***************************/
	void mDrawer_Row(TableLayout TableDrawer, int drawer_row, int drawer_text,
			final int TitleNote, final String IndexNote, int drawer_layout_2,
			int IconNote) {

		TableRow row = (TableRow) getLayoutInflater().inflate(drawer_row, null);
		TableDrawer.addView(row);

		TextView text_row = (TextView) row.findViewById(drawer_text);
		text_row.setText(TitleNote);

		ImageView Image = (ImageView) row.findViewById(R.id.drawer_image);
		Image.setImageResource(IconNote);

		LinearLayout layout = (LinearLayout) row.findViewById(drawer_layout_2);
		layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				OpenFragment(IndexNote);
				mDrawerLayout.closeDrawer(LayoutDrawer);
			}
		});

	}

	/******************** mDrawer ***************************/

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}

	/******************** mDrawer ***************************/

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		mDrawerToggle.onConfigurationChanged(newConfig);
	}


	/************** ActionBar ********************************/

	static void ActionBar(int title, int icon) {
		if (title != 0) {
			mActionBarTitle = title;
			mActionBar.setTitle(title);
		}
		if (icon != 0) {
			mActionBarIcon = icon;
			mActionBar.setIcon(icon);
		}
	}

	static void visibleActionBar(boolean i) {
		if (i) {
			mActionBar.setDisplayHomeAsUpEnabled(true);
			mActionBar.setHomeButtonEnabled(true);
		} else {
			mActionBar.setDisplayHomeAsUpEnabled(false);
			mActionBar.setHomeButtonEnabled(false);
		}
	}

	/******************** Auth ***************************/
	public static void Auth() {
		if (DropboxSunc.mDbxAcctMgr.hasLinkedAccount()) {
			Log("Auth аундитификаци€ прошла");
			DropboxSunc.showTasksView();
			

			if (getPasswordChek()){
				if (pass_tima==0){
					OpenFragment(IndexPassword);
					LayoutDrawer.setVisibility(2);	
				}else {
					Open();
				}		
			}else{
				
				Open();
				
			}

		} else {
			Log("onResume аундитификаци€ не прошла");
			OpenFragment(IndexAuth);
			visibleActionBar(false);
		}

	}
	
	
	
	public static void Open() {
		
		LayoutDrawer.setVisibility(0);
		
		visibleActionBar(true);
	
		if (getSetting(ID_FRAGMENT).length() != 0) {
			OpenFragment(getSetting(ID_FRAGMENT));
		} else {
			OpenFragment(IndexNotebook);
		}
		
		
		Log("OpenFragment = " + getSetting(ID_FRAGMENT));
	}
	
	
	/****************** OpenFragment ******************/	
	
	public static void OpenFragment(String index) {
		
		/**Notebook View*/
		if (IndexNotebookView.equals(index)) {
			ClassNotebook.vFragment_Notebook_View(index);
		}
		/**Notebook List*/

		if (IndexNotebookListAdd.equals(index)) {
			ClassNotebook.vFragment_Notebook_List_add(index);
		}
		if (IndexNotebookListEdit.equals(index)) {
			ClassNotebook.vFragment_Notebook_List_edit(index);
		}
		
		/**Notebook Task*/

		if (IndexNotebookTaskAdd.equals(index)) {
			ClassNotebook.vFragment_Notebook_Task_add(index);
		}
		if (IndexNotebookTaskEdit.equals(index)) {
			ClassNotebook.vFragment_Notebook_Task_edit(index);
		}
		
		
		/**Notebook*/
		if (IndexNotebook.equals(index)) {
			ClassNotebook.vFragment_Notebook(index);
		}
		if (IndexNotebookAdd.equals(index)) {
			ClassNotebook.vFragment_Notebook_add(index);
		}
		if (IndexNotebookEdit.equals(index)) {
			ClassNotebook.vFragment_Notebook_edit(index);
		}

		/**List*/
		if (IndexViewList.equals(index)) {
			ClassViewList.vFragment_View_List(index);
		}
		if (IndexViewListAdd.equals(index)) {
			ClassViewList.vFragment_View_List_add(index);
		}
		if (IndexViewListEdit.equals(index)) {
			ClassViewList.vFragment_View_List_edit(index);
		}
		
		/**Task*/
		if (IndexViewTask.equals(index)) {
			ClassViewTask.vFragment_View_Task(index);
		}
		if (IndexViewTaskAdd.equals(index)) {
			ClassViewTask.vFragment_View_Task_add(index);
		}
		if (IndexViewTaskEdit.equals(index)) {
			ClassViewTask.vFragment_View_Task_edit(index);
		}
		
		/**Basket*/
		if (IndexBasket.equals(index)) {
			ClassBasket.vFragment_Basket(index);
		}
		if (IndexRecoveryList.equals(index)) {
			ClassBasket.vFragment_Recovery_List(index);
		}
		if (IndexRecoveryTask.equals(index)) {
			ClassBasket.vFragment_Recovery_Task(index);
		}
		/**Auth*/
		if (IndexAuth.equals(index)) {
			ClassSettings.vFragment_Auth(index);
		}
		/**Settings*/
		if (IndexSettings.equals(index)) {
			ClassSettings.vFragment_Settings(index);
		} 
		/**Settings*/
		if (IndexPassword.equals(index)) {
			ClassSettings.vFragment_Password(index);
		}
		if (IndexPasswordSave.equals(index)) {
			ClassSettings.vFragment_Password_Save(index);
		}
			
		
	}

	/****************** update ******************/

	public static void update() {
		
		//
		if (ClassNotebook.isFragmentNotebook(IndexNotebook)) {
			Fragment_Notebook.onUpdate();
		}

		//
		if (ClassNotebook.isFragmentNotebookView(IndexNotebookView)) {
			Fragment_Notebook_View.onUpdate();
		}
		
		if (ClassNotebook.isFragmentNotebookListAdd(IndexNotebookListAdd)) {
			Fragment_Notebook_List_add.onUpdate();
		}
		if (ClassNotebook.isFragmentNotebookListEdit(IndexNotebookListEdit)) {
			Fragment_Notebook_List_edit.onUpdate();
		}
		
		if (ClassNotebook.isFragmentNotebookTaskAdd(IndexNotebookTaskAdd)) {
			Fragment_Notebook_Task_add.onUpdate();
		}	
		
		if (ClassNotebook.isFragmentNotebookTaskEdit(IndexNotebookTaskEdit)) {
			Fragment_Notebook_Task_edit.onUpdate();
		}
		//
		if (ClassViewList.isFragmentViewList(IndexViewList)) {
			Fragment_View_List.onUpdate();
		}
		if (ClassViewList.isFragmentViewListAdd(IndexViewListAdd)) {
			Fragment_View_List_add.onUpdate();
		}
		if (ClassViewList.isFragmentViewListEdit(IndexViewListEdit)) {
			Fragment_View_List_edit.onUpdate();
		}
		//task
		if (ClassViewTask.isFragmentViewTask(IndexViewTask)) {
			Fragment_View_Task.onUpdate();
		}
		if (ClassViewTask.isFragmentViewTaskAdd(IndexViewTaskAdd)) {
			Fragment_View_Task_add.onUpdate();
		}
		if (ClassViewTask.isFragmentViewTaskEdit(IndexViewTaskEdit)) {
			Fragment_View_Task_edit.onUpdate();
		}
		//task
		if (ClassBasket.isFragmentViewBasket(IndexBasket)) {
			Fragment_Basket.onUpdate();
		}
		if (ClassBasket.isFragmentRecoveryList(IndexRecoveryList)) {
			Fragment_Recovery_List.onUpdate();
		}
		if (ClassBasket.isFragmentRecoveryTask(IndexRecoveryTask)) {
			Fragment_Recovery_Task.onUpdate();
		}

	}
	/****************** onBackPressed ******************/	
	
	@Override
	public void onBackPressed() {
		Log("обработчик нажати€ кнопки Back");
		
		if (ClassNotebook.isFragmentNotebookListAdd(IndexNotebookListAdd)) {
			OpenFragment(IndexNotebookView);
		}
		
		if (ClassNotebook.isFragmentNotebookTaskAdd(IndexNotebookTaskAdd)) {
			OpenFragment(IndexNotebookView);
		}
		// note
		if (ClassNotebook.isFragmentNotebook(IndexNotebook)) {
			finish();
		}
		if (ClassNotebook.isFragmentNotebookAdd(IndexNotebookAdd)) {
			OpenFragment(IndexNotebook);
		}		
		if (ClassNotebook.isFragmentNotebookEdit(IndexNotebookEdit)) {
			OpenFragment(IndexNotebook);
		}	
		
		if (ClassNotebook.isFragmentNotebookView(IndexNotebookView)) {
			OpenFragment(IndexNotebook);
		}	
		// NotebookList
		if (ClassNotebook.isFragmentNotebookListEdit(IndexNotebookListEdit)) {
			OpenFragment(IndexNotebookView);
		}
	
		
		// NotebookList
		if (ClassNotebook.isFragmentNotebookTaskEdit(IndexNotebookTaskEdit)) {
			OpenFragment(IndexNotebookView);
		}

		
		// list
		if (ClassViewList.isFragmentViewList(IndexViewList)) {
			OpenFragment(IndexNotebook);
		}
		if (ClassViewList.isFragmentViewListAdd(IndexViewListAdd)) {
			OpenFragment(IndexViewList);
		}		
		if (ClassViewList.isFragmentViewListEdit(IndexViewListEdit)) {
			OpenFragment(IndexViewList);
		}
		
		
		//task
		if (ClassViewTask.isFragmentViewTask(IndexViewTask)) {
			OpenFragment(IndexNotebook);
		}
		if (ClassViewTask.isFragmentViewTaskAdd(IndexViewTaskAdd)) {
			OpenFragment(IndexViewTask);
		}		
		if (ClassViewTask.isFragmentViewTaskEdit(IndexViewTaskEdit)) {
			OpenFragment(IndexViewTask);
		}
		
		//Basket
		if (ClassBasket.isFragmentViewBasket(IndexBasket)) {
			OpenFragment(IndexNotebook);
		}
		if (ClassBasket.isFragmentRecoveryList(IndexRecoveryList)) {
			OpenFragment(IndexBasket);
		}	
		if (ClassBasket.isFragmentRecoveryTask(IndexRecoveryTask)) {
			OpenFragment(IndexBasket);
		}	
		
		
		//Settings
		if (ClassSettings.isFragmentSettings(IndexSettings)) {
			OpenFragment(IndexNotebook);
		}
		if (ClassSettings.isFragmentPasswordSave(IndexPasswordSave)) {
			OpenFragment(IndexSettings);
		}
		if (ClassSettings.isFragmentPassword(IndexPassword)) {
			finish();
		}
		

		
	}

	
	/****************** onStart ******************/
	
	public void onStart() {
		super.onStart();
		Log("onStart");
	}
	
	/****************** onResume ******************/

	public void onResume() {
		super.onResume();
		Log("onResume");
		Auth();
	}


	/****************** onPause ******************/
	
	public void onPause() {
		super.onPause();
		Log("onPause");
		if (DropboxSunc.mDatastore != null) {
			DropboxSunc.mDatastore
					.removeSyncStatusListener(DropboxSunc.mDatastoreListener);
			DropboxSunc.mDatastore.close();
			DropboxSunc.mDatastore = null;
			Log("mDatastore != null");
		}
	}
	
	
	/****************** onDestroy ******************/

	public void onDestroy() {
		super.onDestroy();
		Log("onDestroy");
	}

	/****************** Log ******************/

	static void Log(String s2) {
		String TAG = "Note";
		String s1 = "Activity_Main";
		Log.d(TAG, s1 + "  " + s2);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == DropboxSunc.REQUEST_LINK_TO_DBX) {
			if (resultCode == RESULT_OK) {
				DropboxSunc.showTasksView();
			} else {
				Toast.makeText(this, "Link to Dropbox failed.ќшибка!!",
						Toast.LENGTH_SHORT).show();
			}
		} else {
			super.onActivityResult(requestCode, resultCode, data);
		}
	}

	public static void handleException(DbxException e) {
		e.printStackTrace();
		Toast.makeText(context, "—борй системы. ѕерезагрузите приложени€",
				Toast.LENGTH_SHORT).show();
	}



	/***************** Menu ***************************/

	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		switch (item.getItemId()) {/*
		case R.id.action_search:
			//OpenFragment(IndexTask);
			return true;
		case R.id.action_settings:
			return true;*/
		default:
			return super.onOptionsItemSelected(item);
		}
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu items for use in the action bar
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main_activity_actions, menu);
		return super.onCreateOptionsMenu(menu);
	}*/


	/************* проверка на размер экрана *************/
	public static boolean isTablet(Context paramContext) {
		if (!isTabletModeDetermined) {
			if (paramContext.getResources().getConfiguration().smallestScreenWidthDp >= 600)
				isTabletMode = true;
			isTabletModeDetermined = true;
		}
		return isTabletMode;
	}


	/********** сохранение параметров в xml ************/
	public static void setSetting(String parametr, String id) {

		Editor editor;
		editor = mSettings.edit();

		if (parametr.equals(ID_FRAGMENT)) {
			editor.putString(ID_FRAGMENT, id);
		}
		if (parametr.equals(ID_NOTE)) {
			editor.putString(ID_NOTE, id);
		}
		if (parametr.equals(ID_TASK)) {
			editor.putString(ID_TASK, id);
		}
		editor.apply();

	}

	// чтение параметров из xml
	public static String getSetting(String parametr) {
		String s = mSettings.getString(parametr, "");

		Log(" mSettings: " + parametr + " = " + s);
		return s;

	}
	
	
	/***********************set PassWord *****************************/
	
	public static void setPassword(String password) {
		Editor editor;
		editor = mSettings.edit();
		editor.putString(Password, password);
		editor.apply();
		Log("getPasswword - "+getPasswword());
	}

	public static void setPasswordChek(boolean chek) {
		Editor editor;
		editor = mSettings.edit();
		editor.putBoolean(PasswordChek, chek);
		editor.apply();

	}
	public static void setPasswordCount(int i) {
		Editor editor;
		editor = mSettings.edit();
		editor.putInt(PasswordCount, i);
		editor.apply();

	}
	
	/***********************set PassWord *****************************/
	public static String getPasswword() {
		String pass = mSettings.getString(Password, "");
		Log("getPasswword: "+pass);
		return pass;
	}	
	
	public static boolean getPasswordChek() {
		boolean chek = mSettings.getBoolean(PasswordChek, false);
		Log("getPasswordChek: "+chek);
		return chek;
	}	
	
	public static int getPasswordCount() {
		int count = mSettings.getInt(PasswordCount, 5);
		Log(" getPasswordCount: "+count);
		return count;
	}
	
	public static void Toast(String toast) {

		Toast.makeText(context, toast, Toast.LENGTH_SHORT).show();

	}

}
