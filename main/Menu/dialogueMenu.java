package main.Menu;

import java.awt.Color;
import java.awt.Graphics;

import main.ActualGame;
import main.RPG;
import main.Sound;

public class dialogueMenu extends Menu {

	public String[] options;
	public String message;
	private int selected = 0;
	
	public Graphics g;
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public void drawMenu()
	{
		g.setColor(new Color(0,0,0,150));
		g.fillRect(0, 0, RPG.WIDTH, RPG.HEIGHT);

		g.setColor(Color.white);

		for (int i=0; i<options.length; i++) {
			message = options[i];
			if(selected == i)
				message = "-> "+message;
			g.drawString(message, 40,60+i*10);
		}
		
	}
	
	public void tick(ActualGame aGame, boolean up, boolean down, boolean left, boolean right, boolean select)
	{
		
		if (up || down) 
			Sound.moveSelection.play();
		if (up) 
			selected--;
		if (down)
			selected++;
		if (selected < 0) 
			selected  = 0;
		if (selected >= options.length)
			selected = options.length - 1;
		if (select) {
			Sound.select.play();
			if (selected == 0) {
				aGame.setMenu(null);
				aGame.newGame();
			}
			if (selected == 1) {
				aGame.setMenu(new instructionsMenu());
			}
			if (selected == 2) {
				aGame.setMenu(new aboutMenu());
			}
	}
	
	}
}
