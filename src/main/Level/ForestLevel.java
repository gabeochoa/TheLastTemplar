package main.Level;
import java.awt.*;

import main.Art;
import main.RPG;
import main.entity.entity;
import main.entity.regZombie;

public class ForestLevel extends Level {

	Graphics g;
	
	public ForestLevel()
	{
		super();
		xSpawn = 30;
		ySpawn = RPG.HEIGHT - 30;
		name = "Forest";
	}
	public void tick(Graphics gr)
	{
		g = gr;
		super.tick(g);
		//spawnEntities();

		if(entities.size() <7){
			test = new regZombie();
			this.addEntity(test);
			test.level = this;
			test.move();
		}
	}
	


	public void paint(Graphics gr)
	{
		g = gr;
		super.paint(gr);
	}

	public void switchLevel(int id) {
		if (id == 2) aGame.switchLevel("StonePath", 2);
	}

	
}
