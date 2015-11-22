package com.vikasyadav.wooskie;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;

public class Launcher extends Activity {

	  private ProgressBar mProgress;
	     private int mProgressStatus = 0;
	     MediaPlayer mp;

	     private Handler mHandler = new Handler();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 if (getIntent().getBooleanExtra("EXIT", false)) 
   		{
   		        finish();
   		        return;
   		}
		
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_launcher);
       
        mProgress = (ProgressBar) findViewById(R.id.progressBar1);
        mProgress.setProgressDrawable(getResources().getDrawable(R.drawable.layerlist));
         
      mp=MediaPlayer.create(this, R.raw.jingle);
        
        mp.start();
        
        ImageView i=(ImageView) findViewById(R.id.launcherImage);
        i.startAnimation( 
        	    AnimationUtils.loadAnimation(this, R.anim.rotate) );
        
        
        Thread welcomeThread = new Thread() {

            @Override
            public void run() {
           	 int jumpTime = 0;
               while (mProgressStatus < 100) {
                   mProgressStatus++;
                   try {
					sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                   
                   //= doWork();

                   // Update the progress bar
                   mHandler.post(new Runnable() {
                       public void run() {
                           mProgress.setProgress(mProgressStatus);
                       }
                   });
               }
               Intent i = new Intent(Launcher.this,
                       MainActivity.class);
               startActivity(i);
               finish();
           }
        };
        welcomeThread.start();
           
        
	}


	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		
		if(mp!=null)
		{	if(mp.isPlaying())
				{mp.stop();}
		mp.release();}
		
		
	//	Log.d("vikas", "onstop");
	}
	
	

	
}
