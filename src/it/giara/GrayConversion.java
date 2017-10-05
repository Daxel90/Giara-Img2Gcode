package it.giara;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.Random;

public class GrayConversion
{
	public BufferedImage _input;
	public BufferedImage _output;
	
	private Random random = new Random(); 
	
	public GrayConversion(BufferedImage image)
	{	
		_input = image;
		_output = new BufferedImage(_input.getWidth(), _input.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		createWhiteImage(_output);
	}
	
	public void convertGray(int blackLimit, int whiteLimit)
	{
		for (int y = 0; y < _input.getHeight(); y++)
		{
			for (int x = 0; x < _input.getWidth(); x++)
			{
				int r = random.nextInt(255);
				int gray = ColorUtils.RGB2Gry(_input.getRGB(x, y));
				if(gray <blackLimit)
				{
					_output.setRGB(x, y, Color.BLACK.getRGB());
				}
				if(gray > whiteLimit)
				{
					
				}
				else if(gray/2 < r/2)
				{
					_output.setRGB(x, y, Color.BLACK.getRGB());
				}
				
				
			}
		}
	}
	
	private void createWhiteImage(BufferedImage img)
	{
		for (int y = 0; y < img.getHeight(); y++)
		{
			for (int x = 0; x < img.getWidth(); x++)
			{
				img.setRGB(x, y, Color.WHITE.getRGB());
			}
		}
	}
}
