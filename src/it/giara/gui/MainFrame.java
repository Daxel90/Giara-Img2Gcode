package it.giara.gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class MainFrame extends JFrame implements ComponentListener
{
	
	private static final long serialVersionUID = -1807973401694754803L;
	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 500;
	public static MainFrame instance;
	private ContentPanel panel;
	
	public MainFrame()
	{
		instance = this;
		setTitle("Giara Img2Gcode");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds((dim.width - FRAME_WIDTH) / 2, (dim.height - FRAME_HEIGHT) / 2, FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(true);
		
		panel = new ContentPanel();
		
		panel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
		panel.init();
		this.setContentPane(panel);
		
		this.addComponentListener(this);
		this.setVisible(true);
	}

	@Override
	public void componentHidden(ComponentEvent arg0)
	{
	}

	@Override
	public void componentMoved(ComponentEvent arg0)
	{
	}

	@Override
	public void componentResized(ComponentEvent arg0)
	{
		panel.setBounds(0, 0, this.getWidth(), this.getHeight());
		panel.update();
	}

	@Override
	public void componentShown(ComponentEvent arg0)
	{
		
	}
	
}
