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

public class MovieActors extends ListActivity {
	String movietitle;
	TextView tv;
	ArrayList<String> actorslist;
	String [] actors;
	ArrayList<String> idslist;
	String [] ids;
	AMDBAdapter db;
	int arrange;
	
	 private void getActors(int n) {
	        
	        db.open();
	       
	        Cursor c = db.getActorsOfMovie(movietitle, n);
	        
	        //System.out.println("count "+ c.getCount());
	            
	        if(c.getCount() == 0){
	        	
	        	 return;
	            }
	         
	          if (c.moveToFirst())
	         	
	          {
	        	
	              do {
	                 	
	            	  idslist.add(c.getString(0));
	            	  actorslist.add(c.getString(1));
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
		setContentView(R.layout.movie_actors);
		movietitle = getIntent().getStringExtra("movietitle");
		//tv   = (TextView)findViewById(R.id.moviewithactors);
		//tv.setText(movietitle);
		ActionBar actionBar = getActionBar();
        actionBar.setTitle("     "  + movietitle); 		
        //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
        actionBar.setHomeButtonEnabled(true); 
        actionBar.setDisplayHomeAsUpEnabled(true);
       
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.movie_actors);	
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
                
        
		
        actorslist = new ArrayList<String>();
		idslist =  new ArrayList<String>();
		
		db = new AMDBAdapter(this);
		arrange = Integer.parseInt(getIntent().getStringExtra("arrange"));
		System.out.println("** arrange ** " + arrange);
		getActors(arrange);
		
		ListView lv = getListView();
		lv.setTextFilterEnabled(true);
	
		
		actors = actorslist.toArray(new String[actorslist.size()]);
		ids = idslist.toArray(new String[idslist.size()]);
		//pairs = AMPairs.toArray(new Pair[AMPairs.size()]);
		
		AMActorsAdapter adapter = new AMActorsAdapter(this, actors);
		//AMArrayAdapter adapter = new AMArrayAdapter(this, actors, pairs);
	    setListAdapter(adapter);
		
		 }
	
	
	
	public void onListItemClick(ListView parent, View v, 
		    int position, long id) {
		        
		       
		        Intent i = new Intent("org.magic.am.ActorMovie");
		        i.putExtra("actorname", actors[position]);
		        i.putExtra("movietitle", movietitle);
		        i.putExtra("idpair", ids[position]);
		        startActivityForResult(i,1);
		        
	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_movieactors_menu, menu);
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
	
		case R.id.arrange_name:
			Intent i = new Intent("org.magic.am.MovieActors");
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        i.putExtra("arrange", "2");
	        i.putExtra("movietitle", movietitle);
	        startActivityForResult(i,1);
			System.out.println("arrange by name");
			break;
			
		case R.id.arrange_birth:
			Intent i2 = new Intent("org.magic.am.MovieActors");
			i2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	        i2.putExtra("arrange", "1");
	        i2.putExtra("movietitle", movietitle);
	        startActivityForResult(i2,1);
			System.out.println("arrange by birth");
			break;
			
		case R.id.action_settings:	
			return true;
		default:
			break;
	}
		return super.onOptionsItemSelected(item);
	}
	

	
}
