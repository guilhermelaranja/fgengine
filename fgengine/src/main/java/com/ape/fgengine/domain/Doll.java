package com.ape.fgengine.domain;

import java.awt.*;
import java.util.List;

public class Doll {
	
	private String name;
	private Point position;
	
	private List<Move> moves;
	private Move currentMove;
	
	public Doll(String name, int x, int y) {
		super();
		this.name = name;
		this.position = new Point(x, y);
	}
	
	public void executeMove(Move move) {
		currentMove = move;
		move.setCurrentFrame(move.getCurrentFrame()+1);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public List<Move> getMoves() {
		return moves;
	}

	public void setMoves(List<Move> moves) {
		this.moves = moves;
	}

	public Move getCurrentMove() {
		return currentMove;
	}

	public void setCurrentMove(Move currentMove) {
		this.currentMove = currentMove;
	}
	
}
