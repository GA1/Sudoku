package com.ga1.logic;

public class VerificationResult {

	private static final String NO_ERROR_MESSAGE = "NO ERROR MESSAGE";
	private boolean isCorrectMove;
	private String errorMessage;
	
	public VerificationResult(boolean isCorrectMove) {
		this.isCorrectMove = isCorrectMove;
	}
	
	public VerificationResult(boolean isCorrectMove, String errorMessage) {
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

}
