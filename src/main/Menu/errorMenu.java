package main.Menu;

import java.awt.Graphics;
import java.awt.Image;

import main.ActualGame;
import main.Art;
import main.RPG;
import main.Sound;
import main.alphabet.*;

public class errorMenu extends Menu {

	public String[] options = {"Main Menu","Quit"};
	public String message;
	private int selected = 0;
	public Graphics g;
	public whiteLetters let;
	public Image[] imageOptions;
	public Font winsFont;
	
	public errorMenu(String error)
	{
		super();
		message = error;
		let = new whiteLetters();
		winsFont = new Font(Letters.toBufferedImage(Art.getImageFromFile("images/alphabetWhite.png")));
	}
	public errorMenu() {
		super();
		let = new whiteLetters();
		winsFont = new Font(Letters.toBufferedImage(Art.getImageFromFile("images/alphabetWhite.png")));
	}
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public void drawMenu()
	{
		g.drawImage(Art.getImageFromFile("images/menu/black.png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);
		
		winsFont.printLine(message, 40, RPG.WIDTH/10, RPG.HEIGHT/6, g);
		
	    for (int i=0; i<options.length; i++) {
			if(selected == i)
				g.drawImage(let.arrow, ( RPG.WIDTH/4 - 50 ), 301 + i*50,30,30,null);
			winsFont.printLine(options[i], 30, (RPG.WIDTH/4), 300 + i*50, g);
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
				aGame.setMenu(new titleMenu());
			}
			if (selected == 1) {
				System.exit(0);
			}
	}
	
	}
}
