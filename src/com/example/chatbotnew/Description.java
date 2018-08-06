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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Description extends Activity {
	TextView tv1, tv2;
	EditText e1;
	Button b1;
	SharedPreferences s1;
	String [] u_id,qu_id,question,answer,a,emotion,user_id,v;
	String uid="";
	
	String namespace="http://tempuri.org/";
	String method1="getmark";
	String soapaction1="http://tempuri.org/getmark";
	
	String method2="viewlog";
	String soapaction2="http://tempuri.org/viewlog";
	
	String method3="updatelog";
	String soapaction3="http://tempuri.org/updatelog";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_description);
		tv1=(TextView)findViewById(R.id.textView3);
		tv2=(TextView)findViewById(R.id.textView4);
        e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		s1=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		 try {
				
				if(android.os.Build.VERSION.SDK_INT>9)
				{
					StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
				    StrictMode.setThreadPolicy(policy);	
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}

    
	uid=getIntent().getStringExtra("uid");
	try{
		 
		SoapObject sop=new SoapObject(namespace,method1);
		sop.addProperty("u_id",uid);
		

		SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		senv.setOutputSoapObject(sop);
		senv.dotNet=true;
 
		HttpTransportSE htp=new HttpTransportSE(s1.getString("url", ""));
		htp.call(soapaction1, senv);
 
		String result=senv.getResponse().toString();

		if(!result.equals("na")) {
			tv1.setText(result);
		}
    }
	catch(Exception e){
		Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
	}	
	try{
		 
		SoapObject sop1=new SoapObject(namespace,method2);
		sop1.addProperty("u_id",uid);
		

		SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		senv.setOutputSoapObject(sop1);
		senv.dotNet=true;
 
		HttpTransportSE htp=new HttpTransportSE(s1.getString("url", ""));
		htp.call(soapaction2, senv);
 
		String result=senv.getResponse().toString();
		if(!result.equals("na")) {
			tv2.setText(result);
		}
    

	}

	catch(Exception e){


		Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
	}


	b1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			try{
				
				String status=e1.getText().toString();
				
				SoapObject sop3=new SoapObject(namespace,method3);
				
				sop3.addProperty("u_id",uid);
				sop3.addProperty("status",status);
				
		
				SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
				senv.setOutputSoapObject(sop3);
				senv.dotNet=true;
		 
				HttpTransportSE htp=new HttpTransportSE(s1.getString("url", ""));
				htp.call(soapaction3, senv);
		 
				String result=senv.getResponse().toString();
				Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
				
				Intent i=new Intent(getApplicationContext(),QuestionsAnswered.class);
				startActivity(i);
			
		
			}

			catch(Exception e){
		

				Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

			}
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.description, menu);
		return true;
	}

}
