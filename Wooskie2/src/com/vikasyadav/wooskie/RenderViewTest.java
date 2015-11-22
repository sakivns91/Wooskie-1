package com.vikasyadav.wooskie;

/**
 *
 * @author Vikas Yadav
 */

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.vikasyadav.database.DatabaseHandler;
public class RenderViewTest extends Activity implements SensorEventListener, OnTouchListener {
	
	MediaPlayer coinsound=null;
	MediaPlayer crashsound,woo ;
	MediaPlayer backgroundmusic;
	String user;
	  Random Generate1 = new Random();
	  int a = 200;
	  int b = 200;
	  int c = 200;
	  int pause_counter=0;
	
	  Bitmap crash;
	  private boolean crashed = false;
	  SharedPreferences sharedpreferences;
	  int crashedCounter = 0;
	  int crashedCounter2 = 0;
	  int d = 200;
	  private Directions direction = Directions.left;
	  private int distance;
	  private int highScore;
	  private long highestscore;
	  private String gamelevel;
	  Bitmap i;
	  Bitmap i120,icrash120;
	  Bitmap i135,icrash135;
	  Bitmap i45,icrash45;
	  Bitmap i60,icrash60;
	  Bitmap iDownL;
	  Bitmap iDownR;
	  Bitmap idown;
	  Bitmap jump;
	  Bitmap gold,bitcoin;
	  private boolean jumped = false;
	  private int jumpedCounter = 0;
	  private boolean jumpedSlow = false;
	  Snowboarder me;
	  Bitmap mogul;
	  private Moguls[] mymoguls ;
	  private Rocks[] myrocks ;
	  private Santas[] mysantas ;
	  private Skiers[] myskiers;
	  private Trees[] mytrees ;
	  private GoldCoin[] goldcoin ;
	  private Life life;
	  int numCollectedGoldCoin;
	  int numMoguls ;
	  int numRocks;
	  int numSantas;
	  int numSkiers ;
	  int numTrees ;
	  int numgoldcoin;
	  Bitmap heart;
	  private int pauseCounter;
	  int crashCounterThreshold;
	  int radius = 50;
	  Bitmap rock;
	  float sa = 0;
	  Bitmap santa;
	  float sb = 0;
	  float sc = 0;
	  private int score;
	  boolean sensor = true;
	  Bitmap skierL;
	  Bitmap skierR;
	  int speedx = 5;
	  int speedy = 2;
	  private Directions tempdirection;
	  boolean touch = false;
	  Bitmap tree;
	  int window_height;
	  int window_width;
	  int x = 300;
	  int y = 300;
	  int crashpoint=0;
	  int level;
	  int background_speed=9;
	  int background_thresh=10;
	  int k=1000;
	  Paint pause=new Paint();
	  long lastUpdatedTime,currentTime;
	  SensorManager manager;
	  Sensor accelerometer;
	  
	  
	  public int getScore()
	  {
		  float mul=1;
		  if(level==1)
			  mul=1;
		  else if(level==2)
			  mul=(float) 1.2;
		  else if(level==3)
			  mul=(float) 1.5;
		  return (int) (score+((distance/10)*mul)+numCollectedGoldCoin*10);
	  }
	
		class RenderView extends View 
		{
			
			
			
			AssetManager assetManager = getAssets();
			Paint paint=new Paint();
			Random rand = new Random();
			int see=0;
			
			
			public RenderView(Context context,String level) {
				
			super(context);
			
			Display display=((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
			window_height=display.getHeight();
			window_width=display.getWidth();
			setBackgroundResource(R.drawable.pure_ice);
			gamelevel=level;
			if(level.equals("easy"))
			{
				crashCounterThreshold=20;
				  numMoguls = 0;
				   numRocks = window_height/400;
				   numSantas = window_height/600;
				   numSkiers = numSantas;
				   numTrees = numRocks;
				   numgoldcoin=window_height/200;
			}
			else if(level.equals("medium"))
			{
				crashCounterThreshold=20;
				gamelevel=level;
				 numMoguls = 0;
				   numRocks = window_height/300;
				   numSantas = numRocks;
				   numSkiers = window_height/400;
				   numTrees = window_height/300;
				   numgoldcoin=window_height/160;
			}
			else if(level.equals("hard"))
			{
				crashCounterThreshold=20;
				 numMoguls = 0;
				   numRocks = window_height/200;
				   numSantas = window_height/256;
				   numSkiers = numSantas;
				   numTrees = numRocks+1;
				   numgoldcoin=window_height/120;
			}
			 mymoguls = new Moguls[numMoguls];
			   myrocks = new Rocks[numRocks];
			  mysantas = new Santas[numSantas];
			 myskiers = new Skiers[numSkiers];
			  mytrees = new Trees[numTrees];
			  goldcoin=new GoldCoin[numgoldcoin];
			
			
			
			
			
			init(window_width,window_height);
			
			try {
				
				
//				  AssetFileDescriptor afd;
//		            afd = getAssets().openFd("AudioFile.mp3");
//		            mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
//		            mp.prepare();
				
				
			//	AssetManager assetManager = getAssets();
				
			//	String uriStr = "android.resource://" + PACKAGE_NAME + "/" + R.drawable.image;
			//	Bitmap image = BitmapFactory.decodeResource(getResources(), R.drawable.yourBitmap);
				
				tree = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
			
				mogul = BitmapFactory.decodeResource(getResources(), R.drawable.tree);
				heart=	BitmapFactory.decodeResource(getResources(), R.drawable.heart);
				
				rock = BitmapFactory.decodeResource(getResources(), R.drawable.rock);
		
				santa = BitmapFactory.decodeResource(getResources(), R.drawable.santa);
			
				crash = BitmapFactory.decodeResource(getResources(), R.drawable.crash);
		
				skierL =BitmapFactory.decodeResource(getResources(), R.drawable.skier_l);
				
				skierR = BitmapFactory.decodeResource(getResources(), R.drawable.skier_r);
				
				i45 = BitmapFactory.decodeResource(getResources(), R.drawable.i45);
				
				i60 = BitmapFactory.decodeResource(getResources(), R.drawable.i60);
				i120 =BitmapFactory.decodeResource(getResources(), R.drawable.i120);
				i135 =BitmapFactory.decodeResource(getResources(), R.drawable.i135);
				icrash45 =BitmapFactory.decodeResource(getResources(), R.drawable.icrash45);
				icrash60 = BitmapFactory.decodeResource(getResources(), R.drawable.icrash60);
				icrash120 = BitmapFactory.decodeResource(getResources(), R.drawable.icrash120);
				icrash135 = BitmapFactory.decodeResource(getResources(), R.drawable.icrash135);
				
				idown = BitmapFactory.decodeResource(getResources(), R.drawable.idown);
				
				gold =BitmapFactory.decodeResource(getResources(), R.drawable.bitcoin);
				bitcoin = BitmapFactory.decodeResource(getResources(), R.drawable.bitcoin);
			
				
				setImages();
				initializePositions();
				
				
				} catch (Exception e) {
				
				} finally {}
			}
			protected void onDraw(Canvas canvas) {
				
//				 if(direction.equals(Directions.end))
//						return;
				
				if(distance>k)
				{
				background_thresh+=1;
				k+=1000;
				}
				
				canvas.drawRGB(255, 255, 255);
				
				
			
				
				
			      this.paint.setTextSize(25);
			      if(direction.equals(Directions.pause))
			      {
			    	  
			    	 
			    	  if(see%5==0) 
			    	  {
			    		  pause.setStrokeWidth(7);
			    		  pause.setTextSize(100);
			    		  canvas.drawText("PAUSED",window_width/2,window_height/2,pause);
			    	  }
			    	 
			    		  pause.setStrokeWidth(2);
			    		  pause.setTextSize(30);
			    		  canvas.drawText("Tap Your Skier To Resume",window_width/2,200,pause);
			    	  
			    	  
			      }
			      
			      
			      if(see++<200)
			      {
			    	  paint.setStyle(Style.STROKE);
			    	  paint.setStrokeWidth(12);
			    	  paint.setColor(Color.parseColor("#FF0040"));
			    	  if(see%7==0)
			      canvas.drawCircle(me.x+me.image.getWidth()/2, me.y+me.image.getHeight()/2,80, paint);
			      }
			      paint.setStyle(Style.STROKE);
		    	  paint.setStrokeWidth(3);
		    	  paint.setColor(Color.parseColor("#006064"));
		    	// paint.setStrokeWidth(20);
		    	//  paint.setColor(Color.parseColor("#000000"));
		    	  
			      canvas.drawText(String.valueOf(numCollectedGoldCoin), 10+bitcoin.getWidth()+10, 50, this.paint);
			   //  canvas.drawText("crash: " + String.valueOf(sa) + " m", 400.0F, 250.0F, this.paint);
			     
			      canvas.drawText("Score: " + String.valueOf(RenderViewTest.this.score + RenderViewTest.this.distance / 10) , canvas.getWidth()-200, 50.0F, this.paint);
			      
			      canvas.drawText(String.valueOf(crashCounterThreshold-crashedCounter+1), 160+heart.getWidth()+10, 50, this.paint);
				   
			     
			   //  this.paint.setStyle(Paint.Style.FILL);
				
				
				/*
				if(a>=canvas.getWidth()-radius || sa>0 || a<=radius || sa<0)
					speedx=-speedx;
				if(b>=canvas.getHeight()-radius || sb<0 || b<=radius || sb>0
						)
					speedy=-speedy;
				
				a=a+speedx;
				b=b+speedy;
				
				*/
				
//				if(d<=-tree.getHeight())
//					{d=canvas.getHeight();
//					 c=rand.nextInt(canvas.getWidth()-tree.getWidth())+tree.getWidth();
//					}
//				
//				d=d-speedy;
				
				
				//canvas.drawCircle(a,b,radius, paint);
				
				//Log.v("blah","blah blah");
			
			     
			      
				moveSkier();
				
				
				for(int i = 0; i < numMoguls; i++)  
				{
					canvas.drawBitmap(mymoguls[i].image,mymoguls[i].x,mymoguls[i].y,null);
				}
				canvas.drawBitmap(me.image,me.x,me.y,null);
				canvas.drawBitmap(bitcoin,10,10,null);
				canvas.drawBitmap(heart, 160 ,10,null);
			
				for(int i = 0; i < numSkiers; i++)  
				{
					canvas.drawBitmap(myskiers[i].image ,myskiers[i].x,myskiers[i].y,null);
				}
				for(int i = 0; i < numTrees; i++)  
				{
					canvas.drawBitmap(mytrees[i].image,mytrees[i].x,mytrees[i].y,null);
				}
				for(int i = 0; i < numSantas; i++)  
				{
					canvas.drawBitmap(mysantas[i].image,mysantas[i].x,mysantas[i].y,null);
				}
				for(int i = 0; i < numRocks; i++)  
				{
					canvas.drawBitmap(myrocks[i].image,myrocks[i].x,myrocks[i].y,null);
				}
				
				for(int i = 0; i < numgoldcoin; i++)  
				{
					if(goldcoin[i].visible)
					canvas.drawBitmap(goldcoin[i].image,goldcoin[i].x,goldcoin[i].y,null);
				}
				if(life.visible)
					canvas.drawBitmap(life.image,life.x,life.y,null);
			
				
				invalidate();
			
			}
		}
		
		public void init(int a,int b)
		{
			skieobject.canvas_width=a;
			skieobject.canvas_height=b;
			
			pause.setStyle(Style.STROKE);
	    	  pause.setStrokeWidth(10);
	    	  pause.setColor(Color.parseColor("#FF0040"));
	    	  pause.setTextAlign(Align.CENTER);
	    	  pause.setTextSize(100);
			me=new Snowboarder();
			life=new Life();
			for(int i = 0; i < numMoguls; i++)  //Generates initial mogul locations.
			{
				mymoguls[i] = new Moguls();
				
			}
			for(int i = 0; i < numRocks; i++)  //Generates initial rock locations.
			{
				myrocks[i] = new Rocks();
				
			}
			for(int i = 0; i < numTrees; i++)  //Generates initial tree locations.
			{
				mytrees[i] = new Trees();
				
			}
			for(int i = 0; i < numSantas; i++)  //Generates initial Santa locations.
			{
				mysantas[i] = new Santas();
		
			}
			for(int i = 0; i < numSkiers; i++)  //Generates initial skier locations.
			{
				myskiers[i] = new Skiers();
				
				

			}
			for(int i = 0; i < numgoldcoin; i++)  //Generates initial skier locations.
			{
				goldcoin[i] = new GoldCoin();
			
				

			}
		
			
		}
		public void initializePositions()
		{
			for(int i = 0; i < numMoguls; i++)  //Generates initial mogul locations.
			{
				
				mymoguls[i].newObject(Generate1);
				
			}
			for(int i = 0; i < numRocks; i++)  //Generates initial rock locations.
			{
				
				myrocks[i].newObject(Generate1);
				
			}
			for(int i = 0; i < numTrees; i++)  //Generates initial tree locations.
			{
				
				mytrees[i].newObject(Generate1);
		
			}
			for(int i = 0; i < numSantas; i++)  //Generates initial Santa locations.
			{
				
				mysantas[i].newSanta(Generate1);
		
			}
			for(int i = 0; i < numSkiers; i++)  //Generates initial skier locations.
			{
				
				myskiers[i].newSkier(Generate1);
				

			}
			for(int i = 0; i < numgoldcoin; i++)  //Generates initial skier locations.
			{
				goldcoin[i].newObject(Generate1);
				

			}
			life.newObject(Generate1);
		}
		public void setImages()
		{
			me.image=i45;
			life.image=heart;

			for(int i = 0; i < numMoguls; i++)  //Generates initial mogul locations.
			{
				
				mymoguls[i].image=mogul;
			}
			for(int i = 0; i < numRocks; i++)  //Generates initial rock locations.
			{
				
				myrocks[i].image=rock;
			}
			for(int i = 0; i < numTrees; i++)  //Generates initial tree locations.
			{
				
				mytrees[i].image=tree;
			}
			for(int i = 0; i < numSantas; i++)  //Generates initial Santa locations.
			{
				
				mysantas[i].image=santa;
			}
			for(int i = 0; i < numSkiers; i++)  //Generates initial skier locations.
			{
				myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;

			}
			for(int i = 0; i < numgoldcoin; i++)  //Generates initial tree locations.
			{
				
				goldcoin[i].image=gold;
			}
			
		}
		public void moveSkier()
		{
			if(!direction.equals(Directions.pause))
				detectCrash();
		//    detectJump();
		  //  jumpedScore();
			if(direction.equals(Directions.end))
				return;
			
			 if(crashedCounter>crashCounterThreshold)
			{
				 	 
				 
				 Vibrator vibrator;
				 vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
				 vibrator.vibrate(500);
				 
				 Toast.makeText(RenderViewTest.this,"Game Over",Toast.LENGTH_SHORT).show();
				 String s="no";
				 
				 Context context = this;
				
				 DatabaseHandler dbh=new  DatabaseHandler(getApplicationContext());
					
				// highscore=
		            
				 highestscore=sharedpreferences.getLong("highscore", 0);
				 
				 if(getScore()>highestscore)
				 {
				 Editor editor = sharedpreferences.edit();
				 editor.putLong("highscore", getScore());
				 editor.commit();
				 s="yes";
				 }
				 /////////////
		        highScore=dbh.getscore(user);
				 
				if(getScore() > highScore)  //Moves score into highScore if it is larger.
					{highScore = getScore();
					dbh.update_User(user,highScore);
					
					}
				Intent i=new Intent(this,Endactivity.class);
				i.putExtra("highscore", s);
				i.putExtra("highscorevalue",String.valueOf(highScore));
				i.putExtra("score",String.valueOf(getScore()));
				i.putExtra("bonus",String.valueOf(numCollectedGoldCoin*10));
				i.putExtra("distance", String.valueOf(distance));
				i.putExtra("penalty",String.valueOf(-score));
				startActivity(i);
				RenderViewTest.this.finish();
				backgroundmusic.stop();
				direction=Directions.end;
			}
			else
			{
				if(direction.equals(Directions.downleft))  //Snowboarder moves quickly down and left.
				{
					background_speed=background_thresh;
					if(!crashed)
					me.image=i120;
					x = x - 4;  //Moves the snowboarder to the left.
					distance = distance + 2;  //Increments distance
					for(int i = 0; i < numMoguls; i++ )  //Functions for moving the objects
						mymoguls[i].move(background_speed, Generate1,-60);
					for(int i = 0; i < numRocks; i++ )
						myrocks[i].move(background_speed, Generate1,-60);
					for(int i = 0; i < numTrees; i++ )
						mytrees[i].move(background_speed, Generate1,-60);
					for(int i = 0; i < numSantas; i++ )
						mysantas[i].move(background_speed-3, Generate1,-60);
					for(int i = 0; i < numgoldcoin; i++ )
						goldcoin[i].move(background_speed, Generate1,-60);
					for(int i = 0; i < numSkiers; i++ )
						{
						myskiers[i].moveSkier(background_speed-5, Generate1,-60);
						myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
						}
					life.move(background_speed, Generate1,-60);
				}
				if(direction.equals(Directions.downright))  
				{
					background_speed=background_thresh;
					if(!crashed)
					me.image=i60;
					x = x + 4;  //Moves the snowboarder to the right.
					distance = distance + 2;  //Increments distance.
					for(int i = 0; i < numMoguls; i++ )  //Functions for moving the objects.
						mymoguls[i].move(background_speed, Generate1,60);
					for(int i = 0; i < numRocks; i++ )
						myrocks[i].move(background_speed, Generate1,60);
					for(int i = 0; i < numTrees; i++ )
						mytrees[i].move(background_speed, Generate1,60);
					for(int i = 0; i < numgoldcoin; i++ )
						goldcoin[i].move(background_speed, Generate1,60);
					for(int i = 0; i < numSantas; i++ )
						mysantas[i].moveSanta(background_speed-3, Generate1,60);
					
					for(int i = 0; i < numSkiers; i++ )
					{
						myskiers[i].moveSkier(background_speed-5, Generate1,60);
						myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
					}
					life.move(background_speed, Generate1,60);
				}
				if(direction.equals(Directions.down))  
				{
					background_speed=background_thresh;
					if(!crashed)
					me.image=idown;
					distance = distance + 2;  //Increments distance.
					
					for(int i = 0; i < numMoguls; i++ )  //Functions for moving the objects.
						mymoguls[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numRocks; i++ )
						myrocks[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numTrees; i++ )
						mytrees[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numgoldcoin; i++ )
						goldcoin[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numSantas; i++ )
						
						mysantas[i].moveSanta(background_speed-3, Generate1,90);
					for(int i = 0; i < numSkiers; i++ )
					{
						myskiers[i].moveSkier(background_speed-5, Generate1,90);
					myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
					}
					life.move(background_speed, Generate1,90);
				}
				if(direction.equals(Directions.left))  //Snowboarder moves slowly down and left.
				{
					background_speed=background_thresh-5;
					if(!crashed)
					me.image=i135;
					x = x - 4;  //Moves the snowboarder to the left.
					distance = distance + 1;  //Increments distance.
					for(int i = 0; i < numMoguls; i++ )  //Functions for moving the objects.
						mymoguls[i].move(background_speed, Generate1,-45);
					for(int i = 0; i < numRocks; i++ )
						myrocks[i].move(background_speed, Generate1,-45);
					for(int i = 0; i < numTrees; i++ )
						mytrees[i].move(background_speed, Generate1,-45);
					for(int i = 0; i < numgoldcoin; i++ )
						goldcoin[i].move(background_speed, Generate1,-45);
					for(int i = 0; i < numSantas; i++ )
						mysantas[i].moveSanta(background_speed-1, Generate1,-45);
					
					for(int i = 0; i < numSkiers; i++ )
					{
						myskiers[i].moveSkier(background_speed-3, Generate1,-45);
					myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
					}
					life.move(background_speed, Generate1,-45);
				}
				if(direction.equals(Directions.right))  //Snowboarder moves slowly down and right.
				{
					background_speed=background_thresh-5;
					if(!crashed)
					me.image=i45;
					x = x + 4;  //Moves the snowboarder to the right.
					distance = distance + 1;  //Increments distance.
					for(int i = 0; i < numMoguls; i++ )  //Functions for moving the objects.
						mymoguls[i].move(background_speed, Generate1,45);
					for(int i = 0; i < numRocks; i++ )
						myrocks[i].move(background_speed, Generate1,45);
					for(int i = 0; i < numTrees; i++ )
						mytrees[i].move(background_speed, Generate1,45);
					for(int i = 0; i < numgoldcoin; i++ )
						goldcoin[i].move(background_speed, Generate1,45);
					for(int i = 0; i < numSantas; i++ )
						mysantas[i].moveSanta(background_speed-1, Generate1,45);
					for(int i = 0; i < numSkiers; i++ )
					{
						myskiers[i].moveSkier(background_speed-3, Generate1,45);
						myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
					}
					life.move(background_speed, Generate1,45);
				}
				
				
				if(direction.equals(Directions.pause))  //Snowboarder does not move.
				{
					
			//		for(int i = 0; i < numSantas; i++ )  //Functions for moving the objects
			//			mysantas[i].moveSanta(-1, Generate1,90);  //that continue to move when
					for(int i = 0; i < numSkiers; i++ ) 
					{//the snowboarder does not.
						myskiers[i].moveSkier(-3, Generate1,90);
						myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
					}
				}
				if(direction.equals(Directions.slowdown))  
				{
					background_speed=background_thresh-5;
					if(!crashed)
					me.image=idown;
					distance = distance + 1;  //Increments distance.
					for(int i = 0; i < numMoguls; i++ )  //Functions for moving the objects.
						mymoguls[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numRocks; i++ )
						myrocks[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numTrees; i++ )
						mytrees[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numgoldcoin; i++ )
						goldcoin[i].move(background_speed, Generate1,90);
					for(int i = 0; i < numSantas; i++ )
						mysantas[i].moveSanta(background_speed-1, Generate1,90);
					for(int i = 0; i < numSkiers; i++ )
					{
						myskiers[i].moveSkier(background_speed-3, Generate1,90);
						myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
					}
					life.move(background_speed, Generate1,90);
//					if(jumpedSlow == false)  //Sets direction to down if the snowboarder
//					{  //is moving straight down at the end of a slow jump.
//						direction = Directions.down;
//					}
				}
				if(direction.equals(Directions.crashed))  //Moves objects that continue to move when
				{               
					//the snowboarder is crashed.
					
						
					 if(tempdirection.equals(Directions.downleft))
						me.image=icrash120;
					else if(tempdirection.equals(Directions.downright))
						me.image=icrash60;
					else if(tempdirection.equals(Directions.right))
						me.image=icrash45;
					else if(tempdirection.equals(Directions.left))
						me.image=icrash135;
					else if(tempdirection.equals(Directions.down) ||tempdirection.equals(Directions.slowdown) )
						me.image=crash;
					for(int i = 0; i < numSantas; i++ )
						mysantas[i].moveSanta(-1, Generate1,90);
					for(int i = 0; i < numSkiers; i++ )
					{
						myskiers[i].moveSkier(-3, Generate1,90);
						myskiers[i].image = myskiers[i].skiersDirection==Directions.left?skierL:skierR;
					}
				}
				if(!direction.equals("exit") && !direction.equals(Directions.end) && !direction.equals(Directions.pause))
				{
					if(jumped == false )
						detectJump(); //Detects jumps when the snowboarder is not already jumped.
					if(jumped == true)
					{
						jumpedCounter++;  //Increments the jumpedCounter.
						jumpedScore();  //Calculates score if the snowboarder passes over
						if(jumpedCounter == 75)  //anything while jumped.
						{
							jumped = false;  //Ends the jump after a set amount of time.
							jumpedCounter = 0;
						}
					}
//				
//					else if(crashed == true)
//					{
//						crashedCounter++;  //Increments crashedCounter.
//						
////					if(distance > 1000000 && !direction.equals(Directions.crashed))
////						direction = Directions.end;  //Ends game if distance reaches 10,000.
//				 }
			}
		}
			
		}
		
		public void detectJump()  //Detects the snowboarder passing over moguls.
		{
			for(int i = 0; i < numMoguls; i++ )
			{
				if(mymoguls[i].overlap(me))
				{
					if(direction.equals(Directions.down) || direction.equals(Directions.downleft) || direction.equals(Directions.downright) || direction.equals(Directions.left) || direction.equals(Directions.right))
					{
						jumped = true;  //Jumped state for faster downward movement.
						 
						score = score + 50;  //Adds to score.
					}
					
				}
			}
		}
		
		
		public void jumpedScore()  //Detects the snowboarder passing over objects during
		{                          //a faster jump.
			for(int i = 0; i < numRocks; i++ )
			{
				if(myrocks[i].overlap(me))
				{
					score = score + 100;  //Increments score for jumping rocks.
				}
			}
			for(int i = 0; i < numTrees; i++ )
			{
				if(mytrees[i].overlap(me))
				{
					score = score + 50;  //Increments score for jumping trees.
				}
			}
			for(int i = 0; i < numSantas; i++ )
			{
				if(mysantas[i].overlap(me))
				{
					score = score + 50;  //Increments score for jumping santas.
				}
			}
			for(int i = 0; i < numSkiers; i++ )
			{
				if(myskiers[i].overlap(me))
				{
					score = score + 150;  //Increments score for jumping skiers.
				}
			}
		}
		
		
		public void detectCrash()  //Function that detects collisions.
		{
			if(crashedCounter2==0)
			{
				for(int i = 0; i < numRocks; i++ )
				{
					boolean b;
					if(myrocks[i].overlap(me) && !myrocks[i].crash)
					{
						myrocks[i].crash=true;
						direction = Directions.crashed;  //Sets crashed states.
						crashed = true;
						
						score = score - 5;  //Decreases score for hitting rocks.
					}
				}
				for(int i = 0; i < numgoldcoin; i++ )
				{
					if(goldcoin[i].overlap(me))
					{
						//Decreases score for hitting rocks.
						
						if(goldcoin[i].visible)
						{
						numCollectedGoldCoin++; 
						//backgroundmusic.pause();
						coinsound.start();
						//backgroundmusic.start();
						goldcoin[i].visible=false;
						}
						
					}
				}
				if(life.overlap(me))
				{
					//Decreases score for hitting rocks.
					
					if(life.visible)
					{
					crashedCounter--; 
					woo.start();
					life.visible=false;
					}
					
				}
				
				for(int i = 0; i < numTrees; i++ )
				{
					if(mytrees[i].overlap(me) && !mytrees[i].crash)
					{
						mytrees[i].crash=true;
						direction = Directions.crashed;  //Sets the crashed states.
						crashed = true;
						score = score - 3;//Decreases score for hitting trees.
					}
				}
				for(int i = 0; i < numSantas; i++ )
				{
					if(mysantas[i].overlap(me) && !mysantas[i].crash)
					{
						mysantas[i].crash=true;
						direction = Directions.crashed;  //Sets the crashed states.
						crashed = true;
						 //Increments the crashed counters.
						
						
						score = score - 7;  //Decreases score for hitting santa.
					}
				}
				for(int i = 0; i < numSkiers; i++ )
				{
					if(myskiers[i].overlap(me) && !myskiers[i].crash)
					{
						myskiers[i].crash=true;
						direction = Directions.crashed;  //Sets the crashed states.
						crashed = true;
						  //Increments the crashed counters.
						score = score - 5;  //Decreases score for hitting skiers.
					}
				}
				 if(crashed){
					 crashpoint=distance;
					 crashedCounter++;
					 crashedCounter2++;
					 if(!crashsound.isPlaying())
						 crashsound.start();
					
					 }
			}
			else if(!direction.equals(Directions.crashed))  //Disables crash detection for the first
			{  //few movements after a crash to keep from hitting the same object more than
				crashedCounter2++;  //once.
				if(distance-crashpoint>15)
					{crashedCounter2 = 0;crashed=false;}
			}
		}
		
		@Override
		public void onCreate(Bundle savedInstanceState) 
		{
		super.onCreate(savedInstanceState);
		
		 sharedpreferences = this.getPreferences(Context.MODE_PRIVATE);
		Intent intent = getIntent();
		String level = intent.getStringExtra("level");
		if(level.equals("easy"))
		this.level=1;
		else if(level.equals("medium"))
			this.level=2;
		else if(level.equals("hard"))
			this.level=3;
		 user=intent.getStringExtra("USERNAME");
		 manager=(SensorManager) getSystemService(Context.SENSOR_SERVICE);
		 if (manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) 

			{
		accelerometer = manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
		
	//	manager.registerListener((SensorEventListener) this, accelerometer,SensorManager.SENSOR_DELAY_GAME);
		}
		RenderView r=new RenderView(this,level);
		r.setBackgroundResource(R.drawable.pure_ice);
		r.setOnTouchListener(this); 
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(r);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		}
		@Override
		public void finish() {
			// TODO Auto-generated method stub
			super.finish();
			if(backgroundmusic!=null)
			if(backgroundmusic.isPlaying())
				backgroundmusic.stop();
		}
		@Override
		protected void onResume() {
			// TODO Auto-generated method stub
			super.onResume();
			manager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
			coinsound = MediaPlayer.create(this, R.raw.coin);
			woo = MediaPlayer.create(this, R.raw.woo1);
			crashsound = MediaPlayer.create(this, R.raw.crash2);
			backgroundmusic=MediaPlayer.create(this, R.raw.background1);
			backgroundmusic.setLooping(true);
			backgroundmusic.start();
		//	Log.d("vikas", "onresume");
		}

		@Override
		protected void onStop() {
			// TODO Auto-generated method stub
			super.onStop();
			
			
			direction=Directions.pause;
			manager.unregisterListener(this);
			if(backgroundmusic!=null)
			{if(backgroundmusic.isPlaying())
				{backgroundmusic.stop();}
			backgroundmusic.release();}
			coinsound.release();
			woo.release();
			crashsound.release();
			//Log.d("vikas", "onstop");
		}

//@Override
public void onSensorChanged(SensorEvent event) {
	sa= event.values[0];
	sb= event.values[1];
	sc= event.values[2];
	
	if(!direction.equals(Directions.pause))
		tempdirection = direction;
	
	
	if(direction.equals(Directions.pause)||direction.equals(Directions.end))	
		return;
	currentTime=System.currentTimeMillis();
	if(sa > 2)
	{
		
		if(currentTime-lastUpdatedTime<50)
			return;
		
		if(touch)
			direction=Directions.downleft;
		else
			direction=Directions.left;
		
		
	}
	else if(sa<-1)
	{
		
		if(currentTime-lastUpdatedTime<50)
			return;
		if(touch)
			direction=Directions.downright;
		else
			direction=Directions.right;
		
	}
	else
	{
		
		if(currentTime-lastUpdatedTime<50)
			return;
		if(touch)
			direction=Directions.down;
		else
			direction=Directions.slowdown;
		
	}
	lastUpdatedTime=System.currentTimeMillis();
	//	else
	//		direction="spppp";
		
		
	
		
	
		
		
	
}



@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) {
	// TODO Auto-generated method stub
	
}




@Override
public boolean onTouch(View v, MotionEvent event) {
	

	 float x=event.getX();
	 float y=event.getY();
	
	int action = event.getAction();
	switch(action)
	{
	case MotionEvent.ACTION_DOWN:
		if(x>me.x && x<me.x+me.image.getWidth() && y>me.y && y<me.y+me.image.getHeight())
		 { 
			if((pause_counter++)%2==0)
				direction=Directions.pause;
			else
				direction=tempdirection;
		}
		touch=true;
		break;
	case MotionEvent.ACTION_MOVE:
		
		touch=true;
		break;

	case MotionEvent.ACTION_UP:
		touch=false;
		break;
	}
	return true;
}
}