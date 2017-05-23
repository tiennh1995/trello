package edu.ttl.ui;

import javax.swing.JTextPane;

public class SelectableLabel extends JTextPane {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SelectableLabel() {
		this.setContentType("text/html");
		this.setEditable(false);
		this.setBackground(null);
		this.setBorder(null);
	}
	
	public SelectableLabel(String t) {
		this.setContentType("text/html");
		this.setEditable(false);
		this.setBackground(null);
		this.setBorder(null);
		this.setText(t);
	}
	
	@Override
	public void setText(String t) {
		// TODO Auto-generated method stub
		super.setText("<html>".concat(t.concat("<html>")));
	}
	
	public void setText(String t, int width) {
		super.setText(String.format("<html><div style=\"width:%dpx;\">%s</div><html>", width, t));
	}
}
