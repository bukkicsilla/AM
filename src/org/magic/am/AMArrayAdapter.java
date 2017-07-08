package org.magic.am;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.graphics.Typeface;

public class AMArrayAdapter extends ArrayAdapter<String>{
	  private final Context context;
	  private final String[] values;
	  //private final Pair[] pairs;
	  LayoutInflater inflater;
	  TextView textview;
	  Typeface defaultType;
	  Typeface specialType;
	  
	  /*String[] movies = {
	            "The Heirs",
	            "Marriage not dating",
	            "My daughter",
	            "Man who cannot marry",
	            "The Musical",
	            "Scandal",
	            "Rooftop Prince",
	            "Good Doctor",
	            "The Jewel in the Palace",
	            "Doctor Stranger",
	            "Beethoven Virus"
	    };*/
		
	  
	  public AMArrayAdapter(Context context, String[] values) {
		  
		    super(context, R.layout.actors_list, values);
		  
		    this.context = context;
		    this.values = values;
		    //this.pairs = pairs;
		    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  }
	  
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	    //LayoutInflater inflater = (LayoutInflater) context
	      //  .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		//View view = convertView;
	    View view = inflater.inflate(R.layout.actors_list, parent, false);
	    //ViewHolder holder = new ViewHolder();
	    
	    textview = (TextView) view.findViewById(R.id.actorName);
	    defaultType = textview.getTypeface();
	     //System.out.println("default type font  " + defaultType.toString());
	     //Typeface tf = Typeface.createFromAsset(context.getAssets(), "helvetica-neue-bold.ttf");
	     //Typeface tf = Typeface.createFromAsset(context.getAssets(), "ArchitectsDaughter.ttf");
	     //textview.setTypeface(tf);
	     //System.out.println("new type font  " + textview.getTypeface().toString());
	    
        SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
	    String appColor = SP.getString("app_color", "blue");
        String appFont = SP.getString("app_font", "Default");  
        //System.out.println("app font " + appFont);
	    
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
	    
	    
	    //textview = (TextView) view.findViewById(R.id.actorName);
        textview.setText(values[position]);
        //Typeface defaultType = textView.getTypeface();
	    //System.out.println("default type font  " + defaultType);
        
        //??????
        /*holder.relativeList = (RelativeLayout) convertView.findViewById(R.id.arrayadapterlist);
	    holder.textviewContent = (TextView) convertView.findViewById(R.id.actorName);
	    holder.textviewContent.setText(values[position]);
	    view.setTag(holder);*/
        
	    //TextView textView1 = (TextView) rowView.findViewById(R.id.movieName);
	    //ImageView imageView = (ImageView) rowView.findViewById(R.id.actorIcon);
	    //textView1.setText(movies[position]);
	    //textView1.setText(pairs[position].getTitle());
	    //textView1.setText(pairs[position].getId()+"");
	    //imageView.setImageURI(Uri.parse(pairs[position].getImagepath()));
	    
	    //useful later
	    //imageView.setImageResource(R.drawable.blue);
	    
	    // change the icon for Windows and iPhone
	    //String s = values[position];
	    /*switch (position) {
		   case 0:
			   imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/leeminho.jpg"));
			break;
		case 1:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/MarriageNotDating.jpg"));
			
			break;
		case 2:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/leesang.jpg"));
			break;
		case 3: 
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/jijinhee.jpg"));
			break;
		case 4:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/koo-hye-sun.jpg"));
		    break;
		case 5:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/ParkMinYoung.jpg"));
		    break;
		case 6:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/park-yoochun.jpg"));
		    break;
		case 7:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/joowon.jpg"));
		    break;
		case 8:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/Lee-Young-Ae.jpg"));
		    break;
		case 9:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/ParkHaeJin.jpg"));
			break;
		case 10:
			imageView.setImageURI(Uri.parse("/storage/sdcard0/actors/kim-myung-min.jpg"));
			break;
	    default:
	    	imageView.setImageResource(R.drawable.ic_launcher);
	    	
	    	break;
		}*/
	    
	    

	    return view;
	  }
	  
	  
	  
	  static class ViewHolder {
		    RelativeLayout relativeList;
		    TextView textviewContent;
		    ImageView imageviewArrow;
		}
}

