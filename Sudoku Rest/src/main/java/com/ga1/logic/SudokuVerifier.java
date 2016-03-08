package com.ga1.logic;

import org.springframework.stereotype.Component;

@Component
public class SudokuVerifier {

	private static final String INCORRECT_ROW = "Row must be in range from 1 to 9 (both inclusive)";
	private static final String INCORRECT_COLUMN = "Column must be in range from 1 to 9 (both inclusive)";
	private static final String INCORRECT_VALUE = "Value must be in range from 1 to 9 (both inclusive)";
	private static final String THIS_NUMBER_IS_ALREADY_IN_THIS_ROW = "This number is already in this row";
	private static final String THIS_NUMBER_IS_ALREADY_IN_THIS_COLUMN = "This number is already in this column";
	private static final String THIS_NUMBER_IS_ALREADY_IN_THIS_SQUARE = "This number is already in this square";
	private static final String THE_CELL_IS_ALREADY_OCCUPIED = "The cell is already occupied";
	private int[][] board;
	
	
	
	public SudokuVerifier() {
		board = new int[][]{{7, 0, 0, 0, 4, 0, 5, 3, 0}, {0, 0, 5, 0, 0, 8, 0, 1, 0}, {0, 0, 8, 5, 0, 9, 0, 4, 0},
		                    {5, 3, 9, 0, 6, 0, 0, 0, 1}, {0, 0, 0, 0, 1, 0, 0, 0, 5}, {8, 0, 0, 7, 2, 0, 9, 0, 0},
		                    {9, 0, 7, 4, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 5, 7, 0, 0, 0}, {6, 0, 0, 0, 0, 0, 0, 5, 0}};
	}

	public VerificationResult verify(Move move) {
		if (move.getRow() < 1 || 9 < move.getRow())
			return new VerificationResult(false, INCORRECT_ROW);
		if (move.getColumn() < 1 || 9 < move.getColumn())
			return new VerificationResult(false, INCORRECT_COLUMN);
		if (move.getValue() < 1 || 9 < move.getValue())
			return new VerificationResult(false, INCORRECT_VALUE);
		if (isOccupied(move))
			return new VerificationResult(false, THE_CELL_IS_ALREADY_OCCUPIED);
		if (!isValidHorizontally(move.getRow(), move.getValue()))
			return new VerificationResult(false, THIS_NUMBER_IS_ALREADY_IN_THIS_ROW);
		if (!isValidVertically(move.getColumn(), move.getValue()))
			return new VerificationResult(false, THIS_NUMBER_IS_ALREADY_IN_THIS_COLUMN);
		if (!isValidSquare(move.getColumn(), move.getColumn(), move.getValue()))
			return new VerificationResult(false, THIS_NUMBER_IS_ALREADY_IN_THIS_SQUARE);
		return new VerificationResult(true);
	}


	private boolean isValidVertically(int column, int value) {
		for (int[] i: board)
			if (i[column-1] == value)
				return false;
		return true;
	}

	private boolean isValidHorizontally(int row, int value) {
		for (int i: board[row-1])
			if (i == value)
				return false;
		return true;
	}
	
	private boolean isValidSquare(int row, int column, int value) {
		int rowSquareStart = 3*((row - 1)/3);
		int rowSquareEnd = rowSquareStart + 3;
		int columnSquareStart = 3*((column - 1)/3);
		int columnSquareEnd = rowSquareStart + 3;
		for (int i = rowSquareStart; i <rowSquareEnd; i++)
			for (int j = columnSquareStart; j <columnSquareEnd; j++)
				if (board[i][j] == value)
					return false;
		return true;
	}

	private boolean isOccupied(Move move) {
		return board[move.getRow() - 1][move.getColumn() - 1] != 0;
	}
}
