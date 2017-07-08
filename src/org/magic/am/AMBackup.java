package org.magic.am;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Context;
import android.os.Looper;


public class AMBackup {
	
	//private AMDBAdapter db;
	private static final String TAG = "AM";
    String dbText = "";
    List<String> databaseList;
    long longid;
    Context context;
    boolean oldlibrary;
    ListView listView;
	String [] stringArray;
	Dialog dialog;
	
    public AMBackup(Context context){
    	
    	this.context = context;
    }
    /*public boolean isLibraryExist(){
    	//check whether library is empty in app and there is not empty file amdb.txt
    	
    	
    	
    	return false;
    	
    }*/
    public void saveChange(AMDBAdapter database){
    	getDB(database);
    	textSaveToExternal();
    }
    
	public  void backup(AMDBAdapter database){
		
        getDB(database);
        getAll(database);
	}

	public  void nobackup(AMDBAdapter databse){

		
		 try{
			
			 String outFileName = Environment.getExternalStorageDirectory()+"/amdb.txt";
			 String empty = "";
			 
			 //Log.i(TAG, "OUTFILE " + outFileName);
			 File file = new File(outFileName);
			 FileOutputStream fos = new FileOutputStream(file);
				
			 fos.write(empty.getBytes());
			 fos.close();
			
			 	
		 }
		 catch (IOException ex){
				ex.printStackTrace();
				Log.e(TAG, "file not found in text save to external");
			}
	 
		
	}
	
	public  void getDB(AMDBAdapter db){
		dbText = "";
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
		 //Log.i(TAG, "\n??????????????????????\n" + dbText + "\n?????????????????\n");
		db.close();
	}
	 
	private  void getAll(AMDBAdapter db){
		
		db.open();
		Cursor c = db.getAllIDs();
		if(c.getCount() == 0){
           // Log.i(TAG, "\n no pair in library\n");
            //Toast.makeText(this, "Delete successful.", Toast.LENGTH_LONG).show();
            //show dialog!!! see demoneyes!!
            //AMDialog amdialog = new AMDialog(context);
            
           
                //openDialog();
            
                //Log.i(TAG, "\n Dialog closed \n");
                //Log.i(TAG, "\n old library ????? "+ oldlibrary + "\n");
              
            
        	
            
            getTextFromExternal();
            //Log.i(TAG, "\n size of database " + databaseList.size());
            int cycle = databaseList.size()/11;
            //Log.i(TAG, "\n how many pairs " + cycle);
            //refill database
            for (int i = 0; i < cycle; i++ )   {
            	//Log.i(TAG, "outer cycle");
            	int j = i*11;
            	for (int k = 0; k < 11; k++){
            		//Log.i(TAG, "index " + (k+j));
            
            		
            	}
            db.insertAM(databaseList.get(0+j), databaseList.get(1+j), databaseList.get(2+j), databaseList.get(3+j), databaseList.get(4+j), databaseList.get(5+j), databaseList.get(6+j), databaseList.get(7+j), databaseList.get(8+j), databaseList.get(9+j), databaseList.get(10+j));
            }
			return;
           } 
		//isLibraryEmpty = false;
		if (c.moveToFirst()) {
      	     do {
               	 longid = Long.parseLong(c.getString(0));
               	 //Log.i(TAG, "id = " + longid );
          	  } while (c.moveToNext());
      	     textSaveToExternal();
        }
	
		db.close();
	}
	
	public void openDialog(){
		
		 //Thread t = new Thread(){
    	   //    public void run() {
		//Toast.makeText(context, "Showing dialog.", Toast.LENGTH_LONG).show();
		Drawable d = new ColorDrawable(Color.WHITE);
		//d.setAlpha(0);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.alertdialog);
		builder.setCancelable(false);
        listView = new ListView(context);
		
		stringArray = new String[] { "Load old library", "Cancel" };
		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, android.R.id.text1, stringArray);
		listView.setAdapter(modeAdapter);
		//final Dialog dialog = builder.create();
		//listView.setText(Color.BLUE);
		//listView.setBackgroundResource(android.graphics.Color.TRANSPARENT);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
               
            	
            	switch(arg2){
            	
            	case 0:
            		
            		oldlibrary = true;
            		//Log.i(TAG, "\n old library "+ oldlibrary + "\n");
   					dialog.cancel();
                    
            		break;
            	case 1:
            		
            		oldlibrary = false;
            		//Log.i(TAG, "\n old library "+ oldlibrary + "\n");
            		dialog.cancel();
            	    
                    
            		break;
            	
            	}
            }
        });
		builder.setView(listView);
		dialog = builder.create();
		dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
		//dialogSending.getWindow().setGravity(Gravity.BOTTOM);
		//dialogSending.getWindow().getAttributes().gravity = Gravity.BOTTOM;
		//dialog.show();
		dialog.setOnKeyListener(new Dialog.OnKeyListener() {

            @Override
            public boolean onKey(DialogInterface arg0, int keyCode,
                    KeyEvent event) {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    
                	//dialogSending.cancel();
                    
                }
                return true;
            }
        });
		dialog.show();
    	   //    }
		 //};
		 //t.start();
         
         //try{
     		//t.join();
     		
     	//}
     	//catch (InterruptedException ie){
    // }
	}
	
	private  void  getTextFromExternal(){
    	
   	 StringBuilder builder = new StringBuilder();
   	 String externalFileName = Environment.getExternalStorageDirectory()+ "/amdb.txt";
		 //Log.i(TAG, "EXTERNAL FILE " + externalFileName);
		 
		 File externalFile = new File(externalFileName);
		 
		 try{
		    FileInputStream fis = new FileInputStream(externalFile);
        
		    int ch;
		    while((ch = fis.read()) != -1){
		        builder.append((char)ch);
		    }

		   // System.out.println(builder.toString());
		    //Log.i(TAG, "stringbuilder  = " + builder.toString());
		    
			
			
			
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
   		//Log.i(TAG, "&&&&   i = " + j + " , " + databaseList.get(j));
   	 }
   	 
    }
	
	 public void textSaveToExternal(){
			//getDB();
			//Log.i(TAG, dbText);
			 
			 try{
				
				 String outFileName = Environment.getExternalStorageDirectory()+"/amdb.txt";
				 
				 //Log.i(TAG, "OUTFILE " + outFileName);
				 File file = new File(outFileName);
				 FileOutputStream fos = new FileOutputStream(file);
					
				 fos.write(dbText.getBytes());
				 fos.close();
				
				 	
			 }
			 catch (IOException ex){
					ex.printStackTrace();
					Log.e(TAG, "file not found in text save to external");
				}
		 }
	
}
