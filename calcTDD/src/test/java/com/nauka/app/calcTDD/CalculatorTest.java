package com.nauka.app.calcTDD;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import javax.jws.Oneway;

import org.hamcrest.core.IsEqual;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class CalculatorTest {

	public static Calculator calculator;
	public static double actual;
	public static double expected;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		calculator = new Calculator();
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	
	
	@Test
	public void Should_Return_Zero_For_Empty_String() throws NegativesNotAllowedException {
		
		expected = 0;
		actual = calculator.calc("");
		assertEquals("Calculator does not return zero!", expected, actual, 0);
	}
	@Test
	public void Should_Return_One_Param_From_One_Input_Param() throws NegativesNotAllowedException {
		
		expected = 1;
		actual = calculator.calc("1");
		assertEquals("Calculator does not returned the inserted parametr!", expected, actual, 0);
	}
	
	@Test
	public void Should_Return_Sum_Of_Two_Param() throws NegativesNotAllowedException {
		
		expected = 5;
		actual = calculator.calc("1,4");
		assertEquals("Calculator does not returned correct sum of 2 parametrs!", expected, actual, 0);
	}
	@Test
	public void Should_Return_Sum_Of_Multiple_Parametres() throws NegativesNotAllowedException {
		
		expected = 15;
		actual = calculator.calc("1,4,6,4");
		assertEquals("Calculator does not returned correct sum of multiple parameters!", expected, actual, 0);
	}
	@Test
	public void Check_If_New_line_Separator_Works() throws NegativesNotAllowedException {
		
		expected = 8;
		actual = calculator.calc("1\n2,1\n4");
		assertEquals("New line separator \"\\n\"does not works!", expected, actual, 0);
	}
	@Test
	public void Check_For_Differen_Separators() throws NegativesNotAllowedException {
		
		expected = 16;
		actual = calculator.calc("//[;]\n1;2;5;8");
		assertThat("Diffrent separators does not work",actual, is(equalTo(expected)));
	}
	@Test(expected = NegativesNotAllowedException.class)
	public void Should_Return_Exception_Negatives_Not_Allowed() throws NegativesNotAllowedException {

		actual = calculator.calc("//[;]\n1;2;5;-8");
	}
	@Test
	public void Ignore_Numbers_Greater_Then_1000() throws NegativesNotAllowedException {
		
		expected = 16;
		//pasuje
		actual = calculator.calc("//[;]\n2;2000;3000;9000;90");
		assertThat("Numbers over 2000 does not work!",actual, is(equalTo(expected)));
	}
	

}
