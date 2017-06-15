package be.howest.game.mastermind.commons;

import java.util.Locale;

public enum Color {
	
	RED, ORANGE, YELLOW, GREEN, BLUE, PURPLE, PINK, WHITE, BROWN, BLACK;
	
	public String getName() {
		return this.toString().toLowerCase(Locale.ENGLISH);
	}
	
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
