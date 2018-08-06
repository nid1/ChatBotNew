package com.example.chatbotnew;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Qanswers extends Activity implements OnItemClickListener{
	EditText e1,e2;
	Button b1;
	SharedPreferences sp;
	String q_id;
	String namespace="http://tempuri.org/";
	String method="reply";
	String soapaction="http://tempuri.org/reply";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qanswers);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		b1=(Button)findViewById(R.id.button1);
		 
        try {
			
			if(android.os.Build.VERSION.SDK_INT>9)
			{
				StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);	
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
          b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
//				String =e1.getText().toString();
//				String reply=e2.getText().toString();
//				
				String a=e2.getText().toString();
				if(a.equalsIgnoreCase(""))
				{
					e1.setError("Enter Reply");
					
				}
				else
				{
				
				SharedPreferences sh=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
				String id=sh.getString("id", "");
				
				try{
					 
					 SoapObject sop=new SoapObject(namespace,method);
					 
					 sop.addProperty("q_id",q_id);
					 sop.addProperty("p_id",id);
					 sop.addProperty("reply",a);
					 
					 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
					 senv.setOutputSoapObject(sop);
					 senv.dotNet=true;
					 
					 HttpTransportSE htp=new HttpTransportSE(sp.getString("url", ""));
					 htp.call(soapaction, senv);
					 
					 String result=senv.getResponse().toString();
					 
					 Intent i=new Intent(getApplicationContext(),HomePage.class);
					 startActivity(i);
				}
					 catch(Exception e){
					 }
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qanswers, menu);
		return true;
	}



	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}