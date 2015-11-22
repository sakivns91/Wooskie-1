package com.vikasyadav.wooskie;

import java.util.Random;

import android.graphics.Bitmap;

public class GoldCoin extends skieobject{
	
		boolean visible=true;
		
		@Override
		public void newObject(Random g1)  
		{
			do  
			{
				x = (int)(g1.nextDouble() * (canvas_width-20))+2 ;
				y = (int)(g1.nextDouble() * (canvas_height-20))+2 ;
			}
			while(x < (canvas_width/2)+image.getWidth()*2 && x > (canvas_width/2)-image.getWidth()*2 && y < (canvas_height/2)+image.getHeight()*2 && y >(canvas_height/2)-image.getHeight()*2 );
		                    
		}
	
		@Override
		public void move(int y1, Random g1,int angle)  
		{
			
			x=x-(int)(y1/Math.tan(Math.toRadians(angle)));
			y = (int) (y -  Math.abs((y1*Math.sin(Math.toRadians(angle)))));
			
			if(y < 2) 
			{
				visible=true;
				y = canvas_height;
				x = (int)(g1.nextDouble() * canvas_width-20)+2;
			}
			if(x < 2) 
			{
				visible=true;
				y = (int)(g1.nextDouble() * canvas_height-20)+2;
				x = canvas_width-2;
				
			}
			if(x > canvas_width-2) 
			{
				visible=true;
				y =  (int)(g1.nextDouble() * canvas_height-20)+2;
				x = 2;
			}
	                
		}
	     
	}

