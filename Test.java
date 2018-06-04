package com.mytestapp;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;


public class TestClass {

	MathClass math1, math2;
	
    @Before
    public void setUp() throws Exception {
        math1 = new MathClass(9, 3);
		math2 = new MathClass(10, 5);
    }
    
	@Test
	public void testMultipleThree() {
		int x  = 9 ; int y = 3;
		assertEquals(0, math1.mod(x,y));
	}
	
	@Test
	public void testMultipleFive() {
		int x  = 10 ; int y = 5;
		assertEquals(0, math2.mod(x,y));
	}
	
	

	
}
