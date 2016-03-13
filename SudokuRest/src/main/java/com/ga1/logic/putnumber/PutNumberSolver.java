package com.ga1.logic.putnumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ga1.logic.model.BoardCache;
import com.ga1.logic.model.Board.Board;

@Component
public class PutNumberSolver {

	@Autowired
	private BoardCache boardCache;
	
	private static final String INCORRECT_ROW = "Row must be in range from 1 to 9 (both inclusive)";
	private static final String INCORRECT_COLUMN = "Column must be in range from 1 to 9 (both inclusive)";
	private static final String INCORRECT_VALUE = "Value must be in range from 1 to 9 (both inclusive)";
	private static final String THIS_NUMBER_IS_ALREADY_IN_THIS_ROW = "This number is already in this row";
	private static final String THIS_NUMBER_IS_ALREADY_IN_THIS_COLUMN = "This number is already in this column";
	private static final String THIS_NUMBER_IS_ALREADY_IN_THIS_SQUARE = "This number is already in this square";
	private static final String THE_CELL_IS_ALREADY_OCCUPIED = "The cell is already occupied";
	
	public PutNumberLogicResponse putNumber(Move move, int id) {
		Board board = boardCache.getBoard(id);
		if (move.getRow() < 1 || 9 < move.getRow())
			return new PutNumberLogicResponse(false, INCORRECT_ROW);
		if (move.getColumn() < 1 || 9 < move.getColumn())
			return new PutNumberLogicResponse(false, INCORRECT_COLUMN);
		if (move.getValue() < 1 || 9 < move.getValue())
			return new PutNumberLogicResponse(false, INCORRECT_VALUE);
		if (isOccupied(board, move))
			return new PutNumberLogicResponse(false, THE_CELL_IS_ALREADY_OCCUPIED);
		if (!isValidHorizontally(board, move))
			return new PutNumberLogicResponse(false, THIS_NUMBER_IS_ALREADY_IN_THIS_ROW);
		if (!isValidVertically(board, move))
			return new PutNumberLogicResponse(false, THIS_NUMBER_IS_ALREADY_IN_THIS_COLUMN);
		if (!isValidSquare(board, move))
			return new PutNumberLogicResponse(false, THIS_NUMBER_IS_ALREADY_IN_THIS_SQUARE);
		board.put(move);
		boolean isFinished = board.isFull();
		return new PutNumberLogicResponse(true, isFinished);
	}

	private boolean isValidVertically(Board board, Move move) {
		for (int i = 1; i <10; i++)
			if (board.getValue(i, move.getColumn()) == move.getValue())
				return false;
		return true;
	}

	private boolean isValidHorizontally(Board board, Move move) {
		for (int i = 1; i <10; i++)
			if (board.getValue(move.getRow(), i) == move.getValue())
				return false;
		return true;
	}
	
	private boolean isValidSquare(Board board, Move move) {
		int rowSquareStart = 3*((move.getRow() - 1)/3) + 1;
		int rowSquareEnd = rowSquareStart + 3;
		int columnSquareStart = 3*((move.getColumn() - 1)/3) + 1;
		int columnSquareEnd = columnSquareStart + 3;
		for (int i = rowSquareStart; i <rowSquareEnd; i++)
			for (int j = columnSquareStart; j <columnSquareEnd; j++)
				if (board.getValue(i, j) == move.getValue())
					return false;
		return true;
	}

	private boolean isOccupied(Board board, Move move) {
		return board.getValue(move.getRow(), move.getColumn()) != 0;
	}
}
