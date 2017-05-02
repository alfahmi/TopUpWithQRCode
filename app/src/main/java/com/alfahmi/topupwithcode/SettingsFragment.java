package com.alfahmi.topupwithcode;

import android.content.*;
import android.support.v4.preference.PreferenceFragment;
import android.content.SharedPreferences.*;
import android.os.*;
import android.preference.*;
import android.preference.Preference.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.widget.ShareActionProvider.*;




public class SettingsFragment extends PreferenceFragment
{
	
	Context context;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.alfahmi.topupwithcode_preferences",context.MODE_PRIVATE); 

		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.alfahmi__preference);
		
		String nomorChip = sharedPreferences.getString("chipNumber","082214131211");
		((EditTextPreference)findPreference("chipNumber")).setSummary(nomorChip);
		((EditTextPreference)findPreference("chipNumber")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("chipNumber",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.alfahmi.topupwithcode_preferences",context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("chipNumber", type);
					editor.commit();
					return true;
				}
			});
		String MKios = sharedPreferences.getString("MKios","5");
		((ListPreference)findPreference("MKios")).setSummary(MKios);
		((ListPreference)findPreference("MKios")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("MKios",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.alfahmi.topupwithcode_preferences",context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("MKios", type);
					editor.commit();
					return true;
				}
			});


		String empin = sharedPreferences.getString("pin","1234");
		((EditTextPreference)findPreference("pin")).setSummary(empin);
		((EditTextPreference)findPreference("pin")).setOnPreferenceChangeListener(new OnPreferenceChangeListener() {

				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					String type = (String.valueOf(newValue));
					Intent i = new Intent();
					i.putExtra("pin",type);
					preference.setSummary(type);
					SharedPreferences sharedPreferences = getActivity().getSharedPreferences("com.alfahmi.topupwithcode_preferences",context.MODE_PRIVATE);
					SharedPreferences.Editor editor = sharedPreferences.edit();
					editor.putString("pin", type);
					editor.commit();
					return true;
				}
			});

		}
}
