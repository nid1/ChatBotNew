package com.example.chatbotnew;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class IpSettings extends Activity {
	EditText e1;
	Button b1;
	SharedPreferences sp;
	public static String namespace="http://tempuri.org/";
	public static String url="";
	String ip="";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ip_settings);
		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		e1.setText(sp.getString("ip", ""));
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ip=e1.getText().toString();
				url="http://"+ip+"/chatbot/WebService.asmx?WSDL";	
			Editor ed=sp.edit();
			ed.putString("url",url);
			ed.putString("ip", ip);
			ed.commit();
			Intent i=new Intent(getApplicationContext(),Login.class);
			startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ip_settings, menu);
		return true;
	}

}
