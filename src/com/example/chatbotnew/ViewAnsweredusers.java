package com.example.chatbotnew;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.ListView;

public class ViewAnsweredusers extends Activity {
ListView l1;
SharedPreferences sp;

String method="answerdusers";
String namespace="http://tempuri.org/";
String soapaction=namespace+method;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_answeredusers);
		l1=(ListView)findViewById(R.id.listView1);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_answeredusers, menu);
		return true;
	}

}
