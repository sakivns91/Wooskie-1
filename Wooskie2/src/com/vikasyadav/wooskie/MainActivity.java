package com.vikasyadav.wooskie;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	public void onCreate(Bundle savedInstanceState) 
	{
	
		
	super.onCreate(savedInstanceState);
	requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
	setContentView(R.layout.activity_main);
	
	
	Button play=(Button) findViewById(R.id.play);
	Button instruction=(Button) findViewById(R.id.instructions);
	Button highscore=(Button) findViewById(R.id.highscore);
	Button exit=(Button) findViewById(R.id.exit);
	
	 play.setOnClickListener(this);
	  instruction.setOnClickListener(this);
	  highscore.setOnClickListener(this);
	  exit.setOnClickListener(this);

	}
	@Override
	public void onClick(View v) {
	
		Intent i;
		
		switch( v.getId())
		{
		case R.id.exit:
			i= new Intent(this, Launcher.class);
			i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			i.putExtra("EXIT", true);
			startActivity(i);
			finish();
			
			break;
		case R.id.play:
			i=new Intent(this,GoodName.class);
			
			startActivity(i);
			break;
			
		case R.id.instructions:	
			i=new Intent(this,Instructions.class);
			
			startActivity(i);
			break;
		case R.id.highscore:
			i=new Intent(this,LeaderBoard.class);
			
			startActivity(i);
			
			break;
		}
		
	}
	@Override
	public void onBackPressed() {
	}
	
}
