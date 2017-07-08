package org.magic.am;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.File;

import android.os.Environment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;

import android.app.ActionBar;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.database.Cursor;
import android.widget.ImageView;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.AnimationDrawable;
import android.view.animation.Animation;
import 	android.graphics.drawable.BitmapDrawable;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.graphics.Canvas;
import android.widget.ImageView.ScaleType;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.text.SpannableString;
import 	android.text.style.TypefaceSpan;
import android.text.Spannable;
import android.graphics.Typeface;
import android.widget.TextView;
import android.widget.Toast;
//import android.net.Uri;
//import android.app.AlertDialog;
import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.webkit.WebSettings;
//import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.preference.EditTextPreference;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
//import android.widget.FrameLayout;
//import android.widget.RelativeLayout;
import android.widget.LinearLayout;
import android.view.ViewGroup.LayoutParams;
import android.view.View;
import android.view.LayoutInflater;
//import android.view.ViewGroup;
import android.app.Dialog;  
//import android.view.Window;  
//import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Display;
import android.graphics.Point;

public class AMStart extends Activity {
	
	//String actorname;
	//TextView tv;
	//ImageView iv;
	AMDBAdapter db;
	AMBackup ambackup;
	//static final FrameLayout.LayoutParams   FILL = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    //static boolean insertDone = false;
    private Dialog webViewDialog;
    private WebView webView;  
    static final int BLOCKSIZE = 10;
    
    private final String TAG = "AM";
    String dbText = "";
    //RelativeLayout rl;
    //View view;
    public static String webpage = "http://deeplookinc.com/";
    ImageView imageView;
    ArrayList<Long> arrayList;
    ArrayList<String> arrayPath;
    
    TextView textview;
    Typeface defaultType;
	Typeface specialType;

	//for the class AMBackup
	//List<String> databaseList;
	long longid;
	static boolean isLibraryEmpty;
	
	public void onClickViewNew(){
		startActivity(new Intent("org.magic.am.AMNew"));

	}
	private void getIDs(){
		
		db.open();
		
		Cursor c = db.getAllIDs();
		if (c.moveToFirst()){
			do {
				 arrayList.add(Long.parseLong(c.getString(0)));
			} while (c.moveToNext());
		}
		db.close();
	}
	
	 private void getImagePath(long n)  {
	        
	        db.open();
	         
	        Cursor c = db.getAMImages(n);
	     
	        
	        //System.out.println("count "+ c.getCount());
	            
	        if(c.getCount() == 0){
	        	
	        	 return;
	            }
	         
	          if (c.moveToFirst())
	         	
	          {
	        	
	              do {
	                
	            	 
	            	  arrayPath.add(c.getString(0));
	            	  arrayPath.add(c.getString(1));
	            	 
	            	 
	            	} while (c.moveToNext());
	          }
	        
	        db.close();
	    }
	
	/*private void getDB(){
		db.open();
		//Cursor c = db.getAMText(n);
		Cursor c = db.getAllText();
		 if (c.moveToFirst())
         {
             do {
            	 for (int i = 0; i < 10; i++){
            		 if (c.getString(i).equals(""))
            		 {
            			dbText += "****9102856437****\t"; 
            		 }
            		 else{
                       dbText += c.getString(i)+ "\t";
            		 }
            	 }
            	 if (c.getString(10).equals("")){
            		 
            		 dbText += "****9102856437****\n";
            	 }
            	 else{
            	      dbText += c.getString(10)+ "\n";
            	 }
                 } while (c.moveToNext());
          }//if
		 //System.out.println(dbText);
		 Log.i(TAG, dbText);
		db.close();
	}*/
	 
	/*private void getAll(){
		
		db.open();
		Cursor c = db.getAllIDs();
		if(c.getCount() == 0){
            Log.i(TAG, "\n no pair in library\n");
            getTextFromExternal();
            Log.i(TAG, "\n size of database " + databaseList.size());
            int cycle = databaseList.size()/11;
            Log.i(TAG, "\n how many pairs " + cycle);
            //refill database
            for (int i = 0; i < cycle; i++ )   {
            	Log.i(TAG, "outer cycle");
            	int j = i*11;
            	for (int k = 0; k < 11; k++){
            		Log.i(TAG, "index " + (k+j));
            
            		
            	}
            db.insertAM(databaseList.get(0+j), databaseList.get(1+j), databaseList.get(2+j), databaseList.get(3+j), databaseList.get(4+j), databaseList.get(5+j), databaseList.get(6+j), databaseList.get(7+j), databaseList.get(8+j), databaseList.get(9+j), databaseList.get(10+j));
            }
			return;
           } 
		//isLibraryEmpty = false;
		if (c.moveToFirst()) {
      	     do {
               	 longid = Long.parseLong(c.getString(0));
               	 Log.i(TAG, "id = " + longid );
          	  } while (c.moveToNext());
      	     textSaveToExternal();
        }
	
		db.close();
	}*/
	
     /*private void  getTextFromExternal(){
    	
    	 StringBuilder builder = new StringBuilder();
    	 String externalFileName = Environment.getExternalStorageDirectory()+ "/amdb.txt";
		 //Log.i(TAG, "external file " + externalFileName);
		 File externalFile = new File(externalFileName);
		 
		 try{
		    FileInputStream fis = new FileInputStream(externalFile);
         
		    int ch;
		    while((ch = fis.read()) != -1){
		        builder.append((char)ch);
		    }

		    System.out.println(builder.toString());
		    Log.i(TAG, "stringbuilder  = " + builder.toString());
		    
			
			
			
			fis.close();
		 
		 }
		 catch (IOException ex){
				ex.printStackTrace();
				Log.e(TAG, "file not found in text from external to internal");
			}
		 
		 StringTokenizer st = new StringTokenizer(builder.toString());
    	 databaseList = new ArrayList<String>();
		 int i = 0;
    	 while (st.hasMoreTokens()){
    		 String nextT = st.nextToken();
    		 if (nextT.equals("****9102856437****")){
    			 //Log.i(TAG, "i = " + i + " , " + "");
    			 databaseList.add("");
    		 }
    		 else{
    		     //Log.i(TAG, "i = " + i + " , " + nextT);
    		     databaseList.add(nextT);
    		 }
    		 i++;
    		 
          }
    	 
    	 for (int j = 0; j < databaseList.size(); j++){
    		Log.i(TAG, "i = " + j + " , " + databaseList.get(j));
    	 }
    	 
     }*/
	
	/*private void saveDB(){
		
		//String str = "Translate DB to text file, and back.\n";
		try
		{
			//FileOutputStream fileout = openFileOutput("db.txt", MODE_WORLD_READABLE);
			//File file = new File(this.getFilesDir().getAbsolutePath() + "/am/db.txt");
			//String s = this.getFilesDir().getAbsolutePath();
			//System.out.println(s);
			FileOutputStream fileout = openFileOutput("db.txt", MODE_PRIVATE);
			//FileOutputStream fileout = openFileOutput("/storage/sdcard/am/db.txt", MODE_PRIVATE);
			//FileOutputStream fileout = new FileOutputStream("db.txt");
			OutputStreamWriter osw = new OutputStreamWriter(fileout);
			osw.write(dbText);
			osw.flush();
			osw.close();
		}
	
		catch (IOException ex){
			ex.printStackTrace();		
		}
		
	}*/
	
	/*public void loadDB(){
		try{
			
			FileInputStream fileinput = openFileInput("db.txt");
			InputStreamReader isr = new InputStreamReader(fileinput);
			char [] inputBuffer = new char[BLOCKSIZE];
			String str = "";
			int charNum;
		while ( (charNum = isr.read(inputBuffer)) > 0){
			//System.out.println("charNum " + charNum);
			String str1 = String.copyValueOf(inputBuffer, 0, charNum);
			str += str1;
			inputBuffer = new char[BLOCKSIZE];
		}//while
		//count = Integer.parseInt(str);
		 dbText = str;
		//System.out.println(dbText);
		
		}
		catch (IOException ex){
			ex.printStackTrace();		
		}
		
	}*/
	
	/*public void onClickWebView(){
		
		webView.loadUrl(webpage);

		
	}*/
	
	private void openBrowser(){
		Uri uri = Uri.parse(webpage);
		Intent intent = new Intent(Intent.ACTION_VIEW, uri);
		startActivity(intent);

		
	}
	
	 public void onResume(Bundle savedInstanceState){
	        super.onResume();
	        //System.out.println("resumed");
	        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	        String appColor = SP.getString("app_color", "blue");
	        boolean appBackup = SP.getBoolean("back_up",false);
	        //System.out.println("color " + appColor + " appBackup " + appBackup );
	    }
	 
	 
	
	 
	 @Override
	 public void onStart() {
	     super.onStart(); 
	     //Toast.makeText(this, "onStart.", Toast.LENGTH_LONG).show();
	     ActionBar actionBar = getActionBar();
	     //actionBar.hide();
	     LayoutInflater inflator = LayoutInflater.from(this);
			View v = inflator.inflate(R.layout.am_actionbar, null);
			textview = (TextView) v.findViewById(R.id.title1);
		    defaultType = textview.getTypeface();
		    //System.out.println("actionbar type" +  defaultType);
		    
			 //Typeface tf = Typeface.createFromAsset(getAssets(),"helvetica-neue-bold.ttf");
			 //v.setBackgroundColor(getResources().getColor(R.color.dark));
			 //this.getActionBar().setBackgroundDrawable(new ColorDrawable(R.color.actionbarred));
			
			 //gold
			//ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffb18905"));
			
			 //this.getActionBar().setBackgroundDrawable(colorDrawable); 
			//((TextView)v.findViewById(R.id.title1)).setTypeface(tf);
			//this.getActionBar().setCustomView(v);
	     
	     
	     SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	     boolean appBackup = SP.getBoolean("back_up",true);
	     //Log.i(TAG, "app backup " + appBackup);
	     //String appIdentifier = SP.getString("backup_identifier", "");
         //String appPassword = SP.getString("backup_password", "");
         ambackup = new AMBackup(this);
	     
	     //AMBackup.getDB(db);
	     
         if(appBackup){
        	
        
     	   /* if (appIdentifier.equals("")){
     	    	Log.i(TAG, "There is no identifier ");
     	    	Toast.makeText(this, "Please give an identifier containing letters and numbers.", Toast.LENGTH_LONG).show();
     	    	startActivity(new Intent("org.magic.am.AMSetting"));
     	    	
     	    }*/
     	    //else{
     	    	//AMBackup.backup(db);
        	 ambackup.backup(db);
     	    //}
     	    
     	    }
         else{
        	 //AMBackup.nobackup(db);
        	 ambackup.nobackup(db);
         }
         
         
	        String appColor = SP.getString("app_color", "blue");
	        //boolean appBackup = SP.getBoolean("back_up",false);
	        boolean appAnim = SP.getBoolean("animation", true);
	        //Log.i(TAG, "app animation " + appAnim);
	        //Log.i(TAG, "app backup " + appBackup);
	        //System.out.println("color " + appColor + " appBackup " + appBackup  +  " appAnim " + appAnim);
	        
	        
	        
	        Display display = getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            int devicewidth = size.x;
            int deviceheight = size.y;
            //System.out.println("***************** device width " + devicewidth + " device height " + deviceheight + " *********************** ");
       	    Log.i(TAG, "************ device width  " + devicewidth + " device height " + deviceheight + " ************");
            if  (devicewidth == 768 && deviceheight == 1184){
            	//System.out.println("android phone nexus 4");
            	Log.i(TAG, "android phone nexus 4");
            }
	        if (devicewidth == 1200 && deviceheight == 1824){
	        	Log.i(TAG, "android tablet nexus 7");
	        }
	        //getting animation
	        arrayList =  new ArrayList<Long>();
	        arrayPath = new ArrayList<String>();
	        db = new AMDBAdapter(this);
	        getIDs();
	        Log.i(TAG,"how many id "+ arrayList.size());
	        
	        for (int i = 0; i < arrayList.size(); i++){
	        	Log.i(TAG,"i " + i + " actor id "  + arrayList.get(i));
	        	getImagePath(arrayList.get(i));
	        	Log.i(TAG, " image path " + arrayPath.get(i));
	        }
	        Log.i(TAG, "how many photos "+ arrayPath.size());
	        
	        imageView = (ImageView) findViewById(R.id.imageViewAnim);
	        
	        int n = 10;
        	if (arrayPath.size() < n){
        	    n = arrayPath.size();	
        	}
        	String [] imagepath = new String [n];
        	
	        Random randomGenerator = new Random();
	        for (int g = 0; g < n; ++g){
	          int randomInt = randomGenerator.nextInt(arrayPath.size());
	          //System.out.println(randomInt);
	          imagepath[g] = arrayPath.get(randomInt);
	          arrayPath.remove(randomInt);
	         //System.out.println(randomInt);
	        }
	        
	        /*String imagepathActor1 = "/storage/sdcard0/actors/jijinhee.jpg";
	        String imagepathActor2 = "/storage/sdcard0/actors/leesang.jpg";
	        String imagepathActor3 = "/storage/sdcard0/actors/joo-sang-wook.jpg";
	        String imagepathActor4 = "/storage/sdcard0/actors/MarriageNotDating.jpg";
	        arrayPath.add(imagepathActor3);
	        arrayPath.add(imagepathActor2);
	        arrayPath.add(imagepathActor1);
	        arrayPath.add(imagepathActor4);*/
	        
	        int widthfirst = 0; 
	        int heightfirst = 0;
	        int duration = 3000;
	        
	        //System.out.println("arraylist " + arrayList.size());
            //System.out.println("arraypath " + arrayPath.size());
            
	        if (appAnim){
	        
	        	imageView.setVisibility(View.VISIBLE);
	        //	AnimationDrawable anim;
	        	//imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/jijinhee.jpg"));
	        	
	            System.out.println("arraylist " + arrayList.size());
	            System.out.println("arraypath " + arrayPath.size());
	            
	        	//no images yet
	            if (arrayList.size() == 0){
	            	
	            imageView.setBackgroundResource(R.drawable.movie);
	            AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
	            anim.stop();
	            anim.start();
	             }
	            //images
	            else {
	            	
	            	AnimationDrawable anim = new AnimationDrawable();
		        	
		        	anim.setOneShot(false);
		            anim.setEnterFadeDuration(2000);
		            anim.setExitFadeDuration(2000);
	            Bitmap firstbitmap = BitmapFactory.decodeResource(getResources(), R.drawable.trans);
	            //System.out.println("first width " + firstbitmap.getWidth() + " first height " + firstbitmap.getHeight());
	            //firstbitmap = Bitmap.createScaledBitmap(firstbitmap, firstbitmap.getWidth()*2, firstbitmap.getHeight()*2, true);
	            Drawable fr = new BitmapDrawable(getResources(), firstbitmap);
	            //System.out.println("first width " + fr.getIntrinsicWidth() + " first height " + fr.getIntrinsicHeight());
	            //Drawable d = getResources().getDrawable(R.drawable.transparent);
	        	anim.addFrame(fr, 1);
	            
	            for (int i = 0; i < n; i++){
	        	
	        		
	            Bitmap bitmap;
	            BitmapFactory.Options options = new BitmapFactory.Options();
	            options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	            //bitmap = BitmapFactory.decodeFile(arrayPath.get(i), options);
	            //Uri imageUri = Uri.parse(arrayPath.get(i));
	            Uri imageUri = Uri.parse(imagepath[i]);
	            try{
	            bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
	            //bitmap = Bitmap.createScaledBitmap(bitmap,300, 300, true);\
	            //System.out.println("bitmap width " + bitmap.getWidth() + " bitmap height " + bitmap.getHeight());
	            //double ratio =  (double) bitmap.getWidth() / (double) bitmap.getHeight();
	            //System.out.println("ratio " + ratio);
	            /**********************************************************************/
	            //scaling 
	            
	            /*Display display = getWindowManager().getDefaultDisplay();
	            Point size = new Point();
	            display.getSize(size);
	            int phonewidth = size.x;
	            int phoneheight = size.y;
	            System.out.println("*****************  width " + phonewidth + " height " + phoneheight + " *********************** ");
	            if  (phonewidth == 768 && phoneheight == 1184){
	            	System.out.println("android phone nexus 4");
	            }*/
	            
	            int w = bitmap.getWidth();
	            int h = bitmap.getHeight();
	            int newwidth = 0;
	            int newheight = 0;
	            double factor = 0.0;
	            int shiftright = 0;
	            int shiftdown = 0;
	            
	            if (w*4 == h*3){
	            	//System.out.println(" ************* ratio is 0.75 *******************");
	            	factor = 1067.0 / (double) h;
	            	//System.out.println("index " + i + " factor " + factor);
	            	newwidth = (int) ((double) w * factor);
	            	newheight = (int) ((double) h * factor);
	            	//System.out.println("new width " + newwidth + " new height " + newheight);
	            	 bitmap = Bitmap.createScaledBitmap(bitmap, newwidth, newheight, true);
	            	
	            }
	            else if (w*4 < h*3){
	            	//System.out.println(" ****************** ratio is less than 0.75 *****************");
	            	newheight = 1067;
	            	double wd = (double) w;
	            	double hd = (double) h;
	            	//System.out.println("double w " + wd + "double height " + hd);
	            	newwidth = (int) ((wd / hd )* 1067);
	            	//System.out.println("new width " + newwidth + " new height " + newheight);
	            	 bitmap = Bitmap.createScaledBitmap(bitmap, newwidth, newheight, true);
	            	 shiftright = (int)((double)(800 - newwidth) /2.0);
	            }
	            else{
	            	//System.out.println(" ****************** ratio is more than 0.75 *****************");
	            	newwidth = 800;
	            	double wd = (double) w;
	                double hd = (double) h;
	                newheight = (int) ((hd / wd  * 800));
	                //System.out.println("new width " + newwidth + " new height " + newheight);
	            	 bitmap = Bitmap.createScaledBitmap(bitmap, newwidth, newheight, true);
                     shiftdown = (int)((double) (1067 - newheight) / 2.0 );
	            }
	            //bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, true);
	            //bitmap = Bitmap.createScaledBitmap(bitmap, 800, 1067, false);
	            //System.out.println("bitmap width " + bitmap.getWidth() + " bitmap height " + bitmap.getHeight());
	            
	            //imageView.setImageBitmap(bitmap);
	            
	            /*************************************************************************/
	            
	            Bitmap combine = Bitmap.createBitmap(firstbitmap.getWidth(), firstbitmap.getHeight(), firstbitmap.getConfig());
	            Canvas canvas = new Canvas(combine);
	            canvas.drawBitmap(firstbitmap, new Matrix(), null);
	            canvas.drawBitmap(bitmap, shiftright, shiftdown, null);
	            
	            //Drawable frame1 = (Drawable) new BitmapDrawable(getResources(), "/storage/sdcard0/actors/jijinhee.jpg");
	            Drawable frame = new BitmapDrawable(getResources(), combine);
	            //System.out.println("image width " + frame.getIntrinsicWidth() + "image height " + frame.getIntrinsicHeight());
	            //frame.setBounds(0, 0, frame.getIntrinsicWidth(), frame.getIntrinsicHeight());
	            
	            /*float scale = getApplicationContext().getResources().getDisplayMetrics().density;
	            int width = (int) (bitmap.getWidth() * scale + 0.5f);
				int height = (int) (bitmap.getHeight() * scale + 0.5f);
				frame.setBounds(0, 0, width, height);*/
				//anim.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
				
				//*****************************
				anim.addFrame(frame, duration);
	           
				// anim.setBounds(0, 0, anim.getIntrinsicWidth(), anim.getIntrinsicHeight());
	            }
	            catch(Exception e){
	            	
	            }
	            //imageView.setImageDrawable(frame1);
	            
	            //anim.addFrame(frame1, duration);
	                        
	           
	             imageView.setScaleType(ScaleType.FIT_XY);
	             imageView.setImageDrawable(anim);
	            
	            
	              anim.stop();
	    	   	  anim.start();
	            }

	        	}
	        	//imageView.setImageDrawable(anim);
	        
	    	   	    //	}
	        }else {
	        	imageView.setVisibility(View.INVISIBLE);
	        }
	        
	        View view = findViewById(R.id.am_start);	
	    	
	        
	        final int splitBarId = getResources().getIdentifier("split_action_bar", "id", "android");
	        View splitActionBar = findViewById(splitBarId);
	        //splitActionBar.setVisibility(View.INVISIBLE);
	        
	        /*if (splitActionBar != null) {

	            splitActionBar.setBackgroundColor(getResources().getColor(R.color.dark));

	         }*/
	        
	        //System.out.println("split action bar " + splitBarId);
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
	 
	 public void databaseSaveToExternal(){
		//getDB();
		 
		 try{
		    
			 String destPath = getFilesDir().getPath();
			 destPath = destPath.substring(0, destPath.lastIndexOf("/")) + "/databases";
			 //final String inFileName = "/data/data/org.magic.am/databases/ACTORSMOVIES";
			 Log.i(TAG, "internal  " + destPath);
			 
			 final String inFileName = destPath + "/ACTORSMOVIES";
			 File dbFile = new File(inFileName);
			 FileInputStream fis = new FileInputStream(dbFile);
			
			 String outFileName = Environment.getExternalStorageDirectory()+"/ACTORSMOVIES";
			
						 
			 Log.i(TAG, outFileName);
			 File file = new File(outFileName);
				FileOutputStream fos = new FileOutputStream(file);
				
				// Transfer bytes from the inputfile to the outputfile
			    byte[] buffer = new byte[1024];
			    int length;
			    while ((length = fis.read(buffer))>0){
			        fos.write(buffer, 0, length);
			    }
				
				
				fos.close();
				fis.close();
			 
			 
				
			 /*String baseFolder;
			    //storage/emulated/0/Android/data/org.magic.am/files
				if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
				    baseFolder = getExternalFilesDir(null).getAbsolutePath();
				    Log.i(TAG, "if: "+ baseFolder);
				}
				// revert to using internal storage  /data/data/org.magic.am/files
				else {
				    baseFolder = getFilesDir().getAbsolutePath();
				    Log.i(TAG, "else: " + baseFolder);
				}*/
				
		 }
		 catch (IOException ex){
				ex.printStackTrace();
				Log.e(TAG, "file not found in database save in external");
			}
	 }
	 
	 
	 /*public void textSaveToExternal(){
			//getDB();
			//Log.i(TAG, dbText);
			 
			 try{
				
				 String outFileName = Environment.getExternalStorageDirectory()+"/amdb.txt";
				 
				 //Log.i(TAG, outFileName);
				 File file = new File(outFileName);
				 FileOutputStream fos = new FileOutputStream(file);
					
				 fos.write(dbText.getBytes());
				 fos.close();
				
				 	
			 }
			 catch (IOException ex){
					ex.printStackTrace();
					Log.e(TAG, "file not found in text save to external");
				}
		 }*/
	 
	 
	 public void textFromExternalToInternal(){
		 String internalFileName = getFilesDir().getAbsolutePath() + "/internaldb.txt";
		 Log.i(TAG, "internal file " + internalFileName);
		 String externalFileName = Environment.getExternalStorageDirectory()+ "/amdb.txt";
		 Log.i(TAG, "external file " + externalFileName);
		 File externalFile = new File(externalFileName);
		 
		 try{
		    FileInputStream fis = new FileInputStream(externalFile);
         
		    File internalFile = new File(internalFileName);
			FileOutputStream fos = new FileOutputStream(internalFile);
			// Transfer bytes from the inputfile to the outputfile
		    
			byte[] buffer = new byte[1024];
		    int length;
		    while ((length = fis.read(buffer))>0){
		        fos.write(buffer, 0, length);
		    }
			
			
			fos.close();
			fis.close();
		 
		 }
		 catch (IOException ex){
				ex.printStackTrace();
				Log.e(TAG, "file not found in text from external to internal");
			}
	 }
	 
	 public void databaseFromExternalToInternal(){
		 String destPath = getFilesDir().getPath();
		 destPath = destPath.substring(0, destPath.lastIndexOf("/")) + "/databases";
		 
		 String internalFileName = destPath + "/ACTORSMOVIES";
		 Log.i(TAG, "internal file " + internalFileName);
		 String externalFileName = Environment.getExternalStorageDirectory()+ "/ACTORSMOVIES";
		 Log.i(TAG, "external file " + externalFileName);
		 File externalFile = new File(externalFileName);
		 
		 try{
		    FileInputStream fis = new FileInputStream(externalFile);
         
		    File internalFile = new File(internalFileName);
			FileOutputStream fos = new FileOutputStream(internalFile);
			// Transfer bytes from the inputfile to the outputfile
		    
			byte[] buffer = new byte[1024];
		    int length;
		    while ((length = fis.read(buffer))>0){
		        fos.write(buffer, 0, length);
		    }
			
			
			fos.close();
			fis.close();
		 
		 }
		 catch (IOException ex){
				ex.printStackTrace();
				Log.e(TAG, "file not found in database from external to internal");
			}
	 }
	 
	 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if(getResources().getBoolean(R.bool.portrait_only)){
	        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
	    }
		setContentView(R.layout.am_start);
	   	
		//imageView = (ImageView) findViewById(R.id.imageViewAnim);
	   	//imageView.setBackgroundResource(R.drawable.movie);
	   	//AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
	   	
	   	/*for (int i = 0; i < 100; i++){
	   		imageView = (ImageView) findViewById(R.id.imageViewAnim);
	   		imageView.setBackgroundResource(R.drawable.movie);
	   	    AnimationDrawable anim = (AnimationDrawable) imageView.getBackground();
	   	    anim.start();
	   	}*/
	   	
		 db = new AMDBAdapter(this);
         //AMBackup.backup(db);
		 //getDB();
		 //getAll();
		 
		 //saveDB();
		 //databaseSaveToExternal();
		 //databaseFromExternalToInternal();
		 //textSaveToExternal();
		 //getTextFromExternal();
		 //textFromExternalToInternal();
		 
		 //getDB();
		//saveDB();
		//loadDB();
		//openBrowser();
		
        //createCustomActionBar();
        //actionBar.setBackgroundDrawable(new ColorDrawable(R.color.gold));
        //actionBar.setStackedBackgroundDrawable(new ColorDrawable(R.color.magenta));
        
         //View view = findViewById(R.id.am_start);	
	    //view.setBackgroundColor(0xFFEE3333); //red !!!
		//view.setBackgroundColor(getResources().getColor(R.color.chartreuse));
		//AlertDialog.Builder alert = new AlertDialog.Builder(this); 
		//alert.setTitle("Lee Sang Yoon");

		
		
		
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
		//Log.i(TAG,"*****************Another activity is taking focus (this activity is about to be \"paused\")************");
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
		
		//Log.i(TAG, "***********************The activity is no longer visible (it is now \"stopped\")*****************");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		/*SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean appBackup = SP.getBoolean("back_up",false);
        if (appBackup){
		   db = new AMDBAdapter(this);
		   getDB();
        }*/

		//Log.i(TAG, "***********************The activity is about to be destroyed.***********************");
	}
	
	/*private void createCustomActionBar(){
		this.getActionBar().setDisplayShowCustomEnabled(true);
		this.getActionBar().setDisplayShowTitleEnabled(false);
		LayoutInflater inflator = LayoutInflater.from(this);
		View v = inflator.inflate(R.layout.am_actionbar, null);
		 Typeface tf = Typeface.createFromAsset(getAssets(),"helvetica-neue-bold.ttf");
		 //v.setBackgroundColor(getResources().getColor(R.color.dark));
		 //this.getActionBar().setBackgroundDrawable(new ColorDrawable(R.color.actionbarred));
		
		 //gold
		//ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#ffb18905"));
		
		 //this.getActionBar().setBackgroundDrawable(colorDrawable); 
		//((TextView)v.findViewById(R.id.title1)).setTypeface(tf);
		this.getActionBar().setCustomView(v);


	
	}*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.am_start_menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		 SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
	        
	        
	        boolean appHelp = SP.getBoolean("short_help", false);
		
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent intent = new Intent(this, AMStart.class);
			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(intent);
			break;
		
		case R.id.new_item:
			
			onClickViewNew();
			break;
		
		case R.id.delete_item:
			startActivity(new Intent("org.magic.am.AMDelete"));
			break;
		
		case R.id.search_item:
			//startActivity(new Intent("org.magic.am.AMSearch"));
			Intent istart = new Intent("org.magic.am.AMSearch");
			istart.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivityForResult(istart, 0);
			break;
		case R.id.action_help:
			/*if (!appHelp){
			    openBrowser();
			}
			else {
				startActivity(new Intent("org.magic.am.AMHelp"));
				
			}*/
			Intent ii = new Intent("org.magic.am.AMHelp");
			ii.putExtra("help", appHelp+"");
			startActivityForResult(ii, 1);
			break;
			
		case R.id.actors:
			//startActivity(new Intent("org.magic.am.Actors"));
			 Intent i = new Intent("org.magic.am.Actors");
		        i.putExtra("arrange", "2");
		        startActivityForResult(i,1);
			break;
		
		case R.id.movies:
			//startActivity(new Intent("org.magic.am.Movies"));
			Intent im = new Intent("org.magic.am.Movies");
			im.putExtra("arrange", "2");
			startActivityForResult(im, 1);
			break;
			
		case R.id.action_settings:	
			startActivity(new Intent("org.magic.am.AMSetting"));
		
		default:
			break;
	}
		return super.onOptionsItemSelected(item);
	}
}
