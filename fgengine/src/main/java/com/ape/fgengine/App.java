package com.ape.fgengine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.swing.JFrame;

import com.ape.fgengine.domain.Doll;
import com.ape.fgengine.domain.Move;
import com.ape.fgengine.ui.BufferedKeyListener;
import com.ape.fgengine.ui.MainFrame;
import com.sun.jmx.remote.internal.ArrayQueue;

/**
 * Hello world!
 * 
 */
public class App {
	public static final long FRAME = 17;

	public static void main(String[] args) {
		
		List<Doll> chars = new ArrayList<Doll>();
		chars.add(new Doll("Char1", 0, 0));

		Map<Doll, Move> movesMap = new HashMap<Doll, Move>();
		movesMap.put(chars.get(0), new Move("Shoryuken!"));
		
		
		MainFrame mainFrame = new MainFrame();
		mainFrame.setVisible(true);
		

		// game loop
		while (true) {
			long startTime = System.currentTimeMillis();

			// detectMoves();
			// TODO: substituir buffer de key por buffer de input
			// TODO: fazer reconhecimento de input duplo ex. HP+MP+Forward
			// TODO: fazer tick do input somente se o frame atual nao tiver nenhum input
			mainFrame.tickInputBuffer();
			detectMoves(mainFrame.getInputBufferSnapshot());
			// runAI();
			
			// executeMoves();
			executeMoves(movesMap);

			// detectHits();
			// applyDamage();
			// applyStun();
			// checkForDizzy();
			// detectKO();

			renderFrame(chars);
			handleFPS(startTime);
		}
	}

	private static void detectMoves(LinkedList<Integer> inputBuffer) {
		int srk[] = {68, 83, 68, 80};
		int maxDistance = 10;
		
		LinkedList<Integer> firstInputIndexes = new LinkedList<Integer>();
		List<Integer> fiAux = inputBuffer;
		int fInput = srk[0];
		
		for (int i = fiAux.indexOf(fInput); i > -1; i = fiAux.indexOf(fInput)) {
			Integer last = 0;
			try {
				last = firstInputIndexes.getLast();
			} catch (NoSuchElementException e) {}
			
			firstInputIndexes.offer(i+last);
			fiAux = fiAux.subList(i+1, fiAux.size());
		}
		

		for (Integer firstInput : firstInputIndexes) {
			
			//verifica a partir do primeiro input ja encontrado no loop acima
			int lastIndex = firstInput;
			List<Integer> aux = inputBuffer.subList(lastIndex, inputBuffer.size());
			boolean found = false;
			boolean moveInputted = true;
			
			for (int i = 0; i < srk.length; i++) {
				int moveInput = srk[i];
				found = false;
				
				for (int j = 0; j < maxDistance && j < aux.size(); j++) {
					Integer input = aux.get(j);
					if (input.intValue() == moveInput) {
						lastIndex = aux.indexOf(input);
						found = true;
					}
				}
				
				if (!found) {
					moveInputted = false;
					break;
				}
				
				aux = aux.subList(lastIndex, aux.size());
			}
			
			if (moveInputted) {
				Logger.log("SHOOORYUUKEN!!", Logger.INFO);
			}
		}
	}

	private static void executeMoves(Map<Doll, Move> movesMap) {
		for (Doll c : movesMap.keySet()) {
			Move move = movesMap.get(c);
			c.executeMove(move);
			if(move.getCurrentFrame() >= move.getDurationFrames()) {
				movesMap.remove(c);
				c.setCurrentMove(null);
			}
		}
	}

	private static void handleFPS(long startTime) {
		while (System.currentTimeMillis() - startTime < FRAME) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Logger.log("" + 1000 / (System.currentTimeMillis() - startTime), Logger.DEBUG);
	}

	private static void renderFrame(List<Doll> chars) {
		for (Doll c : chars) {
			Logger.log(c.getName() + " - x: " + c.getPosition().getX() + " ; y: " + c.getPosition().getY(), Logger.DEBUG);

			if (c.getCurrentMove() != null) {
				Logger.log("Current move: " + c.getCurrentMove().getName() + "; Current frame: " + c.getCurrentMove().getCurrentFrame(),
						Logger.DEBUG);
			} else {
				Logger.log("Neutral", Logger.DEBUG);
			}
		}
		Logger.log("Frame rendered", Logger.DEBUG);
	}
}
