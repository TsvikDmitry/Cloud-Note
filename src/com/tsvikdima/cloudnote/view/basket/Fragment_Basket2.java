package com.tsvikdima.cloudnote.view.basket;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.sync.android.DbxException;
import com.dropbox.sync.android.DbxRecord;
import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;
import com.tsvikdima.cloudnote.R.drawable;
import com.tsvikdima.cloudnote.R.id;
import com.tsvikdima.cloudnote.R.layout;
import com.tsvikdima.cloudnote.dropbox.DropboxSunc;
import com.tsvikdima.cloudnote.dropbox.Table;
import com.tsvikdima.cloudnote.dropbox.Table.Note;

public class Fragment_Basket2 extends Fragment {
	private static final String TAG = null;
	static AlertDialog.Builder ad;
	static Context context, context2;
	ListView lvActionMode, lvMain;
	static TableLayout tableNote_1, tableNote_2, tableNote_3, tableNote_4,
			tableNote_5;
	TableRow row;
	ArrayAdapter<String> monthAdapter, weekOfDayAdapter;
	Context MyContext;
	LayoutInflater ltInflater;
	static LayoutInflater mInflater;
	Button button, button1;
	int Image = R.drawable.icon_note_1;
	View view;
	static int index = 0;
	
	public Fragment_Basket2() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");
		
		view = inflater.inflate(R.layout.fragment_task_and_list,container, false);

		tableNote_1 = (TableLayout) view.findViewById(R.id.Table_1);
		tableNote_2 = (TableLayout) view.findViewById(R.id.Table_2);
		tableNote_3 = (TableLayout) view.findViewById(R.id.Table_3);

		
	//	Button text_task_add = (Button) view.findViewById(R.id.text_task_add);
		//text_task_add.setText("Очистить корзину");
		
	//	Drawable img = context.getResources().getDrawable( Main.IconTaskBasket);
	//	img.setBounds( 0, 0, 80, 100 );
		//text_task_add.setCompoundDrawables( img, null, null, null );
		/*
		LinearLayout idLayout = (LinearLayout) view.findViewById(R.id.button_task_add);
		idLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				//Main.OpenFragment(Main.IndexTaskAdd);
				List<Table.Task> tasks;
				try {
					//Main.getSetting(Main.ID_NOTE)
					tasks = DropboxSunc.mTable.getTaskTableBasket();
					//tasks = DropboxSunc.mTable.getTaskTable();
				} catch (DbxException e) {
					DropboxSunc.handleException(e);
					return;
				}
				for (final Table.Task task : tasks) {
					
					List<Table.ListItem> lists;
					try {
						
						lists =DropboxSunc.mTable.getListItemId(task.getId());

					
					} catch (DbxException e) {
						return;
					}

					
					for (final Table.ListItem list : lists) {
						try {
							list.delete();
						} catch (DbxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
						
						
					try {
						task.delete();
					} catch (DbxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});	
*/
		
		
		return view;
	}

	
	public static void updateList() {
		Log("updateListNote");
		List<Table.Task> tasks;
		try {
			//Main.getSetting(Main.ID_NOTE)
			tasks = DropboxSunc.mTable.getTaskTableBasket();
			//tasks = DropboxSunc.mTable.getTableTask();
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
			return;
		}

		// планшет
		if (Main.DISPLYA) {
			if (getOrientationPortrait()) {
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();

			}
			
			if (getOrientationLondscape()) {
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
				tableNote_3.removeAllViews();
			}
		// телефон	
		}else{
			if (getOrientationPortrait()) {
				tableNote_1.removeAllViews();

			}
			
			if (getOrientationLondscape()) {
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
			}
		}
		

		int index = 0;
		for (final Table.Task task : tasks) {

			
		
			// планшет
			if (Main.DISPLYA) {
				if (getOrientationPortrait()) {
					Log("onStart");
					if (index == 0) {
						index = 1;
						Log("transp0");
						row(tableNote_1, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());

					} else if (index == 1) {
						Log("transp1");
						index = 0;
						row(tableNote_2, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());
					}

				}
				
				if (getOrientationLondscape()) {
					Log("onStart");
					if (index == 0) {
						index = 1;
						Log("transp0");
						row(tableNote_1, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());

					} else if (index == 1) {
						Log("transp1");
						index = 2;
						row(tableNote_2, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());
					} else if (index == 2) {
						Log("transp1");
						index = 0;
						row(tableNote_3, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());
					}
				}
			// телефон	
			}else{
				if (getOrientationPortrait()) {
					row(tableNote_1, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());

				}
				
				if (getOrientationLondscape()) {
					Log("onStart");
					if (index == 0) {
						index = 1;
						Log("transp0");
						row(tableNote_1, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());

					} else if (index == 1) {
						Log("transp1");
						index = 0;
						row(tableNote_2, task.getId(), task.getName(), task.getText(), R.color.transp1, task.isFavorite());
					}
				}
			}
		}	
	}
	
			
			

	

	public static boolean getOrientationLondscape() {
		boolean ORIENTATION = false;
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
			return ORIENTATION = true;
		return ORIENTATION;
	}

	public static boolean getOrientationPortrait() {
		boolean ORIENTATION = false;
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
			return ORIENTATION = true;
		return ORIENTATION;
	}

	public static void row(
			TableLayout TableTask,
			final String id_task,
			String title,
			String text, 
			int color,
			boolean favorite
			) {

		TableRow row = (TableRow) mInflater.inflate(R.layout.row_task, null);
		TableTask.addView(row);
		
		TextView Title = (TextView) row.findViewById(R.id.name);
		Title.setText(title);
		TextView Text = (TextView) row.findViewById(R.id.text);
		if (text.length()!=0){
			 if (text.length()<=100){
					Text.setText(text.subSequence(0, text.length()));

				}else{
					Text.setText(text.subSequence(0, 100));
				}
		}else {
			Text.setText("");
		}
				
		LinearLayout idLayout = (LinearLayout) row.findViewById(R.id.layout_2);
		idLayout.setBackgroundResource(color);

		LinearLayout Layout = (LinearLayout) row.findViewById(R.id.layout_1);
		Layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Main.setSetting(Main.ID_TASK, id_task);
				//Main.OpenFragment(Main.IndexTaskRecovery);
			
			}
		});

		Layout.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {

				onDialog(id_task);

			}

		});


	}

	public void onStart() {
		super.onStart();
		Log("onStart");

	}

	public void onResume() {
		super.onResume();
		Log("onResume");
		updateList();
		Main.setSetting(Main.ID_FRAGMENT, Main.IndexBasket);

	}

	public void onDestroy() {
		super.onDestroy();
		Log("onDestroy");

	}

	public void onPause() {
		super.onPause();
		Log("onPause");

	}

	static void Log(String s2) {
		// Log("onCreate");
		String TAG = "Note";
		String s1 = "Fragment_Note";
		Log.d(TAG, s1 + " - " + s2);

	}

	protected static void onDialog(final String id) {
		String[] mCatsName = {""};
/*
		List<Table.Task> tasks;
		try {
			tasks = DropboxSunc.mTable.getTaskTableId(id);
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
			return;
		}
		for (final Table.Task task : tasks) {
			if (task.isFavorite()){
				mCatsName = new String[] { "Удалить из избранных", "Добавить в корзину" };
			}else{
				
			}

		}*/
		
		mCatsName = new String[] { "Восстановить", "Удалить" };
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context2);
	  //builder.setTitle("");
		builder.setItems(mCatsName, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {

				if (0 == item) {
					/*
					try {
						Table.TaskBasket(id,false);

					} catch (DbxException e) {
						e.printStackTrace();
					}*/
					Main.setSetting(Main.ID_TASK, id);
					//Main.OpenFragment(Main.IndexTaskRecovery);
				} else if (1 == item) {

						try {
							Table.TaskDeleteID(id);

						} catch (DbxException e) {
							e.printStackTrace();
						}	
						
						List<Table.ListItem> lists;
						try {
							
							lists =DropboxSunc.mTable.getListItemId(id);

						
						} catch (DbxException e) {
							return;
						}

						
						for (final Table.ListItem list : lists) {
							try {
								list.delete();
							} catch (DbxException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						
				}

			}
		});
		builder.setNegativeButton("Отмена",
				new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();

					}
				});
		builder.setCancelable(false);
		builder.create();
		builder.show();

	}

	void setDialog(String text) {
		Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
	}



	public static void onUpdate() {
		// TODO Auto-generated method stub
		// updateListNote();
	}


}