package main;

public enum AreaEnum {

	Forest, ForestNight, Castle, CastleNight;
	
	public static SoundEffect getSoundFromArea(AreaEnum a)
	{
		switch(a)
		{
		case Forest:
			return SoundEffect.Forest;
		case ForestNight:
			return SoundEffect.ForestNight;
		case Castle:
			return SoundEffect.Castle;
		case CastleNight:
			return SoundEffect.CastleNight;
		}
		
		return null;
	}
	public static String getString(AreaEnum ae)
	{
		switch(ae)
		{
		case Forest:
			return "Forest";
		case ForestNight:
			return "ForestNight";
		case Castle:
			return "Castle";
		case CastleNight:
			return "CastleNight";
		
		}
		return null;
	}
}
