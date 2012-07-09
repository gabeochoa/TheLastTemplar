package main.entity;

import java.awt.Image;

/**
 * Write a description of class Peasant here.
 * Peasant and Dialogue
 * @Justin Maguire
 * @Alpha
 */
public class Peasant extends NPC
{

	public String[] dialogue = new String [5];
	public String[] choices = new String[4];
	
public Peasant(int h, int d, int X, int Y, Image im) 
    {
        super(h, d, X, Y, im);
        dialogue[0] = "Thank you for coming my lord, if it was not for you we would never live to survive this day.";
        dialogue[1] = "I was entering the gate to sell my produce at the market, when all this screaming started.";
        dialogue[2] = "The guards were able to hold them off while I fled into the cellar of a generous townsperson.";
        dialogue[3] = "I live on the outskirts of the town as a farmer with my sons and wife... I hope they survived.";
        
        choices [0] = "I must go peasant, have faith.";
        choices [1] = "How were you able to survive during the massacre?";
        choices [2] = "Where do you live?";
        choices [3] = "Where were you when this disaster happened?";
        

    }    
 
    public String[] getDialogue()
    {
        return dialogue;
    }
 
    public String[] getChoices()
    {
        return choices;
    }
}
