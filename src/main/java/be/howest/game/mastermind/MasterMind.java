package be.howest.game.mastermind;

import java.util.List;
import java.util.Objects;

public class MasterMind {

	private final Secret secret;
	private final Template template;
	private final History history;

	MasterMind(Secret secret, Template template) {
		this.secret = secret;
		this.template = template;
		this.history = new History(template);
	}

	public Template getTemplate() {
		return template;
	}

	public History getHistory() {
		return history;
	}

	public List<Feedback> getFeedbackArchive() {
		return history.getFeedbackArchive();
	}
	
	public Feedback getLastFeedback() {
		return history.getLastFeedback();
	}

	public boolean isGameOver() {
		return history.isGameOver();
	}

	public boolean isWon() {
		return history.isWon();
	}

	public Feedback resign() {
		return check(secret.getColorCodes(), true);
	}
	
	public Feedback check(int[] attempt) {
		return check(attempt, false);
	}

	private Feedback check(int[] attempt, boolean resigned) {
		Feedback feedback = new Feedback(secret, attempt, resigned);
		if (history.addFeedback(feedback))
			return history.getLastFeedback();
		return null;
	}

	public boolean isValid() {
		return MasterMind.isValid(this);
	}

	public static boolean isValid(MasterMind masterMind) {
		return masterMind != null && Secret.isValid(masterMind.secret)
				&& Objects.equals(masterMind.template, masterMind.secret.getTemplate());
	}

}
