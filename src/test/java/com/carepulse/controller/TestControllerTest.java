package com.carepulse.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class TestControllerTest {

	@Test
	public void testControllerMessage() {
		TestController testController = new TestController();
		String message = testController.test();
		assertEquals("CarePulse API Running by rajuuuuu", message);
	}
}