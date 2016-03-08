package com.ga1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga1.logic.VerificationResult;

@RequestMapping(value = "/sudoku")
@RestController
public class SudokuController {
	
	@Autowired
	private SudokuServiceActivity activity;	

    @RequestMapping(value = "/putNumber", method = RequestMethod.PUT)
    public ResponseEntity<SudokuServiceError> putNumberOnBoard(@RequestParam(value="row") Integer row,
    														@RequestParam(value="column") Integer column,
    														@RequestParam(value="value") Integer value) {
    	VerificationResult verificationResult = activity.putNumberOnBoard(row, column, value);
    	SudokuServiceError sse = new SudokuServiceError();
    	if (verificationResult.isCorrectMove())
    		return new ResponseEntity<>(sse, HttpStatus.OK);
    	else {
    		sse.setErrorMessage(verificationResult.getErrorMessage());
    		return new ResponseEntity<>(sse, HttpStatus.BAD_REQUEST);
    	}
    }
}
