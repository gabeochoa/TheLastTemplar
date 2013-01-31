package main.entity;
import java.awt.*;

/**
 * Write a description of class Guard here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Guard extends NPC
{
    public String[] dialogue = new String [5];
    public String[] choices = new String[5];
    
public Guard(int h, int d, int X, int Y, Image im) 
    {
        super(h, d, X, Y, im);
       
        dialogue[0] = "You are the only beacon of light here my lord, the situation was bordering on helpless.";
        dialogue[1] = "If we did not lose so many guards to the initial assault, including the captain we would have a chance.";
        dialogue[2] = "That is the true tragedy here, more guards were concerned with buying the civilians time to escape."; 
        dialogue[3] = "The Captain of the Guard designated me and the others in charge of ensuring the civilians safety.";
        dialogue[4] = "It would have been a noble death worthy of song.";

        choices[0] = "I must be leaving now, stay strong";
        choices[1] = "You do not believe you would have survived much longer?";
        choices[2] = "Did you lose many to the plague itself?";
        choices[3] = "I know of the captain's last stand, how did you survive then?";
        choices[4] = "You blame yourself for not dying with them don’t you?";


        
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
