package main;

import java.awt.Graphics;
import java.awt.Image;

public class Area {

	AreaEnum ae;
	Graphics g;
	Image background;
	
	public Area(AreaEnum a)
	{
		a = ae;
		String fileName = AreaEnum.getString(ae);
		background = Art.getImageFromFile(""+fileName+".png");
	}
	
}