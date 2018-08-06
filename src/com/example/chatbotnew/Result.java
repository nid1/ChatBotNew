package com.example.chatbotnew;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

public class Result extends Activity {
	
	TextView tv1, tv2;
	
	String namespace="http://tempuri.org/";
	String method="viewresult";
	String soapaction="http://tempuri.org/viewresult";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		tv1 = (TextView) findViewById(R.id.textView3);
		tv2 = (TextView) findViewById(R.id.textView4);
		
		SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String id=sh.getString("lid", "");
		
		try {
			
   		 SoapObject sop=new SoapObject(namespace, method);
   		 sop.addProperty("u_id", id);
   		 
   		 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
   		 senv.setOutputSoapObject(sop);
   		 senv.dotNet = true;   		 
   		 
   		 HttpTransportSE htp=new HttpTransportSE(IpSettings.url);
   		 htp.call(soapaction, senv);
   		 
   		 String result=senv.getResponse().toString();
   		//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
   		 if(result.equalsIgnoreCase("anytype{}")|| result.equalsIgnoreCase("error"))
   		 {
   			 Toast.makeText(getApplicationContext(), "No data Found", Toast.LENGTH_LONG).show();
   	    		 
   		 }
   		 else
   		 {
   			 String [] list=result.split("\\#");
   			 if(list.length>0)
   			 {
   				 tv1.setText(list[0]);
   				 tv2.setText(list[1]);
   			 }
   			 
   		
   		 }
   		
   		 
   		 
		} catch (Exception e) {
			// TODO: handle exception
			 Toast.makeText(getApplicationContext(), "error"+e.getMessage()+"", Toast.LENGTH_LONG).show();
			 
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
