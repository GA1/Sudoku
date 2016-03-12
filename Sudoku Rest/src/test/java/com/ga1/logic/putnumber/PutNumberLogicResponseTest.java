package com.ga1.logic.putnumber;

import org.junit.Test;

import com.ga1.logic.putnumber.PutNumberLogicResponse;

public class PutNumberLogicResponseTest {

	private PutNumberLogicResponse testSubject;

	@Test (expected = IllegalStateException.class)
	public void notAllowedToGetErrorMessageIfThereIsNoError() {
		testSubject = new PutNumberLogicResponse(true, true);
		testSubject.getErrorMessage();
	}
	
}
