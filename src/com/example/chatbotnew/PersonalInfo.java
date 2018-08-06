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
import android.widget.RadioButton;
import android.widget.Toast;

public class PersonalInfo extends Activity {
	

	EditText e1,e3,e4,e5,e6,e7;
	RadioButton r0,r1;
	Button b1;
	SharedPreferences sp; 
	String fname,dob,gender,quali,addr,email,mobile;
	String method="Psychatristupdate";
	String namespace="http://tempuri.org/";
	String soapaction="http://tempuri.org/Psychatristupdate";
	String method1="pedit";
	String soapaction1="http://tempuri.org/pedit";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_info);
		e1=(EditText)findViewById(R.id.editText1);
	    e3=(EditText)findViewById(R.id.editText3);
	    e4=(EditText)findViewById(R.id.editText4);
	    e5=(EditText)findViewById(R.id.editText5);
	    e6=(EditText)findViewById(R.id.editText6);
		e7=(EditText)findViewById(R.id.editText7);
//	    e9=(EditText)findViewById(R.id.editText9);
//	    e8=(EditText)findViewById(R.id.editText8);
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
        
        try {
			
			
			SoapObject soap=new SoapObject(namespace, method1);
			String id=sp.getString("lid", "");
			soap.addProperty("pid",id);
           SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
           senv.dotNet=true;
           senv.setOutputSoapObject(soap);
           HttpTransportSE htp=new HttpTransportSE(sp.getString("url",""));
           htp.call(soapaction1, senv);
           String res=senv.getResponse().toString();
           Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();
           String r[]=res.split("#");
         for(int i=0;i<r.length;i++)
          {
	  
	  fname =r[0];
	  dob=r[1];
	  gender=r[2];
	  quali=r[3];
	  addr=r[4];
	  email=r[5];
	  mobile=r[6];
  }
  for(int i=0;i<r.length;i++)
  {
	  e1.setText(fname);
	  e3.setText(dob);
	  if(gender=="male")
		{
			r0.setText(gender);
			
		}
		else
		{
			r1.setText(gender);
		}
	  e4.setText(quali);
	  e5.setText(addr);
	  e6.setText(email);
	  e7.setText(mobile);
  }
  
Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();


}
catch(Exception e)
{
	Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
}
        
        
        
        b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			    fname=e1.getText().toString();
			    dob=e3.getText().toString();
			    if(gender=="male")
				{
					r0.getText().toString();
					
				}
				else
				{
					r1.getText().toString();
				}
			    quali=e4.getText().toString();
			    addr=e5.getText().toString();
			    email=e6.getText().toString();
			    mobile=e7.getText().toString();
				try {
					
					
					SoapObject soap=new SoapObject(namespace, method);
					soap.addProperty("id",sp.getString("lid", ""));
					soap.addProperty("fname", fname);
					soap.addProperty("dob", dob);
					soap.addProperty("gender",gender);
					soap.addProperty("quali",quali);
					soap.addProperty("addr", addr);
					soap.addProperty("email", email);
					soap.addProperty("phone", mobile);
		           
		           SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);

		senv.dotNet=true;
		senv.setOutputSoapObject(soap);

		HttpTransportSE htp=new HttpTransportSE(sp.getString("url",""));
		htp.call(soapaction, senv);


		String res=senv.getResponse().toString();

		Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();

		if(res.equalsIgnoreCase("error")||res.equalsIgnoreCase("no"))
		{
		Toast.makeText(getApplicationContext(), "editing not successful", Toast.LENGTH_LONG).show();
		}
		else
		{
		Toast.makeText(getApplicationContext(), "editing successful", Toast.LENGTH_LONG).show();
		Intent i=new Intent(getApplicationContext(),HomePage.class);
		startActivity(i);
		}
		}
		catch(Exception e)
		{
			Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_LONG).show();
		}

}
			
		});
	}


	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_info, menu);
		return true;
	}
}
