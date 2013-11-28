package com.ape.fgengine.domain;

import java.util.List;

public class Move {
	
	private String name;
	private int currentFrame;
	private int durationFrames;
	private List<Frame> frames;

	public Move(String name) {
		super();
		this.name = name;
		currentFrame = 0;
		durationFrames = 10;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCurrentFrame() {
		return currentFrame;
	}

	public void setCurrentFrame(int currentFrame) {
		this.currentFrame = currentFrame;
	}

	public int getDurationFrames() {
		return durationFrames;
	}

	public void setDurationFrames(int durationFrames) {
		this.durationFrames = durationFrames;
	}
	
}
