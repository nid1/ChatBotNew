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

public class UserInfo extends Activity {

	EditText e1,e2,e3,e5,e6,e7;
	RadioButton r0,r1;
	Button b1;
	SharedPreferences sp; 
	String fname,lname,dob,gender,addr,email,mobile;
	String method="Userupdate";
	String method1="uprofile";
	String namespace="http://tempuri.org/";
	String soapaction="http://tempuri.org/Userupdate";
	String soapaction1="http://tempuri.org/uprofile";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
	    e3=(EditText)findViewById(R.id.editText3);
	    e5=(EditText)findViewById(R.id.editText5);
	    e6=(EditText)findViewById(R.id.editText6);
		e7=(EditText)findViewById(R.id.editText7);
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
	soap.addProperty("userid",id);
   SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
   senv.dotNet=true;
   senv.setOutputSoapObject(soap);
   HttpTransportSE htp=new HttpTransportSE(sp.getString("url", ""));
   htp.call(soapaction1, senv);
   String res=senv.getResponse().toString();
  
   String r[]=res.split("#");
  for(int i=0;i<r.length;i++)
  {

fname =r[1];
lname=r[2];
dob=r[3];
gender=r[4];
addr=r[5];
email=r[6];
mobile=r[7];
}
for(int i=0;i<r.length;i++)
{
e2.setText(fname);
e1.setText(lname);
e3.setText(dob);
if(gender.equalsIgnoreCase("male"))
{
  r0.setChecked(true);
  
	
}
else
{
	 r1.setChecked(true);
	  
}

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
			    fname=e2.getText().toString();
			    lname=e1.getText().toString();
			    dob=e3.getText().toString();
			    if(gender=="male")
				{
					r0.getText().toString();
					
				}
				else
				{
					r1.getText().toString();
				}
			    addr=e5.getText().toString();
			    email=e6.getText().toString();
			    mobile=e7.getText().toString();
				try {
					
					
					SoapObject soap=new SoapObject(namespace, method);
					soap.addProperty("id",sp.getString("lid", ""));
					soap.addProperty("fname", fname);
					soap.addProperty("lname",lname);
					soap.addProperty("dob", dob);
					soap.addProperty("gender",gender);
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
	Intent i=new Intent(getApplicationContext(),Userhome.class);
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
    		getMenuInflater().inflate(R.menu.user_info, menu);
    		return true;
    	}
    }