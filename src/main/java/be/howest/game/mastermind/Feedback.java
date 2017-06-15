package be.howest.game.mastermind;

import java.util.Arrays;

public class Feedback {

	private final int[] attempt;
	private final int colorMatchCount;
	private final int positionMatchCount;
	private boolean lost;
	
	Feedback(Secret secret, int[] attempt) {
		this(secret, attempt, false);
	}

	Feedback(Secret secret, int[] attempt, boolean lost) {
		this.attempt = attempt;
		this.lost = lost;
		positionMatchCount = secret.getPositionMatchCount(attempt);
		if (isWon()) {
			colorMatchCount = positionMatchCount;
		} else {
			colorMatchCount = secret.getColorMatchCount(attempt);
		}
	}

	public int[] getAttempt() {
		return Arrays.copyOf(attempt, attempt.length);
	}

	public int getColorMatchCount() {
		return colorMatchCount;
	}

	public int getPositionMatchCount() {
		return positionMatchCount;
	}

	public int getColorMatchPositionMismatchCount() {
		return colorMatchCount - positionMatchCount;
	}

	public boolean isWon() {
		return !lost && isValid() && positionMatchCount == attempt.length;
	}

	public boolean isGameOver() {
		return isWon() || lost;
	}
	
	public boolean isValid() {
		return Feedback.isValid(this);
	}
	
	public static boolean isValid(Feedback feedback) {
		return feedback != null
				&& feedback.attempt != null && feedback.attempt.length > 0
				&& feedback.positionMatchCount <= feedback.colorMatchCount
				&& feedback.colorMatchCount <= feedback.attempt.length;
	}

	public static boolean isValidAttempt(int[] attempt, int boardWidth, int colorCount) {
		return Template.isValidBoardWidth(boardWidth) && attempt.length <= boardWidth
				&& isValidAttempt(attempt, colorCount);
	}
	
	private static boolean isValidAttempt(int[] attempt, int colorCount) {
		if (attempt == null || !Template.isValidColorCount(colorCount))
			return false;
		int validColorCodeCount = 0;
		for (int colorCode : attempt) {
			if (colorCode >= colorCount)
				return false;
			if (colorCode >= 0)
				validColorCodeCount++;
		}
		return validColorCodeCount > 0;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Feedback other = (Feedback) obj;
		if (!Arrays.equals(attempt, other.attempt))
			return false;
		if (colorMatchCount != other.colorMatchCount)
			return false;
		if (lost != other.lost)
			return false;
		if (positionMatchCount != other.positionMatchCount)
			return false;
		return true;
	}
	
	

}
