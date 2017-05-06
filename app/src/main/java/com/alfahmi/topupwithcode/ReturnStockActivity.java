package com.alfahmi.topupwithcode;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.support.design.widget.TextInputLayout;
import android.widget.Button;


public class ReturnStockActivity extends AppCompatActivity
{
	TextInputLayout txtInput1, txtInput2;
	EditText edtText1, edtText2;
	Button btnSubmit;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alfahmi__return_stock_change_pin);
		// FindViewById
		txtInput1 = (TextInputLayout)findViewById(R.id.alfahmi__text_edit_1);
		txtInput2 = (TextInputLayout)findViewById(R.id.alfahmi__text_edit_2);
		
		edtText1 = (EditText)findViewById(R.id.alfahmi__text_edit_field_1);
		edtText2 = (EditText)findViewById(R.id.alfahmi__text_edit_field_2);
		
		btnSubmit = (Button)findViewById(R.id.alfahmi__submit_button);
		
		// Config
		txtInput1.setErrorEnabled(true);
		txtInput1.setError("Required");
		
		txtInput2.setErrorEnabled(true);
		txtInput2.setError("Requirrd");
		
		
		
		
	}
	
}
