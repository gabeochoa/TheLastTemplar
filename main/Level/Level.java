package main.Level;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.*;
import java.util.*;

import main.ActualGame;
import main.Art;
import main.RPG;
import main.Menu.errorMenu;
import main.Menu.gameOverMenu;
import main.entity.*;

public abstract class Level
{
	Image background, healthFrame, clockFrame, clockBackground;
	ArrayList<entity> entities = new ArrayList<entity>();
	Graphics g;
	Graphics2D gClockBackground;
	entity test;
	public Player player;
	public int xSpawn;
	public int ySpawn;
	protected ActualGame aGame;
	public String name = "";
	public double clockAngle = 1;
	public int rbounds, lbounds;
	
	public Level()
	{
		healthFrame = Art.getImageFromFile("./images/HUD/healthBar.png");
		clockFrame = Art.getImageFromFile("./images/HUD/clockOutline.png");
		clockBackground = Art.getImageFromFile("./images/HUD/clockBackground.png");
		gClockBackground = (Graphics2D) clockBackground.getGraphics();
		rbounds = 768;
		lbounds = 0;
	}
	
	public void tick(Graphics gr)
	{
		g = gr;
		collision();	
		checkHealth();
		updateMovement();
		
		//drawBackground();
		drawEntities();
		//winClock();

		drawHUD();
	}
	
	private void checkHealth() {
	
		for(int i=0; i<entities.size(); i++)
		{
			entity e = entities.get(i);
			
			if(e.health <= 0 && !(e instanceof Player))
				entities.remove(e);
			else if(e.health <= 0 && (e instanceof Player))
			{
				aGame.setMenu(new gameOverMenu(player));
			}
		}
	}

	private void drawHUD() 
	{
		g.drawImage(healthFrame, 0, 0,200,50,null);
		g.setColor(Color.red);
		g.fillRect(0, 0, player.health, 50);
		
	}
	public void winClock()
	{
		clockAngle++;
		//Image temp = Art.getImageFromFile("./images/HUD/blank.png");
		//Graphics2D gTemp = (Graphics2D) temp.getGraphics();
		/*gClockBackground.translate(150/2,150/2);
		gClockBackground.rotate(Math.toRadians( (double) clockAngle));
		gClockBackground.translate((150/2)*-1,(150/2)*-1);
		gClockBackground.drawImage(clockBackground, 50, 50,null);*/
		
		gClockBackground.rotate(Math.toRadians( (double) clockAngle),150/2,150/2);
		gClockBackground.drawImage(clockBackground, 0, 0,null);
		//gTemp.rotate(Math.toRadians( (double) clockAngle),150/2,150/2);
		
		gClockBackground.rotate(Math.toRadians( (double) clockAngle*-1),150/2,150/2);
		g.drawImage(clockBackground, 50, 50,null);
	}


    public Image imageRotator(Image toBeRotated, double angle, int imageWidth, int imageHeight)
	{
		Image temp = Art.getImageFromFile("./images/HUD/blank.png");// = createImage(imageWidth,imageHeight);
		Graphics2D temp2D = (Graphics2D) temp.getGraphics();
		temp2D.translate(imageWidth/2,imageHeight/2);
		temp2D.rotate(Math.toRadians(angle));
		temp2D.translate(-(imageWidth/2),-(imageHeight/2));
		temp2D.drawImage(toBeRotated,0,0,null);
		return temp;
	}


	
	void drawBackground() 
	{
		g.drawImage(Art.getImageFromFile("./images/level/"+name+".png"),0, 0, RPG.WIDTH, RPG.HEIGHT,null);
	}	


	private void collision() {
		
			for(entity e: entities)
				if( e instanceof regZombie || e instanceof slowZombie)
						if(player.inRange((enemy) e))
						{ 
							if(player.attacking)
							e.getHurt(player.damage);
							else
							player.getHurt(e.damage);	
						}
		
	}

	private void updateMovement() {
		for(entity e: entities)
		{
			e.move();
			if( e instanceof regZombie || e instanceof slowZombie)
				((enemy)e).moveToX(player.x);
		}
		
	}

	
	public void drawEntities() {
		for(entity e: entities)
			e.paint(g);
	}

	public void spawnEntities()
	{
		if(entities.size() > 7)
			return;
		/*
		Random random = new Random();
		
		switch(random.nextInt(5))
		{		
		case 1:
			test = new slowZombie();
			break;
		case 2:
			test = new regZombie();
			break;
		case 3:
			test = new regZombie();
			break;
		default:
			return;
		}*/
		test = new regZombie();
		this.addEntity(test);
		test.level = this;
		test.move();
	}
	
	public void addEntity(entity p)
	{
		entities.add(p);
	}
	public void removeEntites(entity p)
	{
		entities.remove(p);
	}
	
	public void paint(Graphics gr)
	{
		g = gr;
		drawBackground();
		drawEntities();
		drawHUD();
	}
	
	public static Level loadLevel(ActualGame aGame, String name)
	{
		Level requested = getLevelByName(name);
		if(requested != null)
		{
			requested.init(aGame, name, Art.getImageFromFile("./images/level/"+name+".png"));
			return requested;
		}
		else
			aGame.setMenu(new errorMenu("Failed to Load Level"));
			
		return null;
	}

	private void init(ActualGame aGame, String name, Image imageFromFile) {
		this.aGame = aGame;
		player = aGame.player;
		background = imageFromFile;
	}
	private static Level getLevelByName(String name)
	{
	 try{
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
				return (Level) Class.forName("main.Level." + name + "Level").newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
	}
	public void switchLevel(int id)
	{
		
	}
}