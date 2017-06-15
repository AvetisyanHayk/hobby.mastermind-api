package be.howest.game.mastermind.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class ToolsTest {
	
	@Test
	public void arrayContainsValue_returns_false_if_array_is_null() {
		assertFalse(Tools.arrayContainsValue(null, 1));
	}
	
	@Test
	public void arrayContainsValue_returns_true_if_array_contains_value() {
		int[] array = {44, 7, -5, 6, 8, 10};
		for (int value : array) {
			assertTrue(Tools.arrayContainsValue(array, value));
		}
	}
	
	@Test
	public void arrayContainsValue_returns_false_if_array_is_empty() {
		int[] array = {};
		assertFalse(Tools.arrayContainsValue(array, 1));
	}
	
	@Test
	public void arrayContainsValue_returns_false_if_array_does_not_contain_value() {
		int[] array = {44, 7, -5, 6, 8, 10};
		for (int value = 45; value < 200; value++) {
			assertFalse(Tools.arrayContainsValue(array, value));
		}
	}
	
	@Test(expected = NegativeArraySizeException.class)
	public void getInitialColorsArray_throws_NegativeArraySizeException_if_the_length_of_requested_array_is_negative() {
		assertNull(Tools.getInitialColorsArray(-5));
	}
	
	@Test
	public void getInitialColorsArray_returns_empty_array_if_the_length_of_requested_array_equals_to_0() {
		int[] emptyArray = {};
		assertArrayEquals(emptyArray, Tools.getInitialColorsArray(0));
	}
	
	@Test
	public void getInitialColorsArray_returns_correct_array_of_minus_ones_if_the_length_of_requested_array_is_valid() {
		int[] arrayOfMinusOne = {-1};
		int[] arrayOfTwoMinusOnes = {-1, -1};
		int[] arrayOfTenMinusOnes = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
		assertArrayEquals(arrayOfMinusOne, Tools.getInitialColorsArray(1));
		assertArrayEquals(arrayOfTwoMinusOnes, Tools.getInitialColorsArray(2));
		assertArrayEquals(arrayOfTenMinusOnes, Tools.getInitialColorsArray(10));
		
		int[] arrayOfHundredMinusOnes = Tools.getInitialColorsArray(100);
		assertArrayEquals(arrayOfHundredMinusOnes, Tools.getInitialColorsArray(100));
		for (int value : arrayOfHundredMinusOnes) {
			assertEquals(-1, value);
		}
	}

}
