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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Questions extends Activity implements OnItemClickListener {
	ListView lv1;
	SharedPreferences sp;
	String namespace="http://tempuri.org/";
	String method="viewdques";
	String soapaction="http://tempuri.org/viewdques";
	String[] qid,question;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_questions);
		lv1=(ListView)findViewById(R.id.listView1);
		
		try{
        	
        	if(android.os.Build.VERSION.SDK_INT>9){
        		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        		StrictMode.setThreadPolicy(policy);
        		
        	}
        	
        }catch(Exception e){
        	
        }
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
		String id1=sp.getString("id", "");
		
		
		try {
			
	   		 SoapObject sop=new SoapObject(namespace, method);
	   		 
	   		 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	   		 senv.setOutputSoapObject(sop);
	   		 senv.dotNet = true;   		 
	   		 
	   		 HttpTransportSE htp=new HttpTransportSE(IpSettings.url);
	   		 htp.call(soapaction, senv);
	   		 
	   		 String result=senv.getResponse().toString();
	   		//Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
	   		 if(result.equalsIgnoreCase("anytype{}")|| result.equalsIgnoreCase("error"))
	   		 {
	   			 Toast.makeText(getApplicationContext(), "No data Found", Toast.LENGTH_LONG).show();
	   	    		 
	   		 }
	   		 else
	   		 {
	   			 String [] list=result.split("\\@");
	   			 if(list.length>0)
	   			 {
	   				 qid = new String[list.length];
	   				 question = new String[list.length];
	   				 
	   				 for(int i = 0; i < list.length; i++) {
	   					 String[] item = list[i].split("\\#");
	   					 qid[i] = item[0];
	   					 question[i] = item[1];
	   				 }
	   				 
	   				 ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, question);
	   				 lv1.setAdapter(ad);
	   				 
	   				 lv1.setOnItemClickListener(this);
	   				 
	   			 }
	   			 
	   		
	   		 }
	   		
	   		 
	   		 
			} catch (Exception e) {
				// TODO: handle exception
				 Toast.makeText(getApplicationContext(), "error"+e.getMessage()+"", Toast.LENGTH_LONG).show();
				 
			}
	}


	
	
	
	
//	b1.setOnClickListener(new View.OnClickListener() {
//		
//		@Override
//		public void onClick(View arg0) {
//			
//			Intent i=new Intent(getApplicationContext(),Question.class);
//			startActivity(i);
//			
//		}
//	});
	



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.questions, menu);
		return true;
	}

@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	
	Intent i= new Intent(getApplicationContext(),ReplyQuestions.class);
	i.putExtra("qus", question[arg2]);
	i.putExtra("qid", qid[arg2]);
	startActivity(i);
	
	
}

}
