package com.example.chatbotnew;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.R.string;
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
import android.widget.RadioButton;
import android.widget.Toast;

public class Ureg extends Activity {

	EditText e1,e2,e3,e4,e5,e6,e7,e8,e10;
	RadioButton r0,r1;
	Button b1;
	SharedPreferences sp;
	String method="ureg";
	String namespace="http://tempuri.org/";
	String soapaction="http://tempuri.org/ureg";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ureg);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
	    e3=(EditText)findViewById(R.id.editText3);
	    e5=(EditText)findViewById(R.id.editText5);
	    e6=(EditText)findViewById(R.id.editText6);
	    e7=(EditText)findViewById(R.id.editText7);
		e4=(EditText)findViewById(R.id.editText4);
	    e10=(EditText)findViewById(R.id.editText10);
	    r0=(RadioButton)findViewById(R.id.radio0);
	    r1=(RadioButton)findViewById(R.id.radio1);
	    b1=(Button)findViewById(R.id.button1);
		
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
				String Fname=e2.getText().toString();
				String Lname=e1.getText().toString();
				String DOB=e3.getText().toString();
				String Address=e5.getText().toString();
				String Email=e6.getText().toString();
				String Mobile=e7.getText().toString();
				String Password=e4.getText().toString();
				String gen="";
				if(r0.isChecked())
				{
					gen=r0.getText().toString();
					
				}
				else
				{
					gen=r1.getText().toString();
				}
				
try {
					
					
					SoapObject soap=new SoapObject(namespace, method);
					soap.addProperty("fname", Fname);
					soap.addProperty("lname", Lname);
					soap.addProperty("dob", DOB);
					soap.addProperty("gender",gen);
					soap.addProperty("addr", Address);
					soap.addProperty("email", Email);
					soap.addProperty("phone", Mobile);
                    soap.addProperty("password",Password);
                   SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);

senv.dotNet=true;
senv.setOutputSoapObject(soap);

HttpTransportSE htp=new HttpTransportSE(sp.getString("url", ""));
htp.call(soapaction, senv);


String res=senv.getResponse().toString();

Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();

if(res.equalsIgnoreCase("error")||res.equalsIgnoreCase("no"))
{
	Toast.makeText(getApplicationContext(), "registration not completed", Toast.LENGTH_LONG).show();
}
else
{
Toast.makeText(getApplicationContext(), "registation successful", Toast.LENGTH_LONG).show();
Intent i=new Intent(getApplicationContext(),Login.class);
startActivity(i);
}
}
catch(Exception e)
{
	Toast.makeText(getApplicationContext(), "error"+e, Toast.LENGTH_LONG).show();
	}
}
		});
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ureg, menu);
		return true;
	}
}
