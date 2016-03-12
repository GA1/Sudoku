package com.ga1.logic.putnumber;

import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.ga1.logic.model.BoardCache;
import com.ga1.logic.model.Board.Board;
import com.ga1.logic.putnumber.Move;
import com.ga1.logic.putnumber.PutNumberSolver;
import com.ga1.logic.putnumber.PutNumberLogicResponse;

public class PutNumberSolverTest {

	@InjectMocks
	private PutNumberSolver testSubject;
	
	@Mock
    private BoardCache boardCache;
	
	private Move move;
	private PutNumberLogicResponse result;
	private int[][] defaulConfiguration;
	
	@Before
	public void initialize() { 
		testSubject = new PutNumberSolver();
		MockitoAnnotations.initMocks(this);
		defaulConfiguration = new int[][]{{7, 0, 0, 0, 4, 0, 5, 3, 0},
								          {0, 0, 5, 0, 0, 8, 0, 1, 0}, 
								          {0, 0, 8, 5, 0, 9, 0, 4, 0},
								          {5, 3, 9, 0, 6, 0, 0, 0, 1}, 
								          {0, 0, 0, 0, 1, 0, 0, 0, 5}, 
								          {8, 0, 0, 7, 2, 0, 9, 0, 0},
								          {9, 0, 7, 4, 0, 0, 0, 0, 0}, 
								          {0, 0, 0, 0, 5, 7, 0, 0, 0}, 
								          {6, 0, 0, 0, 0, 0, 0, 5, 0}};
		Mockito.when(boardCache.getBoard(0)).thenReturn(new Board(defaulConfiguration));
	}
	
	@Test
	public void verificationSuccess1() {
		move = new Move(2, 2, 9);
		result = testSubject.putNumber(move, 0);
		assertTrue(result.isCorrectMove());
	}
	
	@Test
	public void verificationSuccess2() {
		move = new Move(5, 6, 3);
		result = testSubject.putNumber(move, 0);
		assertTrue(result.isCorrectMove());
	}
	
	@Test
	public void incorrectRowVerificationFail1() {
		move = new Move(0, 2, 9);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("Row must be in range from 1 to 9 (both inclusive)", result.getErrorMessage());
	}
	
	@Test
	public void incorrectRowVerificationFail2() {
		move = new Move(10, 2, 9);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("Row must be in range from 1 to 9 (both inclusive)", result.getErrorMessage());
	}
	
	@Test
	public void incorrectColumnVerificationFail1() {
		move = new Move(2, 0, 9);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("Column must be in range from 1 to 9 (both inclusive)", result.getErrorMessage());
	}
	
	@Test
	public void incorrectColumnVerificationFail2() {
		move = new Move(2, 10, 9);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("Column must be in range from 1 to 9 (both inclusive)", result.getErrorMessage());
	}
	
	@Test
	public void incorrectValueVerificationFail1() {
		move = new Move(2, 2, 10);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("Value must be in range from 1 to 9 (both inclusive)", result.getErrorMessage());
	}
	
	@Test
	public void incorrectValueVerificationFail2() {
		move = new Move(2, 2, 0);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("Value must be in range from 1 to 9 (both inclusive)", result.getErrorMessage());
	}
	
	@Test
	public void occupiedCellVerificationFail1() {
		move = new Move(1, 1, 7);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("The cell is already occupied", result.getErrorMessage());
	}
	
	@Test
	public void occupiedCellVerificationFail2() {
		move = new Move(7, 4, 1);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("The cell is already occupied", result.getErrorMessage());
	}
	
	@Test
	public void horizontalVerificationFail1() {
		move = new Move(1, 2, 4);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("This number is already in this row", result.getErrorMessage());
	}
	
	@Test
	public void horizontalVerificationFail2() {
		move = new Move(7, 9, 4);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("This number is already in this row", result.getErrorMessage());
	}
	
	@Test
	public void verticalVerificationFail1() {
		move = new Move(4, 6, 8);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("This number is already in this column", result.getErrorMessage());
	}
	
	@Test
	public void verticalVerificationFail2() {
		move = new Move(7, 8, 5);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("This number is already in this column", result.getErrorMessage());
	}
	
	@Test
	public void squareVerificationFail1() {
		move = new Move(6, 2, 5);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("This number is already in this square", result.getErrorMessage());
	}
	
	@Test
	public void squareVerificationFail2() {
		move = new Move(9, 5, 7);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("This number is already in this square", result.getErrorMessage());
	}
	
	@Test
	public void squareVerificationFail3() {
		move = new Move(6, 6, 6);
		result = testSubject.putNumber(move, 0);
		assertFalse(result.isCorrectMove());
		assertEquals("This number is already in this square", result.getErrorMessage());
	}

}
