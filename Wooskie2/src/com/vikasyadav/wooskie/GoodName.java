package com.vikasyadav.wooskie;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.vikasyadav.database.DatabaseHandler;

public class GoodName extends Activity implements OnClickListener{
	EditText username,newusername,goodname;
	DatabaseHandler dbh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_good_name);
		dbh=new  DatabaseHandler(this);
		Button one=(Button) findViewById(R.id.existing_user_button);
		Button two=(Button) findViewById(R.id.adduserbutton);
		Button skip=(Button) findViewById(R.id.skipbutton);
		Button back=(Button) findViewById(R.id.backbutton);
		username=(EditText) findViewById(R.id.username);
		 newusername=(EditText) findViewById(R.id.newusername);
		 goodname=(EditText) findViewById(R.id.goodname);
		
		 
		
		 one.setOnClickListener(this);
		  two.setOnClickListener(this);
		  skip.setOnClickListener(this);
		  back.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.good_name, menu);
		return true;
	}

	 @Override  
     protected void onActivityResult(int requestCode, int resultCode, Intent data)  
     {  
               super.onActivityResult(requestCode, resultCode, data);  
                // check if the request code is same as what is passed  here it is 2  
                 if(requestCode==1)  
                       {  
                          finish();
                       }  
   }  
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
	
		Intent i=new Intent(this,LevelActivity.class);
		
		
		switch( v.getId())
		{
		
		case R.id.skipbutton:
			if(!dbh.CheckIsUserNameExist("Anonymous"))
			{
				dbh.add_user("Anonymous","Anonymous", 0);
			}
			i.putExtra("USERNAME", "Anonymous");
			startActivityForResult(i,1);
			break;
		case R.id.backbutton:
			finish();
			break;
		
		case R.id.existing_user_button:
			String user=this.username.getText().toString();
			if(dbh.CheckIsUserNameExist(user))
			{
				i.putExtra("USERNAME", user);
				startActivityForResult(i,1);
			}	
			else
			{
				Context context = getApplicationContext();
				CharSequence text = "NOT VALID USER";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
				
			
				break;
			
			
		case R.id.adduserbutton:

		{
			String newuser=this.newusername.getText().toString();
			String name=this.goodname.getText().toString();
			if(!newuser.equals("")&&!name.equals(""))
			{
			if(!dbh.CheckIsUserNameExist(newuser))
			{
			dbh.add_user(newuser,name, 0);
			i.putExtra("USERNAME", newuser);
			startActivityForResult(i,1);
			}
			else
			{
				Context context = getApplicationContext();
				CharSequence text = "User exist with same UserID";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
			}
			else
			{
				Context context = getApplicationContext();
				CharSequence text = "Field Cannot Be Empty";
				int duration = Toast.LENGTH_SHORT;

				Toast toast = Toast.makeText(context, text, duration);
				toast.show();
			}
		}
			break;
	
		
		}}
}
