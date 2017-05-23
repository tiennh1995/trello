package edu.ttl.tool;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

import javax.swing.JFrame;

public class MyString extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String text = "Hello World";
	AffineTransform affinetransform = new AffineTransform();     
	FontRenderContext frc = new FontRenderContext(affinetransform,true,true);     
	Font font = new Font("Tahoma", Font.PLAIN, 14);
	int textwidth = (int)(font.getStringBounds(text, frc).getWidth());
	int textheight = (int)(font.getStringBounds(text, frc).getHeight());
}
