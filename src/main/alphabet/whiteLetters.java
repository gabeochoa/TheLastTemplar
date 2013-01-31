package main.alphabet;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import main.Art;

public class whiteLetters extends Letters{

	public Image alphabet;
	public BufferedImage bufferedAlpha;
	public Image arrow;
	public Image[][] letter;
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
			"YZeqacgl",
			"→hns_okpt"
		};
	//Lower case letters
	
	/*e = Exclaimation
	  q = Question
	  a = Apostrophe
	  c = Comma
	  g = >
	  l = <
	  h = Hyphen
	  n = coloN
	  s = Semicolon
	  _ = space
	  o = quOte
	  k = asterisK
	  p = amPersand
	  t = caroT
	*/
	
	//5x5
	//5rx8c
	//49x31
	public whiteLetters()
	{
		alphabet = Art.getImageFromFile("alphabetOrange.png");
		bufferedAlpha = Letters.toBufferedImage(alphabet);
		letter = splitImage(bufferedAlpha,5,8);
		arrow = Art.getImageFromFile("alpha/arrowWhite.png");
	}
	public Image[][] getLetters(){return letter;}
	/*
	 
	 if i input a  it should return A which is letter[0][0]
	 
	 
	 */
	public Image getImageForLetter(String string)
	{
		/*string.toLowerCase();
		
		if(string.equals("!"))
			return Art.getImageFromFile("./images/alpha/exclamation.png");
		if(string.equals("?"))	
			return Art.getImageFromFile("./images/alpha/question.png");
		if(string.equals("\'"))
			return Art.getImageFromFile("./images/alpha/dash.png");
		if(string.equals(":"))
			return Art.getImageFromFile("./images/alpha/colon.png");
		if(string.equals(";"))
			return Art.getImageFromFile("./images/alpha/semiColon.png");
		if(string.equals("\""))
			return Art.getImageFromFile("./images/alpha/quotes.png");
		if(string.equals("'"))
			return Art.getImageFromFile("./images/alpha/apostraphe.png");

		return Art.getImageFromFile("./images/alpha/"+string+".png");
	}*/
	
		for (int i = 0; i < strLets.length; i++)
		{
				if(strLets[i].indexOf(string) != -1)
				{	
					for (int j = 0; j < strLets[i].length()-2; j++)
					{
						String s = strLets[i].substring(j,j+1);
						System.out.println(""+s+"  i = "+i+"    j="+j);
						if(s.equals(string))
						return letter[i][j];
					}
				}
			}
		return null;
	}
	public Image[] getImagesForSentence(String string)
	{
		string = string.toUpperCase();
		
		Image[] imgA = new Image[string.length()];
		for(int i=0; i<imgA.length; i++)
		{
			imgA[i] = getImageForLetter(string.substring(i, i+1));
		}
		return imgA;
	}
	
	public Image[][] splitImage(BufferedImage img, int cols, int rows) { 
		
		int w = img.getWidth()/rows;
		int h = img.getHeight()/cols;
		
		BufferedImage imgs[][] = new BufferedImage[w][h]; 
		
		for(int i=0; i<imgs.length; i+=1)
			for(int j=0; j<imgs[i].length-3; j+=1)
				imgs[i][j] =  img.getSubimage(i*6, j*6, 7, 7);
		
		return (Image[][]) imgs; 
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
