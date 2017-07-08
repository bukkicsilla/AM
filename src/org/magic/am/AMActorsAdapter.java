package org.magic.am;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.graphics.Typeface;

public class AMActorsAdapter extends ArrayAdapter<String>{
	 private final Context context;
	  private final String[] values;
	  
	  LayoutInflater inflater;
	  Typeface defaultType;
	  Typeface specialType;
	 
	  
	  public AMActorsAdapter(Context context, String[] values) {
		  
		    super(context, R.layout.actormovies_list, values);
		  
		    this.context = context;
		    this.values = values;
		    //this.pairs = pairs;
		    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  }
	  
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    
	    View view = inflater.inflate(R.layout.movieactors_list, parent, false);
	    TextView textview = (TextView) view.findViewById(R.id.actorNameOfMovie);
	    defaultType = textview.getTypeface();
	    
	    SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
	    String appColor = SP.getString("app_color", "blue");
        //boolean appBackup = SP.getBoolean("back_up",false);
        //System.out.println("color " + appColor + " appBackup " + appBackup );	
    	
	    String appFont = SP.getString("app_font", "Default");  
	    
	    switch(appFont){
	    case "Default":
	    	specialType = defaultType;
	    	//textview.setTypeface(defaultType);
	    	break;
	    case "Architects Daughter":
	    	 specialType = Typeface.createFromAsset(context.getAssets(), "ArchitectsDaughter.ttf");
	    	break;
	    case "Blokletters-Balpen":
	    	  specialType = Typeface.createFromAsset(context.getAssets(), "Blokletters-Balpen.ttf");
	        break;
	    case "Comic Relief":
	    	 specialType = Typeface.createFromAsset(context.getAssets(), "ComicRelief.ttf");
	    	break;
	    case "Courier Prime":
	    	 specialType = Typeface.createFromAsset(context.getAssets(), "Courier_Prime.ttf");
	    	break;
	    case "Heuristica Italic":
	    	 specialType = Typeface.createFromAsset(context.getAssets(), "Heuristica-Italic.otf");
	    	break;
	    case "ShortStack-Regular":
	    	  specialType = Typeface.createFromAsset(context.getAssets(), "ShortStack-Regular.otf");
	          break;
	    }
        	
	    textview.setTypeface(specialType);
	    
        switch (appColor) {
		case "blue":
			view.setBackgroundColor(context.getResources().getColor(R.color.lightblue));
			break;
		case "red":
			view.setBackgroundColor(context.getResources().getColor(R.color.red));
			break;
		case "cyan":
			view.setBackgroundColor(context.getResources().getColor(R.color.cyan));
			break;
		case "chartreuse":
			view.setBackgroundColor(context.getResources().getColor(R.color.chartreuse));
			break;
		case "purple":
			view.setBackgroundColor(context.getResources().getColor(R.color.purple));
			break;
		case "magenta":
			view.setBackgroundColor(context.getResources().getColor(R.color.magenta));
		  break;
		case "gold":
			view.setBackgroundColor(context.getResources().getColor(R.color.gold));
			break;
    	}
	    
	    //TextView textView = (TextView) view.findViewById(R.id.actorNameOfMovie);
	    textview.setText(values[position]);
	    
	   
	    
	    
	    
	    
	    

	    return view;
	  }
}
