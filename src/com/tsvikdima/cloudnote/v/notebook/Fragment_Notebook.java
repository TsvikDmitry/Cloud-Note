package com.tsvikdima.cloudnote.v.notebook;

import java.util.List;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.dropbox.sync.android.DbxException;
import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;
import com.tsvikdima.cloudnote.dropbox.DropboxSunc;
import com.tsvikdima.cloudnote.dropbox.Table;


public class Fragment_Notebook extends Fragment {
	private static final String TAG = null;
	static AlertDialog.Builder ad;
	static Context context, context2;
	ListView lvActionMode, lvMain;
	static TableLayout tableNote_1, tableNote_2, tableNote_3, tableNote_4,
			tableNote_5;
	TableRow row;
	ArrayAdapter<String> monthAdapter, weekOfDayAdapter;
	Context MyContext;
	static LayoutInflater mInflater;
	Button button, button1;
	int Image = R.drawable.icon_note_1;
	View view;
	private static Handler mHandler = new Handler();
	public Fragment_Notebook() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");


		view = inflater.inflate(R.layout.fragment_note,container, false);

		tableNote_1 = (TableLayout) view.findViewById(R.id.Table_1);
		tableNote_2 = (TableLayout) view.findViewById(R.id.Table_2);
		tableNote_3 = (TableLayout) view.findViewById(R.id.Table_3);
		tableNote_4 = (TableLayout) view.findViewById(R.id.Table_4);

		context2 = getActivity();
		context = getActivity().getApplicationContext();
		mInflater = LayoutInflater.from(context);

		LinearLayout idLayout = (LinearLayout) view.findViewById(R.id.button_note_add);
		idLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Main.OpenFragment(Main.IndexNotebookAdd);
				
			}
		});
		
		
		return view;
	}

	private static Runnable TimeUpdater = new Runnable() {
		public void run() {
			updateList1();
			
		}
	};
	
	public static void onUpdate() {
		
		mHandler.postDelayed(TimeUpdater, 400);
	}
	

	public static void updateList1() {
		Log("updateListNote");
		List<Table.Note> notes;
		try {
			notes = DropboxSunc.mTable.getNoteTableAll();
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
			return;
		}

		
		if (getOrientationLondscape()) {
			if (Main.DISPLYA) {
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
				tableNote_3.removeAllViews();
				tableNote_4.removeAllViews();
			}else{
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
				tableNote_3.removeAllViews();
			}
		}
		
		if (getOrientationPortrait()) {
			if (Main.DISPLYA) {
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
				tableNote_3.removeAllViews();
			}else{
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
			}
		}

		int index = 0;
		for (final Table.Note note : notes) {

		
			//планшет 4  телефон 3
			if (getOrientationLondscape()) {
				if (Main.DISPLYA) {
					if (index == 0) {
						index = 1;
						row(tableNote_1, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 1) {
						index = 2;
						row(tableNote_2, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 2) {
						index = 3;
						row(tableNote_3, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 3) {
						index = 0;
						row(tableNote_4, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					}
				}else{
					if (index == 0) {
						index = 1;
						row(tableNote_1, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 1) {
						index = 2;
						row(tableNote_2, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 2) {
						index = 0;
						row(tableNote_3, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} 				
				}			//планшет 3  телефон 2
	
			}else if (getOrientationPortrait()) {
				if (Main.DISPLYA) {
					if (index == 0) {
						index = 1;
						row(tableNote_1, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 1) {
						index = 2;
						row(tableNote_2, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 2) {
						index = 0;
						row(tableNote_3, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} 
				}else{
					if (index == 0) {
						index = 1;
						row(tableNote_1, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
					} else if (index == 1) {
						index = 0;
						row(tableNote_2, note.getImage(), note.isFavorite(),
								note.getName(), note.getId());
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

	public static void row(TableLayout tableNote, String Image,
			boolean favorite, String Title, final String id_note) {

		TableRow row = (TableRow) mInflater.inflate(R.layout.row_note, null);

		tableNote.addView(row);

		ImageView image_1 = (ImageView) row.findViewById(R.id.image_1);
		image_1.setImageResource(Integer.parseInt(Image));

		ImageView image_2 = (ImageView) row.findViewById(R.id.image_2);
		if (favorite) {
			image_2.setImageResource(R.drawable.ic_favorit_on);
		} else {
			image_2.setImageResource(R.drawable.ic_favorit_off);
		}

		TextView name = (TextView) row.findViewById(R.id.title);
		name.setText(Title);

		LinearLayout idLayout = (LinearLayout) row.findViewById(R.id.layout_1);
		idLayout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				Main.setSetting(Main.ID_NOTE, id_note);
			  Main.OpenFragment(Main.IndexNotebookView);
				
			}
		});

		idLayout.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {

				onDialog(id_note);

			}

		});
		Log("onStart");

	}

	public void onStart() {
		super.onStart();
		Log("onStart");

	}

	public void onResume() {
		super.onResume();
		Log("onResume");
		onUpdate();
	
		Main.setSetting(Main.ID_FRAGMENT, Main.IndexNotebook);
	}

	public void onDestroy() {
		super.onDestroy();
		Log("onDestroy");

	}

	public void onPause() {
		super.onPause();
		Log("onPause");
		mHandler.removeCallbacks(TimeUpdater);

	}

	static void Log(String s2) {
		// Log("onCreate");
		String TAG = "Note";
		String s1 = "Fragment_Note";
		Log.d(TAG, s1 + " - " + s2);

	}

	protected static void onDialog(final String id) {
		String[] mCatsName = {""};

		List<Table.Note> notes;
		try {
			notes = DropboxSunc.mTable.getNoteTableId(id);
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
			return;
		}
		for (final Table.Note note : notes) {
			if (note.isFavorite()){
				mCatsName= new String[] { "Удалить из избранных", "Изменить","Удалить" };
			}else{
				mCatsName= new String[] { "Добавить в избранные", "Изменить",
				"Удалить" };
			}

		}
		

		AlertDialog.Builder builder = new AlertDialog.Builder(context2);
		// builder.setTitle("");
		builder.setItems(mCatsName, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {

				if (0 == item) {

					List<Table.Note> notes;
					try {
						notes = DropboxSunc.mTable.getNoteTableId(id);
					} catch (DbxException e) {
						DropboxSunc.handleException(e);
						return;
					}
					for (final Table.Note note : notes) {
						try {
							note.toFavorite();
						} catch (DbxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				} else
					
				if (1 == item) {
					
					Main.setSetting(Main.ID_NOTE, id);
					Main.OpenFragment(Main.IndexNotebookEdit);

				} else

				if (2 == item) {

					try {
						Table.NoteDeleteID(id);

					} catch (DbxException e) {
						e.printStackTrace();
					}
					
					Log("--- NoteDeleteID");

					List<Table.Task> tasks = null;
					try {
						tasks = DropboxSunc.mTable.getTableTaskAndList();
						Log("--- NoteDeleteID   id");

					} catch (DbxException e) {
						e.printStackTrace();
					}

					for (final Table.Task task : tasks) {
						if (id.contains(task.getNoteID())){
							try {
								task.toBasket();
							} catch (DbxException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}

							
						
						
						Log("--- "+task.getNoteID());
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





}