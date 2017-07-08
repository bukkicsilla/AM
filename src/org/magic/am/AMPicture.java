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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.widget.ImageView;
import android.net.Uri;
import android.util.Log;

public class AMPicture extends Activity {
	
	private static final int SELECT_PHOTO = 10;
	String actorname;
	String actorbirth;
	String actornatio;
		
	String movietitle;
	String movierelease;
	String moviegenre;
	String moviecountry;
	private final String TAG = "AM";
	String dbText = "";
	
	AMDBAdapter db;
	ImageView AMImage;
	Uri imageUri;
	String imagepath; //actor
	boolean imageChanged;
	
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
		 Log.i(TAG, "%%%%%%%%%%5DBDBDBDBDB%%%%%%%%%%%% " + dbText);
		db.close();
	}*/
	
	public void addAM() {
        //System.out.println("before opening");
        db.open();
        //System.out.println("after opening");
        if (imagepath == null){
			imagepath = "";
		}
        if (db.insertAM(
            actorname, actorbirth, actornatio, movietitle, movierelease, moviegenre, moviecountry, imagepath, "", "", "") >= 0){
            Toast.makeText(this, actorname + " Add successful.", Toast.LENGTH_SHORT).show();
            //Toast.makeText(this, movietitle + " Add successful.", Toast.LENGTH_SHORT).show();
        }
            db.close();
    }

	//not needed
	  private void getImage() {
        
        db.open();
        Cursor c = db.getImageOfActor(actorname);
        //Cursor c = db.getDistinctActors();
        System.out.println("cursor count " + c.getCount());
        if (c != null && c.getCount() != 0){
        	
         if (c.moveToFirst())
         {
             do {
                 imagepath = c.getString(1);
                 System.out.println(imagepath);
             } while (c.moveToNext());
          }
         }
        db.close();
    }
	  
	  //not needed
       private void updateImages(){
          db.open();
          Cursor c = db.getIDsWithActor(actorname);
          if (c != null && c.getCount() != 0){
          	
              if (c.moveToFirst())
              {
                  do {
                      long id = Long.parseLong(c.getString(0));
                      //String name = c.getString(1);
                      //System.out.println("id " + id + " name "+ name);
                	  db.updateImagepath(id, imagepath);
                  } while (c.moveToNext());
               }
              }
             db.close();
        }

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.am_picture);
		
		ActionBar actionBar = getActionBar();
		actionBar.setTitle("");
	   // actionBar.setHomeButtonEnabled(true);
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        
        View view = findViewById(R.id.am_picture);	
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
        
        
		actorname = getIntent().getStringExtra("actorname");
		actorbirth = getIntent().getStringExtra("actorbirth");
		actornatio = getIntent().getStringExtra("actornatio");
		
		movietitle = getIntent().getStringExtra("movietitle");
		movierelease = getIntent().getStringExtra("movierelease");
		moviegenre = getIntent().getStringExtra("moviegenre");
		moviecountry = getIntent().getStringExtra("moviecountry");
		
		AMImage = (ImageView) findViewById(R.id.imageViewNew);
        //AMImage.setImageDrawable(getResources().getDrawable(R.drawable.blue));
		//imagepath = "storage/sdcard0/actors/blue.jpg";
		//AMImage.setImageURI(Uri.parse("/storage/sdcard0/actors/blue.jpg"));
		db = new AMDBAdapter(this);
		
		//it displays the last image saved with that person 
		//getImage();
		//if (imagepath != null && !imagepath.isEmpty()){
			//AMImage.setImageURI(Uri.parse(imagepath));
		//}
	   
	    
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
		Log.i(TAG,"%%%%%%%%%%%%%%%%%Another activity is taking focus (this activity is about to be \"paused\")%%%%%%%%%%%%%%%%%");
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
		
		Log.i(TAG, "%%%%%%%%%%%%%%%The activity is no longer visible (it is now \"stopped\")%%%%%%%%%%%%%%%%");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean appBackup = SP.getBoolean("back_up",false);
        if (appBackup){
		   db = new AMDBAdapter(this);
		  
        }

		Log.i(TAG, "%%%%%%%%%%%%%%%The activity is about to be destroyed.%%%%%%%%%%%%%%%%%%%%%%%%");
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.am_picture_menu, menu);
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

			
			case R.id.picture:
				Intent imagePicker = new Intent(Intent.ACTION_PICK,
			            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			            startActivityForResult(imagePicker, SELECT_PHOTO); 
			            
			           /* Intent imagePicker = new Intent();
                        imagePicker.setType("image/*");
                        imagePicker.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(imagePicker, "Select Picture"), SELECT_PHOTO);*/
				break;
			case R.id.done:
				//save data to database
				//if a new image is choosen, other images are set to this image. Not needed
				//if(imageChanged){
					//System.out.println("image changed");
					//updateImages();
				//}
				addAM();
				Intent intent2 = new Intent(this, AMStart.class);
				intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent2);
				
				//Bundle extras =  new Bundle();
				//extras.putString("str", actorname);
				//intent2.putExtras(extras);
				//startActivityForResult(intent2, 1);
				break;
			case R.id.action_settings:	
				return true;
			default:
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent returnedImage){
    	super.onActivityResult(requestCode, resultCode, returnedImage);
    	//Toast toastnot =  Toast.makeText(( this ).getBaseContext(), "NOT OK", Toast.LENGTH_SHORT);
    	
    	//if (requestCode == captureMomo){
    	switch(requestCode){
    	case SELECT_PHOTO:
    	 if (resultCode == Activity.RESULT_OK){
    		
    		 //Toast toast =  Toast.makeText(( this ).getBaseContext(), "OK", Toast.LENGTH_SHORT);
    		 //Uri selectedImage = returnedImage.getData();
    		 imageUri  = returnedImage.getData();
    		 imagepath = imageUri.toString();
    		 System.out.println("imageUri from gallery "  + imagepath);
    		 //String imagePath = getPathFromUri(this, imageUri);
    		 
    		 if (imageUri != null){
                  
    			AMImage.setImageURI(Uri.parse(imagepath));
    			imageChanged = true;
    			 
    			 
    		 }
    		 
    	     	 
    		        }//if
    	 else{
    		 
    		 finish();
    	 }
    	 break;
    	 
    	 
    	    		 }//switch
    	   //}
    	//}
    }
}
