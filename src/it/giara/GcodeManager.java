package it.giara;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class GcodeManager
{
	private File _file;
	private int gcode_lines = 0;
	private float _up_position;
	private float _down_position;
	private float _px_size;
	private float offX = 0;
	private float offY = 0;
	
	private boolean isUp = false;
	
	public GcodeManager(File f, float up, float down, float pixel)
	{
		_file = f;
		_up_position = up;
		_down_position = down;
		_px_size = pixel;
	}
	
	public void setOffset(float x, float y)
	{
		offX = x;
		offY = y;
	}
	
	public void up()
	{
		if (!isUp)
		{
			write("G1 Z" + F2S(_up_position));
			isUp = true;
		}
	}
	
	public void down()
	{
		if (isUp)
		{
			write("G1 Z" + F2S(_down_position));
			isUp = false;
		}
	}
	
	public void move(int x, int y)
	{
		if (isUp)
			write("G1 X" + F2S(offX + (x * _px_size)) + " Y" + F2S(offY + (y * _px_size)));
		else
			write("G1 X" + F2S(offX + (x * _px_size)) + " Y" + F2S(offY + (y * _px_size)) + " E0");
	}
	
	public void unlockMotors()
	{
		write("M84");
	}
	
	public boolean getIsUp()
	{
		return isUp;
	}
	
	private void write(String text)
	{
		gcode_lines++;
//		System.out.println("Writed: " + gcode_lines + " lines");
		
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try
		{
			if (!_file.getParentFile().exists())
				_file.getParentFile().mkdirs();
			fWriter = new FileWriter(_file, true);
			writer = new BufferedWriter(fWriter);
			
			writer.append(text);
			writer.newLine();
			writer.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private String F2S(float value)
	{
		return String.format("%.5f", value).replace(",", ".");
	}
	
}
