package main.alphabet;

import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import main.Art;
public class Font
{
    BufferedImage mainImage;
    String[] order = 
    	{"a","b","c","d","e","f","g","h",
    	 "i","j","k","l","m","n","o","p",
    	 "q","r","s","t","u","v","w","x",
    	 "y","z","!","?","'",",",">","<",
    	 "0","1","2","3","4","5","6","7",
    	 "8","9","-"
    	};
    ArrayList <Letter> letters = new ArrayList();
    int columns = 8;
    int imageSize = 6;
    public Font()
    {
        this.mainImage = Letters.toBufferedImage(Art.getImageFromFile("./images/alphabetWhiteNumbers.png"));
        
        BufferedImage tempImage = null;
        Letter tempLetter;
        int row = 0;
        int col = 0;
        for(String currentLetter : order)
        {
            tempImage = mainImage.getSubimage(col*imageSize,row*imageSize,imageSize,imageSize);
            tempLetter = new Letter(currentLetter,tempImage);
            letters.add(tempLetter);
            col++;
            if(col >= columns)
            {
                col = 0;
                row++;
            }
        }        
    }
    public Font(BufferedImage mainImage)
    {
        this.mainImage = mainImage;
        BufferedImage tempImage = null;
        Letter tempLetter;
        int row = 0;
        int col = 0;
        for(String currentLetter : order)
        {
            tempImage = mainImage.getSubimage(col*imageSize,row*imageSize,imageSize,imageSize);
            tempLetter = new Letter(currentLetter,tempImage);
            letters.add(tempLetter);
            col++;
            if(col >= columns)
            {
                col = 0;
                row++;
            }
        }        
    }
    public void printLine(String s,int textSize,int xPos,int yPos,Graphics g)
    {
        String tempString;
        Letter tempLetter;
        for(int i = 0; i < s.length(); i++)
        {
            tempString = s.substring(i,i+1);
            for(int x = 0; x < letters.size(); x++)
            {
                tempLetter = letters.get(x);
                if(tempString.equalsIgnoreCase(tempLetter.ID))
                {
                    g.drawImage(tempLetter.letterValue,xPos+(i*textSize),yPos,textSize,textSize,null);
                    break;
                }
            }
        }
    }
}
