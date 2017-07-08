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
//import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.AutoCompleteTextView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
//import android.widget.AdapterView.OnItemClickListener;
import android.util.Log;
import android.app.AlertDialog;
import android.content.DialogInterface;

public class AMDelete extends Activity {
	
	//EditText etDelete;
	AutoCompleteTextView actvActor;
	AutoCompleteTextView actvMovie;
	AMDBAdapter db;
	AMBackup ambackup;
	String [] actors;
	ArrayList<String> actorslist;
	String [] movies;
	ArrayList<String> movieslist;
	String strid;
	long longid;
	private final String TAG = "AM";
	String dbText = "";
	boolean deleteSuccess;
	
	
	/*private void getDB(){
		db.open();
		//Cursor c = db.getAMText(n);
		Cursor c = db.getAllText();
		 if (c.moveToFirst())
         {
             do {
            	 for (int i = 0; i < 10; i++){
                       dbText += c.getString(i)+ "|";
            	 }
            	 dbText += c.getString(10)+ "\n";
	           
                 } while (c.moveToNext());
          }//if
		 //System.out.println(dbText);
		 Log.i(TAG, "-----------DBDBDBDBDB----------- " + dbText);
		db.close();
	}*/
	
	private void deleteSuccess(){

		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	     boolean appBackup = SP.getBoolean("back_up",true);
	     ambackup = new AMBackup(this);
	     
	     if(appBackup){
		     Log.i(TAG, "after deleting items\n");
		     //AMBackup.getDB(db);
              //ambackup.getDB(db);;
		     //AMBackup.textSaveToExternal();
              //ambackup.textSaveToExternal();
              ambackup.saveChange(db);
		     Log.i(TAG, "after deleting items\n");
	     }
	    Intent intent2 = new Intent(this, AMStart.class);
	    intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	    startActivity(intent2);
	}
	
	public void deleteAll(){
		
		Log.i(TAG, "deleting all items\n");
		Toast.makeText(this, "Deleting all items.", Toast.LENGTH_LONG).show();
		db.open();
		Cursor c = db.getAllIDs();
		if(c.getCount() == 0){
			deleteSuccess = true;
			return;
		}
		if (c.moveToFirst()) {
     	     do {
              	 longid = Long.parseLong(c.getString(0));
              	 //Log.i(TAG, "id = " + longid );
              	 deleteAM(longid);
         	  } while (c.moveToNext());
     	    
       }
	
		db.close();
	}
	
	
	public void deleteAM(long i) {
        db.open();
        if (db.deleteAM(i)){
            Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
            deleteSuccess();
            deleteSuccess = true;
        }
        else{
            Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
            deleteSuccess = false;
        }
        db.close();
    }
	
	public void deleteAM(){
		db.open();
		String actorname = actvActor.getText().toString();
		String movietitle = actvMovie.getText().toString();
		if (actorname.equals("*") && movietitle.equals("*")){
			deleteAll();
			return;
		}
		
		Cursor c = db.getAMPair(actorname, movietitle);
		if(c.getCount() == 0){
			Toast.makeText(this, "Delete failed.", Toast.LENGTH_LONG).show();
			deleteSuccess = false;
			return;
           }
		if (c.moveToFirst()) {
      	     do {
               	 longid = Long.parseLong(c.getString(0));
          	  } while (c.moveToNext());
        }
		deleteAM(longid);
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
	
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.delete);
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("");
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.delete);	
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
        
		
		//etDelete   = (EditText)findViewById(R.id.amid);
		actvActor = (AutoCompleteTextView)findViewById(R.id.actorac);
		actorslist = new ArrayList<String>();
		actvMovie = (AutoCompleteTextView)findViewById(R.id.movieac);
		movieslist = new ArrayList<String>();
		
		db = new AMDBAdapter(this);
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
	protected void onPause() {
		super.onPause();
		/*SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean appBackup = SP.getBoolean("back_up",false);
        if (appBackup){
		   db = new AMDBAdapter(this);
		   getDB();
        }*/
		Log.i(TAG,"---------------Another activity is taking focus (this activity is about to be \"paused\")-----------");
	}
	
	@Override
	protected void onStop() {
		super.onStop();
		//SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        //boolean appBackup = SP.getBoolean("back_up",false);
        //if (appBackup){
		  // db = new AMDBAdapter(this);
		  // getDB();
        //}
		
		Log.i(TAG, "--------------The activity is no longer visible (it is now \"stopped\")----------------");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		/*SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean appBackup = SP.getBoolean("back_up",false);
        Log.i(TAG, "-----------------appBackup----------- " + appBackup);
        if (appBackup){
		   db = new AMDBAdapter(this);
		   getDB();
        }*/

		Log.i(TAG, "----------------The activity is about to be destroyed.---------------------");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.am_delete_menu, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		
		switch (item.getItemId()) {
			case android.R.id.home:
				Intent intent = new Intent(this, AMStart.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				//Log.i(TAG, "----------------Goinf back.---------------------");
				startActivity(intent);
				break;

			
			case R.id.delete:
				
				try{
					
					new AlertDialog.Builder(this)
					   .setTitle("Delete")
					   .setMessage("Are you sure you want to do this?")
					   .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
					       public void onClick(DialogInterface dialog, int which) { 
					        // continue with delete
					    	   //System.out.println("delete in dialog");
					    	   deleteAM();
					    	  
					      }
					    })
					   .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog, int which) { 
					        // do nothing
					      }
					    })
					    .setIcon(android.R.drawable.ic_dialog_alert)
					 .show();
					
					
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
