/**
 * 
 */
package main;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import main.Level.*;
import main.Menu.*;
import main.entity.*;

/**
 * @author Gabe
 *@version added in Alpha 1.2
 *@current 1.2
 */
public class ActualGame {

	public int time;
	public Level level;
	public Player player;
	public int pauseTime;
	public Menu menu;
	private Graphics g;
	private boolean first;
	private boolean start;

	public ActualGame() {
		setMenu(new titleMenu(true));
	}
	public ActualGame(Graphics gr) {
		setMenu(new titleMenu(true));
		g = gr;
	}

	public void newGame() {
		first = start = true;
		pauseTime = 1000;
        RPG.startTime = System.currentTimeMillis();
        
		level = Level.loadLevel(this, "forest");
		player = new Player();
		player.level = level;
		level.player = player;
	    player.x = level.xSpawn;
	    player.y = level.ySpawn;
	    level.addEntity(player);
	    level.addEntity(new regZombie());
	}

	public void switchLevel(String name, int id) {
		first = false;
		pauseTime = 50;
		level = Level.loadLevel(this, name);
		level.player = player;
		if(id ==2)
		player.x = level.xSpawn;
		else
		player.x = 768;
		
		player.level = level;
		level.addEntity(player);
	}


	public void tick(boolean[] keys) {
		if (pauseTime > 0) {
			if(first)
			drawCutScene();
			else
			drawLevelTitle(level.name);
			pauseTime--;
			return;
		}

		time++;
		boolean up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_NUMPAD8];
		boolean down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_NUMPAD2];
		boolean left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT] ;
		boolean right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];

		boolean use = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_ENTER];

		if (keys[KeyEvent.VK_ESCAPE]) {
			keys[KeyEvent.VK_ESCAPE] = false;
			if (menu == null) {
				setMenu(new pauseMenu());
			}
		}

		if (use) {
			keys[KeyEvent.VK_SPACE] = false;
		}

		if (menu != null) {
			keys[KeyEvent.VK_W] = keys[KeyEvent.VK_UP] = keys[KeyEvent.VK_NUMPAD8] = false;
			keys[KeyEvent.VK_S] = keys[KeyEvent.VK_DOWN] = keys[KeyEvent.VK_NUMPAD2] = false;
			keys[KeyEvent.VK_A] = false;
			keys[KeyEvent.VK_D] = false;

			menu.tick(this, up, down, left, right, use);
		}
		else {
			player.tick(up, down, left, right);
			if (use) {
				player.action();
			}
			if(player.health < 250)
			player.getHealed(2);
			
			level.tick(g);
			level.paint(g);

			checkBounds();
		}
		if(menu != null)
		menu.paint(g);
	}


	private void drawCutScene() {

		ImageIcon icon = new ImageIcon("./images/Introgif.gif"); 
	    g.drawImage(icon.getImage(),0,0,RPG.WIDTH,RPG.HEIGHT,null);
	    if(start)
		{
			start = false;
			Sound.cutScene.play();
		}
	}
	public void drawLevelTitle(String name)
	{
		g.drawImage(Art.getImageFromFile("./images/level-plates/"+name+".png"), 0,0,null);
	}
	
private void checkBounds() {
	//true is right
	if(player.x < -50)
		level.switchLevel(0);
	if(player.x > 768+100)
		level.switchLevel(2);
	}

	public void getLoot(Item item) {
		player.addLoot(item);
	}

	public void win(Player player) {
		setMenu(new winMenu(player));
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public void lose(Player player) {
		setMenu(new gameOverMenu(player));
	}

	public void initializeGame() {
		// TODO Auto-generated method stub
		
	}
}
