package org.magic.am;

import android.app.ActionBar;
import android.graphics.Typeface;
//import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
//import android.preference.PreferenceScreen;
//import android.view.Menu;
//import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.widget.TextView;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.content.SharedPreferences;
import android.preference.EditTextPreference;
import android.util.Log;
import android.content.pm.ActivityInfo;


public class AMSetting extends PreferenceActivity {
	//SharedPreferences SP;
	//String appUsername;
	//String appPassword;
	
	private static final String TAG = "AM";
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		
		ActionBar actionBar = getActionBar();
        actionBar.setTitle("");
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#dd27abd3"));
		actionBar.setBackgroundDrawable(colorDrawable); 
		//SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		
		getFragmentManager().beginTransaction().replace(android.R.id.content, new MyPreferenceFragment()).commit();
	    //createCustomActionBar();
		/*SharedPreferences settings = getSharedPreferences("UserInfo", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("Username","luna");
        editor.putString("Password","magic");
        editor.commit();*/
		
	}
	
	public class MyPreferenceFragment extends PreferenceFragment {
	 @Override
     public void onCreate(final Bundle savedInstanceState)
     {
         super.onCreate(savedInstanceState);
         addPreferencesFromResource(R.xml.preferences);
         
         /*SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
         
         boolean appBackup = SP.getBoolean("backup", false);
         String appUsername = SP.getString("backup_username", "");
         String appPassword = SP.getString("backup_password", "");
         Log.i(TAG, "Settings");
         Log.i(TAG, "Backup " + appBackup);*/
         
         
         /*if(appBackup){
         	//EditTextPreference etpUsername = (EditTextPreference) findPreference("backup_username");
         	//etpUsername.setText("echo");
         	//etpUsername.setSummary("echo");
         	//appUsername = "luna";
         	//appPassword = "magic";
        	 Log.i(TAG, "Settings backup ok");
         	
         }*/
        
         
     }
	 
	 @Override
	 public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
	     View view = super.onCreateView(inflater, container, savedInstanceState);
	     view.setBackgroundColor(getResources().getColor(R.color.transblue));
	     //TextView titleView = (TextView) view.findViewById(android.R.id.title);
	     
	     //TextView tv =  (TextView)super.onCreateView(inflater, container, savedInstanceState);
	     //tv.setTextColor(Color.RED);

	     return view;
	 }
	}
	
	
	 
	
}
