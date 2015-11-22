package com.vikasyadav.wooskie;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.vikasyadav.database.DatabaseHandler;
import com.vikasyadav.database.User;

public class LeaderBoard extends Activity {

	 private ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.leaderboard);
		 lv = (ListView) findViewById(R.id.leaderboard);
		
        DatabaseHandler dbh=new  DatabaseHandler(getApplicationContext());
			
		 List<User> tdl=dbh.get_userdetails();
		 Collections.sort(tdl,SCORE_ORDER);
		 
		
		 
		 ArrayList<HashMap<String, String>> a=new ArrayList<HashMap<String, String>>();
		 int i=0;
		  for(User t : tdl)
		  {
			  i++;
			  int id=t.getId();
			  HashMap<String, String> map = new HashMap<String, String>();
			  map.put("Name", t.getName());
			  map.put("Score",String.valueOf(t.getScore()));
			  map.put("Rank",String.valueOf(i) );
			  a.add(map);
		  }
		  
      
        
       		ListAdapter adapter = new SimpleAdapter(
						LeaderBoard.this,a,
						R.layout.list_item, new String[] { "Name","Score","Rank"
								},
						new int[] { R.id.leaderboardname, R.id.leaderboardscore,R.id.leaderboardrank });
       		
				Log.d("vikasvikas",a.toString());
				
			
        lv.setAdapter(adapter);
			

	}
	
	 Comparator<User> SCORE_ORDER = new Comparator<User>() { 
		   
			@Override
			public int compare(User lhs, User rhs) {
				// TODO Auto-generated method stub
				return -(lhs.getScore()-rhs.getScore());
			}
		}; 


}
