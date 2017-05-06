package com.alfahmi.topupwithcode;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.*;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;
import android.content.*;
import android.widget.*;
import android.net.*;
import android.database.sqlite.*;
import java.text.*;
import java.util.*;



public class MainActivity extends AppCompatActivity implements View.OnClickListener {
	
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
	private Button btScan;
	private IntentIntegrator qrScan;
	private EditText edtChipNumber, edtMkios, edtPin;
	SQLiteDatabase SQLITEDATABASE;
	String PhoneNumber, Price, DateTrx;
	String SQLiteQuery;
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initNavigationDrawer();
		SharedPreferences sharedPreferences = getSharedPreferences("com.alfahmi.topupwithcode_preferences",Context.MODE_PRIVATE); 
		String nomorChip = sharedPreferences.getString("chipNumber","082214131211");
		String MKios = sharedPreferences.getString("MKios","5");
		String empin = sharedPreferences.getString("pin","1234");
		
		edtChipNumber = (EditText)findViewById(R.id.alfahmi_no_chip);
		edtMkios = (EditText)findViewById(R.id.alfahmi_mkios);
		edtPin = (EditText)findViewById(R.id.alfahmi_pin);
		
		edtChipNumber.setText(nomorChip);
		edtMkios.setText("M-KIOS V"+MKios);
		edtPin.setText(empin);
		
		btScan = (Button)findViewById(R.id.alfahmi_scan);
	
		qrScan = new IntentIntegrator(this);
		btScan.setOnClickListener(this);
		
		this.AlfahmiDBCreate();
    }
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
		if (result != null) {
			//if qrcode has nothing in it
			if (result.getContents() == null) {
				Toast.makeText(this, "Result Not Found", Toast.LENGTH_LONG).show();
			} else {
				//if qr contains data
				
				String raw = result.getContents().toString();

				raw = raw.substring(raw.length() -12);
				//Toast.makeText(this, raw, Toast.LENGTH_LONG).show();
				SharedPreferences sharedPreferences = getSharedPreferences("com.alfahmi.topupwithcode_preferences",Context.MODE_PRIVATE); 
				String MKios = sharedPreferences.getString("MKios","5");
				String empin = sharedPreferences.getString("pin","1234");
				String bintang = "*";
				String pagar = Uri.encode("#");
				String tujuh = "777";
				String uri = "tel:" + bintang + tujuh + bintang + raw + bintang + MKios + bintang + empin + pagar;
				Intent callIntent = new Intent(Intent.ACTION_CALL);
				callIntent.setData(Uri.parse(uri));
				startActivity(callIntent);
				
				// Ngambil Tanggal
				String dateTrx = DateFormat.getDateInstance().format(new Date());
				
				
				// Simpan Data Ke Database
				PhoneNumber = raw.toString();
				Price = MKios.toString();
				DateTrx = dateTrx.toString();
				
				SQLiteQuery = "INSERT INTO transactionTable (PHONE,PRICE,DATETRX) VALUES('"+PhoneNumber+"', '"+Price+"', '"+dateTrx+"');";
				SQLITEDATABASE.execSQL(SQLiteQuery);
			}
		}
	}
	public void onClick(View view) {
		//initiating the qr code scan
		qrScan.initiateScan();
	}
	// Navigation Drawer

    public void initNavigationDrawer() {

        NavigationView navigationView = (NavigationView)findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                int id = menuItem.getItemId();

                switch (id){
            
                    case R.id.alfahmi_menu_settings:
						drawerLayout.closeDrawers();
                   		Intent Pengaturan = new Intent(MainActivity.this, com.alfahmi.topupwithcode.SettingsActivity.class);
						startActivity(Pengaturan);
						finish();
						break;
					case R.id.alfahmi_menu_return_stock:
						drawerLayout.closeDrawers();
                   		Intent ReturnStock = new Intent(MainActivity.this, com.alfahmi.topupwithcode.ReturnStockActivity.class);
						startActivity(ReturnStock);
						break;
                    case R.id.alfahmi_menu_trx:
						drawerLayout.closeDrawers();
                   		Intent Penjualan = new Intent(MainActivity.this, com.alfahmi.topupwithcode.PenjualanActivity.class);
						startActivity(Penjualan);
                        break;
                    case R.id.alfahmi_menu_exit:
                        finish();

                }
                return true;
            }
        });
        View header = navigationView.getHeaderView(0);
        TextView tv_email = (TextView)header.findViewById(R.id.tv_email);
        tv_email.setText("alfahmi096@gmail.com");
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
            }

            @Override
            public void onDrawerOpened(View v) {
                super.onDrawerOpened(v);
            }
        };
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
	
	public void AlfahmiDBCreate(){

    	SQLITEDATABASE = openOrCreateDatabase("AlfahmiDataBase", Context.MODE_PRIVATE, null);
    	SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS transactionTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, PHONE VARCHAR, PRICE VARCHAR, DATETRX VARCHAR);");
    }

    
					

}
