package com.ga1.logic.putnumber;

public class PutNumberLogicResponse {

	private static final String NO_ERROR_MESSAGE = "NO ERROR MESSAGE";
	private boolean isCorrectMove;
	private boolean isFinished; 
	private String errorMessage;
	
	public PutNumberLogicResponse(boolean isCorrectMove, boolean isFinished) {
		this.isCorrectMove = isCorrectMove;
		this.isFinished = isFinished;
	}
	
	public PutNumberLogicResponse(boolean isCorrectMove, String errorMessage) {
		this.isCorrectMove = isCorrectMove;
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		if (isCorrectMove)
			throw new IllegalStateException(NO_ERROR_MESSAGE);
		return errorMessage;
	}

	public boolean isCorrectMove() {
		return isCorrectMove;
	}

	public void setCorrectMove(boolean isCorrectMove) {
		this.isCorrectMove = isCorrectMove;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(boolean isFinished) {
		this.isFinished = isFinished;
	}
}
