package be.howest.game.mastermind.util;

public final class Tools {

	private Tools() {}
	
	public static boolean arrayContainsValue(int[] array, int value) {
		for (int item : array) {
			if (item == value) {
				return true;
			}
		}
		return false;
	}
	
	public static int[] getInitialColorsArray(int length) {
		int[] colors = new int[length];
		for (int i = 0; i < colors.length; i++) {
			colors[i] = -1;
		}
		return colors;
	}
}
