package org.magic.am;

import java.util.ArrayList;

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
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.view.View.OnFocusChangeListener;

public class AMNew extends Activity {
	
	//EditText etActorName;
	AutoCompleteTextView actvActorName;
	TextView tvActorName;
	EditText etActorBirth;
	EditText etActorNatio;
	
	//EditText etMovieTitle;
	AutoCompleteTextView actvMovieTitle;
	EditText etMovieRelease;
	EditText etMovieGenre;
	EditText etMovieCountry;
	
	String birthS;
	String natioS;
	String releaseS;
	String genreS;
	String countryS;
	
	AMDBAdapter db;
	String [] actors;
	ArrayList<String> actorslist;
	String [] movies;
	ArrayList<String> movieslist;
	int cursorCount;
	
	
	public void AMPair(){
		db.open();
        Cursor c = db.getAMPair(actvActorName.getText().toString(), actvMovieTitle.getText().toString() );
        //System.out.println("cursor count " + c.getCount());
        cursorCount = c.getCount();
        db.close();
        }
	
   private void getActors() {
        
        db.open();
        Cursor c = db.getDistinctActors(0);
        if(c.getCount() == 0){ return; }
        if (c.moveToFirst()) {
        	do {
                 	actorslist.add(c.getString(0));
                 } while (c.moveToNext());
           }
         db.close();
    }
   
   private void getMovies() {
       
       db.open();
       Cursor c = db.getDistinctMovies(0);
       if(c.getCount() == 0){ return; }
       if (c.moveToFirst()) {
       	  do {
                movieslist.add(c.getString(0));
              } while (c.moveToNext());
         }
         db.close();
   }
   
	public void getInfoActor(){
		db.open();
		Cursor c = db.getInfoOfActor(actvActorName.getText().toString());
		if (c != null && c.getCount() != 0){
        	
	         if (c.moveToFirst())
	         {
	             do {
	                
	                 if (!c.getString(0).isEmpty()){
	                  birthS = c.getString(0);	 
	                	}
	                 if (!c.getString(1).isEmpty()){
		                  natioS = c.getString(1);	 
		                	}
	             } while (c.moveToNext());
	          }//if
	         }//if
		etActorBirth.setText(birthS);
		etActorNatio.setText(natioS);
		
		 db.close();
		
	}
	public void getInfoMovie(){
		db.open();
		Cursor c = db.getInfoOfMovie(actvMovieTitle.getText().toString());
		if (c != null && c.getCount() != 0){
        	
	         if (c.moveToFirst())
	         {
	             do {
	                
	                 if (!c.getString(0).isEmpty()){
	                  releaseS = c.getString(0);	 
	                	}
	                 if (!c.getString(1).isEmpty()){
		                  genreS = c.getString(1);	 
		                	}
	                 if (!c.getString(2).isEmpty()){
		                  countryS = c.getString(2);	 
		                	}
	                 
	             } while (c.moveToNext());
	          }//if
	         }//if
		etMovieRelease.setText(releaseS);
		etMovieGenre.setText(genreS);
		etMovieCountry.setText(countryS);
		
		 db.close();
		
	}
	
	public void onClickViewPicture(){
        Intent i = new Intent("org.magic.am.AMPicture");
        i.putExtra("actorname", actvActorName.getText().toString());
        i.putExtra("actorbirth", etActorBirth.getText().toString());
        i.putExtra("actornatio", etActorNatio.getText().toString());
        
        i.putExtra("movietitle", actvMovieTitle.getText().toString());
        i.putExtra("movierelease", etMovieRelease.getText().toString());
        i.putExtra("moviegenre", etMovieGenre.getText().toString());
        i.putExtra("moviecountry", etMovieCountry.getText().toString());
        
		startActivityForResult(i,1);

	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.am_new);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("");
	   // actionBar.setHomeButtonEnabled(true);
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.am_new);	
        final int splitBarId = getResources().getIdentifier("split_action_bar", "id", "android");
        View splitActionBar = findViewById(splitBarId);
        //TextView tvauto = (TextView) findViewById(R.id.am_newauto);
        
        switch (appColor) {
		case "blue":
			view.setBackgroundColor(getResources().getColor(R.color.lightblue));
			 ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ff0589b1"));
			    actionBar.setBackgroundDrawable(colorDrawable);
			    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarblue));
			    //tvauto.setBackgroundColor(getResources().getColor(R.color.actionbarblue));
			break;
		case "red":
			view.setBackgroundColor(getResources().getColor(R.color.red));
			ColorDrawable colorDrawable1 = new ColorDrawable(Color.parseColor("#ee3333"));
		    actionBar.setBackgroundDrawable(colorDrawable1);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarred));
		    //tvauto.setBackgroundColor(getResources().getColor(R.color.actionbarred));
			break;
		case "cyan":
			view.setBackgroundColor(getResources().getColor(R.color.cyan));
			ColorDrawable colorDrawable2 = new ColorDrawable(Color.parseColor("#ff05b189"));
		    actionBar.setBackgroundDrawable(colorDrawable2);
		    splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarcyan));
		   // tvauto.setBackgroundColor(getResources().getColor(R.color.actionbarcyan));
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
        
		
		actorslist = new ArrayList<String>();
		movieslist = new ArrayList<String>();
		//etActorName   = (EditText)findViewById(R.id.actor);
		
		actvActorName = (AutoCompleteTextView)findViewById(R.id.actor);
	    etActorBirth   = (EditText)findViewById(R.id.yearbirth);
 	    etActorNatio   = (EditText)findViewById(R.id.nationality);
 	    
 	    //etMovieTitle = (EditText)findViewById(R.id.title);
 	    actvMovieTitle = (AutoCompleteTextView)findViewById(R.id.title);
 	    etMovieRelease = (EditText)findViewById(R.id.yearrelease);
 	    etMovieGenre = (EditText)findViewById(R.id.genre);
 	    etMovieCountry = (EditText)findViewById(R.id.country);
 	    
	    actvActorName.setOnFocusChangeListener(new OnFocusChangeListener() {          
	    	
	        public void onFocusChange(View v, boolean hasFocus) {
	            if (!hasFocus) {
	               // code to execute when EditText loses focus
	               //etActorBirth.setText("1974");
	            	getInfoActor();
	            }
	        }
	    });
	    
        actvMovieTitle.setOnFocusChangeListener(new OnFocusChangeListener() {          
	    	
	        public void onFocusChange(View v, boolean hasFocus) {
	            if (!hasFocus) {
	               // code to execute when EditText loses focus
	               //etActorBirth.setText("1974");
	            	getInfoMovie();
	            }
	        }
	    });
	    
	    db = new AMDBAdapter(this);
	    getActors();
	    actors = actorslist.toArray(new String[actorslist.size()]);
	    /*for (int i= 0 ; i < actors.length; i++){
	    	System.out.println("actor " + actors[i]);
	    }*/
	    //AMAutoAdapter adapterActor = new AMAutoAdapter(this, actors);
		//actvActorName.setAdapter(adapterActor);
		ArrayAdapter<String> adapterActor = new ArrayAdapter<String>(this, R.layout.new_list, actors);
		actvActorName.setAdapter(adapterActor);
		getMovies();
	    movies = movieslist.toArray(new String[movieslist.size()]);
	    //AMAutoAdapter adapterMovie = new AMAutoAdapter(this, movies);
		//actvMovieTitle.setAdapter(adapterMovie);
		ArrayAdapter<String> adapterMovie = new ArrayAdapter<String>(this, R.layout.new_list, movies);
		actvMovieTitle.setAdapter(adapterMovie);
	    
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.am_new_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		//int id = item.getItemId();
		String name = actvActorName.getText().toString();
		String title = actvMovieTitle.getText().toString();
		
		switch (item.getItemId()) {
			case android.R.id.home:
				Intent intent = new Intent(this, AMStart.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;

			
			case R.id.next:
				//check Actor Movie pair
				AMPair();
				
				if (cursorCount == 0){
					if (!name.isEmpty() && (!title.isEmpty())){
				    onClickViewPicture();
					}
					else
					{
						 Toast.makeText(this, "Name and Title cannot be empty", Toast.LENGTH_LONG).show();
					}
				}
				else{
					 Toast.makeText(this, "Actor Movie pair is in the library.", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.action_settings:	
				return true;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}

}
