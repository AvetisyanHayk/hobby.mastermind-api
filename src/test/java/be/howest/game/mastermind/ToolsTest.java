package be.howest.game.mastermind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import be.howest.game.mastermind.util.Tools;

public class ToolsTest {

	@Test
	public void getInitialColorsArray_of_100_returs_array_of_100_integers_equal_to_minus_one() {
		int[] array = Tools.getInitialColorsArray(100);
		assertEquals(100, array.length);
		for (int value : array) {
			assertEquals(-1, value);
		}
	}
	
	@Test
	public void array_contains_value_returns_true_if_array_contains_value() {
		int[] array = {3, 0, 5, -8, 22, 34, -8, -79};
		for (int value : array) {
			assertTrue(Tools.arrayContainsValue(array, value));
		}
	}
	
	@Test
	public void array_contains_value_returns_false_if_array_does_not_contain_value() {
		int[] array = {3, 0, 5, -8, 22, 34, -8, -79};
		for (int value : array) {
			assertFalse(Tools.arrayContainsValue(array, value + 200));
		}
	}

}
