package com.ga1.service.createnewgame;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ga1.logic.createnewgame.CreateNewGameLogicResult;
import com.ga1.logic.createnewgame.CreateNewGameSolver;

@Component
public class CreateNewBoardActivity {

	@Autowired
	private CreateNewGameSolver solver;
	
	public CreateNewGameLogicResult createNewGame() {
		return solver.createNewGame();
	}

}
