package org.magic.am;

import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.app.ActionBar;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.Toast;

public class AMFullscreen extends Activity {
	
	ImageView imageFullscreen;
	String imagepath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		
		setContentView(R.layout.am_fullscreen);
		//Toast.makeText(this, "Full screen", Toast.LENGTH_LONG).show();
		imagepath = getIntent().getStringExtra("imagepath");
		
		ActionBar actionBar = getActionBar();
        actionBar.hide(); 		
        
        //SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.am_fullscreen);	
        view.setBackgroundColor(getResources().getColor(R.color.pitchblack));
        
        
        
        imageFullscreen = (ImageView) findViewById(R.id.imageFullscreen); 
        imageFullscreen.setImageURI(Uri.parse(imagepath));
        
        
        
	   	
	}
}
