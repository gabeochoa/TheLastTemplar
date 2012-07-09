package main.entity;

import java.awt.Color;

public enum Item {
	none(-1,Color.WHITE, "", ""), 
	Rock(0, Color.GRAY, "ROCK","some rock!"); 
	
	public final int icon;
	public final Color color;
	public final String name;
	public final String description;
	
	private Item(int icon, Color color, String name, String description) {
		this.icon = icon;
		this.color = color;
		this.name = name;
		this.description = description;
	}

}
