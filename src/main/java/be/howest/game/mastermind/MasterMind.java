package be.howest.game.mastermind;

import java.util.List;
import java.util.Objects;

/**
 * MasterMind Game Class
 * @author Hayk
 *
 */
public class MasterMind {

	private final Secret secret;
	private final Template template;
	private final History history;

	MasterMind(Secret secret, Template template) {
		this.secret = secret;
		this.template = template;
		this.history = new History(template);
	}

	/**
	 * Gets the template which has been used to create this MasterMind
	 * @return The template used to create this MasterMind
	 */
	public Template getTemplate() {
		return template;
	}

	/**
	 * Gets the history of Feedback-instances
	 * @return History of Feedback-instances
	 */
	public History getHistory() {
		return history;
	}

	/**
	 * Gets a copy of Feedback instance list from within history
	 * @return A copy of Feedback instance list
	 */
	public List<Feedback> getFeedbackArchive() {
		return history.getFeedbackArchive();
	}
	
	/**
	 * Gets the last added feedback from history
	 * @return null if no feedback found in history or the last added feedback
	 */
	public Feedback getLastFeedback() {
		return history.getLastFeedback();
	}

	/**
	 * Checks in history if the game is over (based on the last added feedback)
	 * @return False if the game is not over yet, or True if the game is over (won, lost or resigned)
	 */
	public boolean isGameOver() {
		return history.isGameOver();
	}

	/**
	 * Checks in history if the game is won (based on the last added feedback)
	 * @return False if the game is not won (yet) or if the game is lost or has been resigned
	 * or True if the game is won
	 */
	public boolean isWon() {
		return history.isWon();
	}

	/**
	 * Stops (resigns) the game, and gets the corresponding feedback
	 * @return The Feedback containing secret (as attempt) and lost = true
	 */
	public Feedback resign() {
		return check(secret.getColorCodes(), true);
	}
	
	/**
	 * Checks if the attempt, which is made to guess the secret, is correct, and gets corresponding feedback
	 * @param attempt An attempt containing color(-code) combination
	 * @return The corresponding feedback
	 */
	public Feedback check(int[] attempt) {
		return check(attempt, false);
	}

	private Feedback check(int[] attempt, boolean resigned) {
		Feedback feedback = new Feedback(secret, attempt, resigned);
		if (history.addFeedback(feedback))
			return history.getLastFeedback();
		return null;
	}

	/**
	 * Checks if the MasterMind instance is valid
	 * @return False if the MasterMind instance is not valid or True if it is valid
	 */
	public boolean isValid() {
		return MasterMind.isValid(this);
	}
	
	/**
	 * Checks if a masterMind-object is valid
	 * @param masterMind A masterMind-object to be checked on validity
	 * @return False if masterMind is null or the secret is not valid,
	 * or if the secret and masterMind templates are not compatible, or else True 
	 */
	public static boolean isValid(MasterMind masterMind) {
		return masterMind != null && Secret.isValid(masterMind.secret)
				&& Objects.equals(masterMind.template, masterMind.secret.getTemplate());
	}

}
