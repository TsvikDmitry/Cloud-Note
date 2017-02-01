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


public class Fragment_View_Task_add extends Fragment {
	static ArrayList<String> List_id;
	static ArrayList<String> List_name;

	//RelativeLayout rl;
	static Spinner sp;
	List<String> list;
	static Context context;
	static String id_note;
	boolean ImageFavorit = false; 
	EditText Title, Text;
	public Fragment_View_Task_add() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");
		View view = inflater.inflate(R.layout.fragment_mod_task, container, false);

	    sp = (Spinner) view.findViewById(R.id.spinner_note);
	    id_note = Main.getSetting(Main.ID_NOTE);
	    context = getActivity();
	   
	    Title =(EditText) view.findViewById(R.id.task_mod_title);
	    Text =(EditText) view.findViewById(R.id.task_mod_text);
	    
	    ImageButton  Finish = (ImageButton) view.findViewById(R.id.button_finish);
	 
		final ImageButton ImageFavorite = (ImageButton) view.findViewById(R.id.image_favorite);
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
	    
	    
		LinearLayout  layout_finish = (LinearLayout) view.findViewById(R.id.layout_finish);
		layout_finish.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				String name= Title.getText().toString();
				String text= Text.getText().toString();
				boolean favorite = ImageFavorit;
				boolean password_test = false; 
				String password = "";
				String color= "";
				
				try {
					DropboxSunc.mTable.CreateTask(id_note,name, text,false, favorite, password_test, password, color);
				} catch (DbxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Main.OpenFragment(Main.IndexViewTask);
			}
		});

		return view;

	}

	

	public static void updateList(){
		List<Table.Note> notes;
		try {
			notes =DropboxSunc.mTable.getNoteTableAll();
		} catch (DbxException e) {
			//DropboxSunc.handleException(e);
			//return container;
			return;
		}
		List_id = new ArrayList<String>();
		List_name = new ArrayList<String>();
		
		for (final Table.Note note : notes) {

			List_id.add(note.getId());
			List_name.add(note.getName());
		}
		
		 ArrayAdapter<String> adp = new ArrayAdapter<String>(context,  R.layout.row_spinner, R.id.weekofday, List_name);   
		 sp.setAdapter(adp);
	     
	        int size = List_id.size()-1;
	        for (int i=0;i<=size;i++){

	        	if (List_id.get(i).contains(Main.getSetting(Main.ID_NOTE))){
	        		sp.setSelection(i);
	        		Log("setSelection"+ i);
	        	}else{
	        		Log(""+ i);
	        	}
	        	
	        }
	        
	        sp.setOnItemSelectedListener(new OnItemSelectedListener() {
	    		public void onItemSelected(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
	    			
	    			Log(""+arg2);
	    			Log(""+List_id.get(arg2));
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

	}

	public void onResume() {
		super.onResume();
		Log("onResume");
		updateList();
		Main.setSetting(Main.ID_FRAGMENT, Main.IndexViewTaskAdd);

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