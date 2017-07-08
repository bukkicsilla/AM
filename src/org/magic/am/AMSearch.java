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
import android.view.View;
import android.widget.EditText;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class AMSearch extends Activity {
	
	
	
	EditText etDelete;
	AutoCompleteTextView actvActor;
	AutoCompleteTextView actvMovie;
	AMDBAdapter db;
	String [] actors;
	ArrayList<String> actorslist;
	String [] movies;
	ArrayList<String> movieslist;
	String strid;
	long longid, actorlongid, movielongid;
	String actorname;
	String movietitle;
	
	/*public void searchAM(long i) {
        db.open();
        if (db.deleteAM(i))
            Toast.makeText(this, "Search successful.", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, "Search failed.", Toast.LENGTH_LONG).show();
        db.close();
    }*/
	
	public void searchAM(){
		db.open();
		actorname = actvActor.getText().toString();
		movietitle = actvMovie.getText().toString();
		//longid = 0;
		Cursor c = db.getAMPair(actorname, movietitle);
		if(c.getCount() == 0){
			//Toast.makeText(this, "Search failed.", Toast.LENGTH_LONG).show();
			//longid = 0;
			
			//Toast.makeText(this, "long id." + longid, Toast.LENGTH_LONG).show();
			return;
           } 
		if (c.moveToFirst()) {
      	     do {
               	 longid = Long.parseLong(c.getString(0));
          	  } while (c.moveToNext());
        }
		//searchAM(longid);
		//System.out.println("long id " + longid);
		//Toast.makeText(this, "long id." + longid, Toast.LENGTH_LONG).show();
		db.close();
	}
	
	private void isActor(){
		
		db.open();
		actorname = actvActor.getText().toString();
		actorlongid = 0;
		Cursor c = db.getIDsWithActor(actorname);
		if (c.getCount() == 0){
			return;
		}
	   
			if (c.moveToFirst()) {
	      	     do {
	               	 actorlongid = Long.parseLong(c.getString(0));
	               	 System.out.println("longid " + actorlongid);
	          	  } while (c.moveToNext());
	        }
			db.close();
	}
	
    private void isMovie(){
		
		db.open();
		movietitle = actvMovie.getText().toString();
		movielongid = 0;
		Cursor c = db.getIDsWithMovie(movietitle);
		if (c.getCount() == 0){
			return;
		}
	   
			if (c.moveToFirst()) {
	      	     do {
	               	 movielongid = Long.parseLong(c.getString(0));
	               	 System.out.println("longid " + movielongid);
	          	  } while (c.moveToNext());
	        }
			db.close();
	}
	//for autocomplete
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
   
    //for autocomplete
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
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.am_search);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("");
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.am_search);	
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
        
        
        
		actvActor = (AutoCompleteTextView)findViewById(R.id.actorac);
		actorslist = new ArrayList<String>();
		actvMovie = (AutoCompleteTextView)findViewById(R.id.movieac);
		movieslist = new ArrayList<String>();
		
		db = new AMDBAdapter(this);
		//for autocomplete
		getActors();
	    actors = actorslist.toArray(new String[actorslist.size()]);
		ArrayAdapter<String> adapterActor = new ArrayAdapter<String>(this, R.layout.deleteactor_list, actors);
		actvActor.setAdapter(adapterActor);
		getMovies();
	    movies = movieslist.toArray(new String[movieslist.size()]);
		ArrayAdapter<String> adapterMovie = new ArrayAdapter<String>(this, R.layout.deleteactor_list, movies);
		actvMovie.setAdapter(adapterMovie);
		
		/*actvActor.setOnItemClickListener(new OnItemClickListener() {

			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				Toast.makeText(getApplicationContext(), "The winner is:" + arg0.getAdapter().getItem(arg2), Toast.LENGTH_SHORT).show();
				
			}
		});*/
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.am_search_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		switch (item.getItemId()) {
			case android.R.id.home:
				Intent intent = new Intent(this, AMStart.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;

			
			case R.id.search:
				
				try{
					searchAM();
					isActor();
					isMovie();
					
					System.out.println("SEARCH");
					System.out.println("ACTOR " + actorname);
					System.out.println("MOVIE " + movietitle);
					
					if (!actorname.isEmpty()){
					
						//System.out.println("actor yes");
						if (!movietitle.isEmpty()){
					       //actor yes , movie yes
							//System.out.println("actor yes, movie yes");
							
					       //searchAM();
					       if (longid > 0){
						
						       Intent i = new Intent("org.magic.am.ActorMovie");
						       i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					           i.putExtra("actorname", actorname);
					           i.putExtra("movietitle", movietitle);
					           i.putExtra("idpair", longid+"");
					           startActivityForResult(i,1);
					
					         } 
					
					       else {
								Toast.makeText(this, "Search failed.", Toast.LENGTH_LONG).show();
							}  
					       
					    	}//if movie yes
					
						else { //movie no
						
						//actor yes, ActorMovies
						//System.out.println("actor yes, movie no");
						//System.out.println("actorlongid " + actorlongid);
						if (actorlongid > 0){
						
						       Intent i = new Intent("org.magic.am.ActorMovies");
						       i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					           i.putExtra("actorname", actorname);
					           i.putExtra("arrange", "2");
					           startActivityForResult(i,1);
					
					         }
						else {
							Toast.makeText(this, "Search failed.", Toast.LENGTH_LONG).show();
						}
						
					}//else
					
					
					
						
					}
				   //actor no
					else {
						if (!movietitle.isEmpty()){
							// movie yes, MovieActors
							System.out.println("actor no, movie yes");
							if (movielongid > 0){
								
							       Intent i = new Intent("org.magic.am.MovieActors");
							       i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						           i.putExtra("movietitle", movietitle);
						           i.putExtra("arrange", "2");
						           startActivityForResult(i,1);
						
						         }
							else {
								Toast.makeText(this, "Search failed.", Toast.LENGTH_LONG).show();
							}
							
						}
						else{
							//search failed
							//System.out.println("search failed, no actor, no movie (((");
							Toast.makeText(this, "Search failed.", Toast.LENGTH_LONG).show();
						}
					}
	
				}
				catch(Exception e){
					
				}
				/*String ids = etDelete.getText().toString();
				System.out.println("id string " + ids);
				try{
				    long id =  Long.parseLong(etDelete.getText().toString());
				    System.out.println("id " + id);
				    deleteAM(id);
				}
				catch(NumberFormatException nfe){
					Toast.makeText(this, "This is not a valid ID.", Toast.LENGTH_LONG).show();
				}*/
			
				/*Intent intent1 = new Intent(this, AMStart.class);
				intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent1);*/
				break;
			case R.id.action_settings:	
				return true;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
}
