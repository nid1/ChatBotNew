package com.example.chatbotnew;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends Activity {

	EditText e1,e2;
	Button b1;
	
	String method="fpassword";
	String soapaction="http://tempuri.org/fpassword";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forgot_password);
		
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText3);
		
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
				String Username=e2.getText().toString();
				String Email=e1.getText().toString();
				
try {
					
					
					SoapObject soap=new SoapObject(IpSettings.namespace, method);
					soap.addProperty("uname", Username);
					soap.addProperty("email", Email);
					
					
					SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
					
					senv.dotNet=true;
					senv.setOutputSoapObject(soap);
					
					HttpTransportSE htp=new HttpTransportSE(IpSettings.url);
					htp.call(soapaction, senv);
					
					
					String res=senv.getResponse().toString();
					
					Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
					
					if(res.equalsIgnoreCase("error")||res.equalsIgnoreCase("no"))
					{
						
						if(res.equalsIgnoreCase("no"))
						{
							Toast.makeText(getApplicationContext(), "email  invalid",Toast.LENGTH_LONG).show();				
							
					    }
					
				    }
				
					
					
				} catch (Exception e) {
					// TODO: handle exception
					
					Toast.makeText(getApplicationContext(), "error "+e.getMessage()+"",Toast.LENGTH_LONG).show();				
					
				}
				
				
			}
			
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgot_password, menu);
		return true;
		
	}
		
	}
	


