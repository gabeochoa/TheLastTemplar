package main.Menu;

import java.awt.Color;
import java.awt.Graphics;

import main.ActualGame;
import main.Art;
import main.RPG;
import main.Sound;
import main.alphabet.Font;
import main.alphabet.Letters;

public class instructionsMenu extends Menu {

	public int delayTicks = 60;
	public Font winsFont;
	public Graphics g;
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public instructionsMenu()
	{
		super();
		winsFont = new Font();
	}
	public void drawMenu()
	{
		g.drawImage(Art.getImageFromFile("./images/menu/black.png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);
		g.setColor(Color.white);
		//g.drawString("Instructions", 40, 120);
		
		String[] lines = {
				"Use W,A,S,D or arrow",
				"keys to move",
				"and space to attack",
				"-------------------",
				"Try to survive as long",
				"as you can....",
		};
		
		for (int i=0; i<lines.length; i++) {
			winsFont.printLine(lines[i], 40, 50, 150+i*75, g);
		}
		
		if (delayTicks == 0)
			winsFont.printLine("-> Continue", 40, 50, 150+(lines.length+1)*75, g);
			
	}
	
	public void tick(ActualGame aGame, boolean up, boolean down, boolean left, boolean right, boolean select)
	{
		if (delayTicks > 0)
			delayTicks--;
		else if (select) {
			Sound.select.play();
			aGame.setMenu(new titleMenu(false));
		}
		
		
	}
	
}
