package com.vikasyadav.wooskie;

import java.util.Random;

public class Life extends GoldCoin{

	
	public void newObject(Random g1)  
	{
		
			x = (int)(g1.nextDouble() * (canvas_width-20))+2 ;
			y = canvas_height ;
		
		         
	}
	
	@Override
	public void move(int y1, Random g1,int angle)  
	{
		
		x=x-(int)(y1/Math.tan(Math.toRadians(angle)));
		y = (int) (y -  Math.abs((y1*Math.sin(Math.toRadians(angle)))));
		if(y < 2) 
		{
			if(g1.nextInt(1000)==301)
			{
			visible=true;
			y = canvas_height;
			x = (int)(g1.nextDouble() * canvas_width-20)+2;
			}
		}
		
		if(x < 2) 
		{
			if(g1.nextInt(1000)==125)
			{
			visible=true;
			y = canvas_height/2+(int)(g1.nextDouble() * canvas_height/2-20)+2;
			x = canvas_width-2;
			}
			
		}
		if(x > canvas_width-2) 
		{
			if(g1.nextInt(1000)==27)
			{
			visible=true;
			y =  canvas_height/2+(int)(g1.nextDouble() * canvas_height/2-20)+2;
			x = 2;
		}
			}
		
	}
}
