package main.Menu;

import java.awt.*;

import main.ActualGame;
import main.Art;
import main.RPG;
import main.Sound;
import main.alphabet.Font;
import main.alphabet.Letters;
import main.alphabet.whiteLetters;

public class aboutMenu extends Menu {

	public int delayTicks = 60;
	public Graphics g;
	public Font winsFont;
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public aboutMenu()
	{
		super();
		winsFont = new Font();
	}
	
	public void drawMenu()
	{
		g.drawImage(Art.getImageFromFile("images/menu/black.png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);

		g.setColor(Color.white);
		//g.setFont(new Font("Times New Roman", 10, 100));
		
		
		String title = "The Last Templar ";
		String[] lines = {
				//innapropiate black man in a country club
				"",
				"",
				"by Gabe, Justin, Tom,",
				"Max, and Micheal.",
				"Made May-June for the",
				"2012 SEE Project.",
				"",
		};

		winsFont.printLine(title, 60, 50, 135, g);
		
		for (int i=0; i<lines.length; i++) {
			winsFont.printLine(lines[i], 40, 50, 150+i*75, g);
		}
		
		if (delayTicks == 0)
			winsFont.printLine("-> Continue", 40, 50, 600,g);
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
