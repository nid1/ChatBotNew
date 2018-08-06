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
import android.content.SharedPreferences.Editor;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class Q1 extends Activity {
	TextView t1;
	RadioButton rb1,rb2,rb3,rb4,rb5;
	Button b1,b2;
	int arsz=0;
	static int qno=0;
	String answer;

	String namespace="http://tempuri.org/";
	String method="uploadanswer";
	String soapaction="http://tempuri.org/uploadanswer";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_q1);
		t1=(TextView)findViewById(R.id.textView1);
		rb1=(RadioButton)findViewById(R.id.radio0);
		rb2=(RadioButton)findViewById(R.id.radio1);
		rb3=(RadioButton)findViewById(R.id.radio2);
		rb4=(RadioButton)findViewById(R.id.radio3);
		rb5=(RadioButton)findViewById(R.id.radio4);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
	

		try
	    {
	    	if(android.os.Build.VERSION.SDK_INT>9)
	    	{
	    		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    		StrictMode.setThreadPolicy(policy);
	    	}
	    }
	    catch(Exception e)
	    {
	    	
	    }
		arsz=QorImage.qid.length;
		
		displayQ(qno);

		
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
//				Toast.makeText(getApplicationContext(), arsz+"", Toast.LENGTH_LONG).show();	
					
				
				if(qno>0)
				{
					qno--;
//					Toast.makeText(getApplicationContext(), qno+""	, Toast.LENGTH_SHORT).show();
					

					displayQ(qno);
					
				}
				else
				{	
					
				}			
			}
		});


	
		
		
b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				if(b2.getText().toString().equals("Submit"))
				{
					//UserHome.xx=1;
					SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					String id=sh.getString("lid", "");
				
				
					if(rb1.isChecked())
					{
						answer="0";
					}
					else if(rb2.isChecked())
					{
						answer="1";
					}
					else if(rb3.isChecked())
					{
						answer="2";
					}
					else if(rb4.isChecked())
					{
						answer="3";
					}
					else if(rb5.isChecked())
					{
						answer="4";
					}
					
					try{
						 
						 SoapObject sop=new SoapObject(namespace,method);
						
						 
//						 Toast.makeText(getApplicationContext(), "question id  "+QorIm.qu_id[qno]+"", Toast.LENGTH_SHORT).show();
						 
						 sop.addProperty("id",id);
						 sop.addProperty("qu_id",QorImage.qid[qno]);
						 sop.addProperty("answer",answer);
						
						 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
						 senv.setOutputSoapObject(sop);
						 senv.dotNet=true;
						 
						 HttpTransportSE htp=new HttpTransportSE(IpSettings.url);
						 htp.call(soapaction, senv);
						 
						 String result=senv.getResponse().toString();
						 
						
						 
						 Intent i=new Intent(getApplicationContext(),QorImage.class);
							startActivity(i);
						 
//						 Toast.makeText(getApplicationContext(), result+"", Toast.LENGTH_SHORT).show();
					}
					catch(Exception e){
						

//						 Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
					 }

					
				}
				else
				{
					
				if(qno<(arsz-1))
				{
//					Toast.makeText(getApplicationContext(), qno+""	, Toast.LENGTH_SHORT).show();
					
					SharedPreferences sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
					String id=sh.getString("lid", "");
				
					if(rb1.isChecked())
					{
						answer="0";
					}
					else if(rb2.isChecked())
					{
						answer="1";
					}
					else if(rb3.isChecked())
					{
						answer="2";
					}
					else if(rb4.isChecked())
					{
						answer="3";
					}
					else if(rb5.isChecked())
					{
						answer="4";
					}
					
					try{
						 
						 SoapObject sop=new SoapObject(namespace,method);
						
						 
//						 Toast.makeText(getApplicationContext(), "question id  "+QorIm.qu_id[qno]+"", Toast.LENGTH_SHORT).show();
						 
						 sop.addProperty("id",id);
						 sop.addProperty("qu_id",QorImage.qid[qno]);
						 sop.addProperty("answer",answer);
						
						 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
						 senv.setOutputSoapObject(sop);
						 senv.dotNet=true;
						 
						 HttpTransportSE htp=new HttpTransportSE(IpSettings.url);
						 htp.call(soapaction, senv);
						 
						 String result=senv.getResponse().toString();
						 
//						 Toast.makeText(getApplicationContext(), result+"", Toast.LENGTH_SHORT).show();
					}
					catch(Exception e){
						

//						 Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
					 }
					
					
					qno++;
					displayQ(qno);
					
				}
				else
				{
				
				}	
				}
			}
		});

		
		
		
		
			
		}
		   
	private void displayQ(int qno2) {
		t1.setText(QorImage.qs[qno2]);
		String[] options = QorImage.op[qno2].split("-");
		rb1.setText(options[0]);
		rb2.setText(options[1]);
		rb3.setText(options[2]);
		rb4.setText(options[3]);
		rb5.setText(options[4]);
		
		rb1.setChecked(false);
		rb2.setChecked(false);
		rb3.setChecked(false);
		rb4.setChecked(false);
		rb5.setChecked(false);
		if(qno==0)
		{
			b1.setVisibility(View.INVISIBLE);
		}
		else
		{
			b1.setVisibility(View.VISIBLE);
		}
		if(qno==arsz-1)
		{
			b2.setText("Submit");
		}
		else
		{
			b2.setText("Next");
		}
		
	}
 
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.q1, menu);
		return true;
	}

}
