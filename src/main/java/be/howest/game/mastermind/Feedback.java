package be.howest.game.mastermind;

import java.util.Arrays;

/**
 * Contains the attempt that has been made to guess the MasterMind secret combination
 * @author Hayk
 *
 */
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

	/**
	 * Gets a copy of the attempt that has been made to guess the MasterMind secret combination
	 * @return A copy of attempt
	 */
	public int[] getAttempt() {
		return Arrays.copyOf(attempt, attempt.length);
	}

	/**
	 * Gets total count of color matches
	 * @return Total count of color matches
	 */
	public int getColorMatchCount() {
		return colorMatchCount;
	}

	/**
	 * Gets total count of color(-code) matches at correct positions
	 * @return Total count of color(-code) matches at correct positions
	 */
	public int getPositionMatchCount() {
		return positionMatchCount;
	}

	/**
	 * Calculates total count of color(-code) matches at wrong positions
	 * @return Total count of color(-code) matches at wrong positions
	 */
	public int getColorMatchPositionMismatchCount() {
		return colorMatchCount - positionMatchCount;
	}

	/**
	 * Checks if the game is won
	 * @return False if the game is not won (yet) or if the game is lost or has been resigned
	 * or True if the game is won
	 */
	public boolean isWon() {
		return !lost && isValid() && positionMatchCount == attempt.length;
	}

	/**
	 * Checks if the game is over
	 * @return False if the game is not over yet, or True if the game is over (won, lost or resigned)
	 */
	public boolean isGameOver() {
		return isWon() || lost;
	}
	
	/**
	 * Checks if the feedback is valid
	 * @return False if the feedback is not valid or True if it is valid
	 */
	public boolean isValid() {
		return Feedback.isValid(this);
	}
	
	/**
	 * Checks if a feedback-object is valid
	 * @param feedback a feedback-object to be checked on validity
	 * @return False if a feedback-object is null or not valid, or True if it is a valid feedback-object
	 */
	public static boolean isValid(Feedback feedback) {
		return feedback != null
				&& feedback.attempt != null && feedback.attempt.length > 0
				&& feedback.positionMatchCount <= feedback.colorMatchCount
				&& feedback.colorMatchCount <= feedback.attempt.length;
	}

	/**
	 * Checks if attempt is valid 
	 * @param attempt an attempt containing color(-code) combination
	 * @param boardWidth MasterMind pawn width which defines the total color(-code)s in secret
	 * @param colorCount Total count of color(-code)s that may be found in a secret
	 * @return False if the boardWidth (the MasterMind pawn width) is not valid,
	 * or if total count of color(-code)s within attempt is more than the total holes on a pawn (boardWidth),
	 * or if the attempt-array contains invalid color(-code)s,
	 * or True if the boardWidth is valid,
	 * total count of color(-code)s within attempt are less or equal to total holes on a pawn (board width),
	 * and the boardWidth is valid
	 */
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

	/**
	 * Returns hashCode of Object superclass. Two instances are same only if both point to the same object
	 * (e.g. only if `Feedback feedback1 = new Feedback(...); Feedback feedback2 = feedback1`;)
	 */
	@Override
	public int hashCode() {
		return super.hashCode();
	}

	/**
	 * Two Feedback instances are equal to each other if they contain
	 * same attempt, colorMatchCount, positionMatchCount, and lost properties
	 */
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
