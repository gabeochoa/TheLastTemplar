package main;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

public class BackgroundImage {

	Image staticImage;
	ImageIcon moving;
	Graphics g;
	
	
	public BackgroundImage()
	{
		
	}
	
	public void drawBackground(Graphics gr,int x)
	{
		g = gr;
		
		g.drawImage(staticImage,0,0,null);
    	g.drawImage(moving.getImage(),0-(x/2),0,null);
	}
	
	
	
}
