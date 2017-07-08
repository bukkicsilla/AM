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

public class AMAutoAdapter extends ArrayAdapter<String> {
	 private final Context context;
	  private final String[] values;
	  LayoutInflater inflater;
	  
	 
		
	  
	  public AMAutoAdapter(Context context, String[] values) {
		  
		    super(context, R.layout.new_list, values);
		  
		    this.context = context;
		    this.values = values;
		    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  }
	  
	  
	  @Override
	  public View getView(int position, View convertView, ViewGroup parent) {
	   
	    View view = inflater.inflate(R.layout.new_list, parent, false);
	   
	    
       SharedPreferences SP = PreferenceManager.getDefaultSharedPreferences(context);
	    String appColor = SP.getString("app_color", "blue");
       boolean appBackup = SP.getBoolean("back_up",false);
       
       	
   	
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
	    
	    
	    TextView textView = (TextView) view.findViewById(R.id.am_auto);
       textView.setText(values[position]);
       
      
	    

	    return view;
	  }
	  
	  
	  
	  
}
