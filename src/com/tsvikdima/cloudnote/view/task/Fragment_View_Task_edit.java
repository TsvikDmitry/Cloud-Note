package com.tsvikdima.cloudnote.view.task;

import java.util.ArrayList;
import java.util.List;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.dropbox.sync.android.DbxException;
import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;
import com.tsvikdima.cloudnote.dropbox.DropboxSunc;
import com.tsvikdima.cloudnote.dropbox.Table;

public class Fragment_View_Task_edit extends Fragment {
	static ArrayList<String> TaskList_id;
	static ArrayList<String> TaskList_name;

	// RelativeLayout rl;
	static Spinner sp;
	List<String> list;
	static Context context;
	static String id_note;
	public static boolean isFavorit = false;
	static ImageButton ImageFavorite;
	static String TaskTitle;
	static String TaskText;
	static EditText Title;
	static EditText Text;
	static ImageButton Finish;
	static LinearLayout  layout_finish;
	static boolean ImageFavorit = false; 
	public Fragment_View_Task_edit() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");
		View view = inflater.inflate(R.layout.fragment_mod_task, container,
				false);

		sp = (Spinner) view.findViewById(R.id.spinner_note);
		id_note = Main.getSetting(Main.ID_NOTE);
		context = getActivity();

		Title = (EditText) view.findViewById(R.id.task_mod_title);
		Text = (EditText) view.findViewById(R.id.task_mod_text);

		ImageFavorite = (ImageButton) view.findViewById(R.id.image_favorite);


		layout_finish = (LinearLayout) view.findViewById(R.id.layout_finish);

		final ImageButton ImageFavorite = (ImageButton) view.findViewById(R.id.image_favorite);
		
		
		return view;

	}

	public static void updateList() {

		ImageFavorite.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				ImageFavorit = !ImageFavorit;
				
				if (ImageFavorit){
					ImageFavorite.setImageResource(R.drawable.ic_favorit_on);
				}else {
					ImageFavorite.setImageResource(R.drawable.ic_favorit_off);
				}
			}
		});

		
		layout_finish.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {


				List<Table.Task> tasks = null;
				try {
					tasks = DropboxSunc.mTable.getTaskTableId(Main
							.getSetting(Main.ID_TASK));
				} catch (DbxException e) {
					DropboxSunc.handleException(e);
				}
				for (final Table.Task task : tasks) {

					try {
						task.setName(Title.getText().toString());
						task.setText(Text.getText().toString());
						task.setNoteID(id_note);
						task.setFavorite(isFavorit);
					} catch (DbxException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				Main.OpenFragment(Main.IndexViewTask);
			}
		});
		

		List<Table.Task> tasks = null;
		try {
			// 06-11 17:53:50.749: D/Note(3433): Activity_Main mSettings:
			// TASK_ID = De30qVedmuNM0l4IqEmtSg
			// De30qVedmuNM0l4IqEmtSg
			tasks = DropboxSunc.mTable.getTaskTableId(Main.getSetting(Main.ID_TASK));
			// tasks =
			// DropboxSunc.mTable.getTaskTableId("De30qVedmuNM0l4IqEmtSg");

		} catch (DbxException e) {
			DropboxSunc.handleException(e);
		}
		for (final Table.Task task : tasks) {

			isFavorit = task.isFavorite();
			TaskTitle = task.getName();
			TaskText = task.getText();

			Title.setText(TaskTitle);
			Text.setText(TaskText);

			if (isFavorit) {
				ImageFavorite.setImageResource(R.drawable.ic_favorit_on);
			} else {
				ImageFavorite.setImageResource(R.drawable.ic_favorit_off);
			}

		}

		List<Table.Note> notes;
		try {
			notes = DropboxSunc.mTable.getNoteTableAll();
		} catch (DbxException e) {
			// DropboxSunc.handleException(e);
			// return container;
			return;
		}
		TaskList_id = new ArrayList<String>();
		TaskList_name = new ArrayList<String>();

		for (final Table.Note note : notes) {

			TaskList_id.add(note.getId());
			TaskList_name.add(note.getName());
		}

		ArrayAdapter<String> adp = new ArrayAdapter<String>(context,
				R.layout.row_spinner, R.id.weekofday, TaskList_name);
		sp.setAdapter(adp);

		int size = TaskList_id.size() - 1;
		for (int i = 0; i <= size; i++) {

			if (TaskList_id.get(i).contains(Main.getSetting(Main.ID_NOTE))) {
				sp.setSelection(i);
				Log("setSelection" + i);
			} else {
				Log("" + i);
			}

		}

		sp.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

				Log("" + arg2);
				Log("" + TaskList_id.get(arg2));
				id_note = TaskList_id.get(arg2);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

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
		Main.setSetting(Main.ID_FRAGMENT, Main.IndexViewTaskEdit);

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

	}

	static void Log(String s2) {
		// Log("onCreate");
		String TAG = "Note";
		String s1 = "Fragment_Auth";
		Log.d(TAG, s1 + " - " + s2);

	}

}