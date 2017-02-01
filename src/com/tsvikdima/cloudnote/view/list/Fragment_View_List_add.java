package com.tsvikdima.cloudnote.view.list;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.dropbox.sync.android.DbxException;
import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;
import com.tsvikdima.cloudnote.dropbox.DropboxSunc;
import com.tsvikdima.cloudnote.dropbox.Table;

public class Fragment_View_List_add extends Fragment {
	static ArrayList<String> List_id;
	static ArrayList<String> List_name;

	// RelativeLayout rl;
	static Spinner sp;
	List<String> list;
	static String id_note;
	static boolean isFavorit = false;
	static TableLayout table_list_item;
	static LayoutInflater mInflater;
	static Context context, context2;
	static String id_task = "";
	static EditText Title, Text;

	public Fragment_View_List_add() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");
		View view = inflater.inflate(R.layout.fragment_mod_list, container,false);
		context2 = getActivity();
		context = getActivity().getApplicationContext();
		mInflater = LayoutInflater.from(context);
		sp = (Spinner) view.findViewById(R.id.spinner_note);
		context = getActivity();
		/*	для notebook, получения последнего блокнота
		id_note = Main.getSetting(Main.ID_NOTE);*/
		table_list_item = (TableLayout) view.findViewById(R.id.table_list_item);

		Title = (EditText) view.findViewById(R.id.task_mod_title);
		Text = (EditText) view.findViewById(R.id.task_mod_text);

		// favorite
		final ImageButton ImageFavorite = (ImageButton) view.findViewById(R.id.image_favorite);
		ImageFavorite.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				isFavorit = !isFavorit;
				if (isFavorit) {
					ImageFavorite.setImageResource(R.drawable.ic_favorit_on);
				} else {
					ImageFavorite.setImageResource(R.drawable.ic_favorit_off);
				}
			}
		});
		
		// button_list_add
		Button button_list_add = (Button) view.findViewById(R.id.button_list_add);
		button_list_add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (Text.getText().toString().length() != 0) {
					if (id_task.length() == 0) {
						createTask();
						createListItem();
					} else {
						updateTaskSave();
						createListItem();
					}
					Text.setText("");
				} else {
					Main.Toast("Введите текст, поле пустое!");
				}
			}
		});
		
		
		LinearLayout  layout_finish = (LinearLayout) view.findViewById(R.id.layout_finish);
		layout_finish.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				if (id_task.length() == 0) {
					createTask();

				} else {
					updateTaskSave();
				}
				Main.OpenFragment(Main.IndexViewList);
			}
		});

		return view;

	}

	public static void updateTaskSave() {
		List<Table.Task> tasks = null;
		try {
			tasks = DropboxSunc.mTable.getTaskTableId(id_task);
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
		}
		for (final Table.Task task : tasks) {

			try {
				task.setName(Title.getText().toString());
				task.setText("");
				task.setNoteID(id_note);
				task.setFavorite(isFavorit);
			} catch (DbxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void createListItem() {
        Log("createListItem");

        String normalTextEnc;
        try {
			DropboxSunc.mTable.CreateListItem(id_task,  Text.getText().toString());
		} catch (DbxException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

              
           
		

	}

	public static void createTask() {

		String name = Title.getText().toString();

	

		boolean password_test = false;
		String password = "";
		String color = "";

		try {
			DropboxSunc.mTable.CreateTask(id_note, name, "", true, isFavorit,
					password_test, password, color);
		} catch (DbxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Log("updateListNote");
		List<Table.Task> tasks;
		try {
			tasks = DropboxSunc.mTable.getTaskIdList();
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
			return;
		}

		for (final Table.Task task : tasks) {
			id_task = task.getId();
		}

	}

	public static void updateListItem() {

		List<Table.ListItem> lists;
		try {

			lists = DropboxSunc.mTable.getListItemId(id_task);

		} catch (DbxException e) {
			return;
		}
		table_list_item.removeAllViews();

		for (final Table.ListItem list : lists) {

			TableRow row = (TableRow) mInflater.inflate(R.layout.row_list_item,
					null);
			table_list_item.addView(row);

			TextView listItem = (TextView) row.findViewById(R.id.list_item);
			listItem.setText(list.getText());

			
			CheckBox check_button = (CheckBox) row.findViewById(R.id.check_button);
			if (list.isCheck()){
				check_button.setChecked(true);
			}else{
				check_button.setChecked(false);
			}			
			
			check_button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					try {
						list.toCheck();
					} catch (DbxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				}
			});
			
			
			ImageView delete_button = (ImageView) row
					.findViewById(R.id.delete_button);
			delete_button.setVisibility(View.VISIBLE);
			delete_button.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {

					try {
						list.delete();
					} catch (DbxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		}

	}

	public static void updateTask() {
		List<Table.Note> notes;
		try {
			notes = DropboxSunc.mTable.getNoteTableAll();
		} catch (DbxException e) {
			// DropboxSunc.handleException(e);
			// return container;
			return;
		}
		List_id = new ArrayList<String>();
		List_name = new ArrayList<String>();

		for (final Table.Note note : notes) {

			List_id.add(note.getId());
			List_name.add(note.getName());
		}

		ArrayAdapter<String> adp = new ArrayAdapter<String>(context,
				R.layout.row_spinner, R.id.weekofday, List_name);
		sp.setAdapter(adp);
		
		/*для определния курсора списка по id_note
		int size = List_id.size() - 1;
		for (int i = 0; i <= size; i++) {

			if (List_id.get(i).contains(Main.getSetting(Main.ID_NOTE))) {
				sp.setSelection(i);
				Log("setSelection" + i);
			} else {
				Log("" + i);
			}
			
		}*/	

		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				Log("" + arg2);
				Log("" + List_id.get(arg2));
				id_note = List_id.get(arg2);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	public void onStart() {
		super.onStart();
		Log("onStart");
		id_task = "";

	}

	public void onResume() {
		super.onResume();
		Log("onResume");
		id_task = "";

		updateTask();
		updateListItem();
		Main.setSetting(Main.ID_FRAGMENT, Main.IndexViewListAdd);

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
		updateListItem();
	}

	static void Log(String s2) {
		// Log("onCreate");
		String TAG = "Note";
		String s1 = "Fragment_View_List_add";
		Log.d(TAG, s1 + " - " + s2);

	}

}