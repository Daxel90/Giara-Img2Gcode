package it.giara;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import it.giara.gui.MainFrame;

public class Png2Gcode
{
	static float penSize = 1f;
	static float PixelSize = 0.2f;
	static float upPosition = 1f;
	static float downPosition = -0.01f;
	
	public static void main(String[] arg)
	{
		new MainFrame();
		
//		File output = new File(".", "test/deathnote.gcode");
//		BufferedImage img = null;
//		try
//		{
//			img = ImageIO.read(new File("test/start.png"));
//		} catch (IOException e)
//		{}
//		
//		GrayConversion conv = new GrayConversion(img);
//		conv.convertGray(200,240);
//		saveImg("test/gray.png", conv._output);
//		
//		GcodeManager gcode = new GcodeManager(output, upPosition, downPosition, PixelSize);
//		
//		Perimetro per = new Perimetro(conv._output, gcode);
//		
//		per.generatePerimeter(200);
//		
//		per.createPath();
//		
//		saveImg("test/infill.png", per._infill);
//		saveImg("test/perimetro.png", per._perimetro);
//		
//		Infill infill = new Infill(per._infill, gcode);
//		infill.createPath();
		
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
