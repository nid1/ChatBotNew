package com.example.chatbotnew;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class Custom extends BaseAdapter {
	private android.content.Context Context;
	String[] c;
	String[] d;

	public Custom(Context applicationContext, String[] qid, String[] qst) {

		this.Context=applicationContext;
		this.c=qid;
		this.d=qst;
		
		
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
LayoutInflater inflator=(LayoutInflater)Context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View gridView;
		if(convertview==null)
		{
			gridView=new View(Context);
			gridView=inflator.inflate(R.layout.activity_custom, null);
			
		}
		else
		{
			gridView=(View)convertview;
			
		}
		
		TextView tv1=(TextView)gridView.findViewById(R.id.textView1);
		TextView tv2=(TextView)gridView.findViewById(R.id.textView2);
		
		tv1.setTextColor(Color.BLUE);
		tv2.setTextColor(Color.BLUE);
		
		tv1.setText(c[position]);
		tv2.setText(d[position]);
		
		
		return gridView;
	}

}
