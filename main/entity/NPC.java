package main.entity;

import java.awt.Image;

public class NPC extends entity{
	
	public String[] dialogue;
	public String[] choices;
	
	public NPC(int h, int d, int X, int Y, Image im) {
		super(h, d, X, Y, im);
	}	

	public String getDialougue(int i)
	{
		return dialogue[i];
	}
	public String getPlayerSpeech(int i)
	{
		return choices[i];
	}

	@Override
	public void move() {
		
	}
	

}
