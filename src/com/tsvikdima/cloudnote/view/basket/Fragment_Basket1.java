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


public class Fragment_Basket1 extends Fragment {
	static AlertDialog.Builder ad;
	static Context context, context2;
	ListView lvActionMode, lvMain;
	static TableLayout tableNote_1, tableNote_2, tableNote_3, tableNote_4,tableNote_5;
	TableRow row;
	ArrayAdapter<String> monthAdapter, weekOfDayAdapter;
	Context MyContext;
	static LinearLayout text_hint ;
	LayoutInflater ltInflater;
	static LayoutInflater mInflater;
	Button button, button1;
	int Image = R.drawable.icon_note_1;
	View view;
	static int index = 0;

	public Fragment_Basket1() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");

		view = inflater.inflate(R.layout.fragment_task_and_list, container, false);
	
		context2 = getActivity();
		context = getActivity().getApplicationContext();
		mInflater = LayoutInflater.from(context);
		
		tableNote_1 = (TableLayout) view.findViewById(R.id.Table_1);
		tableNote_2 = (TableLayout) view.findViewById(R.id.Table_2);
		tableNote_3 = (TableLayout) view.findViewById(R.id.Table_3);
		 text_hint = (LinearLayout) view.findViewById(R.id.text_hint);	

		 Button button_task_add = (Button) view.findViewById(R.id.button_task_add);
		 button_task_add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				

			}
		});
		 Button button_list_add = (Button) view.findViewById(R.id.button_list_add);
		 button_list_add.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Main.OpenFragment(Main.IndexViewListAdd);

			}
		});
		return view;
	}

	public static void onUpdate() {
		Log("updateListNote");
		List<Table.Task> tasks;
		try {
			tasks = DropboxSunc.mTable.getTaskTableBasket();
			// tasks = DropboxSunc.mTable.getTaskTable();
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
			return;
		}

		if (Main.DISPLYA) {		// �������
			if (getOrientationPortrait()) {
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
			}
			if (getOrientationLondscape()) {
				tableNote_1.removeAllViews();
				tableNote_2.removeAllViews();
				tableNote_3.removeAllViews();
			}
		} else {// �������
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
			// �������
			if (Main.DISPLYA) {
				if (getOrientationPortrait()) {//������� �������
					if (index == 0) {
						index = 1;
						rowTask(tableNote_1, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());

					} else if (index == 1) {
						index = 0;
						rowTask(tableNote_2, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());
					}

				}

				if (getOrientationLondscape()) { //������� ������
					if (index == 0) {
						index = 1;
						rowTask(tableNote_1, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());

					} else if (index == 1) {
						index = 2;
						rowTask(tableNote_2, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());
					} else if (index == 2) {
						index = 0;
						rowTask(tableNote_3, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());
					}
				}
				// �������
			} else { //  �������
				if (getOrientationPortrait()) {// ������� �������
					if (task.isList()) {// ������
						rowList(tableNote_1, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());

					} else { //�������
						rowTask(tableNote_1, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());

					}
				}

				if (getOrientationLondscape()) {// ������� ������
					Log("onStart");
					if (index == 0) {
						index = 1;
						Log("transp0");
						rowTask(tableNote_1, task.getId(), task.getName(),
								task.getText(), R.color.transp1,
								task.isFavorite());

					} else if (index == 1) {
						Log("transp1");
						index = 0;
						
						if (task.isList()) {
							rowTask(tableNote_2, task.getId(), task.getName(),
									task.getText(), R.color.transp1,
									task.isFavorite());
						} else {
							rowList(tableNote_2, task.getId(), task.getName(),
									task.getText(), R.color.transp1,
									task.isFavorite());

						}
					}
				}
			}
		}
		
		if (tableNote_1.getChildCount()!=0){
			text_hint.setVisibility(View.GONE);
			Log("�� �����");
		}else{
			Log("�����");
			text_hint.setVisibility(View.VISIBLE);
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
	
	/**************************** row list ******************************/
	public static void rowList(TableLayout TableTask, final String id_task,
			String title, String text, int color, boolean favorite) {

		TableRow row = (TableRow) mInflater.inflate(R.layout.row_list, null);
		TableTask.addView(row);
		
		// ����
		LinearLayout idLayout = (LinearLayout) row.findViewById(R.id.layout_2);
		idLayout.setBackgroundResource(color);
		
		// ���������� �������
		LinearLayout Layout = (LinearLayout) row.findViewById(R.id.layout_1);
		Layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Main.setSetting(Main.ID_TASK, id_task);
				Main.OpenFragment(Main.IndexViewListEdit);

			}
		});
		Layout.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			public void onCreateContextMenu(ContextMenu menu, View v,
					ContextMenuInfo menuInfo) {
				onDialog(id_task);
			}
		});
		
		
		// ���������
		ImageView image_2 = (ImageView) row.findViewById(R.id.image_favorite);
		if (favorite) {
			image_2.setImageResource(R.drawable.ic_favorit_on);
		} else {
			image_2.setImageResource(R.drawable.ic_favorit_off);
		}
		
		// ��������
		TextView Title = (TextView) row.findViewById(R.id.name);
		Title.setText(title);


		// ������� �������
		TableLayout Table_list = (TableLayout) row.findViewById(R.id.table_list);

		List<Table.ListItem> lists;
		try {

			lists = DropboxSunc.mTable.getListItemId(id_task, false);

		} catch (DbxException e) {
			return;
		}

		Table_list.removeAllViews();
		for (final Table.ListItem list : lists) {

			TableRow row2 = (TableRow) mInflater.inflate(
					R.layout.row_list_item, null);
			Table_list.addView(row2);

			TextView listItem = (TextView) row2.findViewById(R.id.list_item);
			listItem.setText(list.getText());

			Button delete_button = (Button) row2
					.findViewById(R.id.delete_button);
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
	
	
	/**************************** row Task ******************************/
	public static void rowTask(TableLayout TableTask, final String id_task,
			String title, String text, int color, boolean favorite) {

		TableRow row = (TableRow) mInflater.inflate(R.layout.row_task, null);
		TableTask.addView(row);

		TextView Title = (TextView) row.findViewById(R.id.name);
		Title.setText(title);
		TextView Text = (TextView) row.findViewById(R.id.text);

		if (text.length() != 0) {
			if (text.length() <= 100) {
				Text.setText(text.subSequence(0, text.length()));

			} else {
				Text.setText(text.subSequence(0, 100));
			}
		} else {
			Text.setText("");
		}

		ImageView image_2 = (ImageView) row.findViewById(R.id.image_favorite);
		if (favorite) {
			image_2.setImageResource(R.drawable.ic_favorit_on);
		} else {
			image_2.setImageResource(R.drawable.ic_favorit_off);
		}

		LinearLayout idLayout = (LinearLayout) row.findViewById(R.id.layout_2);
		idLayout.setBackgroundResource(color);

		LinearLayout Layout = (LinearLayout) row.findViewById(R.id.layout_1);
		Layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				Main.setSetting(Main.ID_TASK, id_task);
				//Main.OpenFragment(Main.IndexTaskEdit);

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
		onUpdate();
		//Main.setSetting(Main.ID_FRAGMENT, Main.IndexView);

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
		String TAG = "Note";
		String s1 = "Fragment_Note";
		Log.d(TAG, s1 + " - " + s2);

	}

	protected static void onDialog(final String id) {
		String[] mCatsName = { "" };

		List<Table.Task> tasks;
		try {
			tasks = DropboxSunc.mTable.getTaskTableId(id);
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
			return;
		}
		for (final Table.Task task : tasks) {
			if (task.isFavorite()) {
				mCatsName = new String[] { "������� �� ���������",
						"�������� � �������" };
			} else {
				mCatsName = new String[] { "�������� � ���������",
						"�������� � �������" };
			}

		}

		AlertDialog.Builder builder = new AlertDialog.Builder(context2);
		// builder.setTitle("");
		builder.setItems(mCatsName, new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int item) {

				if (0 == item) {

					List<Table.Task> tasks;
					try {
						tasks = DropboxSunc.mTable.getTaskTableId(id);
					} catch (DbxException e) {
						DropboxSunc.handleException(e);
						return;
					}
					for (final Table.Task task : tasks) {
						try {
							task.toFavorite();
						} catch (DbxException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

				} else

				if (1 == item) {

					try {
						Table.TaskBasket(id, true);

					} catch (DbxException e) {
						e.printStackTrace();
					}
				}

			}
		});
		builder.setNegativeButton("������",
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