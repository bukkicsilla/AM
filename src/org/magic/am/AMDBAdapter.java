package org.magic.am;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AMDBAdapter {

	static final String KEY_ROW_ID = "_id";
    static final String KEY_NAME = "name";
    static final String KEY_BIRTH = "birth";
    static final String KEY_NATIONALITY = "nationality";
    static final String KEY_TITLE = "title";
    static final String KEY_RELEASE = "release";
    static final String KEY_GENRE = "genre";
    static final String KEY_COUNTRY = "country";
    static final String KEY_IMAGEPATH = "imagepath";
    static final String KEY_IMAGEMOVIE = "imagemovie";
    static final String KEY_WEBACTOR = "webactor";
    static final String KEY_WEBMOVIE = "webmovie";
    
    static final String TAG = "DatabaseAdapter";

    static final String DATABASE_NAME = "ACTORSMOVIES";
    static final String DATABASE_TABLE = "AM";
    static final int DATABASE_VERSION = 1;
    
    static final String DATABASE_CREATE = "create table AM (_id integer primary key autoincrement, name text not null, birth text, nationality text, title text not null, release text, genre text, country text, imagepath text not null, imagemovie text not null, webactor text, webmovie text);";
    
    //static final String DATABASE_CREATE = "create table " +  DATABASE_TABLE + " (" + KEY_ROW_ID + " integer primary key autoincrement, " + 
    //KEY_NAME + " text not null, " + KEY_BIRTH + " text, " + KEY_NATIONALITY + " text, " + KEY_TITLE + " text not null," +
    	//	KEY_RELEASE + " text," + KEY_GENRE + " text," + KEY_COUNTRY + " text," + KEY_IMAGEPATH + "text not null);";
    
    final Context context;
    AMDatabaseHelper AMDBHelper;
    SQLiteDatabase db;
    
    public AMDBAdapter(Context ctx){
        this.context = ctx;
        AMDBHelper = new AMDatabaseHelper(context);
    }
    
    //inner class
    private static class AMDatabaseHelper extends SQLiteOpenHelper
    {
        AMDatabaseHelper(Context context)
        {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }
        
        @Override
        public void onCreate(SQLiteDatabase db)
        {
            try{
            	System.out.println("creating database");
                db.execSQL(DATABASE_CREATE);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            
            Log.w(TAG, "Upgrade");
            db.execSQL("DROP TABLE IF EXISTS DATABASE_TABLE");
            onCreate(db);
        }
    }//end of class AMDatabaseHelper

    public AMDBAdapter open() throws SQLException
    {
         db = AMDBHelper.getWritableDatabase();
         return this;
    }
    
    public void close()
    {
        AMDBHelper.close();
    }
    
    public long insertAM(String name, String birth, String nationality, String title, String release, String genre, String country, String imagepath, String imagemovie, String webactor, String webmovie)
    {
        ContentValues initialValues = new ContentValues();
        initialValues.put(KEY_NAME, name);
        initialValues.put(KEY_BIRTH, birth);
        initialValues.put(KEY_NATIONALITY, nationality);
        initialValues.put(KEY_TITLE, title);
        initialValues.put(KEY_RELEASE, release);
        initialValues.put(KEY_GENRE, genre);
        initialValues.put(KEY_COUNTRY, country);
        initialValues.put(KEY_IMAGEPATH, imagepath);
        initialValues.put(KEY_IMAGEMOVIE, imagemovie);
        initialValues.put(KEY_WEBACTOR, webactor);
        initialValues.put(KEY_WEBMOVIE, webmovie);
        return db.insert(DATABASE_TABLE, null, initialValues);
        
    }
    
    public Cursor getAMPair(String actorname, String movietitle){
    	
    	
    	try{
    	 return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_NAME, KEY_TITLE}, KEY_NAME+"=? and "+KEY_TITLE+"=?", new String [] {actorname, movietitle}, null, null, null);
    	}
    	catch(Exception e){
    		return null;
    	}
    	}
    
       public Cursor getInfoOfMovie(String movietitle){
    	
    	try{
          	 return db.query(DATABASE_TABLE, new String[] {KEY_RELEASE, KEY_GENRE, KEY_COUNTRY, KEY_WEBMOVIE}, KEY_TITLE+"=?", new String [] {movietitle}, null, null, null);
          	}
          	catch(Exception e){
          		return null;
          	}
    }
 
    public Cursor getInfoOfActor(String actorname){
    	
    	try{
          	 return db.query(DATABASE_TABLE, new String[] {KEY_BIRTH, KEY_NATIONALITY, KEY_WEBACTOR}, KEY_NAME+"=?", new String [] {actorname}, null, null, null);
          	}
          	catch(Exception e){
          		return null;
          	}
    }
    
    
    public Cursor getActorsOfMovie(String movietitle, int n){
    	try{
    		if (n == 1){
    		   return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_NAME}, KEY_TITLE+"=?", new String [] {movietitle}, null, null, KEY_BIRTH + " DESC");
    		}
    		else {
       	       return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_NAME}, KEY_TITLE+"=?", new String [] {movietitle}, null, null, KEY_NAME + " ASC");
    		}
    	}
       	catch(Exception e){
       		return null;
       	}
    	
    }
    
    public Cursor getMoviesOfActor(String actorname, int n){
    	try{
    		if (n == 1){
    			return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_TITLE}, KEY_NAME+"=?", new String [] {actorname}, null, null, KEY_RELEASE + " DESC");		
    		}
    		else{
       	 return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_TITLE}, KEY_NAME+"=?", new String [] {actorname}, null, null, KEY_TITLE + " ASC");
    		}
    		}
       	catch(Exception e){
       		return null;
       	}
    	
    }
    public Cursor getImageOfActor(String actorname){
    	
    	
    	try{
    	 return db.query(DATABASE_TABLE, new String[] {KEY_NAME, KEY_IMAGEPATH}, KEY_NAME+"=?", new String [] {actorname}, null, null, null);
    	}
    	catch(Exception e){
    		return null;
    	}
    	}
    
    public Cursor getIDsWithActor(String actorname){
    	
    	
    	try{
    	 return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_NAME}, KEY_NAME+"=?", new String [] {actorname}, null, null, null);
    	}
    	catch(Exception e){
    		return null;
    	}
    	}
    
   public Cursor getIDsWithMovie(String movietitle){
    	
    	
    	try{
    	 return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_TITLE}, KEY_TITLE+"=?", new String [] {movietitle}, null, null, null);
    	}
    	catch(Exception e){
    		return null;
    	}
    	}
    

    public Cursor getDistinctActors(int n){
    	if (n == 1){
    	return db.query(true, DATABASE_TABLE, new String[] {KEY_NAME} , null, null, null, null, KEY_BIRTH+" DESC", null, null);
    	}
    	else {
    	return db.query(true, DATABASE_TABLE, new String[] {KEY_NAME} , null, null, null, null, KEY_NAME+" ASC", null, null);
    	}
    }
    
    
    public Cursor getDistinctMovies(int n){
    	if (n == 1 ){
    		
    		return db.query(true, DATABASE_TABLE, new String[] {KEY_TITLE} , null, null, null, null, KEY_RELEASE+" DESC", null, null);
    	}
    	else {
    		
    	   return db.query(true, DATABASE_TABLE, new String[] {KEY_TITLE} , null, null, null, null, KEY_TITLE+" ASC", null, null);
    	}
    }
    
    public Cursor getAllIDs()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID}, null, null, null, null, null);
    }
    
    public Cursor getAllAMs()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_ROW_ID, KEY_NAME,
                KEY_TITLE, KEY_IMAGEPATH}, null, null, null, null, null);
    }
    
    
    public Cursor getAllText()
    {
        return db.query(DATABASE_TABLE, new String[] {KEY_NAME, KEY_BIRTH, KEY_NATIONALITY, KEY_TITLE, KEY_RELEASE, KEY_GENRE, KEY_COUNTRY, KEY_IMAGEPATH, KEY_IMAGEMOVIE, KEY_WEBACTOR, KEY_WEBMOVIE}, null, null, null, null, null);
    }
    
    
    public Cursor getAM(long rowId) throws SQLException 
    {
        Cursor myCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_ROW_ID,
                KEY_NAME, KEY_BIRTH , KEY_NATIONALITY, KEY_WEBACTOR, KEY_TITLE, KEY_RELEASE, KEY_GENRE, KEY_COUNTRY, KEY_WEBMOVIE, KEY_IMAGEPATH, KEY_IMAGEMOVIE}, KEY_ROW_ID + "=" + rowId, null,
                null, null, null, null);
        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }
    
    public Cursor getAMImages(long rowId ) throws SQLException 
    {
    	Cursor myCursor =
                db.query(true, DATABASE_TABLE, new String[] { KEY_IMAGEPATH, KEY_IMAGEMOVIE}, KEY_ROW_ID + "=" + rowId, null,
                null, null, null, null);
        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }
    
    public Cursor getAMText(long rowId) throws SQLException 
    {
        Cursor myCursor =
                db.query(true, DATABASE_TABLE, new String[] {KEY_NAME, KEY_BIRTH , KEY_NATIONALITY,  KEY_TITLE, KEY_RELEASE, KEY_GENRE, KEY_COUNTRY, KEY_IMAGEPATH, KEY_IMAGEMOVIE, KEY_WEBACTOR, KEY_WEBMOVIE }, KEY_ROW_ID + "=" + rowId, null,
                null, null, null, null);
        if (myCursor != null) {
            myCursor.moveToFirst();
        }
        return myCursor;
    }
    
    //it is not good enough!!!
    public boolean updateAM(long rowId, String name, String title, String imagepath) 
    {
        ContentValues args = new ContentValues();
        args.put(KEY_NAME, name);
        args.put(KEY_TITLE, title);
        args.put(KEY_IMAGEPATH, imagepath);
        return db.update(
            DATABASE_TABLE, args, KEY_ROW_ID + "=" + rowId, null) > 0;
    }
    
  //it is not good enough!!!
    public boolean updateImagepath(long rowId, String imagepath) 
    {
        ContentValues args = new ContentValues();
        
        args.put(KEY_IMAGEPATH, imagepath);
        return db.update(
            DATABASE_TABLE, args, KEY_ROW_ID + "=" + rowId, null) > 0;
    }
    
    public boolean updateImageMovie(long rowId, String imagepath) 
    {
        ContentValues args = new ContentValues();
        
        args.put(KEY_IMAGEMOVIE, imagepath);
        return db.update(
            DATABASE_TABLE, args, KEY_ROW_ID + "=" + rowId, null) > 0;
    }
    
    public boolean updateTextAM(long rowId, String actorbirth, String actornatio, String actorweb, String movierelease, String moviegenre, String moviecountry, String movieweb){
    	
    	ContentValues args = new ContentValues();
    	args.put(KEY_BIRTH, actorbirth);
    	args.put(KEY_NATIONALITY, actornatio);
    	args.put(KEY_WEBACTOR, actorweb);
    	args.put(KEY_RELEASE, movierelease);
    	args.put(KEY_GENRE, moviegenre);
    	args.put(KEY_COUNTRY, moviecountry);
    	args.put(KEY_WEBMOVIE, movieweb);
    return db.update(DATABASE_TABLE, args, KEY_ROW_ID + "=" + rowId, null) > 0;
    }
    
    
    public boolean deleteAM(long rowId) 
    {
        return db.delete(DATABASE_TABLE, KEY_ROW_ID + "=" + rowId, null) > 0;
    }
    
}
