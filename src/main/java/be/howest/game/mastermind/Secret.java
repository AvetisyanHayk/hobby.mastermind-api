package be.howest.game.mastermind;

import java.util.Arrays;

import be.howest.game.mastermind.util.Tools;

class Secret {
	
	private final int[] colorCodes;
	private final Template template;
	
	Secret(int[] colorCodes, Template template) {
		this.colorCodes = colorCodes;
		this.template = template;
	}
	
	int[] getColorCodes() {
		return Arrays.copyOf(colorCodes, colorCodes.length);
	}
	
	Template getTemplate() {
		return template;
	}
	
	int getBoardWidth() {
		return template.getBoardWidth();
	}
	
	int getBoardHeight() {
		return template.getBoardHeight();
	}
	
	int getColorCount() {
		return template.getColorCount();
	}
	
	int getColorMatchCount(int[] attempt) {
		int colorMatchCount = 0;
		if (Feedback.isValidAttempt(attempt, getBoardWidth(), getColorCount()))
			for (int color : attempt)
				if (color >= 0 && Tools.arrayContainsValue(colorCodes, color))
					colorMatchCount++;
		return colorMatchCount;
	}
	
	int getPositionMatchCount(int[] attempt) {
		int positionMatchCount = 0;
		if (Feedback.isValidAttempt(attempt, getBoardWidth(), getColorCount()))
			for (int i = 0; i < attempt.length; i++)
				if (colorCodes[i] == attempt[i])
					positionMatchCount++;			
		return positionMatchCount;
	}
	
	boolean isValid() {
		return Secret.isValid(this);
	}
	
	static boolean isValid(Secret secret) {
		return secret != null && isValid(secret.colorCodes, secret.template);
	}
	
	static boolean isValid(int[] colorCodes, Template template) {
		if (!template.isValid())
			return false;
		if (colorCodes == null)
			return false;
		if (colorCodes.length != template.getBoardWidth())
			return false;
		for (int colorCode : colorCodes)
			if (colorCode < 0 || colorCode >= template.getColorCount())
				return false;
		return true;
	}

}
