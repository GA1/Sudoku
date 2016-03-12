package com.ga1.SudokuIntegrationTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class SudokuRestTest  {

	private static final String SUDOKU_URL = "http://localhost:8080/sudoku/";
	private HttpClient httpClient;
	private HttpPost request; 
	private String gameId, gameId1, gameId2;
	private HttpResponse httpResponse;
	
	@Before
	public void initialize() {
		httpClient = HttpClientBuilder.create().build();	
	}
	
	@Test
	public void newGameHttpCodeTest() {
		request = new HttpPost(SUDOKU_URL + "newGame");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
	}
	
	@Test
	public void whenCreatingGamesIdsAreDifferentTest() {
		gameId1 = createGameAndGetItsId();
		gameId2 = createGameAndGetItsId();
		assertTrue(Integer.parseInt(gameId2) != Integer.parseInt(gameId1));
	}
	

	@Test
	public void putNumberSuccessTest() {
		gameId = createGameAndGetItsId();
		request = new HttpPost(SUDOKU_URL + "putNumber/" + gameId + "?row=1&column=2&value=2");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(200, httpResponse.getStatusLine().getStatusCode());
		assertEquals("false", getAttributeFromJson(httpResponse, "isFinished"));
	}
	
	@Test
	public void putNumberNegativeValueTest() {
		gameId = createGameAndGetItsId();
		request = new HttpPost(SUDOKU_URL + "putNumber/" + gameId + "?row=1&column=2&value=-1");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(400, httpResponse.getStatusLine().getStatusCode());
		assertEquals("Value must be in range from 1 to 9 (both inclusive)", getAttributeFromJson(httpResponse, "errorMessage"));
	}
	
	@Test
	public void putNumberInvalidColumnTest() {
		gameId = createGameAndGetItsId();
		request = new HttpPost(SUDOKU_URL + "putNumber/" + gameId + "?row=1&column=10&value=2");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(400, httpResponse.getStatusLine().getStatusCode());
		assertEquals("Column must be in range from 1 to 9 (both inclusive)", getAttributeFromJson(httpResponse, "errorMessage"));
	}
	
	@Test
	public void putNumberInvalidRowTest() {
		gameId = createGameAndGetItsId();
		request = new HttpPost(SUDOKU_URL + "putNumber/" + gameId + "?row=0&column=2&value=2");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(400, httpResponse.getStatusLine().getStatusCode());
		assertEquals("Row must be in range from 1 to 9 (both inclusive)", getAttributeFromJson(httpResponse, "errorMessage"));
	}
	
	@Test
	public void numberInTheSameRowTest() {
		gameId = createGameAndGetItsId();
		request = new HttpPost(SUDOKU_URL + "putNumber/" + gameId + "?row=1&column=6&value=7");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(400, httpResponse.getStatusLine().getStatusCode());
		assertEquals("This number is already in this row", getAttributeFromJson(httpResponse, "errorMessage"));
	}
	
	@Test
	public void numberInTheSameColumnTest() {
		String id = createGameAndGetItsId();
		request = new HttpPost(SUDOKU_URL + "putNumber/" + id + "?row=2&column=1&value=9");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(400, httpResponse.getStatusLine().getStatusCode());
		assertEquals("This number is already in this column", getAttributeFromJson(httpResponse, "errorMessage"));
	}
	
	@Test
	public void numberInTheSameSquareTest() {
		String id = createGameAndGetItsId();
		request = new HttpPost(SUDOKU_URL + "putNumber/" + id + "?row=2&column=2&value=7");
		httpResponse = callSudokuService(httpClient, request);
		assertEquals(400, httpResponse.getStatusLine().getStatusCode());
		assertEquals("This number is already in this square", getAttributeFromJson(httpResponse, "errorMessage"));
	}

	private String createGameAndGetItsId() {
		request = new HttpPost(SUDOKU_URL + "newGame");
		httpResponse = callSudokuService(httpClient, request);
		return getAttributeFromJson(httpResponse, "gameId");
	}

	private String getJsonBody(HttpResponse httpResponse) {
		String result = "";
		try {
			result = EntityUtils.toString(httpResponse.getEntity());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	private HttpResponse callSudokuService(HttpClient httpClient, HttpPost request) {
		HttpResponse response = null;
		try {
			response = httpClient.execute(request);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	
	private String getAttributeFromJson(HttpResponse httpResponse, String attribute) {
		JsonParser parser = new JsonParser();
		String jsonBody = getJsonBody(httpResponse);
		JsonObject obj = parser.parse(jsonBody).getAsJsonObject();
		String token = obj.get(attribute).getAsString();
		return token;
	}
}
