package com.ga1.logic.createnewgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ga1.logic.model.BoardCache;

@Component
public class CreateNewGameSolver {

	@Autowired
	private BoardCache boardCache;
	
	public CreateNewGameLogicResult createNewGame() {
		CreateNewGameLogicResult result = new CreateNewGameLogicResult();
		Integer newId = boardCache.createNewBoard();
		result.setId(newId);
		return result;
	}
}
