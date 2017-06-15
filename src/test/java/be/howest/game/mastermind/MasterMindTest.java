package be.howest.game.mastermind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class MasterMindTest {

	private Template template = null;
	private MasterMindManager manager = null;
	private Secret secret = null;
	int[] secretArray;
	int[] matchingAttempt;
	int[] mismatchingAttempt;
	private Feedback matchingFeedback = null;
	private Feedback mismatchingFeedback = null;
	private Feedback resignedGameFeedback = null;
	private MasterMind masterMind = null;
	
	@Before
	public void before() {
		template = new Template(100, 8, 10); // boardHeight = 18
		manager = new MasterMindManager();
		secretArray = manager.createSecretArray(template);
		matchingAttempt = Arrays.copyOf(secretArray, secretArray.length);
		mismatchingAttempt = manager.createSecretArray(template);
		while (Objects.deepEquals(secretArray, mismatchingAttempt)) {
			mismatchingAttempt = manager.createSecretArray(template);
		}
		secret = new Secret(secretArray, template);
		matchingFeedback = new Feedback(secret, matchingAttempt);
		mismatchingFeedback = new Feedback(secret, mismatchingAttempt);
		resignedGameFeedback = new Feedback(secret, matchingAttempt, true);
		masterMind = new MasterMind(secret, template);
	}

	@Test
	public void getTemplate_returns_correct_template() {
		assertEquals(template, masterMind.getTemplate());
	}

	@Test
	public void getHistory_returns_correct_history() {
		assertNotNull(masterMind.getHistory());
		assertEquals(History.class, masterMind.getHistory().getClass());
	}
	
	@Test
	public void getLastFeedback_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		assertNull(masterMind.getLastFeedback());
		for (int i = 0; i < template.getBoardHeight(); i++) {
			int attempt[] = manager.createSecretArray(template);
			Feedback feedback = new Feedback(secret, attempt);
			if (feedback.isWon()) {
				i--;
			} else {
				Feedback feedbackFromCheck = masterMind.check(attempt);
				Feedback lastFeedback = masterMind.getLastFeedback();
				assertEquals(feedback, feedbackFromCheck);
				assertEquals(feedbackFromCheck, lastFeedback);
				assertArrayEquals(attempt, lastFeedback.getAttempt());
			}
		}
		int attempt[] = manager.createSecretArray(template);
		Feedback feedback = new Feedback(secret, attempt);
		Feedback feedbackFromCheck = masterMind.check(attempt);
		assertNull(feedbackFromCheck);
		Feedback lastFeedback = masterMind.getLastFeedback();
		assertNotEquals(feedback, feedbackFromCheck);
		assertNotEquals(feedbackFromCheck, lastFeedback);
	}

	@Test
	public void getLastFeedback_returns_correct_feedback_after_winning() {
		Feedback feedbackFromCheck = masterMind.check(matchingAttempt); 
		assertNotNull(feedbackFromCheck);
		assertEquals(matchingFeedback, feedbackFromCheck);
		
		assertNull(masterMind.resign());
		assertEquals(matchingFeedback, masterMind.getLastFeedback());
	}

	@Test
	public void getLastFeedback_returns_correct_feedback_after_resigning() {
		Feedback feedbackFromCheck = masterMind.resign(); 
		assertNotNull(feedbackFromCheck);
		assertEquals(resignedGameFeedback, feedbackFromCheck);
		
		assertNull(masterMind.check(mismatchingAttempt));
		assertEquals(feedbackFromCheck, masterMind.getLastFeedback());
	}
	
	@Test
	public void getFeedbackArchive_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		List<Feedback> feedbackArchive = new ArrayList<>();
		assertEquals(feedbackArchive, masterMind.getFeedbackArchive());
		for (int i = 0; i < template.getBoardHeight(); i++) {
			assertNotNull(masterMind.check(mismatchingAttempt));
			feedbackArchive.add(mismatchingFeedback);
			assertEquals(feedbackArchive, masterMind.getFeedbackArchive());
		}
		assertNull(masterMind.check(mismatchingAttempt));
		assertEquals(feedbackArchive, masterMind.getFeedbackArchive());
		feedbackArchive.add(mismatchingFeedback);
		assertNotEquals(feedbackArchive, masterMind.getFeedbackArchive());
	}

	@Test
	public void getFeedbackArchive_returns_correct_feedback_after_winning() {
		List<Feedback> feedbackArchive = new ArrayList<>();

		assertNotNull(masterMind.check(matchingAttempt));
		feedbackArchive.add(matchingFeedback);
		assertEquals(feedbackArchive, masterMind.getFeedbackArchive());

		assertNull(masterMind.check(mismatchingAttempt));
		assertEquals(feedbackArchive, masterMind.getFeedbackArchive());

		feedbackArchive.add(mismatchingFeedback);
		assertNotEquals(feedbackArchive, masterMind.getFeedbackArchive());
	}

	@Test
	public void getFeedbackArchive_returns_correct_feedback_after_losing() {
		List<Feedback> feedbackArchive = new ArrayList<>();

		assertNotNull(masterMind.resign());
		feedbackArchive.add(resignedGameFeedback);
		assertEquals(feedbackArchive, masterMind.getFeedbackArchive());

		assertNull(masterMind.check(matchingAttempt));
		assertEquals(feedbackArchive, masterMind.getFeedbackArchive());

		feedbackArchive.add(matchingFeedback);
		assertNotEquals(feedbackArchive, masterMind.getFeedbackArchive());
	}
	
	@Test
	public void isGameOver_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		assertFalse(masterMind.isGameOver());
		for (int i = 0; i < template.getBoardHeight() - 1; i++) {
			assertNotNull(masterMind.check(mismatchingAttempt));
			assertFalse(masterMind.isGameOver());
		}
		assertNotNull(masterMind.check(mismatchingAttempt));
		assertTrue(masterMind.isGameOver());
		assertNull(masterMind.check(matchingAttempt));
	}

	@Test
	public void isGameOver_returns_true_if_game_is_won() {
		assertNotNull(masterMind.check(matchingAttempt));
		assertTrue(masterMind.isGameOver());
		assertNull(masterMind.resign());
	}

	@Test
	public void isGameOver_returns_true_if_game_is_resigned() {
		assertNotNull(masterMind.resign());
		assertTrue(masterMind.isGameOver());
		assertNull(masterMind.check(matchingAttempt));
	}

	@Test
	public void isWon_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		for (int i = 0; i < template.getBoardHeight(); i++) {
			assertNotNull(masterMind.check(mismatchingAttempt));
			assertFalse(masterMind.isWon());
		}
		assertNull(masterMind.check(matchingAttempt));
		assertFalse(masterMind.isWon());
	}

	@Test
	public void isWon_returns_true_if_game_is_won() {
		assertNotNull(masterMind.check(matchingAttempt));
		assertTrue(masterMind.isWon());
		assertNull(masterMind.resign());
		assertTrue(masterMind.isWon());
	}

	@Test
	public void isWon_returns_false_if_game_is_resigned() {
		assertNotNull(masterMind.resign());
		assertFalse(masterMind.isWon());
		assertNull(masterMind.check(matchingAttempt));
		assertFalse(masterMind.isWon());
	}
	
	@Test
	public void masterMind_with_null_secret_is_not_valid() {
		assertFalse(new MasterMind(null, template).isValid());
	}

	@Test
	public void masterMind_with_null_template_is_not_valid() {
		assertFalse(new MasterMind(secret, null).isValid());
	}
	
	@Test
	public void null_masterMind_is_not_valid() {
		assertFalse(MasterMind.isValid(null));
	}
	
	@Test
	public void masterMind_with_incompatible_template_within_secret_is_not_valid() {
		Template templateWithinSecret = new Template(1, 4, 5);
		Template incompatibleTemplate = new Template(1, 4, 7);
		Secret validSecret = new Secret(new int[]{1, 2, 3, 0}, templateWithinSecret);
		MasterMind invalidMasterMind = new MasterMind(validSecret, incompatibleTemplate);
		assertFalse(invalidMasterMind.isValid());
	}
	
	@Test
	public void masterMind_with_invalid_template_or_invalid_secret_is_not_valid() {
		Template invalidTemplate = new Template(8, Template.MAX_BOARD_WIDTH + 1, 5);
		Secret invalidSecret1 = new Secret(secretArray, invalidTemplate);
		Secret invalidSecret2 = new Secret(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9}, template);
		Secret invalidSecret3 = new Secret(new int[]{1, 2, 3, 4, 5, 6, 11, 7}, template);
		assertFalse(new MasterMind(invalidSecret1, invalidTemplate).isValid());
		assertFalse(new MasterMind(invalidSecret2, template).isValid());
		assertFalse(new MasterMind(invalidSecret3, template).isValid());
	}
	
}
