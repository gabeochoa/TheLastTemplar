package main.Menu;

import java.awt.Graphics;
import java.awt.Image;

import main.ActualGame;
import main.Art;
import main.RPG;
import main.Sound;
import main.alphabet.*;

public class pauseMenu extends Menu {

	public String[] options = {"Abort","Main Menu","Resume"};
	public String message;
	private int selected = 0;
	public Graphics g;
	public whiteLetters let;
	public Image[] imageOptions;
	public Font winsFont;

	public pauseMenu() {
		super();
		let = new whiteLetters();
		winsFont = new Font();
	}
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public void drawMenu()
	{
		g.drawImage(Art.getImageFromFile("images/menu/black.png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);
	    
	    for (int i=0; i<options.length; i++) {
			if(selected == i)
			g.drawImage(let.arrow, ( RPG.WIDTH/4 - 50 ), 301 + i*50,50,50,null);
			winsFont.printLine(options[i], 40, (RPG.WIDTH/4), 300 + i*50, g);
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
				System.exit(0);
			}
			if (selected == 1) {
				aGame.setMenu(new titleMenu(false));
			}
			if (selected == 2) {
				aGame.setMenu(null);
			}
	}
	
	}
}