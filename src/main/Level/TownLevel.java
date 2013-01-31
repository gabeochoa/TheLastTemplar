package main.Level;

import java.awt.Graphics;

import main.Art;
import main.RPG;

public class TownLevel extends Level {
	
Graphics g;
	
	public TownLevel()
	{
		super();
		xSpawn = 30;
		ySpawn = RPG.HEIGHT - 30;
		name = "Town";
	}
	public void tick(Graphics gr)
	{
		g = gr;
		super.tick(g);
	}
	
	
	void drawBackground() 
	{
		g.drawImage(Art.getImageFromFile("./images/level/"+name+".png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);
	}	

	public void paint(Graphics gr)
	{
		g = gr;
		drawBackground();
		super.paint(gr);
	}

	public void switchLevel(int id) {
		if (id == 0) aGame.switchLevel("StonePath", 0);
		//if (id == 2) aGame.switchLevel("Forest", 2);
	}

	
}

