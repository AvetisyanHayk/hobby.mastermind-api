package be.howest.game.mastermind.services;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import be.howest.game.mastermind.commons.Color;

/**
 * Color Service class
 * @author Hayk
 *
 */
public class ColorService {
	
	private static final Map<Integer, Color> COLORS = new LinkedHashMap<>();

	static {
		COLORS.put(0, Color.RED);
		COLORS.put(1, Color.ORANGE);
		COLORS.put(2, Color.YELLOW);
		COLORS.put(3, Color.GREEN);
		COLORS.put(4, Color.BLUE);
		COLORS.put(5, Color.PURPLE);
		COLORS.put(6, Color.PINK);
		COLORS.put(7, Color.WHITE);
		COLORS.put(8, Color.BROWN);
		COLORS.put(9, Color.BLACK);
	}

	/**
	 * Reads a color by id (color-code)
	 * @param id Color-code
	 * @return Null if color not found or the corresponding Color (Color enum value)
	 */
	public Color read(int id) {
		if (id >= 0 && id < count())
			return COLORS.get(id);
		return null;
	}
	
	/**
	 * Gets List of colors by ids (color-codes)
	 * @param colorIds Color-codes
	 * @return ArrayList of colors (nulls as well)
	 */
	public List<Color> findAllByIds(int[] colorIds) {
		List<Color> colors = new ArrayList<>();
		for (int colorId : colorIds) {
			Color color = read(colorId);
			if(!colors.contains(color)) {
				colors.add(color);
			}
		}
		return colors;
	}

	/**
	 * Gets total count of available colors
	 * @return Total count of available colors
	 */
	public int count() {
		return COLORS.size();
	}
	
}
