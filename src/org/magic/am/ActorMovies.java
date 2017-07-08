package org.magic.am;

import java.util.ArrayList;

import android.app.ListActivity;
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
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.app.ActionBar;

public class ActorMovies extends ListActivity {
	String actorname;
	TextView tv;
	ArrayList<String> movieslist;
	String [] movies;
	ArrayList<String> idslist;
	String [] ids;
	AMDBAdapter db;
	int arrange;
	
	
    private void getMovies(int n) {
        
        db.open();
         //Cursor c = db.getAllAMs();
        //Cursor c = db.getDistinctActors();
        Cursor c = db.getMoviesOfActor(actorname, n);
        //Cursor c = db.getImageOfActor("l");
        //System.out.println("MAYA *****************************");
        //System.out.println("count "+ c.getCount());
            
        if(c.getCount() == 0){
        	
        	 return;
            }
         
          if (c.moveToFirst())
         	//System.out.println("cmovetofirst");
          {
        	
              do {
                 	//System.out.println("cursor ");
            	  idslist.add(c.getString(0));
            	  movieslist.add(c.getString(1));
                    //CreatePairs(c);
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
		setContentView(R.layout.actor_movies);
		actorname = getIntent().getStringExtra("actorname");
		
		//tv   = (TextView)findViewById(R.id.actorwithmovies);
		//tv.setText(actorname);
		ActionBar actionBar = getActionBar();
        actionBar.setTitle("     "  + actorname); 		
        //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.actor_movies);	
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
        
		movieslist = new ArrayList<String>();
		idslist =  new ArrayList<String>();
		//AMPairs = new ArrayList<Pair>();
		
		

		db = new AMDBAdapter(this);
		arrange = Integer.parseInt(getIntent().getStringExtra("arrange"));
		System.out.println("arrange " + arrange);
		getMovies(arrange);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
	
		
		movies = movieslist.toArray(new String[movieslist.size()]);
		ids = idslist.toArray(new String[idslist.size()]);
		//pairs = AMPairs.toArray(new Pair[AMPairs.size()]);
		
		AMMoviesAdapter adapter = new AMMoviesAdapter(this, movies);
		//AMArrayAdapter adapter = new AMArrayAdapter(this, actors, pairs);
	    setListAdapter(adapter);
		
		 }
	
	
	
	public void onListItemClick(ListView parent, View v, 
		    int position, long id) {
		 
		/*switch (position) {
		   case 0:
			
			break;
		case 1:
			//Toast.makeText(this, "Something new!", Toast.LENGTH_LONG).show();
			//startActivity(new Intent("org.magic.eagerstudy.Novalee"));
			break;
		
		
	    default:
	    	break;
		}*/
		        
		        //Toast.makeText(this, " actor : " + movies[position], 
		            //Toast.LENGTH_SHORT).show();
		        Intent i = new Intent("org.magic.am.ActorMovie");
		        i.putExtra("actorname", actorname);
		        i.putExtra("movietitle", movies[position]);
		        i.putExtra("idpair", ids[position]);
		        startActivityForResult(i,1);
		        
	
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_actormovies_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		/*int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}*/
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, AMStart.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
	
		case R.id.arrange_title:
			Intent i = new Intent("org.magic.am.ActorMovies");
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        i.putExtra("arrange", "2");
	        i.putExtra("actorname", actorname);
	        startActivityForResult(i,1);
			System.out.println("arrange by title");
			break;
			
		case R.id.arrange_release:
			Intent i2 = new Intent("org.magic.am.ActorMovies");
			i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        i2.putExtra("arrange", "1");
	        i2.putExtra("actorname", actorname);
	        startActivityForResult(i2,1);
			System.out.println("arrange by release");
			break;
		
		case R.id.action_settings:	
			return true;
		default:
			break;
	}
		return super.onOptionsItemSelected(item);
	}
	

}
