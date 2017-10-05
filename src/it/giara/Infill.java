package it.giara;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Infill
{
	private BufferedImage _img;
	private GcodeManager _gcode;
	
	public Infill(BufferedImage i, GcodeManager gm)
	{
		_img = i;
		_gcode = gm;
	}
	
	public void createPath()
	{
		_gcode.up();
		
		int prevX = 0;
		int prevY = 0;
		
		for (int y = 0; y < _img.getHeight(); y++)
		{
			for (int x = 0; x < _img.getWidth(); x++)
			{
				if (y != prevY && !_gcode.getIsUp())
				{
					_gcode.move(prevX, prevY);
					_gcode.up();
				}
				
				if (_img.getRGB(x, _img.getHeight() -1 -y) == Color.BLACK.getRGB())
				{
					prevX = x;
					prevY = y;
					if (_gcode.getIsUp())
					{
						_gcode.move(x, y);
						_gcode.down();
					}
				}
				else
				{
					if (!_gcode.getIsUp())
					{
						_gcode.move(prevX, prevY);
						_gcode.up();
					}
				}
				
			}
		}
		_gcode.up();
		_gcode.move(0, 0);
		_gcode.unlockMotors();
		
	}
}
