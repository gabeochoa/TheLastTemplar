package main;

import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class Art {
	
	
	public static Image getImageFromFile(String file)
	{
		try{
			File im = new File(file);
			Image title = ImageIO.read(im);
			return title;
    }catch(Exception e){e.printStackTrace();}
		return null;
	}
}