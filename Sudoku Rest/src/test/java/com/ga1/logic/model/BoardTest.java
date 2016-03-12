package com.ga1.logic.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ga1.logic.model.Board.Board;

public class BoardTest {

	private Board board;
	private int[][] configuration;

	@Test(expected=AssertionError.class)
	public void testAssertionsEnabled() {
		assert(false);
	}
	
	@Test
	public void lacksOneDigit() {
		configuration = new int[9][9];
		fillArrayWith5(configuration);
		configuration[8][8] = 0;
		board = new Board(configuration);
		assertFalse(board.isFull());
	}
	
	@Test
	public void fullBoard() {
		configuration = new int[9][9];
		fillArrayWith5(configuration);
		board = new Board(configuration);
		assertTrue(board.isFull());
	}
	
	public void fillArrayWith5(int[][] array) {
		assert array.length > 0;
		for (int i = 0; i < array.length; i++)
			for (int j = 0; j < array[0].length; j++)
				array[i][j] = 5;
	}
}
