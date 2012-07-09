package main.entity;
import java.awt.*;

/**
 * Write a description of class Wizard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Wizard extends NPC
{

    public String[] dialogue = new String [5];
	public String[] choices = new String[4];

public Wizard(int h, int d, int X, int Y, Image im) 
    {
        super(h, d, X, Y, im);
        
        dialogue[0] = "At least someone here has some form of competence.";
        dialogue[1] = "Of course this plague is as much of a spell as it is a disease.";
        dialogue[2] = "Whoever created this disease is much more powerful than I, this spell is awfully sophisticated.";
        dialogue[3] = "It does not seem they are here to control the city… I suspect something more malevolent."; 

        
        choices[0] = "I will get to the bottom of this.";
        choices[1] = "I assume this is no natural malady.";
        choices[2] = "What is the source of this disease?";
        choices[3] = "Do you see any purpose for such devastation?";

        
        

        
}
}
