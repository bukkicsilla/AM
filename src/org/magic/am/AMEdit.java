package org.magic.am;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.view.View;

public class AMEdit extends Activity {

	String idpair;
    long idlong;
    String actorname;
	String movietitle;
	String actorbirth;
	String actornatio;
	String actorweb;
	String movierelease;
	String moviegenre;
	String moviecountry;
    String movieweb;
    
    EditText etActorName;
	EditText etActorBirth;
	EditText etActorNatio;
	EditText etActorWeb;
	
	EditText etMovieTitle;
	EditText etMovieRelease;
	EditText etMovieGenre;
	EditText etMovieCountry;
	EditText etMovieWeb;
	
    AMDBAdapter db;
    
    
    public void getInfoActor(){
		db.open();
		Cursor c = db.getInfoOfActor(etActorName.getText().toString());
		if (c != null && c.getCount() != 0){
        	
	         if (c.moveToFirst())
	         {
	             do {
	                
	                 if (!c.getString(0).isEmpty()){
	                  actorbirth = c.getString(0);	 
	                	}
	                 if (!c.getString(1).isEmpty()){
		                  actornatio = c.getString(1);	 
		                	}
	                 
	                 if (!c.getString(2).isEmpty()){
		                  actorweb = c.getString(2);	 
		                	}
	                 
	             } while (c.moveToNext());
	          }//if
	         }//if
		etActorBirth.setText(actorbirth);
		etActorNatio.setText(actornatio);
		etActorWeb.setText(actorweb);
		
		 db.close();
		
	}
	public void getInfoMovie(){
		db.open();
		Cursor c = db.getInfoOfMovie(etMovieTitle.getText().toString());
		if (c != null && c.getCount() != 0){
        	
	         if (c.moveToFirst())
	         {
	             do {
	                
	                 if (!c.getString(0).isEmpty()){
	                  movierelease = c.getString(0);	 
	                	}
	                 if (!c.getString(1).isEmpty()){
		                  moviegenre = c.getString(1);	 
		                	}
	                 if (!c.getString(2).isEmpty()){
		                  moviecountry = c.getString(2);	 
		                	}
	                 if (!c.getString(3).isEmpty()){
		                  movieweb = c.getString(3);	 
		                	}
	             } while (c.moveToNext());
	          }//if
	         }//if
		etMovieRelease.setText(movierelease);
		etMovieGenre.setText(moviegenre);
		etMovieCountry.setText(moviecountry);
		etMovieWeb.setText(movieweb);
		 
		db.close();
		
	}
	
	private void updateAM(){
		
	db.open();
	
	actorbirth = etActorBirth.getText().toString();
	actornatio = etActorNatio.getText().toString();
	actorweb = etActorWeb.getText().toString();
	movierelease =  etMovieRelease.getText().toString();
	moviegenre =  etMovieGenre.getText().toString();
	moviecountry =  etMovieCountry.getText().toString();
	movieweb =  etMovieWeb.getText().toString();
	
	db.updateTextAM(idlong, actorbirth, actornatio, actorweb, movierelease, moviegenre, moviecountry, movieweb);
	db.close();
	}
	
	
	
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.am_edit);
	    idpair = getIntent().getStringExtra("idpair");
        idlong = Long.parseLong(idpair);
		
		actorname = getIntent().getStringExtra("actorname");
		actorbirth = getIntent().getStringExtra("actorbirth");
		actornatio = getIntent().getStringExtra("actornatio");
		actorweb = getIntent().getStringExtra("actorweb");
		
		movietitle = getIntent().getStringExtra("movietitle");
		movierelease = getIntent().getStringExtra("movierelease");
		moviegenre = getIntent().getStringExtra("moviegenre");
		moviecountry = getIntent().getStringExtra("moviecountry");
		movieweb = getIntent().getStringExtra("movieweb");
		
		etActorName   = (EditText)findViewById(R.id.editactor);
	    etActorBirth   = (EditText)findViewById(R.id.edityearbirth);
 	    etActorNatio   = (EditText)findViewById(R.id.editnationality);
 	    etActorWeb   = (EditText)findViewById(R.id.editactorweb);
 	   
 	    etMovieTitle = (EditText)findViewById(R.id.edittitle);
 	    etMovieRelease = (EditText)findViewById(R.id.edityearrelease);
 	    etMovieGenre = (EditText)findViewById(R.id.editgenre);
 	    etMovieCountry = (EditText)findViewById(R.id.editcountry);
 	    etMovieWeb   = (EditText)findViewById(R.id.editmovieweb);	
		
        
	    etActorName.setText(actorname);
	    etActorBirth.setText(actorbirth);
	    etActorNatio.setText(actornatio);
	    etActorWeb.setText(actorweb);
	    
	    etMovieTitle.setText(movietitle);
	    etMovieRelease.setText(movierelease);
	    etMovieGenre.setText(moviegenre);
	    etMovieCountry.setText(moviecountry);
	    etMovieWeb.setText(movieweb);
	   
	    
	    db = new AMDBAdapter(this);
	    getInfoActor();
	    getInfoMovie();
	    
	    ActionBar actionBar = getActionBar();
        actionBar.setTitle("");
        
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.am_edit);	
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
		getMenuInflater().inflate(R.menu.am_edit_menu, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		switch (item.getItemId()) {
			case android.R.id.home:
				Intent intent = new Intent(this, AMStart.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;

			
			/*case R.id.picture:
				Intent imagePicker = new Intent(Intent.ACTION_PICK,
			            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			            startActivityForResult(imagePicker, SELECT_PHOTO); 
				break;*/
			case R.id.done:
				//save data to database
	              updateAM();			 
				 Intent i = new Intent("org.magic.am.ActorMovie");
				 i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			        i.putExtra("actorname", actorname);
			        i.putExtra("movietitle", movietitle);
			        i.putExtra("idpair", idpair);
			        startActivityForResult(i,1);
				break;
			case R.id.action_settings:	
				return true;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
}
