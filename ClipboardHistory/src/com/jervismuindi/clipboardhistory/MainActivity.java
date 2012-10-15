package com.jervismuindi.clipboardhistory;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.ClipboardManager;
import android.content.ClipboardManager.OnPrimaryClipChangedListener;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ArrayList<String> mClips = new ArrayList<String>(10);
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        
        startMonitoringClipboard();
        
        ListView listView1 = (ListView) findViewById(R.id.listView1);
        
        String[] items = { "One", "Two", "tgree", "four", "five" };
        
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, items);
        
        listView1.setAdapter(adapter);
        
        
        
    }

    public void showMessage(String msg){
    	Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    
    
    public void startMonitoringClipboard() {
    	showMessage("Starting Monitoring ClipBoard");
    	final ClipboardManager cm = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
    	    	       	
    	// Add a clipboard listener. 
    	cm.addPrimaryClipChangedListener(new OnPrimaryClipChangedListener() {
			
			public void onPrimaryClipChanged() {
				// TODO Auto-generated method stub
				if(cm.getPrimaryClipDescription().hasMimeType(ClipDescription.MIMETYPE_TEXT_PLAIN)) {
		    		showMessage("Clipboard has data");
		    		
		    		ClipData cd = cm.getPrimaryClip();
		    		int count = cd.getItemCount(); 
		    		for(int i = 0; i < count; ++i){
		    			String new_clip = cd.getItemAt(i).getText().toString();
		    			mClips.add(new_clip);
		    		}
		    		

		    	} else  {
		    		showMessage("Clipboard has no text data");    		
		    	}
				
			}
		});
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
