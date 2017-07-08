package org.magic.am;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.widget.Toast;
import android.widget.PopupWindow;
import android.widget.ImageView.ScaleType;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.content.ActivityNotFoundException;
import android.content.pm.ActivityInfo;
import android.util.Log;

public class ActorMovie extends Activity {
	private static final int SELECT_PHOTO_ACTOR = 20;
	private static final int SELECT_PHOTO_MOVIE = 40;
	private static final String TAG = "AM";
	private  boolean bigImageActor;
	private  boolean bigImageMovie;
	boolean imageActorBadUri;
	boolean imageMovieBadUri;
	
	String actorname;
	String movietitle;
	String idpair;
	String actorbirth;
	String actornatio;
	String actorweb;
	String movierelease;
	String moviegenre;
	String moviecountry;
	String movieweb;
	
	long idlong;
	
	TextView tvActorname; 
	TextView tvMovietitle; 
	TextView tvActorbirth;
	TextView tvActornatio;
	TextView tvActorweb;
	TextView tvMovierelease;
	TextView tvMoviegenre;
	TextView tvMoviecountry;
	TextView tvMovieweb;
	
	ImageView imageActor;
	ImageView imageMovie;
	String imagepathActor;
	AMDBAdapter db;
	AMBackup ambackup;
	
	String imagepathMovie;
	
	
	Uri imageUri;
	Uri uri;
	int widthActor;
	int heightActor;
	int widthMovie;
	int heightMovie;
	
	LinearLayout rightbottom;
	LinearLayout leftbottom;
	LinearLayout righttop;
	LinearLayout lefttop;
	
	boolean appShare;
	boolean appBackup;
	
	Typeface defaultType;
	Typeface specialType;
	int abcolor;
	int textcolor;
	ColorDrawable colorDrawable;
	
	boolean deleteSuccess;
	
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
	
	 private void getInfo()  {
	        
	        db.open();
	         
	        Cursor c = db.getAM(idlong);
	     
	        
	        //System.out.println("count "+ c.getCount());
	            
	        if(c.getCount() == 0){
	        	
	        	 return;
	            }
	         
	          if (c.moveToFirst())
	         	
	          {
	        	
	              do {
	                
	            	  actorbirth = c.getString(2);
	            	  actornatio = c.getString(3);
	            	  actorweb = c.getString(4);
	            	  movierelease = c.getString(6);
	            	  moviegenre = c.getString(7);
	            	  moviecountry = c.getString(8);
	            	  movieweb = c.getString(9);
	            	  
	            	  imagepathActor = c.getString(10);
	            	  //imageActor.setImageURI(Uri.parse(imagepathActor));
	            	  
	            	  Uri imageUri = Uri.parse(imagepathActor);
	            	  Bitmap bitmap;
	  	              BitmapFactory.Options options = new BitmapFactory.Options();
	  	              options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	            	  
	  	              try {
	                     /* Bitmap bitmap = MediaStore.Images.Media.getBitmap(  
	                                         getApplicationContext().getContentResolver(),   
	                                         Uri.parse(imagepathActor));*/

	  	            	bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
	  	            	System.out.println("actor movie : " + bitmap.getWidth() + " height " + bitmap.getHeight());
	  	            	/*int m =  Math.max(bitmap.getWidth(), bitmap.getHeight());
	  	            	int l = 1000;
	  	            	if (m > l){
	  	            		
	  	            		if (bitmap.getWidth() > bitmap.getHeight()){
	  	            			int w = l;
	  	            			int h = (int) ((double) l * (double) bitmap.getHeight() / (double) bitmap.getWidth());
	  	            			System.out.println("width > height :  weight " + w  + " height " + h);
	  	            		bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
	  	            		}
	  	            		else {
	  	            			int h = l;
	  	            			int w = (int)((double) l * (double) bitmap.getWidth() / (double) bitmap.getHeight());
	  	            			System.out.println("width <&= height :  weight " + w  + " height " + h);
	  	            		   bitmap = Bitmap.createScaledBitmap(bitmap, w, h, true);
	  	            		}
	  	            	}*/
	  	            	//bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/5, bitmap.getHeight()/5, true);
	  	            	imageActor.setImageBitmap(bitmap);
	  	            	
	                  } catch (FileNotFoundException e) {
	                      imageActorBadUri =  true;
	                      System.out.println("******  bad Uri  Actor ************");
	                      
	                      e.printStackTrace();
	                  } catch (IOException e) {
	                	  imageActorBadUri =  true;
	                	  
	                	  System.out.println("******  bad Uri  Actor IO ************");
	                      e.printStackTrace();
	                  }
	            	 
	            	
	            	 
	            	  
	            	  tvActorbirth.setText("Year of Birth : " + actorbirth);
	            	  tvActornatio.setText("Nationality : "+ actornatio);
	            	  //tvActorweb.setText(actorweb);
	            	  //tvActorweb.setVisibility(View.INVISIBLE);
	            	  
	            	  imagepathMovie = c.getString(11);
	            	  imageMovie.setImageURI(Uri.parse(imagepathMovie));
	            	  
	            	  try {
	                      Bitmap bitmap1 = MediaStore.Images.Media.getBitmap(  
	                                         getApplicationContext().getContentResolver(),   
	                                         Uri.parse(imagepathActor));

	                      
	                  } catch (FileNotFoundException e) {
	                      imageMovieBadUri =  true;
	                      System.out.println("******  bad Uri  Movie ************");
	                      
	                      e.printStackTrace();
	                  } catch (IOException e) {
	                	  imageMovieBadUri =  true;
	                	  
	                	  System.out.println("******  bad Uri  Movie IO ************");
	                      e.printStackTrace();
	                  }
	            	 
	            	  
	            	  tvMovierelease.setText("Year of Release : "+ movierelease);
	            	  tvMoviegenre.setText("Genre : " + moviegenre);
	            	  tvMoviecountry.setText("Country : " + moviecountry);
	            	  //tvMovieweb.setText(movieweb);
	            	  //tvMovieweb.setVisibility(View.INVISIBLE);
	                    
	               } while (c.moveToNext());
	          }
	        
	        db.close();
	    }
	
	 private void setImageActor(){
		 
		 db.open();
		 db.updateImagepath(idlong, imagepathActor);
		 
		 db.close();
		 
	 }
    
	  private void setImageMovie(){
		 
		 db.open();
		 db.updateImageMovie(idlong, imagepathMovie);
		 
		 db.close();
		 
	 }
	 public void onWindowFocusChanged(boolean hasFocus) {
		    // TODO Auto-generated method stub
		    super.onWindowFocusChanged(hasFocus);

               widthActor = imageActor.getWidth();
               heightActor = imageActor.getHeight();
               widthMovie = imageMovie.getWidth();
               heightMovie = imageMovie.getHeight();
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
	 
	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.actor_movie);
		actorname = getIntent().getStringExtra("actorname");
        movietitle = getIntent().getStringExtra("movietitle");
        idpair = getIntent().getStringExtra("idpair");
        idlong = Long.parseLong(idpair);
        
        
        ActionBar actionBar = getActionBar();
        actionBar.setTitle("");
        //actionBar.setTitle("    "  + actorname + "  :   " +  movietitle); 		
        //actionBar.setTitle("  ID : " + idpair + " " + actorname + " " + movietitle);
        //actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.blue));
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        
        rightbottom = (LinearLayout) findViewById(R.id.am_rightbottom);
    	leftbottom = (LinearLayout) findViewById(R.id.am_leftbottom);
    	righttop = (LinearLayout) findViewById(R.id.am_righttop);
    	lefttop = (LinearLayout) findViewById(R.id.am_lefttop);
        
    	tvActorname   = (TextView)findViewById(R.id.actorspec);
        tvActorbirth = (TextView)findViewById(R.id.actorbirth);
        tvActornatio = (TextView)findViewById(R.id.actornatio);
        tvMovietitle   = (TextView)findViewById(R.id.moviespec);
        tvMovierelease = (TextView)findViewById(R.id.movierelease);
        tvMoviegenre = (TextView)findViewById(R.id.moviegenre);
        tvMoviecountry = (TextView)findViewById(R.id.moviecountry);
        
    	defaultType = tvActorname.getTypeface();
    	
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        String appColor = SP.getString("app_color", "blue");
        appShare = SP.getBoolean("sharing", true);
        appBackup = SP.getBoolean("backup", true);
        String appFont = SP.getString("app_font", "Default");
        
        switch(appFont){
	    case "Default":
	    	specialType = defaultType;
	    	//textview.setTypeface(defaultType);
	    	break;
	    case "Architects Daughter":
	    	 specialType = Typeface.createFromAsset(getAssets(), "ArchitectsDaughter.ttf");
	    	break;
	    case "Blokletters-Balpen":
	    	  specialType = Typeface.createFromAsset(getAssets(), "Blokletters-Balpen.ttf");
	        break;
	    case "Comic Relief":
	    	 specialType = Typeface.createFromAsset(getAssets(), "ComicRelief.ttf");
	    	break;
	    case "Courier Prime":
	    	 specialType = Typeface.createFromAsset(getAssets(), "Courier_Prime.ttf");
	    	break;
	    case "Heuristica Italic":
	    	 specialType = Typeface.createFromAsset(getAssets(), "Heuristica-Italic.otf");
	    	break;
	    case "ShortStack-Regular":
	    	  specialType = Typeface.createFromAsset(getAssets(), "ShortStack-Regular.otf");
	          break;
	    }
        tvActorname.setTypeface(specialType);
        tvActorbirth.setTypeface(specialType);
        tvActornatio.setTypeface(specialType);
        tvMovietitle.setTypeface(specialType);
        tvMovierelease.setTypeface(specialType);
        tvMoviegenre.setTypeface(specialType);
        tvMoviecountry.setTypeface(specialType);
        
        
        View view = findViewById(R.id.actor_movie);	
        //final int splitBarId = getResources().getIdentifier("split_action_bar", "id", "android");
        //View splitActionBar = findViewById(splitBarId);
        //System.out.println("split action bar " + splitBarId);
        //final int popupWindow = getResources().getIdentifier("popup_window", "id", "android");
        //System.out.println("popup window " + popupWindow);
        
        
        switch (appColor) {
		case "blue":
			abcolor = R.color.actionbarblue;
			textcolor = R.color.lightblue;
			colorDrawable = new ColorDrawable(Color.parseColor("#ff0589b1"));
			
			   //view.setBackgroundColor(getResources().getColor(R.color.lightblue));
			    
			    //actionBar.setBackgroundDrawable(colorDrawable);
			    //splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarblue));
			    
			    rightbottom.setBackground(getResources().getDrawable(R.drawable.border_rb_blue));
			    leftbottom.setBackground(getResources().getDrawable(R.drawable.border_lb_blue));
			    righttop.setBackground(getResources().getDrawable(R.drawable.border_rt_blue));
			    lefttop.setBackground(getResources().getDrawable(R.drawable.border_lt_blue));
			    
		        	    
		     break;
		case "red":
			
			abcolor = R.color.actionbarred;
			textcolor = R.color.red;
			//view.setBackgroundColor(getResources().getColor(R.color.red));
			colorDrawable = new ColorDrawable(Color.parseColor("#ee3333"));
		    //actionBar.setBackgroundDrawable(colorDrawable1);
		    //splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarred));
		    rightbottom.setBackground(getResources().getDrawable(R.drawable.border_rb_red));
		    leftbottom.setBackground(getResources().getDrawable(R.drawable.border_lb_red));
		    righttop.setBackground(getResources().getDrawable(R.drawable.border_rt_red));
		    lefttop.setBackground(getResources().getDrawable(R.drawable.border_lt_red));
		    break;
		case "cyan":
			abcolor = R.color.actionbarcyan;
			textcolor = R.color.cyan;
			//view.setBackgroundColor(getResources().getColor(R.color.cyan));
			colorDrawable = new ColorDrawable(Color.parseColor("#ff05b189"));
		    //actionBar.setBackgroundDrawable(colorDrawable2);
		    //splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarcyan));
		    rightbottom.setBackground(getResources().getDrawable(R.drawable.border_rb_cyan));
		    leftbottom.setBackground(getResources().getDrawable(R.drawable.border_lb_cyan));
		    righttop.setBackground(getResources().getDrawable(R.drawable.border_rt_cyan));
		    lefttop.setBackground(getResources().getDrawable(R.drawable.border_lt_cyan));
		    break;
		case "chartreuse":
			abcolor = R.color.actionbarchartreuse;
			textcolor = R.color.chartreuse;
			//view.setBackgroundColor(getResources().getColor(R.color.chartreuse));
			colorDrawable = new ColorDrawable(Color.parseColor("#ff89b105"));
		    //actionBar.setBackgroundDrawable(colorDrawable3);
		    //splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarchartreuse));
		    rightbottom.setBackground(getResources().getDrawable(R.drawable.border_rb_chartreuse));
		    leftbottom.setBackground(getResources().getDrawable(R.drawable.border_lb_chartreuse));
		    righttop.setBackground(getResources().getDrawable(R.drawable.border_rt_chartreuse));
		    lefttop.setBackground(getResources().getDrawable(R.drawable.border_lt_chartreuse));
			break;
		case "purple":
			abcolor = R.color.actionbarpurple;
			textcolor = R.color.purple;
			//view.setBackgroundColor(getResources().getColor(R.color.purple));
			colorDrawable = new ColorDrawable(Color.parseColor("#ff8905b1"));
		    //actionBar.setBackgroundDrawable(colorDrawable4);
		    //splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarpurple));
		    rightbottom.setBackground(getResources().getDrawable(R.drawable.border_rb_purple));
		    leftbottom.setBackground(getResources().getDrawable(R.drawable.border_lb_purple));
		    righttop.setBackground(getResources().getDrawable(R.drawable.border_rt_purple));
		    lefttop.setBackground(getResources().getDrawable(R.drawable.border_lt_purple));
			break;
		case "magenta":
			abcolor = R.color.actionbarmagenta;
			textcolor = R.color.magenta;
			//view.setBackgroundColor(getResources().getColor(R.color.magenta));
			colorDrawable = new ColorDrawable(Color.parseColor("#ffb10589"));
		    //actionBar.setBackgroundDrawable(colorDrawable5);
		    //splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbarmagenta));
		    rightbottom.setBackground(getResources().getDrawable(R.drawable.border_rb_magenta));
		    leftbottom.setBackground(getResources().getDrawable(R.drawable.border_lb_magenta));
		    righttop.setBackground(getResources().getDrawable(R.drawable.border_rt_magenta));
		    lefttop.setBackground(getResources().getDrawable(R.drawable.border_lt_magenta));
		  break;
		case "gold":
			abcolor = R.color.actionbargold;
			textcolor = R.color.gold;
			//view.setBackgroundColor(getResources().getColor(R.color.gold));
			colorDrawable = new ColorDrawable(Color.parseColor("#ffb18905"));
		    //actionBar.setBackgroundDrawable(colorDrawable6);
		    //splitActionBar.setBackgroundColor(getResources().getColor(R.color.actionbargold));
		    rightbottom.setBackground(getResources().getDrawable(R.drawable.border_rb_gold));
		    leftbottom.setBackground(getResources().getDrawable(R.drawable.border_lb_gold));
		    righttop.setBackground(getResources().getDrawable(R.drawable.border_rt_gold));
		    lefttop.setBackground(getResources().getDrawable(R.drawable.border_lt_gold));
		    break;
    	}
        

		    view.setBackgroundColor(getResources().getColor(textcolor));
		    actionBar.setBackgroundDrawable(colorDrawable);
		    //splitActionBar.setBackgroundColor(getResources().getColor(abcolor));
		    tvActorname.setTextColor(getResources().getColor(abcolor));
		    tvActorbirth.setTextColor(getResources().getColor(abcolor));
	        tvActornatio.setTextColor(getResources().getColor(abcolor));
	        tvMovietitle.setTextColor(getResources().getColor(abcolor));
	        tvMovierelease.setTextColor(getResources().getColor(abcolor));
	        tvMoviegenre.setTextColor(getResources().getColor(abcolor));
	        tvMoviecountry.setTextColor(getResources().getColor(abcolor));
        
	        
	        db = new AMDBAdapter(this);
	        ambackup = new AMBackup(this);
	        if (appBackup){
	             //AMBackup.backup(db);
	        	//ambackup.backup(db);
	        	ambackup.saveChange(db);
	        }
	        else{
	        	//AMBackup.nobackup(db);
	        	ambackup.nobackup(db);
	        }
	        
        imageActor = (ImageView) findViewById(R.id.imageActor); 
        
        imageActor.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                         
            	if (!bigImageActor && !bigImageMovie){
            	        Intent imagePicker = new Intent(Intent.ACTION_PICK,
			            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			            startActivityForResult(imagePicker, SELECT_PHOTO_ACTOR); 
			            //db = new AMDBAdapter(this);
			            //Log.i(TAG, "PPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPPP");
			            //AMBackup.backup(db);
			            ambackup.saveChange(db);
            	//if (!bigImageActor && !bigImageMovie){
			           /* Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PHOTO_ACTOR);*/
                
            	}
            	        //AMBackup.backup(db);
                        return false;
            }
        });
        
       
        
        /*tvActorname   = (TextView)findViewById(R.id.actorspec);
        tvActorbirth = (TextView)findViewById(R.id.actorbirth);
        tvActornatio = (TextView)findViewById(R.id.actornatio);
        tvActorweb = (TextView)findViewById(R.id.actorweb);*/
        
        imageMovie = (ImageView) findViewById(R.id.imageMovie);
        
        
        
        //imageMovie.setBackground(getResources().getDrawable(R.drawable.border_image));
        
        imageMovie.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                            
            	if (!bigImageMovie && !bigImageActor){           
            	        Intent imagePicker = new Intent(Intent.ACTION_PICK,
			            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			            startActivityForResult(imagePicker, SELECT_PHOTO_MOVIE); 
			            //AMBackup.backup(db);
			            ambackup.saveChange(db);
            	/*if (!bigImageMovie && !bigImageActor){
			            Intent intent = new Intent();
                        intent.setType("image/*");
                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PHOTO_MOVIE);*/
            	}
                        return false;
            }
        });
        
        
        /*tvMovietitle   = (TextView)findViewById(R.id.moviespec);
        tvMovierelease = (TextView)findViewById(R.id.movierelease);
        tvMoviegenre = (TextView)findViewById(R.id.moviegenre);
        tvMoviecountry = (TextView)findViewById(R.id.moviecountry);
        tvMovieweb = (TextView)findViewById(R.id.movieweb);*/
        
        tvActorname.setText("Actor/Actress : " + actorname);
        tvMovietitle.setText("Movie : " + movietitle);
        
        
        
        
        getInfo();
        
        //isEmpty might not needed!!!
        if (imagepathActor.isEmpty() || imageActorBadUri){
        	 imageActor.setBackground(getResources().getDrawable(R.drawable.border_image));
        }
        if (imagepathMovie.isEmpty() || imageMovieBadUri){
             imageMovie.setBackground(getResources().getDrawable(R.drawable.border_image));
        }
        
        
        //these are here, because they need query from DB, imagepath
        imageActor.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	
            if (!imagepathActor.isEmpty() && !imageActorBadUri){
   			    Intent i = new Intent("org.magic.am.AMFullscreen");
   		        i.putExtra("imagepath", imagepathActor);
   		        startActivityForResult(i,1);
            }
            	//Display display = getWindowManager().getDefaultDisplay();
            	//int width = display.getWidth(); // ((display.getWidth()*20)/100)
            	//int height = display.getHeight();// ((display.getHeight()*30)/100)
            	//display.getSize(Point);
               
                
                /*if (!bigImageActor){
            		if (!imagepathActor.isEmpty() && !imageActorBadUri){
            	      LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(800,1067);
            	      imageActor.setLayoutParams(parms);
            	      //imageActor.setScaleType(ScaleType.FIT_XY);
            	      //imageActor.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                      imageActor.setAdjustViewBounds(true);
            	      bigImageActor = true;
            	      //System.out.println("big actor image *******************");
            		}
            	}
            	else{
            		LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(widthActor, heightActor);
                	imageActor.setLayoutParams(parms);
                	bigImageActor = false;
            	}*/
            }
        });
        

        imageMovie.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            
            	 if (!imagepathMovie.isEmpty() && !imageMovieBadUri){
        			    Intent i = new Intent("org.magic.am.AMFullscreen");
        		        i.putExtra("imagepath", imagepathMovie);
        		        startActivityForResult(i,1);
                 }
            	 
            	/*if (!bigImageMovie){
            		if (!imagepathMovie.isEmpty()){
            	      LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(800,1067);
            	      imageMovie.setLayoutParams(parms);
            	      //imageMovie.setScaleType(ScaleType.FIT_XY);
            	      //imageMovie.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                      imageMovie.setAdjustViewBounds(true);
            	      bigImageMovie = true;
            		}
            	}
            	else{
            		LinearLayout.LayoutParams parms = new LinearLayout.LayoutParams(widthMovie, heightMovie);
                	imageMovie.setLayoutParams(parms);
                	bigImageMovie = false;
            	}*/
            }
        });
        
   }
	
	 public void onClickWebpage(){
			openBrowser(1);
			
		}

	 private void openBrowser(int n){
		 if (n == 1){
			//uri = Uri.parse(tvActorweb.getText().toString());
			uri = Uri.parse(actorweb);
		 }
		 if (n == 2)
		 {
			 //uri = Uri.parse(tvMovieweb.getText().toString());
		     uri = Uri.parse(movieweb);
		 }
			 
		 try{
			 Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			startActivity(intent);
		 }
		 catch(ActivityNotFoundException e){
			
			 System.out.println("web url is not correct!!!!");
			 Toast.makeText(this, "no link added ", Toast.LENGTH_LONG).show();
			 e.printStackTrace();
		 }
			
		}
	
	 public void onClickWebpageMovie(){
			openBrowser(2);
			
		}

	 
	 
	 @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.actor_movie_menu, menu);
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
		case R.id.edittext:
			 //Toast.makeText(this, "Edit", Toast.LENGTH_LONG).show();
			 
			 Intent i = new Intent("org.magic.am.AMEdit");
			    i.putExtra("idpair", idpair);
		        i.putExtra("actorname", actorname);
		        i.putExtra("movietitle", movietitle);
		        i.putExtra("actorbirth", actorbirth);
		        i.putExtra("actornatio", actornatio);
		        i.putExtra("actorweb", actorweb);
		        i.putExtra("movierelease", movierelease);
		        i.putExtra("moviegenre", moviegenre);
		        i.putExtra("moviecountry", moviecountry);
		        i.putExtra("movieweb", movieweb);
		        startActivityForResult(i,1);
			 break;
		case R.id.linkactor:
			onClickWebpage();
			break;
		case R.id.linkmovie:
			onClickWebpageMovie();
			break;
		case R.id.share:
			if (appShare){
			Intent intentShare = new Intent(Intent.ACTION_SEND_MULTIPLE);
			//Intent intentShare = new Intent(Intent.ACTION_SEND);
			//intentShare.setType("text/html");
			//intentShare.setType("message/rfc822");
			intentShare.setType("application/image");
			intentShare.putExtra(Intent.EXTRA_EMAIL, "emailaddress@emailaddress.com");
			intentShare.putExtra(Intent.EXTRA_SUBJECT, actorname + ", " + movietitle);
			intentShare.putExtra(Intent.EXTRA_TEXT, actorweb + "\n\n" + movieweb);
			
			ArrayList<Uri> uris = new ArrayList<Uri>();
			uris.add(Uri.parse(imagepathActor));
			uris.add(Uri.parse(imagepathMovie));
			intentShare.putParcelableArrayListExtra(Intent.EXTRA_STREAM, uris);
			
			//intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse(imagepathActor));
			//intentShare.putExtra(Intent.EXTRA_STREAM, Uri.parse(imagepathMovie));

			startActivity(Intent.createChooser(intentShare, "Sending Email"));
			}
			else{
				 Toast.makeText(( this ).getBaseContext(), "Share is not enabled", Toast.LENGTH_LONG).show();
			}
			break;
			
		case R.id.deletepair:
			try{
				
				new AlertDialog.Builder(this)
				   .setTitle("Delete")
				   .setMessage("Are you sure you want to do this?")
				   .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
				       public void onClick(DialogInterface dialog, int which) { 
				        // continue with delete
				    	   //System.out.println("delete in dialog");
				    	   deleteAM(idlong);
				    	  
				      }
				    })
				   .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
				    public void onClick(DialogInterface dialog, int which) { 
				        // do nothing
				      }
				    })
				    .setIcon(android.R.drawable.ic_dialog_alert)
				 .show();
				/*deleteAM(idlong);
				if (deleteSuccess){
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
				}*/
			}
			catch(Exception e){
				
			}
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
    	case SELECT_PHOTO_ACTOR:
    	 if (resultCode == Activity.RESULT_OK){
    		
    		 Toast toast =  Toast.makeText(( this ).getBaseContext(), "Image choosen", Toast.LENGTH_SHORT);
    		 //Uri selectedImage = returnedImage.getData();
    		 imageUri  = returnedImage.getData();
    		 imagepathActor = imageUri.toString();
    		 
    		 if (imageUri != null){
                  
    			imageActor.setImageURI(Uri.parse(imagepathActor));
    			setImageActor();
    			//db = new AMDBAdapter(this);
    			if(appBackup){
    				
       			//AMBackup.backup(db);
    				//ambackup.backup(db);
    				ambackup.saveChange(db);
    			}
    			
    			else{
    				//AMBackup.nobackup(db);
    				ambackup.nobackup(db);
    			}
    			
    	        if (imagepathActor.isEmpty()){
    	        	 imageActor.setBackground(getResources().getDrawable(R.drawable.border_image));
    	        }
    	        else{
    	        	imageActor.setBackground(getResources().getDrawable(R.drawable.border_image_invisible));
    	        }
    			imageActorBadUri = false;
    			 }
    		 }//if
    	 else{
    		 
    		 finish();
    	 }
    	 break;
    	case SELECT_PHOTO_MOVIE:
    		if (resultCode == Activity.RESULT_OK){
        		
       		 Toast toast =  Toast.makeText(( this ).getBaseContext(), "Image choosen", Toast.LENGTH_SHORT);
       		 //Uri selectedImage = returnedImage.getData();
       		 imageUri  = returnedImage.getData();
       		 imagepathMovie = imageUri.toString();
       		 
       		 if (imageUri != null){
                     
       			imageMovie.setImageURI(Uri.parse(imagepathMovie));
       			setImageMovie();
       		    //db = new AMDBAdapter(this);
       			if (appBackup){
       			   //AMBackup.backup(db);
       				//ambackup.backup(db);
       				ambackup.saveChange(db);
       			}
       			else{
       				//AMBackup.nobackup(db);
       				ambackup.nobackup(db);
       			}
       		 
       	        if (imagepathMovie.isEmpty()){
       	        	 imageMovie.setBackground(getResources().getDrawable(R.drawable.border_image));
       	        }
       	        else{
       	        	imageMovie.setBackground(getResources().getDrawable(R.drawable.border_image_invisible));
       	        }
       			imageMovieBadUri = false;
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
