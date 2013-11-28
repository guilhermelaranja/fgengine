package com.ape.fgengine.ui;

import java.util.LinkedList;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private BufferedKeyListener bufferedKeyListener;

	public MainFrame () {
		super("FGEngine");
		this.setSize(300, 300);
		
		bufferedKeyListener = new BufferedKeyListener();
		this.addKeyListener(bufferedKeyListener);
	}
	
	public LinkedList<Integer> getInputBufferSnapshot() {
		return bufferedKeyListener.getInputBufferSnapshot();
	}
	
	public void tickInputBuffer() {
		bufferedKeyListener.tick();
	}
}
