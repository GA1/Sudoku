package com.ga1.logic;

import org.junit.Test;

public class VerificationResultTest {

	private VerificationResult testSubject;

	@Test (expected = IllegalStateException.class)
	public void occupiedVerificationFail() {
		testSubject = new VerificationResult(true);
		testSubject.getErrorMessage();
	}
	
}
