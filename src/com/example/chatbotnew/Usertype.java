package com.example.chatbotnew;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Usertype extends Activity {
	RadioButton r0,r1;
	Button b1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usertype);
		 r0=(RadioButton)findViewById(R.id.radio0);
		 r1=(RadioButton)findViewById(R.id.radio1);
         b1=(Button)findViewById(R.id.button1);
	b1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(r0.isChecked())
			{
				Intent i=new Intent(getApplicationContext(), Ureg.class);
			startActivity(i);
			}
				else if(r1.isChecked())
				{
					Intent i=new Intent(getApplicationContext(),PageRegister.class);
					startActivity(i);
				}	
		}
	});
	
	
	}
		@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.usertype, menu);
		return true;
	}

}
