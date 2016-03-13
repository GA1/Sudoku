package com.ga1.logic.model.Board;

import com.ga1.logic.putnumber.Move;
import com.ga1.logic.utils.ArraysUtils;

public class Board {

	private int[][] configuration;
	
    public Board(int[][] configuration) {
    	this.configuration = ArraysUtils.clone(configuration); // ensure no bugs related to arrays' mutability
    	assert (containsOnlyNumbersFrom0to9(configuration)); // run only in tests
	}

	public int getValue(int row, int column) { 
    	return configuration[row-1][column-1];
    }
    
    public void put(Move move) {
    	configuration[move.getRow() - 1][move.getColumn() - 1] = move.getValue();
    }
    
	public boolean isFull() {
		int sum = 0;
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (configuration[i][j] != 0)
					sum++;
		return sum == 81;
	}
	
	private boolean containsOnlyNumbersFrom0to9(int[][] configuration2) {
		for (int i = 0; i < 9; i++)
			for (int j = 0; j < 9; j++)
				if (configuration[i][j] < 0 || 9 < configuration[i][j])
					return false;
		return true;
	}
}
