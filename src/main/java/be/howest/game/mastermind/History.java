package be.howest.game.mastermind;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class History {
	
	private final List<Feedback> feedbackArchive = new ArrayList<>();
	private final Template template;
	
	public History(Template template) {
		this.template = template;
	}
	
	public List<Feedback> getFeedbackArchive() {
		return Collections.unmodifiableList(feedbackArchive);
	}
	
	boolean addFeedback(Feedback feedback) {
		boolean added = false;
		if (Feedback.isValid(feedback) && isAvailable()) {
			added = feedbackArchive.add(feedback);
		}
		return added;
	}
	
	public Feedback getLastFeedback() {
		if (isEmpty()) {
			return null;
		}
		return feedbackArchive.get(feedbackArchive.size() - 1);
	}
	
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

	public boolean isGameOver() {
		return !isEmpty() && (getLastFeedback().isGameOver()
				|| feedbackArchive.size() == getMaxSize());
	}
	
	public boolean isWon() {
		return !isEmpty() && getLastFeedback().isWon();
	}
	
	public boolean isValid() {
		return History.isValid(this);
	}
	
	public static boolean isValid(History history) {
		return history != null && Template.isValid(history.template);
	}

}
