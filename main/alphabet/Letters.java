package main.alphabet;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.Art;

public class Letters {

	public Image alphabet;
	public BufferedImage bufferedAlpha;
	public Image arrow;
	public Image[][] letter = new Image[5][8];
	private static final String chars = "" + //
			"ABCDEFGH"+
			"IJKLMNOP"+
			"QRSTUVWX"+
			"YZ!?',><"+
			"→-:; \"*&^";
	private static final String[] strLets =
		{
			"ABCDEFGH",
			"IJKLMNOP",
			"QRSTUVWX",
			"YZ!?',><",
			"→-:; \"*&^"
		};

	//5x5
	//5rx8c
	//49x31
	public Letters()
	{
		alphabet = Art.getImageFromFile("./images/alphabet.png");
		bufferedAlpha = Letters.toBufferedImage(alphabet);
		letter = splitImage(bufferedAlpha,8,5);
		arrow = Art.getImageFromFile("./images/alpha/arrowWhite.png");
	}
	
	public Image getImageForLetter(String string)
	{
		for (int i = 0; i < strLets.length; i++)
			for (int j = 0; j < strLets[i].length(); j++)
			{
				if(strLets[i].indexOf(string) != -1)
					return letter[i][j];
			}
		return null;
	}
	public Image[] getImagesForSentence(String string)
	{
		String[] strA = new String[string.length()];
		for(int i=0; i<strA.length; i++)
			strA[i] = string.substring(i, i+1);
		
		Image[] imgA = new Image[string.length()];
		for(int i=0; i<imgA.length; i++)
			imgA[i] = getImageForLetter(strA[i]);
		
		return imgA;
	}
	
	public Image[][] splitImage(BufferedImage img, int cols, int rows) { 
		
		int w = img.getWidth()/rows;
		int h = img.getHeight()/cols;
		
		Image imgs[][] = new Image[w][h]; 
		
		for(int i=0; i<imgs.length; i++)
			for(int j=0; j<imgs[i].length; j++)
				imgs[i][j] = (Image) img.getSubimage(i*5, j*5, 5,5);
		
		return imgs; 
		}
	
	public static Image toImage(BufferedImage bufferedImage) {
	    return Toolkit.getDefaultToolkit().createImage(bufferedImage.getSource());
	}
	
	
	public static BufferedImage toBufferedImage(Image image) {
	    if (image instanceof BufferedImage) {
	        return (BufferedImage)image;
	    }

	    // This code ensures that all the pixels in the image are loaded
	    image = new ImageIcon(image).getImage();
	    // Determine if the image has transparent pixels; for this method's
	    // implementation, see Determining If an Image Has Transparent Pixels

	    // Create a buffered image with a format that's compatible with the screen
	    BufferedImage bimage = null;
	    GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	    try {
	        // Determine the type of transparency of the new buffered image
	        int transparency = Transparency.BITMASK;
	       
	        // Create the buffered image
	        GraphicsDevice gs = ge.getDefaultScreenDevice();
	        GraphicsConfiguration gc = gs.getDefaultConfiguration();
	        bimage = gc.createCompatibleImage(
	            image.getWidth(null), image.getHeight(null), transparency);
	    } catch (HeadlessException e) {
	        // The system does not have a screen
	    }

	    return bimage;
	}
}
