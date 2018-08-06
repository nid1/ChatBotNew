package com.example.chatbotnew;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Capture extends Activity {

	Button b1,b2;
	ImageView img;
	 private static final int CAMERA_PIC_REQUEST = 2500;
	 Uri imageUri=null;
	 byte[] bitmapdata;	 
	 String base64img;
	 public static String encodedImage="";
	 String method="Log";
		String namespace="http://tempuri.org/";
		String soapaction=namespace+method;
		SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_capture);
		img=(ImageView)findViewById(R.id.imageView1);
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		sp=PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

		ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "NewPicture");
        values.put(MediaStore.Images.Media.DESCRIPTION, "From your Camera");
        imageUri= getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
		
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
	            intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
	            startActivityForResult(intent, CAMERA_PIC_REQUEST);
				
				
			}
		});
		
		b2.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					
					String s=sp.getString("lid", "");
	           		SoapObject sop=new SoapObject(namespace, method);
	           		sop.addProperty("u_id",s);
	           		sop.addProperty("img",encodedImage);
	           		
	           		 SoapSerializationEnvelope senv=new SoapSerializationEnvelope(SoapEnvelope.VER11);
	           		 senv.setOutputSoapObject(sop);
	           		 senv.dotNet= true;
	           		 
	           		 HttpTransportSE http= new HttpTransportSE(sp.getString("url",""));
	           		 http.call(soapaction, senv);
	           		 
	           		 String result=senv.getResponse().toString();	
	           		 
	           		Toast.makeText(getApplicationContext(), "Your data is being processed...!!! Please have Patience...!!!", Toast.LENGTH_LONG).show();	 	 
           			 startActivity(new Intent(getApplicationContext(), Userhome.class));
           			 
           		 }
				 catch (Exception e) {
					// TODO: handle exception
					 Toast.makeText(getApplicationContext(), "error "+e.getMessage(), Toast.LENGTH_LONG).show();
					 Toast.makeText(getApplicationContext(), "Your data is being processed...!!! Please have Patience...!!!", Toast.LENGTH_LONG).show();	 
           			 startActivity(new Intent(getApplicationContext(), Userhome.class));
				}
	
			
			}
		});
	}
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			  
			if (requestCode == CAMERA_PIC_REQUEST)
			{	        
				if (resultCode == RESULT_OK) 
				{   	   	
		            try {
		               Bitmap thumbnail = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
		               img.setImageBitmap(thumbnail);
		               String imageurl = getRealPathFromURI(imageUri);   
		               
		               Toast.makeText(getApplicationContext(), imageurl, Toast.LENGTH_SHORT).show();
		               
		               File file = new File(imageurl);
		               int ln=(int) file.length();
		               byte[] byteArray = null;
			   	       try
			   	        {
			   		        InputStream inputStream = new FileInputStream(file);
			   		        ByteArrayOutputStream bos = new ByteArrayOutputStream();
			   		        byte[] b = new byte[ln];
			   		        int bytesRead = 0;
			   		        
			   		        while ((bytesRead = inputStream.read(b)) != -1)
			   		        {
			   		        	bos.write(b, 0, bytesRead);
			   		        }
			   		        inputStream.close();
			   		        byteArray = bos.toByteArray();
			   	        }
			   	        catch (IOException e)
			   	        {
			   	            Toast.makeText(this,"String :"+e.getMessage().toString(), Toast.LENGTH_LONG).show();
			   	        }
			   	        String str = Base64.encodeToString(byteArray, Base64.DEFAULT);
		               
			   	        encodedImage=str;

		            } catch (Exception e) {
		                e.printStackTrace();
		            }

				}
			}
					
		}

		 public String getRealPathFromURI(Uri contentUri) {
			 String[] proj = { MediaStore.Images.Media.DATA };
		     Cursor cursor = managedQuery(contentUri, proj, null, null, null);
		     int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		     cursor.moveToFirst();
		     return cursor.getString(column_index);
		 }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.capture, menu);
		return true;
	}

}
