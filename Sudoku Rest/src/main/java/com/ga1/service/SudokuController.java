package com.ga1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ga1.logic.createnewgame.CreateNewGameLogicResult;
import com.ga1.logic.putnumber.PutNumberLogicResponse;
import com.ga1.service.createnewgame.CreateNewBoardActivity;
import com.ga1.service.createnewgame.CreateNewBoardServiceResponse;
import com.ga1.service.putnumber.PutNumberActivity;
import com.ga1.service.putnumber.PutNumberServiceResponse;

@RequestMapping(value = "/sudoku", produces = "application/json")
@RestController
public class SudokuController {
	
	@Autowired
	private PutNumberActivity putNubmerActivity;
	
	@Autowired
	private CreateNewBoardActivity createNewGameActivity;

    @RequestMapping(value = "/putNumber/{boardId}", method = RequestMethod.POST)
    public ResponseEntity<PutNumberServiceResponse> putNumberOnBoard(@RequestParam(value="row") Integer row,
    														@RequestParam(value="column") Integer column,
    														@RequestParam(value="value") Integer value,
    														@PathVariable(value="boardId") Integer boardId) {
    	PutNumberLogicResponse appResponse = putNubmerActivity.putNumberOnBoard(row, column, value, boardId);
    	PutNumberServiceResponse serviceResponse = new PutNumberServiceResponse();
    	if (appResponse.isCorrectMove()) {
    		serviceResponse.setIsFinished(appResponse.getIsFinished());
    		return new ResponseEntity<>(serviceResponse, HttpStatus.OK);
    	} else {
    		serviceResponse.setErrorMessage(appResponse.getErrorMessage());
    		return new ResponseEntity<>(serviceResponse, HttpStatus.BAD_REQUEST);
    	}
    }
    
    @RequestMapping(value = "/newGame", method = RequestMethod.POST)
    public ResponseEntity<CreateNewBoardServiceResponse> createNewGame() {
    	CreateNewGameLogicResult creationResult = createNewGameActivity.createNewGame();
    	CreateNewBoardServiceResponse cnbsr = new CreateNewBoardServiceResponse();
    	cnbsr.setGameId(creationResult.getId());
		return new ResponseEntity<CreateNewBoardServiceResponse>(cnbsr, HttpStatus.OK);
    }
}
