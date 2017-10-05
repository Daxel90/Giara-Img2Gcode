package it.giara;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

public class TxtWriter
{
	
	static boolean txtWrite = false;
	
	public static void write(String text, String file)
	{
		FileWriter fWriter = null;
		BufferedWriter writer = null;
		try
		{
			File f = new File(".", file);
			if (!f.getParentFile().exists())
				f.getParentFile().mkdirs();
			fWriter = new FileWriter(f, true);
			writer = new BufferedWriter(fWriter);
			
			writer.append(text);
			writer.newLine();
			writer.close();
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
}
