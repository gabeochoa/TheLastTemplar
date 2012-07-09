package main.Menu;

import java.awt.Graphics;
import main.ActualGame;

public class Menu {

	public int selectedOption = 0;

	public Graphics g;
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawMenu();
	}
	public void drawMenu(){}
	
	public void tick(ActualGame aGame, boolean up, boolean down, boolean left, boolean right, boolean select)
	{
		
	}
	
}
