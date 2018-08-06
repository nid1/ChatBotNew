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

public class ChangePassword extends Activity {
	EditText e1,e2,e3;
	Button b1;
	SharedPreferences s1;
	String namespace="http://tempuri.org/";
	String method="repassword";
	String soapaction="http://tempuri.org/repassword";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		e1=(EditText)findViewById(R.id.editText1);
		e2=(EditText)findViewById(R.id.editText2);
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
         b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String OldPassword=e1.getText().toString();
				String NewPassword=e2.getText().toString();
				String uid=s1.getString("lid", "");		
			
			try {
				
				
				SoapObject soap=new SoapObject(namespace, method);
				soap.addProperty("opas", OldPassword);
				soap.addProperty("npas", NewPassword);
				soap.addProperty("uid",uid);
                SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);

senv.dotNet=true;
senv.setOutputSoapObject(soap);

HttpTransportSE htp=new HttpTransportSE(s1.getString("url", ""));
htp.call(soapaction, senv);


String res=senv.getResponse().toString();

Toast.makeText(getApplicationContext(), res, Toast.LENGTH_LONG).show();

if(res.equalsIgnoreCase("error")||res.equalsIgnoreCase("no"))
{
	Toast.makeText(getApplicationContext(), "password not match", Toast.LENGTH_LONG).show();
}
else
{
Toast.makeText(getApplicationContext(), "password changed", Toast.LENGTH_LONG).show();
Intent i=new Intent(getApplicationContext(),Login.class);
startActivity(i);
}
}
catch(Exception e)
{
	}
}
		});
	
	}
         
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.change_password, menu);
		return true;
	}

}
