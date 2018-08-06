package com.example.chatbotnew;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom2 extends BaseAdapter {
	private android.content.Context context;
	String[] b;
	String[] c;
	String[] d;

	public Custom2(Context applicationContext, String[] b, String[] c,String[] d) {

		this.context=applicationContext;
		this.b=b;
		this.c=c;
		this.d=d;
		
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return c.length;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertview, ViewGroup parent) {
LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;
		if(convertview==null)
		{
			gridView=new View(context);
			gridView=inflator.inflate(R.layout.activity_custom2, null);
			
		}
		else
		{
			gridView=(View)convertview;
			
		}
		
		TextView tv1=(TextView)gridView.findViewById(R.id.textView1);
		TextView tv2=(TextView)gridView.findViewById(R.id.textView2);
		TextView tv3=(TextView)gridView.findViewById(R.id.textView3);
		
		tv1.setTextColor(Color.BLUE);
		tv2.setTextColor(Color.RED);
		tv3.setTextColor(Color.BLUE);
		
		tv1.setText("Qus:"+b[position]);
		tv2.setText("Ans:"+c[position]);
		tv3.setText("Psychatrist:"+d[position]);
		
		return gridView;
	}

}
