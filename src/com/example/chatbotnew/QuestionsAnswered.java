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
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class QuestionsAnswered extends Activity implements OnItemClickListener{
    ListView lv1;
    SharedPreferences s1;
	
	String namespace="http://tempuri.org/";
	String method="viewreply";
	String soapaction="http://tempuri.org/viewreply";
	
	String [] question,reply,psy;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions_answered);
		lv1=(ListView)findViewById(R.id.listView1);	
		s1=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
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
	        sop.addProperty("u_id", s1.getString("lid", ""));
			SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
			senv.setOutputSoapObject(sop);
			senv.dotNet=true;
	 
			HttpTransportSE htp=new HttpTransportSE(s1.getString("url", ""));
			htp.call(soapaction, senv);
	 
			String result=senv.getResponse().toString();
			
		Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
			
			if(result.equalsIgnoreCase("anyType{}")||result.equalsIgnoreCase("na"))
			
			{
				Toast.makeText(getApplicationContext(),"No answerd user", Toast.LENGTH_SHORT).show();
	
			}
			
			else
			{
				String[] res=result.split("\\@");
				question=new String[res.length];
				reply=new String[res.length];
				psy=new String[res.length];
				for(int i=0;i<res.length;i++)
				{
					String temp[]=res[i].split("\\#");
					question[i]=temp[0];
					reply[i]=temp[1];
					psy[i]=temp[2];
					
				}
					
				//ArrayAdapter<String> ad=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, uname);
				//lv1.setAdapter(ad);
	        lv1.setAdapter(new Custom2(getApplicationContext(),question,reply,psy));
	        lv1.setOnItemClickListener(this);
			}
		}

		catch(Exception e){
	

			Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
		}		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questions_answered, menu);
		return true;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

}
