package main.entity;
import java.awt.*;

/**
 * Write a description of class enemy here.
 * Basic shell for enemies in the game
 * @author Justin Maguire
 * @version Alpha 5/22/2012
 */
public abstract class enemy extends entity
{
    double xStep, yStep;
    public enemy(int h, int d, int X, int Y, Image im)
    {
        super(h, d, X, Y, im);
        health = 150;
        damage = 5;
    }
    
    public void moveToX(int px)
    {
    	xStep = Math.abs(xStep);
    	
    	if(x < px+((int)Math.random()*15))
    		xStep*= 1;
    	if(x > px+((int)Math.random()*15))
    		xStep *= -1;
    }
    public abstract void drawHealth();
    
}
