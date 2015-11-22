package com.vikasyadav.wooskie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;

public class LevelActivity extends Activity implements OnClickListener{

	String username;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.levels);
		
		Intent i = getIntent();
		username = i.getStringExtra("USERNAME");
		Button easy=(Button) findViewById(R.id.easy);
		Button hard=(Button) findViewById(R.id.hard);
		Button medium=(Button) findViewById(R.id.medium);
		Button back=(Button) findViewById(R.id.back);
		
		 easy.setOnClickListener(this);
		  hard.setOnClickListener(this);
		  medium.setOnClickListener(this);
		  back.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if(v.getId()==R.id.back)
		{
			setResult(1);
			finish();
			return;
		}
		Intent i=new Intent(this,RenderViewTest.class);
		
		
		switch( v.getId())
		{
		case R.id.easy:
			
			i.putExtra("level", "easy");
			
			break;
			
		case R.id.hard:
			i.putExtra("level", "hard");
			break;
		case R.id.medium:
			i.putExtra("level", "medium");
			
			break;
		}
		i.putExtra("USERNAME",username);
		startActivity(i);
	}
}
