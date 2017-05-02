package com.alfahmi.topupwithcode;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SQLiteListAdapter extends BaseAdapter {
	
    Context context;
    ArrayList<String> userNO;
    ArrayList<String> UserNOMOR;
    ArrayList<String> UserHARGA;
	ArrayList<String> UserDateTrx;
    

    public SQLiteListAdapter(
    		Context context2,
    		ArrayList<String> NO,
    		ArrayList<String> NOMOR,
    		ArrayList<String> HARGA,
			ArrayList<String> DATETRX
    		) 
    {
        	
    	this.context = context2;
        this.userNO = NO;
        this.UserNOMOR = NOMOR;
        this.UserHARGA = HARGA;
		this.UserDateTrx = DATETRX;
		
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userNO.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {
    	
        Holder holder;
        
        LayoutInflater layoutInflater;
        
        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.alfahmi_data_penjualan, null);
            
            holder = new Holder();
            
            holder.textviewid = (TextView) child.findViewById(R.id.alfahmi__penjualan_no);
            holder.textviewphone_number = (TextView) child.findViewById(R.id.alfahmi__penjualan_nomor);
            holder.textviewsubject = (TextView) child.findViewById(R.id.alfahmi__penjualan_harga);
            holder.textviewdatetrx = (TextView) child.findViewById(R.id.alfahmi__penjualan_tanggaltrx);
            
            child.setTag(holder);
            
        } else {
        	
        	holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userNO.get(position));
        holder.textviewphone_number.setText(UserNOMOR.get(position));
        holder.textviewsubject.setText(UserHARGA.get(position));
		holder.textviewdatetrx.setText(UserDateTrx.get(position));
		
        return child;
    }

    public class Holder {
        TextView textviewid;
        TextView textviewphone_number;
        TextView textviewsubject;
		TextView textviewdatetrx;
    }

}
