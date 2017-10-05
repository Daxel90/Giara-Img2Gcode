package it.giara;

public class ColorUtils
{	
	public static int RGB2Gry(int rgb)
	{
		int r = (rgb >> 16) & 0xFF;
		int g = (rgb >> 8) & 0xFF;
		int b = (rgb & 0xFF);
		
		return (r + g + b) / 3;
	}
}
