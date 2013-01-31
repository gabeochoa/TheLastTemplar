package main.entity;

import java.awt.*;
import java.util.Random;

import main.Art;

/**
 * Write a description of class quickZombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class quickZombie extends enemy
{
    double xStep, yStep;
    Random random = new Random();
    //////////////////////////////////////////range is currently a placeholder.
    private int range = 20;
    public quickZombie()
    {
 	   super(100,5,(int)(-Math.random()*5)-2,0,null);
 	   xStep = 6;
 	   yStep = 0;
 	   
    }
    public quickZombie(int h, int d, int X, int Y, Image im, double xS)
    {
       super(h,d,X,Y,im); 
       xStep = xS*2; 
       yStep = 0;
    }
    ////////////////////////////////////simple move method, zombies should not need to move up
    public void move()
    {
        x += xStep;
        y += yStep;
    }
    /////////////////////////////////////modifier methods
    public void changeXS(int a)
    {
        xStep = a;
    }
    public void changeYS(int a)
    {
        yStep = a;
    }

    public int getRange()
    {
    	return range;
    }
	@Override
	public void drawHealth() {
		// TODO Auto-generated method stub
		
	}

}
