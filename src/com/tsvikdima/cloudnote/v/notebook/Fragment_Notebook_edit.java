package com.tsvikdima.cloudnote.v.notebook;



import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dropbox.sync.android.DbxException;
import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;
import com.tsvikdima.cloudnote.dropbox.DropboxSunc;
import com.tsvikdima.cloudnote.dropbox.Table;

@SuppressWarnings("deprecation")
public class Fragment_Notebook_edit extends Fragment {

	static GridView gridView;
	private int currentPic = 0;
	private PicAdapter imgAdapt;
	private Gallery picGallery;
	TextView note_add_edit;
	int[] s = { R.drawable.icon_note_1, R.drawable.icon_note_2,
			R.drawable.icon_note_3, R.drawable.icon_note_4,
			R.drawable.icon_note_5, R.drawable.icon_note_6, R.drawable.icon_note_7};

	static Context context;
	ArrayList<Integer> List_id ;
	
	int Image = s[0];
	
	public Fragment_Notebook_edit() {
	}

	@SuppressLint("CutPasteId")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");

		View rootView = inflater.inflate(R.layout.fragment_mod_note, container,
				false);

		note_add_edit = (TextView) rootView.findViewById(R.id.note_add_edit);
		context = getActivity().getApplicationContext();
		
		List_id = new ArrayList<Integer>();
		List_id.add(R.drawable.icon_note_1);
		
		picGallery = (Gallery) rootView.findViewById(R.id.gallery);
		imgAdapt = new PicAdapter(context);
		picGallery.setAdapter(imgAdapt);
		
		//picGallery.setSelection(1);
		
		
		List<Table.Note> notes = null;
		try {
			
			notes = DropboxSunc.mTable.getNoteTableId(Main.getSetting(Main.ID_NOTE));
		} catch (DbxException e) {
			DropboxSunc.handleException(e);
		}
		
		
		for (final Table.Note note : notes) {
			
			note_add_edit.setText(""+note.getName());	
			Log(""+note.getName());
			
			for (int i = 0; i<=s.length-1;i++){
	        	if (s[i]==Integer.parseInt(note.getImage())){
					picGallery.setSelection(i);
	        		Log(i+"  "+s[i]);
				}
			}
		}
		

		
		
		picGallery.setOnItemSelectedListener(new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				Log("image = " + s[position]);
				Image =s[position];
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		
		

		
		LinearLayout layout_finish = (LinearLayout) rootView.findViewById(R.id.layout_finish);
		layout_finish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {


				
					List<Table.Note> notes = null;
					try {
						notes = DropboxSunc.mTable.getNoteTableId(Main.getSetting(Main.ID_NOTE));
						
					} catch (DbxException e) {
						DropboxSunc.handleException(e);
					}

					Log("-----");
					
					for (final Table.Note note : notes) {

						try {
							note.setImage(""+Image);
						} catch (DbxException e1) {
							Log("DbxException");
							e1.printStackTrace();
						}
						try {
							note.setName(""+note_add_edit.getText());
						} catch (DbxException e) {
							Log("DbxException");
							e.printStackTrace();
						}
					}
					
					Main.OpenFragment(Main.IndexNotebook);
					
				
				
			}

		});

		return rootView;
	}

	public class PicAdapter extends BaseAdapter {

		int defaultItemBackground;
		private Context galleryContext;
		private Bitmap[] imageBitmaps;
		Bitmap placeholder;

		public PicAdapter(Context c) {

			galleryContext = c;

			imageBitmaps = new Bitmap[s.length];
			for (int i = 0; i < imageBitmaps.length; i++)
				imageBitmaps[i] = BitmapFactory.decodeResource(getResources(), s[i]);

			TypedArray styleAttrs = galleryContext.obtainStyledAttributes(R.styleable.PicGallery);

			defaultItemBackground = styleAttrs.getResourceId(R.styleable.PicGallery_android_galleryItemBackground, 0);

			styleAttrs.recycle();
			
		}

		public int getCount() {
			return imageBitmaps.length;
		}

		public Object getItem(int position) {
			return position;
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {

			ImageView imageView = new ImageView(galleryContext);
			imageView.setImageBitmap(imageBitmaps[position]);
			imageView.setScaleType(ImageView.ScaleType.MATRIX);
		//	imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
			//imageView.setBackgroundResource(defaultItemBackground);
			return imageView;
		}
		
		public void addPic(Bitmap newPic) {
			imageBitmaps[currentPic] = newPic;
		}

		public Bitmap getPic(int posn) {
			return imageBitmaps[posn];
		}
	}

	public void onStart() {
		super.onStart();
		Log("onStart");

	}

	public void onResume() {
		super.onResume();
		Log("onResume");
		Main.setSetting(Main.ID_FRAGMENT, Main.IndexNotebookEdit);

	}

	public void onDestroy() {
		super.onDestroy();
		Log("onDestroy");

	}

	public void onPause() {
		super.onPause();
		Log("onPause");
		//Main.keyboard.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		
	}

	public static void onUpdate() {
		Log("onUpdate");

	}

	static void Log(String s2) {
		String TAG = "Note";
		String s1 = "Fragment_Note_Edit";
		Log.d(TAG, s1 + " - " + s2);

	}
	


}