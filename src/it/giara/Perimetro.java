package it.giara;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Perimetro
{
	private BufferedImage _input;
	public BufferedImage _infill;
	public BufferedImage _perimetro;
	GcodeManager gcode;
	
	public Perimetro(BufferedImage in, GcodeManager g)
	{
		_input = in;
		gcode = g;
		_infill = new BufferedImage(_input.getWidth(), _input.getHeight(), BufferedImage.TYPE_INT_ARGB);
		_perimetro = new BufferedImage(_input.getWidth(), _input.getHeight(), BufferedImage.TYPE_INT_ARGB);
		createWhiteImage(_infill);
		createWhiteImage(_perimetro);
	}
	
	public void generatePerimeter(int gray_level)
	{
		for (int y = 0; y < _input.getHeight(); y++)
		{
			for (int x = 0; x < _input.getWidth(); x++)
			{
				int rgb = _input.getRGB(x, y);
				int grayLevel = ColorUtils.RGB2Gry(rgb);
				
				if (grayLevel < gray_level)
				{
					int xA = (x + 1 >= _input.getWidth()) ? x : x + 1;
					int xB = (x - 1 < 0) ? x : x - 1;
					int yA = (y + 1 >= _input.getHeight()) ? y : y + 1;
					int yB = (y - 1 < 0) ? y : y - 1;
					
					int[] value = new int[4];
					value[0] = ColorUtils.RGB2Gry(_input.getRGB(xA, y));
					value[1] = ColorUtils.RGB2Gry(_input.getRGB(xB, y));
					value[2] = ColorUtils.RGB2Gry(_input.getRGB(x, yA));
					value[3] = ColorUtils.RGB2Gry(_input.getRGB(x, yB));
					
					boolean white_side = false;
					
					for (int gry : value)
					{
						if (gry >= gray_level)
						{
							white_side = true;
						}
						
					}
					
					if (white_side)
					{
						_perimetro.setRGB(x, y, Color.black.getRGB());
					}
					else
					{
						_infill.setRGB(x, y, Color.black.getRGB());
					}
				}
				
			}
		}
		
	}
	
	public void createPath()
	{
		for (int y = 0; y < _perimetro.getHeight(); y++)
		{
			for (int x = 0; x < _perimetro.getWidth(); x++)
			{
				int rgb = _perimetro.getRGB(x, y);
				
				if (rgb == Color.BLACK.getRGB())
				{
					gcode.up();
					gcode.move(x,  _perimetro.getHeight()-1-y);
					
					recursivePath(x, y, 0, _perimetro.getWidth(), _perimetro.getHeight());
					gcode.up();
				}
			}
			
		}
	}
	
	private void recursivePath(int x, int y, int deep, int width, int height)
	{
		System.out.println(deep);
		
		if (deep > 2048)
			return;
		
		_perimetro.setRGB(x, y, Color.RED.getRGB());
		_infill.setRGB(x, y, Color.RED.getRGB());
		
		for (int j = -1; j <= 1; j++)
		{
			for (int k = -1; k <= 1; k++)
			{
//				System.out.println("X: " + x + " Y: " + y + "J: " + x + j + " K: " + y + k);
				
				 if (j == 0 && k == 0)
				 continue;
				
				 if (x + j < 0 || y + k < 0)
				 continue;
				
				 if (x + j >= width || y + k >= height)
				 continue;
				
				if (_perimetro.getRGB(x + j, y + k) == Color.BLACK.getRGB())
				{
					gcode.move(x, height-1-y);
					gcode.down();
					gcode.move(x + j, height-1-(y + k));
					recursivePath(x + j, y + k, deep + 1, width, height);
					gcode.up();
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
