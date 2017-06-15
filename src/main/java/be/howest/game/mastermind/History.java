package be.howest.game.mastermind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Contains and manages all Feedback instances that have been generated after attempts
 * to guess the MasterMind secret color(-code) combination
 * @author Hayk
 *
 */
public class History {
	
	private final List<Feedback> feedbackArchive = new ArrayList<>();
	private final Template template;
	
	/**
	 * History class constructor specifying the template which is used for a certain MasterMind game session
	 * @param template A MasterMind game session template
	 */
	public History(Template template) {
		this.template = template;
	}
	
	/**
	 * Gets a copy of Feedback instance list
	 * @return A copy of Feedback instance list
	 */
	public List<Feedback> getFeedbackArchive() {
		return Collections.unmodifiableList(feedbackArchive);
	}
	
	/**
	 * Adds a feedback to the archive
	 * @param feedback a new feedback to be added tot the archive
	 * @return False if the new feedback instance is not valid or if there's no available place for any new feedback
	 * in the archive, or else True
	 */
	boolean addFeedback(Feedback feedback) {
		boolean added = false;
		if (Feedback.isValid(feedback) && isAvailable()) {
			added = feedbackArchive.add(feedback);
		}
		return added;
	}
	
	/**
	 * Gets the last added feedback from the archive
	 * @return null if no feedback found in the archive or the last added feedback
	 */
	public Feedback getLastFeedback() {
		if (isEmpty()) {
			return null;
		}
		return feedbackArchive.get(feedbackArchive.size() - 1);
	}
	
	/**
	 * Gets the total count of feedback instances in the archive
	 * @return Total count of feedback instances
	 */
	public int getFeedbackCount() {
		return feedbackArchive.size();
	}
	
	int getMaxSize() {
		return template.getBoardHeight();
	}
	
	boolean isAvailable() {
		return !isGameOver() && feedbackArchive.size() < getMaxSize();
	}
	
	boolean isLastTimeAvailable() {
		return !isGameOver() && feedbackArchive.size() == getMaxSize() - 1;
	}
	
	boolean isEmpty() {
		return feedbackArchive.isEmpty();
	}

	/**
	 * Checks if the game is over (based on the last added feedback)
	 * @return False if the game is not over yet, or True if the game is over (won, lost or resigned)
	 */
	public boolean isGameOver() {
		return !isEmpty() && (getLastFeedback().isGameOver()
				|| feedbackArchive.size() == getMaxSize());
	}
	
	/**
	 * Checks if the game is won (based on the last added feedback)
	 * @return False if the game is not won (yet) or if the game is lost or has been resigned
	 * or True if the game is won
	 */
	public boolean isWon() {
		return !isEmpty() && getLastFeedback().isWon();
	}
	
	/**
	 * Checks if the history is valid
	 * @return False if the history is not valid or True if it is valid
	 */
	public boolean isValid() {
		return History.isValid(this);
	}
	
	/**
	 * Checks if a history-object is valid
	 * @param history a history-object to be checked on validity
	 * @return False if a history-object is null or contains invalid template, or else True
	 */
	public static boolean isValid(History history) {
		return history != null && Template.isValid(history.template);
	}

}
