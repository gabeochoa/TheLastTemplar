package main.entity;

import java.awt.*;
import java.util.Random;

import main.Level.Level;

/**
 * Write a description of class slowZombie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class slowZombie extends enemy
{
   double xStep, yStep;
   Random random = new Random();
   Level level;
   //////////////////////////////////////////range is currently a placeholder.
   private int range = 20;
   public slowZombie()
   {
	   super(100,5,(int)(-Math.random()*5)-2,0,null);
	   xStep = 2;
	   yStep = 0;
	   who = "zombie2";
   }
    public slowZombie(int h, int d, int X, int Y, Image im, double xS)
    {
       super(h,d,X,Y,im); 
       xStep = xS*.5; 
       yStep = 0;
    }
    ////////////////////////////////////simple move method, zombies should not need to move up
    public void move()
    {
        x += xStep;
        y += yStep;
    }
    public void move(int yx)
    {
        x += xStep;
        y += yStep;
        moveToX(yx);
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
	public void drawHealth()
    {
    	g.setColor(Color.red);
    	g.drawRect(x-10,y-10,health*2, 20);
    }

}
