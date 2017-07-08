package org.magic.am;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;

public class AMDialog {
 
	Context context;
	ListView listView;
	String [] stringArray;
	Dialog dialog;
	private static final String TAG = "AM";
	public static boolean oldlibrary = true;
	/*@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.am_dialog);
		
	}*/
	
	public AMDialog(){
		
	}
	public AMDialog(Context context ){
		
		this.context = context;
	}
	
	public void openDialog(){
		Toast.makeText(context, "Showing dialog.", Toast.LENGTH_LONG).show();
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
   					dialog.cancel();
                    
            		break;
            	case 1:
            		
            		oldlibrary = false;
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
	}
}
