package main.alphabet;

import java.awt.image.BufferedImage;


public class Letter
{
    BufferedImage letterValue;
    String ID;
    public Letter(String ID, BufferedImage letterValue)
    {
        this.ID = ID;
        this.letterValue = letterValue;
    }
}