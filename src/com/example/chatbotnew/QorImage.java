package com.example.chatbotnew;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class QorImage extends Activity {
	RadioButton r0,r1;
	Button b1;
	
	public static String [] qid,qs, op;
	
	String namespace="http://tempuri.org/";
	String method="viewques";
	String soapaction="http://tempuri.org/viewques";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_qor_image);
		r0=(RadioButton)findViewById(R.id.radio0);
		r1=(RadioButton)findViewById(R.id.radio1);
		b1=(Button)findViewById(R.id.button1);
		
		
		
		
		
		r0.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				
				try {
					
		    		 SoapObject sop=new SoapObject(namespace, method);
		    		 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    		 senv.setOutputSoapObject(sop);
		    		 
		    		 
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
		    				 qid=new String[list.length];
		    				 qs=new String[list.length];
		    				 op=new String[list.length];
		    				 for(int i=0;i<list.length;i++)
		    				 {
		    					 String [] item=list[i].split("\\#");
		    					 qid[i]=item[0];
		    					 qs[i]=item[1];
		    					 op[i]=item[2];
		    				 }
//		    				 ArrayAdapter<String> ad=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,qs);
//		    				 l1.setAdapter(ad);
//		    				 l1.setOnItemClickListener(this);
		    				 Intent in=new Intent(getApplicationContext(),Q1.class);					
		  					startActivity(in);
		    				 
		    				
		    			 }
		    			 
		    		
		    		 }
		    		
		    		 
		    		 
				} catch (Exception e) {
					// TODO: handle exception
					 Toast.makeText(getApplicationContext(), "error"+e.getMessage()+"", Toast.LENGTH_LONG).show();
					 
				}
			}	
			});	

r1.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View arg0) {
		
		Intent in=new Intent(getApplicationContext(),Capture.class);					
			startActivity(in);
	}

});
		
		
		
	b1.setOnClickListener(new View.OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			
			Intent i=new Intent(getApplicationContext(),Result.class);
			startActivity(i);
		}
	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.qor_image, menu);
		return true;
	}

}
