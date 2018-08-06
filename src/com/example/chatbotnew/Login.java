package com.example.chatbotnew;




import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@TargetApi(Build.VERSION_CODES.GINGERBREAD)
public class Login extends Activity {
	
	//String namespace="http://tempuri.org";
	String method="login";
	String soapaction="http://tempuri.org/login";
//	String url="http://192.168.43.195/chatbot/WebService.asmx";
	
	
	EditText e1,e2;
	Button b1,b2,b3;
	SharedPreferences sp;
	String namespace="http://tempuri.org/";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
		
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		
		
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
				
				String uname=e1.getText().toString();
				String pass=e2.getText().toString();
				
				try {
					
					
					SoapObject soap=new SoapObject(namespace, method);
					soap.addProperty("user_name", uname);
					soap.addProperty("password", pass);
					
					
					SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
					
					senv.dotNet=true;
					senv.setOutputSoapObject(soap);
					
					HttpTransportSE htp=new HttpTransportSE(sp.getString("url", ""));
					htp.call(soapaction, senv);
					
					
					String res=senv.getResponse().toString();
					
					Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
					
					if(res.equalsIgnoreCase("error")||res.equalsIgnoreCase("no"))
					{
						
						if(res.equalsIgnoreCase("no"))
						{
							Toast.makeText(getApplicationContext(), "password not valid",Toast.LENGTH_LONG).show();				
							
					    }
					
				    }
				else
				{
					String reslt[]=res.split("#");
					Editor ed=sp.edit();
					ed.putString("lid", reslt[0]);
					ed.commit();
					if(reslt[1].equalsIgnoreCase("psychiatrist"))
					{
						Intent i=new Intent(getApplicationContext(),HomePage.class);
						startActivity(i);
					}
					
					else if(reslt[1].equalsIgnoreCase("user"))
					{
						Intent i=new Intent(getApplicationContext(),Userhome.class);
						startActivity(i);
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Invalid User",Toast.LENGTH_LONG).show();				
					}
				}

				} catch (Exception e) {
					// TODO: handle exception
					
					Toast.makeText(getApplicationContext(), "error "+e.getMessage()+"",Toast.LENGTH_LONG).show();				
					
				}
				
				
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),Usertype.class);
				startActivity(i);
			}
		});
		
		b3.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent i=new Intent(getApplicationContext(),IpSettings.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
