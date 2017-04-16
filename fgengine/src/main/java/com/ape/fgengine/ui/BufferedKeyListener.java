package com.ape.fgengine.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;

import com.ape.fgengine.Logger;

public class BufferedKeyListener implements KeyListener {
	
	private static final int BUFFER_CAPACITY = 60;

	private static LinkedList<Integer> inputBuffer = new LinkedList<>();
	
	LinkedList<Integer> getInputBufferSnapshot() {
		LinkedList<Integer> result;
		
		synchronized(inputBuffer) {
			result = new LinkedList<>(inputBuffer);
		}
		
		return result;
	}
	
	public void tick() {
		addToBuffer(-1);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		addToBuffer(e);
		Logger.log("KeyPressed: " + e.getKeyChar() + " code " + e.getKeyCode(), Logger.INFO);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		addToBuffer(e);
		Logger.log("KeyReleased:" + e.getKeyChar() + " code " + e.getKeyCode(), Logger.INFO);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//Nothing to do here
	}
	
	private void addToBuffer(Integer i){
		synchronized (inputBuffer) {
			if (inputBuffer.size() == BUFFER_CAPACITY) {
				inputBuffer.poll();
			}
			inputBuffer.offer(i);
		}
	}
	
	private void addToBuffer(KeyEvent e) {
		this.addToBuffer(e.getKeyCode());
	}
	
}