package com.alfahmi.topupwithcode;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.app.AlertDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.TextView;
import android.view.*;
import java.io.*;


public class PenjualanActivity extends AppCompatActivity {
	
	SQLiteHelper SQLITEHELPER;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    SQLiteListAdapter ListAdapter ;
	private static String DB_PATH = "/data/data/com.alfahmi.topupwithcode/databases/";
	private static String DB_NAME = "AlfahmiDataBase";
	
    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> PHONE_ArrayList = new ArrayList<String>();
    ArrayList<String> PRICE_ArrayList = new ArrayList<String>();
	ArrayList<String> DATETRX_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.alfahmi_penjualan);
	
		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setTitle("Penjualan"); 
		LISTVIEW = (ListView) findViewById(R.id.alfahmi__list_penjualan);

        SQLITEHELPER = new SQLiteHelper(this);
        
    }

    @Override
    protected void onResume() {

    	ShowSQLiteDBdata() ;
        super.onResume();
    }

    private void ShowSQLiteDBdata() {
    	
    	SQLITEDATABASE = SQLITEHELPER.getWritableDatabase();
    	
        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM transactionTable", null);

        ID_ArrayList.clear();
        PHONE_ArrayList.clear();
        PRICE_ArrayList.clear();
		DATETRX_ArrayList.clear();
        
        if (cursor.moveToFirst()) {
            do {
            	ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_ID)));
            	
            	PHONE_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_PHONE)));
            	
            	PRICE_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_PRICE)));

				DATETRX_ArrayList.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.KEY_DATETRX)));
				
            } while (cursor.moveToNext());
        }
        
        ListAdapter = new SQLiteListAdapter(PenjualanActivity.this,
        		
        		ID_ArrayList,
        		PHONE_ArrayList,
        		PRICE_ArrayList,
				DATETRX_ArrayList
        		
        		);
        
        LISTVIEW.setAdapter(ListAdapter);
        
        cursor.close();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.alfahmi__menu_penjualan_hapus, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				onBackPressed();
				break;
			case R.id.alfahmi__action_delete:
				SQLiteDatabase.deleteDatabase(new File(DB_PATH + DB_NAME));
				cursor.requery();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
		return false;
	}
}
