package org.magic.am;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.animation.Animation;
import android.text.Html;
import android.text.Spanned;
import android.webkit.WebView;

public class AMHelp extends Activity{
	
	Boolean help;
	private final String TAG = "AM";
	ImageView imageView;
	TextView textView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.am_help);
		ActionBar actionBar = getActionBar();
        actionBar.setTitle("     Help"); 		
        //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
        //actionBar.setHomeButtonEnabled(true);
        //actionBar.setDisplayHomeAsUpEnabled(true);
        help = Boolean.parseBoolean( getIntent().getStringExtra("help"));
        textView  = (TextView) findViewById(R.id.helptext);
        if(help){
        	//Log.i(TAG, "Help Animation\n");
    		//Toast.makeText(this, "Help Animation.", Toast.LENGTH_LONG).show();
    		imageView = (ImageView) findViewById(R.id.imageViewAnim);
  
    		textView.setVisibility(View.GONE);
    		//textView.setText("");
    		imageView.setBackgroundResource(R.drawable.movie);
    		AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
            anim.stop();
            anim.start();
	
         }
        else{
        	//Log.i(TAG, "Help simple \n");
    		//Toast.makeText(this, "Help Simple", Toast.LENGTH_LONG).show();
            /*textView.setText("It gonna be a long help tutorial.\n\n"
            		+ "First you have an empty library which you can populate.\n\n"
            		+ "Adding a new pair of actor movie by choosing the 'New' from the menu.\n\n ");  	
            */
            //textView.setText(Html.fromHtml("<h2>Title</h2><br><p>Description here</p>"));
            
         // get our html content
            String htmlAsString = getString(R.string.html);
            Spanned htmlAsSpanned = Html.fromHtml(htmlAsString); // used by TextView

            // set the html content on the TextView
            //TextView textView = (TextView) findViewById(R.id.textView);
            textView.setText(htmlAsSpanned);
            
            //WebView webView = (WebView) findViewById(R.id.webView);
            //webView.loadDataWithBaseURL(null, htmlAsString, "text/html", "utf-8", null);
        }
        
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.actor_movie);	
        final int splitBarId = getResources().getIdentifier("split_action_bar", "id", "android");
        View splitActionBar = findViewById(splitBarId);
        
        switch (appColor) {
		case "blue":
			view.setBackgroundColor(getResources().getColor(R.color.lightblue));
			 ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ff0589b1"));
			    actionBar.setBackgroundDrawable(colorDrawable);
			    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarblue));
			break;
		case "red":
			view.setBackgroundColor(getResources().getColor(R.color.red));
			ColorDrawable colorDrawable1 = new ColorDrawable(Color.parseColor("#ee3333"));
		    actionBar.setBackgroundDrawable(colorDrawable1);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarred));
			break;
		case "cyan":
			view.setBackgroundColor(getResources().getColor(R.color.cyan));
			ColorDrawable colorDrawable2 = new ColorDrawable(Color.parseColor("#ff05b189"));
		    actionBar.setBackgroundDrawable(colorDrawable2);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarcyan));
			break;
		case "chartreuse":
			view.setBackgroundColor(getResources().getColor(R.color.chartreuse));
			ColorDrawable colorDrawable3 = new ColorDrawable(Color.parseColor("#ff89b105"));
		    actionBar.setBackgroundDrawable(colorDrawable3);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarchartreuse));
			break;
		case "purple":
			view.setBackgroundColor(getResources().getColor(R.color.purple));
			ColorDrawable colorDrawable4 = new ColorDrawable(Color.parseColor("#ff8905b1"));
		    actionBar.setBackgroundDrawable(colorDrawable4);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarpurple));
			break;
		case "magenta":
			view.setBackgroundColor(getResources().getColor(R.color.magenta));
			ColorDrawable colorDrawable5 = new ColorDrawable(Color.parseColor("#ffb10589"));
		    actionBar.setBackgroundDrawable(colorDrawable5);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarmagenta));
		  break;
		case "gold":
			view.setBackgroundColor(getResources().getColor(R.color.gold));
			ColorDrawable colorDrawable6 = new ColorDrawable(Color.parseColor("#ffb18905"));
		    actionBar.setBackgroundDrawable(colorDrawable6);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbargold));
			break;
    	}
	   	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.am_help_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		//String name = actvActorName.getText().toString();
		//String title = actvMovieTitle.getText().toString();
		
		switch (item.getItemId()) {
			case android.R.id.home:
				Intent intent = new Intent(this, AMStart.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;

			
			case R.id.nothing:
				
				
				
				break;
			case R.id.action_settings:	
				return true;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}
