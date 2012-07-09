
package main.entity;
import java.awt.*;

/**
 * Write a description of class Priest here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Priest extends NPC
{
    public String[] dialogue = new String [4];
    public String[] choices = new String[4];
    
public Priest(int h, int d, int X, int Y, Image im) 
    {
        super(h, d, X, Y, im);
        
        dialogue[0] = "I knew that the gods would send one of their agents to save us."; 
        dialogue[1] = "We must have faith, the gods help those that help themselves.";
        dialogue[2] = "Steel is fine here, but what about the afterlife?";;
        dialogue[3] = "If it is not, what is it?";
        
        choices[0] = "Farewell priest, may the gods protect";
        choices[1] = "It is hard to believe that the gods have any care for us in such a dark time."; 
        choices[2] = "The only thing I have faith in this time is steel.";
        choices[3] = "You believe that this is the end of days?";
        

        
}
}