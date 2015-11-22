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
import android.widget.TextView;

public class Endactivity extends Activity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent = getIntent();
		String highscore = intent.getStringExtra("highscore");
		String highscorevalue = intent.getStringExtra("highscorevalue");
		String score = intent.getStringExtra("score");
		String bonus = intent.getStringExtra("bonus");
		String penalty = intent.getStringExtra("penalty");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.end);
//		if(highscore.equals("yes"))
//		{
			TextView scoreval=(TextView) findViewById(R.id.scorevalue);
			TextView bonusval=(TextView) findViewById(R.id.bonusvalue);
			TextView penaltyval=(TextView) findViewById(R.id.penaltyvalue);
			TextView highscoreval=(TextView) findViewById(R.id.highscorevalue);
			TextView highscorelabel=(TextView) findViewById(R.id.highscorelabel);
			
			scoreval.setText(score);
			bonusval.setText(bonus);
			penaltyval.setText(penalty);
			highscoreval.setText(highscorevalue);
			if(highscore.equals("yes"))
			{
				highscorelabel.setText("HIGH SCORE");
			}else
			{
				highscorelabel.setText("BEAT IF U CAN !");
			}
			
		//	t.setVisibility(View.VISIBLE);
		//}
		
		Button b=(Button) findViewById(R.id.exit);
		b.setOnClickListener(this);
	}
	@Override
	public void onBackPressed() {
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();
	}

	
}
