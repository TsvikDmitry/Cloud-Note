package com.tsvikdima.cloudnote.password;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tsvikdima.cloudnote.Main;
import com.tsvikdima.cloudnote.R;

public class Fragment_Password_Save extends Fragment {
	static Button but1, but2, but3, but4, but5, but6, but7, but8, but9, but0;
	static Button cancel;
	static Button remove;
	static TextView text_pass;
	static EditText edit1, edit2, edit3, edit4;
	static String pass1;
	static String pass2;
	public Fragment_Password_Save() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log("onCreateView");
		
		View rootView = inflater.inflate(R.layout.fragment_password, container, false);
		but1 = (Button) rootView.findViewById(R.id.but_1);
		but2 = (Button) rootView.findViewById(R.id.but_2);
		but3 = (Button) rootView.findViewById(R.id.but_3);
		but4 = (Button) rootView.findViewById(R.id.but_4);
		but5 = (Button) rootView.findViewById(R.id.but_5);
		but6 = (Button) rootView.findViewById(R.id.but_6);
		but7 = (Button) rootView.findViewById(R.id.but_7);
		but8 = (Button) rootView.findViewById(R.id.but_8);
		but9 = (Button) rootView.findViewById(R.id.but_9);
		but0 = (Button) rootView.findViewById(R.id.but_0);
		cancel = (Button) rootView.findViewById(R.id.but_cancel);
		remove = (Button) rootView.findViewById(R.id.but_remove);

		text_pass = (TextView) rootView.findViewById(R.id.text_pass);
		edit1 = (EditText) rootView.findViewById(R.id.edit_1);
		edit2 = (EditText) rootView.findViewById(R.id.edit_2);
		edit3 = (EditText) rootView.findViewById(R.id.edit_3);
		edit4 = (EditText) rootView.findViewById(R.id.edit_4);
		clear();
		Main.setPasswordCount(5);
		
		ButtonClick();

		return rootView;
	}

	void namber(int i) {

		if (edit1.getText().length() == 0) {
			edit1.append("" + i);
		} else if (edit2.getText().length() == 0) {
			edit2.append("" + i);
		}else if (edit3.getText().length() == 0) {
			edit3.append("" + i);
		}else if (edit4.getText().length() == 0) {
			edit4.append("" + i);
	
		}

		if (edit4.getText().length() != 0 && pass1.length()==0) {
			pass1 = edit1.getText().toString()+edit2.getText().toString()+edit3.getText().toString()+edit4.getText().toString();
			text_pass.setText("Ведите пароль еще раз");
			edit1.setText("");
			edit2.setText("");
			edit3.setText("");
			edit4.setText("");
		} else if (edit4.getText().length() != 0 &&  pass2.length()==0) {
			pass2 = edit1.getText().toString()+edit2.getText().toString()+edit3.getText().toString()+edit4.getText().toString();
			
			
			if (pass1.contains(pass2)){
				try {
					Main.setPassword(SHA.pass(pass2));
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Main.setPasswordChek(true);			
				Main.Toast("Пароль сохранен!");
				Main.OpenFragment(Main.IndexSettings);
			}else {
				text_pass.setText("Пароли не совпадают, введите еще раз!");	
			}
			
			
			
			clear();
			
		}
		


	}

	void clear(){
		edit1.setText("");
		edit2.setText("");
		edit3.setText("");
		edit4.setText("");
		pass1 = "";
		pass2 = "";
	}
	
	
	void ButtonClick() {

		but1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(1);
			}
		});

		but2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(2);
			}
		});
		but3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(3);
			}
		});
		but4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(4);
			}
		});
		but5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(5);
			}
		});
		but6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(6);
			}
		});
		but7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(7);
			}
		});
		but8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(8);
			}
		});
		but9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(9);
			}
		});
		but0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				namber(0);
			}
		});
		cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Main.OpenFragment(Main.IndexSettings);
			}
		});
		remove.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				clear();
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

	}

	public void onDestroy() {
		super.onDestroy();
		Log("onDestroy");

	}

	public void onPause() {
		super.onPause();
		Log("onPause");
		// Activity_Main.saveFragment(Activity_Main.IconTaskAdd);
	}

	public static void onUpdate() {
		Log("onUpdate");

	}

	static void Log(String s2) {
		// Log("onCreate");
		String TAG = "Note";
		String s1 = "Password_Save";
		Log.d(TAG, s1 + " - " + s2);

	}

}