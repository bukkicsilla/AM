package org.magic.am;

import android.app.ActionBar;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemLongClickListener;

import java.util.ArrayList;


public class Movies extends ListActivity {
	String [] movies;
	
	ArrayList<String> movieslist;
	
	AMDBAdapter db;
	
	int arrange;
	
  private void GetAMs(int n) {
        
        db.open();
         
        Cursor c = db.getDistinctMovies(n);
        
        //System.out.println("count "+ c.getCount());
            
        if(c.getCount() == 0){
        	
        	 return;
            }
         
          if (c.moveToFirst())
         	
          {
        	
              do {
                 	//System.out.println("cursor ");
                 	
                    CreatePairs(c);
               } while (c.moveToNext());
          }
        
        db.close();
    }
 
 private void CreatePairs(Cursor c)
    {
        /*Toast.makeText(this,
                "id: " + c.getString(0) + "\nName: " + c.getString(1) + "\nTitle:  " + c.getString(2),
                        Toast.LENGTH_LONG).show();*/
	     //getAllAMs()
         //actorslist.add(c.getString(1));
         //Pair pair = new Pair(Long.parseLong(c.getString(0)), c.getString(1), null, null, c.getString(2), null, null,null, c.getString(3));
         
         
         //getDistinctActors()
         movieslist.add(c.getString(0));
         //Pair pair = new Pair(1, c.getString(0), null, null, "", null, null,null, c.getString(1));
         
         //getimageOfActor()
	 
         //actorslist.add(c.getString(0));
         //Pair pair = new Pair(1, c.getString(0), null, null, "", null, null,null, c.getString(1));
         
         //AMPairs.add(pair);
    
    }
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.movies);
		movieslist = new ArrayList<String>();
		//AMPairs = new ArrayList<Pair>();
		
		/*setListAdapter(new ArrayAdapter<String>(this, R.layout.luna_list,
				getResources().getStringArray(R.array.relax)));*/
		ActionBar actionBar = getActionBar();
        actionBar.setTitle("     Movies"); 		
        //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        
        
		db = new AMDBAdapter(this);
		arrange = Integer.parseInt( getIntent().getStringExtra("arrange"));
		GetAMs(arrange);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
		
		/*setListAdapter(new ArrayAdapter<String>(this, 
		            android.R.layout.simple_list_item_1, relax));*/
		
		movies = movieslist.toArray(new String[movieslist.size()]);
		//pairs = AMPairs.toArray(new Pair[AMPairs.size()]);
		
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.movies);	
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
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.actors_list, R.id.actorName, actors));
		//AMArrayAdapter adapter = new AMArrayAdapter(this, actors);
		AMArrayAdapter adapter = new AMArrayAdapter(this, movies);
		setListAdapter(adapter);
		
		 }
	
	public void onListItemClick(ListView parent, View v, 
		    int position, long id) {
		 
		
		        
		        //Toast.makeText(this, " actor : " + movies[position], 
		          //  Toast.LENGTH_SHORT).show();
		        Intent i = new Intent("org.magic.am.MovieActors");
		        i.putExtra("movietitle", movies[position]);
		        i.putExtra("arrange", "2");
		        startActivityForResult(i,1);
		        
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.listmovie_menu, menu);
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
	
		case R.id.arrange_title:
			Intent i = new Intent("org.magic.am.Movies");
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        i.putExtra("arrange", "2");
	        //i.putExtra("actorname", actorname);
	        startActivityForResult(i,1);
			System.out.println("arrange by title");
			break;
			
		case R.id.arrange_release:
			Intent i2 = new Intent("org.magic.am.Movies");
			i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        i2.putExtra("arrange", "1");
	        //i2.putExtra("actorname", actorname);
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
