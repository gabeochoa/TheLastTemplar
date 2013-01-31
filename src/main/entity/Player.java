package main.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import main.Art;
import main.RPG;
import main.Sound;
import main.Level.*;

public class Player extends entity
{
	private int xVel,yVel;
	private boolean jump;
	private final int jumpForce = 20;
	private int daysSurvived = 0;
	private int range = 30;
	private int speed = 10;
	private int walkSoundCount;
	private int count = 0;
	private int soundDelay;
	public int width = 0;
	private int acount;
	
	public Player(int h, int d, int X, int Y, Image im) {
		super(h, d, X, Y, im);
		// TODO Auto-generated constructor stub
	}

	public Player()
	{
		super(0,0,0,0,null);	
		height = 100;
		width = 209;
		setXVel(setYVel(0));
		x = 0;
		y = RPG.HEIGHT-height;
		animCount = 1;
		who = "player";
		
		damage = 10;
		health = 200;
	}
	private boolean colliding() 
	{
		// TODO Auto-generated method stub
		return false;
	}
	private void jump() 
	{		
		if(colliding() || y > RPG.HEIGHT-height)
		{
			if(yVel >= 0)
			{
				Sound.walk.play();
				jump = false;
				yVel = 0;	
			}		
		}
		else 
		if(jump)
			yVel++;
	}
	  public void tick(boolean up, boolean down, boolean left, boolean right)
      {
		  u = up;
		  d = down;
		  l = left;
		  r = right;
		  
              walkSoundCount = 6;
              jump();
             
              if(up)
              {
                      if(!jump)
                      {
                              Sound.jump.play();
                              yVel = -(jumpForce);
                              jump = true;
                      }
                      up = false;
                      soundDelay--;
              }
              if(left)
              {
            	  if(!jump)
            		  walkSound();
                      x-=speed;
                      side = false;
              }
              else
            	  if(right)
            	  {
            		  if(!jump)
            			  walkSound();
            		      x+=speed;
            		      side = true;
            	  }
              if(y>RPG.HEIGHT-height)
              {
                  y = RPG.HEIGHT-height;
              }              
              x+=xVel;
              y+=yVel;
              
              
              acount--;
              if(acount ==0)
            	  attacking = false;
      }
	public void walkSound()
	{
		if(count >= walkSoundCount)
		{
			count = 0;
			Sound.walk.play();
		}
		else
		{
			count++;
		}	
	}
	public int getYVel() 
	{
		return yVel;
	}
	public int setYVel(int yVel) 
	{
		this.yVel = yVel;
		return yVel;
	}
	public int getXVel() 
	{
		return xVel;
	}
	public void setXVel(int xVel) 
	{
		this.xVel = xVel;
	}
	public int getRange()
	{
	    return range;
	}
    public void paint(Graphics gr)
    {
	    g = gr;
 	    drawEntity();
    }
    public void drawEntity()
    {	    
	    super.drawEntity();
    }
	public int getDaysSurvived() {
		return daysSurvived;
	}
	public void addDays(int days)
	{
		daysSurvived += days;
	}
	public void action() 
	{
		attacking = true;
		acount=20;
	}
    public void move(){}
	public void addLoot(Item item) 
	{
		// TODO Auto-generated method stub
	}

	public boolean inRange(enemy e) {

		if(x > e.x-range && x < e.x+range )
		return true;
		return false;
	}
}