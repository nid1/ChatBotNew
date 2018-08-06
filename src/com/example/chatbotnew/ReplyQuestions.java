package com.example.chatbotnew;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.net.LocalSocketAddress.Namespace;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ReplyQuestions extends Activity {
	EditText e1;
	TextView t1;
	Button b1;
    SharedPreferences sp;
    String method="Reply";
	String soapaction="http://tempuri.org/Reply";
	String namespace="http://tempuri.org/";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reply_questions);
		e1=(EditText)findViewById(R.id.editText3);
		t1=(TextView)findViewById(R.id.textView1);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String id1=sp.getString("id", "");
		
		b1=(Button)findViewById(R.id.button1);
		
		String question=getIntent().getStringExtra("qus");
		
		final String qid=getIntent().getStringExtra("qid");
		
		
		
		t1.setText(question);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				
				String reply=e1.getText().toString();
				try{
					SoapObject sop= new SoapObject(namespace,method);
					sop.addProperty("q_id", qid);
					sop.addProperty("p_id", sp.getString("lid", ""));
					sop.addProperty("reply", reply);
					
					 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
					 senv.setOutputSoapObject(sop);
					 senv.dotNet=true;
					 
					 HttpTransportSE htp=new HttpTransportSE(sp.getString("url", ""));
					 htp.call(soapaction, senv);
					 
					 String result=senv.getResponse().toString();
					 
					 Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
					 
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
		getMenuInflater().inflate(R.menu.reply_questions, menu);
		return true;
	}

}
