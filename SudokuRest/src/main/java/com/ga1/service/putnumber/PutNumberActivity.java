package com.ga1.service.putnumber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ga1.logic.putnumber.Move;
import com.ga1.logic.putnumber.PutNumberSolver;
import com.ga1.logic.putnumber.PutNumberLogicResponse;

@Component
public class PutNumberActivity {

	@Autowired
	private PutNumberSolver sudokuVerifier;

	public PutNumberLogicResponse putNumberOnBoard(Integer row, Integer column, Integer value, Integer id) {
		return sudokuVerifier.putNumber(new Move(row, column, value), id);
	}
}
