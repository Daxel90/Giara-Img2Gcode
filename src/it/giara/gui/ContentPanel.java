package it.giara.gui;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class ContentPanel extends JPanel
{
	private static final long serialVersionUID = 1683848878615076103L;
	private final JFileChooser fcInput = new JFileChooser();
	private JMenuBar mbar; 
	
	public ContentPanel()
	{
		this.setLayout(null);
		this.setVisible(true);
	}
	
	public void init()
	{
		mbar = new JMenuBar();
		JMenu file = new JMenu("File");
		JMenu info = new JMenu("Info");
		
		JMenuItem about =new JMenuItem("About");    
		JMenuItem importImg = new JMenuItem("Import Image");  
		JMenuItem advanceSettings = new JMenuItem("Advance Settings");
		
		info.add(about);
		file.add(importImg);
		file.add(advanceSettings);
		
		mbar.add(file);
		mbar.add(info);
		
		mbar.setBounds(0, 0, this.getWidth(), 20);
		
		this.add(mbar);
	}
	
	public void update()
	{
		mbar.setBounds(0, 0, this.getWidth(), 20);
		
		this.repaint();
	}
	
}
