/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vikasyadav.wooskie;

import java.util.Random;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;


/**
 *
 * @author Vikas Yadav
 */

public class Skiers extends skieobject
{

	public int speed;  //Speed and direction variables for skiers.
	public Directions skiersDirection;
	public int skiersCounter;
	public int skiersChange;
	
	
	
	
	public void newSkier(Random g1)  //Function for generating new skiers.
	{
		do
		{
			x = (int)(g1.nextDouble() * canvas_width) ;
			y = (int)(g1.nextDouble() * canvas_height) ;
		}
		while(x < (canvas_width/2)+image.getWidth() && x > (canvas_width/2)-image.getWidth() && y < (canvas_height/2)+image.getHeight() && y >(canvas_height/2)-image.getHeight() );
		if(((int)(g1.nextDouble() * 2) + 1) == 1)  //Randomly generates direction of skier.
			skiersDirection = Directions.left;
		else
			skiersDirection = Directions.right;
		skiersCounter = 0;
		skiersChange = (int)(g1.nextDouble() * 500) + 1;  //Sets how often the skier 
		speed = (int)(g1.nextDouble() *3)-2;  //changes direction and variable speed.
	}
	
	public void moveSkier(int y1, Random g1,int angle)  //Function for moving skiers.
	{
		skiersCounter++;
		if(skiersCounter > skiersChange)  //Chance to change direction after a random
		{   
			//amount of time.
			
			if(((int)(g1.nextDouble() * 2) + 1) == 1)
				skiersDirection = Directions.left;
			else
				skiersDirection = Directions.right;
			skiersCounter = 0;
			skiersChange = (int)(g1.nextDouble() * 500) + 1;
		}
		y = y - y1 - speed;
		if(skiersDirection == Directions.left)
			x = x - 3 + speed;
		else
			x = x + 3 - speed;
		if(y < 0)  //Generates a new skier at the bottom if one leaves the top of the screen.
		{
			y = canvas_height;
			x = (int)(g1.nextDouble() * canvas_width) ;
			if(((int)(g1.nextDouble() * 2) + 1) == 1)
				skiersDirection = Directions.left;
			else
				skiersDirection = Directions.right;
			speed = (int)(g1.nextDouble() * 3) - 1;
		}
		if(y >canvas_height)  //Generates a new skier at the top if one leaves the bottom of the screen.
		{
			crash=false;
			y = 2;
			x = (int)(g1.nextDouble() * canvas_width);
			if(((int)(g1.nextDouble() * 2) + 1) == 1)
				skiersDirection = Directions.left;
			else
				skiersDirection = Directions.right;
			speed = (int)(g1.nextDouble() * 3) - 1;
		}
		if(x+image.getWidth() > canvas_width-2)  //Pushes skiers back if they collide with the right side.
		{
			crash=false;
			x = x - 6 + speed;
			skiersDirection = Directions.left;
		}
		if(x < 2)  //Pushes skiers back if they collide with the left side.
		{
			crash=false;
			x = x + 6 - speed;
			skiersDirection = Directions.right;
		}
	}
}