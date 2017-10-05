package it.giara;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Png2Gcode
{
	static float penSize = 1f;
	static float PixelSize = 0.2f;
	static float upPosition = 1f;
	static float downPosition = -0.01f;
	
	public static void main(String[] arg)
	{
		File output = new File(".", "deathnote.gcode");
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(new File("deathnote.png"));
		} catch (IOException e)
		{}
		
		GrayConversion conv = new GrayConversion(img);
		conv.convertGray(195,240);
		saveImg("gray.png", conv._output);
		
		GcodeManager gcode = new GcodeManager(output, upPosition, downPosition, PixelSize);
		
		Perimetro per = new Perimetro(img, gcode);
		
		per.generatePerimeter(200);
		
		per.createPath();
		
		saveImg("infill.png", per._infill);
		saveImg("perimetro.png", per._perimetro);
		
		Infill infill = new Infill(per._infill, gcode);
		infill.createPath();
		
	}
	
	private static void saveImg(String name, BufferedImage img)
	{
		File infillF = new File(name);
		try
		{
			ImageIO.write(img, "png", infillF);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
