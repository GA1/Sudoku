package com.ga1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ga1.logic.Move;
import com.ga1.logic.SudokuVerifier;
import com.ga1.logic.VerificationResult;

@Component
public class SudokuServiceActivity {

	@Autowired
	private SudokuVerifier sudokuVerifier;

	public VerificationResult putNumberOnBoard(Integer row, Integer column, Integer value) {
		return sudokuVerifier.verify(new Move(row, column, value));
	}
	
}
