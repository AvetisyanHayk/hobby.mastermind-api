package be.howest.game.mastermind.commons;

import java.util.Locale;

/**
 * Color enumeration for a MasterMind game
 * @author Hayk
 *
 */
public enum Color {
	
	RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, WHITE, BROWN, BLACK;
	
	/**
	 * Gets the lowercase color name
	 * @return Lowercase color name
	 */
	public String getName() {
		return this.toString().toLowerCase(Locale.ENGLISH);
	}
	
	/**
	 * Gets Color enumerated value by a color name
	 * @param name Color name
	 * @return null if name is null or if such a color does not exist in the Enumeration, or the Color if it exists
	 */
	public static Color get(String name){
		if (name == null)
			return null;
		for(Color color : values()) {
			if (name.equalsIgnoreCase(color.toString()))
				return color;
		}
		return null;
	}
	
}
