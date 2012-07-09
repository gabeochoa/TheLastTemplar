package main.Menu;

import java.awt.*;

import main.ActualGame;
import main.Art;
import main.RPG;
import main.Sound;
import main.alphabet.Font;
import main.alphabet.Letters;
import main.alphabet.whiteLetters;
import main.entity.*;
public class gameOverMenu extends Menu {

	public int delayTicks = 60;
	public Graphics g;
	public Font winsFont;
	public Player play;
	public int timeMade;
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public gameOverMenu()
	{
		super();
		winsFont = new Font();
		timeMade = RPG.getTimePassed();
	}
	
	public gameOverMenu(Player p)
	{
		super();
		winsFont = new Font();
		timeMade = RPG.getTimePassed();
		
	}
	
	public void drawMenu()
	{
		g.drawImage(Art.getImageFromFile("./images/menu/black.png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);

		g.setColor(Color.white);
		
		
		
		String[] lines = {
				"The Templars",
				"Are No More", //in a country club
				"",
				"Game Over",
				"",
		};
		for (int i=0; i<lines.length; i++) {
			winsFont.printLine(lines[i], 40, 50, 150+i*75, g);
		}
		String s = "You survived for "+(timeMade-16)+" seconds";
		winsFont.printLine(s, 30, 50, 150+(lines.length)*75, g);
		
		if (delayTicks == 0)
			winsFont.printLine("-> Continue", 40, 50, 150+(lines.length+2)*75 ,g);
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
