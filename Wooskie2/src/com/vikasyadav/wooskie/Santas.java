
package com.vikasyadav.wooskie;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import java.util.Random;


/**
 *
 * @author Vikas Yadav
 */


public class Santas extends skieobject
{
	
	public void newSanta(Random g1)  //Function for generating new santas.
	{
		do  //Loop keeps santas from generating on top of the snowboarder.
		{
			x = (int)(g1.nextDouble() * (canvas_width-20)) + 2;
			y = (int)(g1.nextDouble() * (canvas_height-20)) + 2;
		}
		while(x < (canvas_width/2)+24 && x > (canvas_width/2)-24 && y < (canvas_height/2)+24 && y >(canvas_height/2)-24 );
	}
	
	public void moveSanta(int y1, Random g1,int angle)  //Function for moving santas.
	{
		y = (int) (y - Math.abs((y1*Math.sin(Math.toRadians(angle)))));

		x=x-(int)(y1/Math.tan(Math.toRadians(angle)));
		if(y < 2)  //New santa at bottom of screen if one leaves the top of the screen.
		{
			y = canvas_height;
			x = (int)(g1.nextDouble() * (canvas_width-20)) + 2;
		}
		if(y > canvas_height)  //New santa at top of screen if one leaves the bottom of the screen.
		{
			crash=false;
			y = 2;
			x = (int)(g1.nextDouble() * (canvas_width-20)) + 2;
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


