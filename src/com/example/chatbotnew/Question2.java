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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Question2 extends Activity {
	EditText e1;
	Button b1;
	String namespace="http://tempuri.org/";
	String method="Ques";
	String soapaction="http://tempuri.org/Ques";
	String url="http://169.254.41.214/MindReader/WebService.asmx";
	
SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_question2);

		e1=(EditText)findViewById(R.id.editText1);
		b1=(Button)findViewById(R.id.button1);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		
		try{
        	
        	if(android.os.Build.VERSION.SDK_INT>9){
        		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        		StrictMode.setThreadPolicy(policy);
        		
        	}
        	
        }catch(Exception e){
        	
        }

		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				String ques=e1.getText().toString();
				if(ques.equalsIgnoreCase(""))
				{
					e1.setError("Enter Question");
					
				}
				else
				{
				
				
				String id=sp.getString("lid", "");
				
				try{
					 
					 SoapObject sop=new SoapObject(namespace,method);
					 
					 sop.addProperty("u_id",id);
					 sop.addProperty("question",ques);
					 
					 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
					 senv.setOutputSoapObject(sop);
					 senv.dotNet=true;
					 
					 HttpTransportSE htp=new HttpTransportSE(sp.getString("url", ""));
					 htp.call(soapaction, senv);
					 
					 String result=senv.getResponse().toString();
					 
					 Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
					 
					 Intent i=new Intent(getApplicationContext(),Userhome.class);
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
		getMenuInflater().inflate(R.menu.question2, menu);
		return true;
	}

}
