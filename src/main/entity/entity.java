package main.entity;
import java.awt.*;
import java.awt.image.BufferedImage;

import main.Art;
import main.Level.Level;

/**
 * Write a description of class entity here.
 * Abstract Class for both the player and enemies
 * @author Justin Maguire
 * @version Alpha 5/22/12
 */
 public abstract class entity
{   
    /////////player stats
    public int health;
    public int damage;
    /////////coords
    public int x;
	public int y;
	public int height;
    //////////////////image
    Graphics g;
    Image pic; 
    String who;
    ///////////////Level
    public Level level;
    ///////////////Animation Variables
    public int frame;
    public int frames;
    public int animCount;
    
    boolean side;
	boolean u;
	boolean d;
	boolean l;
	boolean r;
	public boolean attacking;
	private int range;
    
    public entity(int h, int d, int X, int Y, Image im)
    {
        health = h;
        damage = d;
        x = X;
        y = Y;
        pic = im;
    }

    //////////////////////////to lose health when player is attacked
    public void getHurt(int dam)
    {
        health -= dam;
    }
    /////////////////////////to gain health when player is healed
    public void getHealed(int heal)
    {
        health += heal;
    }
    
    public abstract void move(); 
    
    ////////////////////////////////////get methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public void drawEntity()
    {
    	if(!attacking)
    	{
    		if(l||r)
    		{
    			if(animCount < 60)
    				animCount++;
    			else
    				animCount = 10;
    			Image im = Art.getImageFromFile("entities/"+who+"/Frame"+animCount/10+".png");
    			Image flip = horizontalflip(im);
    			
    			if(l)
    			{
    				g.drawImage(flip, x, y,height-2,height-2, null);
    				g.drawImage(Art.getImageFromFile("entities/"+who+"/b/Frame"+animCount/10+".png"), x, y,height-2,height-2, null);
    			}
    			else if(r)
    				g.drawImage(Art.getImageFromFile("entities/"+who+"/Frame"+animCount/10+".png"), x, y,height-2,height-2, null);

    		}
    		if(!l && !r)
    				g.drawImage(Art.getImageFromFile("entities/"+who+"/Frame0.png"), x, y,height-2,height-2, null);	  
    	}
    	else
    	{
    			if(animCount < 50)
    				animCount++;
    			else
    				animCount = 10;
	    	
    			Image im = Art.getImageFromFile(" entities/"+who+"/swing/Frame"+animCount/10+".png");
    			Image flip = horizontalflip(im);
    			if(!side)
    				g.drawImage(flip, x, y,(int)(flip.getWidth(null)/(1.5)),(int) (flip.getHeight(null)/(1.5)), null);
    			else 
    				g.drawImage(im, x, y,(int)(im.getWidth(null)/(1.5)),(int) (im.getHeight(null)/(1.5)), null);
    	}
    }
    
    public static Image horizontalflip(Image img) {  
        int w = img.getWidth(null);  
        int h = img.getHeight(null);  
        Image dimg = new BufferedImage(w, h, ((BufferedImage) img).getType());  
        Graphics2D g = (Graphics2D) dimg.getGraphics();  
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);  
        g.dispose();  
        return dimg;  
    }  

	public boolean inRange(entity e) {

		if(x > e.x-range && x < e.x+range)
			if(y == e.y || ( y <= e.y+5 && y >= e.y-5) )
				return true;
		return false;
	}
	
    public int getHealth()
    {
        return health;
    }
    
    public int getDamage()
    {
        return damage;
    }
    
    public int getX()
    {
        return x;
    }
    
    public int getY()
    {
        return y;
    }
    
    public Image getImage()
    {
        return pic;
    }
    
    public void paint(Graphics gr)
    {
    	g = gr;
    	drawEntity();
    }

	public void sendKeys(boolean[] keys){}
}