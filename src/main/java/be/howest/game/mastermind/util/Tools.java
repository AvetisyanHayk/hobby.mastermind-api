package be.howest.game.mastermind.util;

/**
 * Tools utility, containing functions for general use
 * @author Hayk
 *
 */
public final class Tools {

	private Tools() {}
	
	/**
	 * Checks if an integer-array contains certain integer-value
	 * @param array The referenced array
	 * @param value The value to be found in array
	 * @return False if array is null or if value not found, True if value is found
	 */
	public static boolean arrayContainsValue(int[] array, int value) {
		if (array == null)
			return false;
		for (int item : array)
			if (item == value)
				return true;
		return false;
	}
	
	/**
	 * Creates and gets array of specific length, where each value equals to -1
	 * @param length Specific array length
	 * @return Array of specific length where each value equals to -1
	 */
	public static int[] getInitialColorsArray(int length) {
		int[] colors = new int[length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = -1;
		}
		return colors;
	}
	
}
