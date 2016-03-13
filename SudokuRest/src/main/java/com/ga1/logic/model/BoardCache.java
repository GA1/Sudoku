package com.ga1.logic.model;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.ga1.logic.model.Board.Board;
import com.ga1.logic.utils.ArraysUtils;

@Component
@Scope("singleton")
public class BoardCache {

	private static int[][] defaultConfiguration = new int[][]{{7, 0, 0, 0, 4, 0, 5, 3, 0},
											          {0, 0, 5, 0, 0, 8, 0, 1, 0}, 
											          {0, 0, 8, 5, 0, 9, 0, 4, 0},
											          {5, 3, 9, 0, 6, 0, 0, 0, 1}, 
											          {0, 0, 0, 0, 1, 0, 0, 0, 5}, 
											          {8, 0, 0, 7, 2, 0, 9, 0, 0},
											          {9, 0, 7, 4, 0, 0, 0, 0, 0}, 
											          {0, 0, 0, 0, 5, 7, 0, 0, 0}, 
											          {6, 0, 0, 0, 0, 0, 0, 5, 0}}; 
	
	private final static Map<Integer, Board> boards = new ConcurrentHashMap<>();
	
	public synchronized int createNewBoard() {
		int createdId = boards.size();
		boards.put(createdId, new Board(ArraysUtils.clone(defaultConfiguration)));
		return createdId;
	}
	
	public Board getBoard(int id) { 
		return boards.get(id);
	}
	
}
