package main.Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

import main.ActualGame;
import main.Art;
import main.RPG;
import main.Sound;
import main.alphabet.*;

public class titleMenu extends Menu {

	public String[] options = {"New Game","Instructions","About","Quit"};
	public boolean start = true;
	public String message;
	private int selected = 0;
	public Graphics g;
	public whiteLetters let;
	public Image[] imageOptions;
	public Font winsFont;
	
	public titleMenu(boolean s)
	{
		super();
		start = s;
		let = new whiteLetters();
		winsFont = new Font();
	}
	public titleMenu() {
		super();
		start = false;
		winsFont = new Font();
	}
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public void drawMenu()
	{
		g.drawImage(Art.getImageFromFile("menu/black.png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);
		
		ClassLoader cldr = this.getClass().getClassLoader();

		java.net.URL imageURL   = cldr.getResource("menu/Menu.gif");
		//ImageIcon aceOfDiamonds = new ImageIcon(imageURL);
		
		ImageIcon icon = new ImageIcon(imageURL);//new ImageIcon("menu/Menu.gif"); 
	    g.drawImage(icon.getImage(),0,0,RPG.WIDTH,RPG.HEIGHT,null);
	    
		winsFont.printLine("The Last Templar", 60, 40, 620, g);
		
	    
	    for (int i=0; i<options.length; i++) {
			message = options[i];
			if(selected == i)
				g.drawImage(let.arrow, ( RPG.WIDTH/4 - 50 ), 301 + i*50,50,50,null);
			winsFont.printLine(message, 40, (RPG.WIDTH/4), 300 + i*50, g);
		}
	
	
	}
	public void tick(ActualGame aGame, boolean up, boolean down, boolean left, boolean right, boolean select)
	{
		if(start)
		{
			start = false;
			Sound.mainMenu.play();
		}
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
				Sound.mainMenu.stop();
				aGame.setMenu(null);
				aGame.newGame();
			}
			if (selected == 1) {
				aGame.setMenu(new instructionsMenu());
			}
			if (selected == 2) {
				aGame.setMenu(new aboutMenu());
			}
			if (selected == 3) {
				System.exit(0);
			}
	}
	
	}
}
