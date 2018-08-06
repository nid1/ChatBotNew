package com.example.chatbotnew;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ViewQuestions extends Activity implements OnItemClickListener {
	ListView lv1;
	SharedPreferences sp;
	String[] a;
	String [] question,q_id;
	String namespace="http://tempuri.org/";
	String method="viewallquestions";
	String soapaction="http://tempuri.org/viewallquestions";



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_questions);

		lv1=(ListView)findViewById(R.id.listView1);
		
		try{
        	
        	if(android.os.Build.VERSION.SDK_INT>9){
        		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        		StrictMode.setThreadPolicy(policy);        		
        	}        	
        }
		catch(Exception e){        	
        }
	
	try{
		 
		SoapObject sop=new SoapObject(namespace,method);

		SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		senv.setOutputSoapObject(sop);
		senv.dotNet=true;
 
		HttpTransportSE htp=new HttpTransportSE(sp.getString("url", ""));
		htp.call(soapaction, senv);
 
		String result=senv.getResponse().toString();
		
		Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
		
		if(!result.equalsIgnoreCase("anyType{}"))
			
		{
		
		a=result.split("\\@");
		q_id=new String[a.length];
		question=new String[a.length];
		
		for(int i=0;i<a.length;i++)
		{
			String temp[]=a[i].split("\\#");
			q_id[i]=temp[0];
			question[i]=temp[1];
			
		}
			
		ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1,question);
		lv1.setAdapter(ad);
//    lv1.setAdapter(new custome0(getApplicationContext(), uid, uname));
    lv1.setOnItemClickListener(this);
//
		}
		
		else
		{
			Toast.makeText(getApplicationContext(),"No answerd user", Toast.LENGTH_SHORT).show();
		}
	}
	

	catch(Exception e){


		Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
	}
	
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_questions, menu);
		return true;
		

	}




	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		Intent i=new Intent(getApplicationContext(),Qanswers.class);
		i.putExtra("ques", question[arg2]);
		i.putExtra("qid", q_id[arg2]);
		
	}

}
