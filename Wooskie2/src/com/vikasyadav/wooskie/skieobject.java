package com.vikasyadav.wooskie;

import java.util.Random;

import android.graphics.Bitmap;

public class skieobject {
	
	int x;
	int y;
	Bitmap image;
	boolean crash=false;
	static int canvas_width;
	static int canvas_height;
	


	public  boolean overlap(skieobject s)
	{
		
		
		
//		if(s.x+s.image.getWidth()<this.x+this.image.getWidth()/4
//				|| this.x+(this.image.getWidth()*3)/4<s.x ||
//				s.y>this.y+(this.image.getHeight()*3)/4 ||
//				this.y+this.image.getHeight()/4>s.y+s.image.getHeight())
//			{return false;}
//		else{
//			return true;	}
		
		if(s.x>this.x+this.image.getWidth()/1.5
				|| this.x>s.x+s.image.getWidth()/2 ||
				s.y>this.y+this.image.getHeight()/2 ||
				this.y>s.y+s.image.getHeight()/2)
			{return false;}
		else{
			return true;	}
//		
//		
//		
//		if(s.x>this.x+this.image.getWidth()
//				|| this.x>s.x+s.image.getWidth() ||
//				s.y>this.y+this.image.getHeight() ||
//				this.y>s.y+s.image.getHeight())
//			{return false;}
//		
////		else{
////			
//			int XA1=this.x;
//			int XA2=XA1+this.image.getWidth();
//			int YA1=this.y;
//			int YA2=YA1+this.image.getHeight();
//			int XB1=s.x;
//			int XB2=XB1+s.image.getWidth();
//			int YB1=s.y;
//			int YB2=YB1+s.image.getHeight();
//			int SA=this.image.getWidth()*this.image.getHeight();
//			int SB=s.image.getWidth()*s.image.getHeight();
//			int SI = Math.max(0, Math.max(XA2, XB2) - Math.min(XA1, XB1)) * Math.max(0, Math.max(YA2, YB2) - Math.min(YA1, YB1));
//			int SU = SA + SB - SI;
//			
//			if((SI/(float)SU)>.5)
//				return true;
//			
//				
//		return false;
	}
	public void newObject(Random g1)  
	{
		do  
		{
			x = (int)(g1.nextDouble() * (canvas_width-20))+2 ;
			y = (int)(g1.nextDouble() * (canvas_height-20))+2 ;
		}
		while(x < (canvas_width/2)+image.getWidth() && x > (canvas_width/2)-image.getWidth() && y < (canvas_height/2)+image.getHeight() && y >(canvas_height/2)-image.getHeight() );
	                
	}
	
	public void move(int y1, Random g1,int angle)  
	{
		x=x-(int)(y1/Math.tan(Math.toRadians(angle)));
		y = (int) (y -  Math.abs((y1*Math.sin(Math.toRadians(angle)))));

		
		if(y < 2) 
		{
			crash=false;
			y = canvas_height;
			x = (int)(g1.nextDouble() * (canvas_width-20))+2;
		}		
		if(x < 2) 
		{
			crash=false;
			y = (int)(g1.nextDouble() * canvas_height-20)+2;
			x = canvas_width-2;
		}
		if(x > canvas_width-2) 
		{
			crash=false;
			y =  (int)(g1.nextDouble() * canvas_height-20)+2;
			x = 2;
		}
            
	}
}
