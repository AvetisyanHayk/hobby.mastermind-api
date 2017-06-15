package be.howest.game.mastermind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class HistoryTest {

	private Template template = null;
	private History history = null;
	private MasterMindManager manager = null;
	private Secret secret = null;
	private Feedback matchingFeedback = null;
	private Feedback mismatchingFeedback = null;
	private Feedback resignedGameFeedback = null;

	@Before
	public void before() {
		template = new Template(100, 8, 10); // boardHeight = 18
		history = new History(template);
		manager = new MasterMindManager();
		int[] secretArray = manager.createSecretArray(template);
		int[] matchingAttempt = Arrays.copyOf(secretArray, secretArray.length);
		int[] mismatchingAttempt = manager.createSecretArray(template);
		while (Objects.deepEquals(secretArray, mismatchingAttempt)) {
			mismatchingAttempt = manager.createSecretArray(template);
		}
		secret = new Secret(secretArray, template);
		matchingFeedback = new Feedback(secret, matchingAttempt);
		mismatchingFeedback = new Feedback(secret, mismatchingAttempt);
		resignedGameFeedback = new Feedback(secret, matchingAttempt, true);
	}

	@Test
	public void new_history_with_null_template_is_not_valid() {
		assertFalse(new History(null).isValid());
	}

	@Test
	public void null_history_is_not_valid() {
		assertFalse(History.isValid(null));
	}

	@Test
	public void getMaxSize_equals_to_boardHeight_of_template() {
		assertEquals(18, history.getMaxSize());
		assertEquals(history.getMaxSize(), template.getBoardHeight());
	}

	@Test
	public void getLastFeedback_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		assertNull(history.getLastFeedback());
		for (int i = 0; i < template.getBoardHeight(); i++) {
			int attempt[] = manager.createSecretArray(template);
			Feedback feedback = new Feedback(secret, attempt);
			if (feedback.isWon()) {
				i--;
			} else {
				assertTrue(history.addFeedback(feedback));
				Feedback lastFeedback = history.getLastFeedback();
				assertEquals(feedback, lastFeedback);
				assertArrayEquals(attempt, lastFeedback.getAttempt());
			}
		}
		int attempt[] = manager.createSecretArray(template);
		Feedback feedback = new Feedback(secret, attempt);
		assertFalse(history.addFeedback(feedback));
		Feedback lastFeedback = history.getLastFeedback();
		assertNotEquals(feedback, lastFeedback);
	}

	@Test
	public void getLastFeedback_returns_correct_feedback_after_winning() {
		assertTrue(history.addFeedback(matchingFeedback));
		assertFalse(history.addFeedback(resignedGameFeedback));
		assertEquals(matchingFeedback, history.getLastFeedback());
	}

	@Test
	public void getLastFeedback_returns_correct_feedback_after_resigning() {
		assertTrue(history.addFeedback(resignedGameFeedback));
		assertFalse(history.addFeedback(mismatchingFeedback));
		assertEquals(resignedGameFeedback, history.getLastFeedback());
	}

	@Test
	public void getFeedbackArchive_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		List<Feedback> feedbackArchive = new ArrayList<>();
		assertEquals(feedbackArchive, history.getFeedbackArchive());
		for (int i = 0; i < template.getBoardHeight(); i++) {
			assertTrue(history.addFeedback(mismatchingFeedback));
			feedbackArchive.add(mismatchingFeedback);
			assertEquals(feedbackArchive, history.getFeedbackArchive());
		}
		assertFalse(history.addFeedback(mismatchingFeedback));
		assertEquals(feedbackArchive, history.getFeedbackArchive());
		feedbackArchive.add(mismatchingFeedback);
		assertNotEquals(feedbackArchive, history.getFeedbackArchive());
	}

	@Test
	public void getFeedbackArchive_returns_correct_feedback_after_winning() {
		List<Feedback> feedbackArchive = new ArrayList<>();

		assertTrue(history.addFeedback(matchingFeedback));
		feedbackArchive.add(matchingFeedback);
		assertEquals(feedbackArchive, history.getFeedbackArchive());

		assertFalse(history.addFeedback(mismatchingFeedback));
		assertEquals(feedbackArchive, history.getFeedbackArchive());

		feedbackArchive.add(mismatchingFeedback);
		assertNotEquals(feedbackArchive, history.getFeedbackArchive());
	}

	@Test
	public void getFeedbackArchive_returns_correct_feedback_after_losing() {
		List<Feedback> feedbackArchive = new ArrayList<>();

		assertTrue(history.addFeedback(resignedGameFeedback));
		feedbackArchive.add(resignedGameFeedback);
		assertEquals(feedbackArchive, history.getFeedbackArchive());

		assertFalse(history.addFeedback(matchingFeedback));
		assertEquals(feedbackArchive, history.getFeedbackArchive());

		feedbackArchive.add(matchingFeedback);
		assertNotEquals(feedbackArchive, history.getFeedbackArchive());
	}

	@Test
	public void getFeedbackCount_returns_correct_values_after_adding_feedback_multiple_times() {
		for (int i = 0; i < template.getBoardHeight(); i++) {
			assertTrue(history.addFeedback(mismatchingFeedback));
			assertEquals(i + 1, history.getFeedbackCount());
		}
		int size = history.getFeedbackArchive().size();
		assertFalse(history.addFeedback(mismatchingFeedback));
		assertEquals(size, history.getFeedbackCount());
	}

	@Test
	public void isAvailable_and_isLastTimeAvailable_return_correct_values_after_adding_mismatching_feedback_multiple_times() {
		assertTrue(history.isAvailable());
		assertFalse(history.isLastTimeAvailable());
		for (int i = 0; i < template.getBoardHeight(); i++) {
			if (i == template.getBoardHeight() - 1) {
				assertTrue(history.isLastTimeAvailable());
			} else {
				assertFalse(history.isLastTimeAvailable());
			}
			assertTrue(history.isAvailable());
			assertTrue(history.addFeedback(mismatchingFeedback));
		}
		assertFalse(history.isAvailable());
		assertFalse(history.isLastTimeAvailable());
		assertFalse(history.addFeedback(mismatchingFeedback));
		assertFalse(history.isAvailable());
		assertFalse(history.isLastTimeAvailable());
	}

	@Test
	public void isAvailable_returns_false_if_game_is_won() {
		assertTrue(history.addFeedback(matchingFeedback));
		assertFalse(history.isAvailable());
	}

	@Test
	public void isAvailable_returns_false_if_game_is_resigned() {
		assertTrue(history.addFeedback(resignedGameFeedback));
		assertFalse(history.isAvailable());
	}

	@Test
	public void isLastTimeAvailable_returns_false_if_game_is_won() {
		for (int i = 0; i < template.getBoardHeight() - 2; i++) {
			assertTrue(history.addFeedback(mismatchingFeedback));
		}
		assertTrue(history.addFeedback(matchingFeedback));
		assertFalse(history.isLastTimeAvailable());
	}

	@Test
	public void isLastTimeAvailable_returns_false_if_game_is_resigned() {
		for (int i = 0; i < template.getBoardHeight() - 2; i++) {
			assertTrue(history.addFeedback(mismatchingFeedback));
		}
		assertTrue(history.addFeedback(resignedGameFeedback));
		assertFalse(history.isLastTimeAvailable());
	}

	@Test
	public void isEmpty_returns_correct_values_after_adding_feedback_multiple_times() {
		assertTrue(history.isEmpty());
		for (int i = 0; i < template.getBoardHeight(); i++) {
			assertTrue(history.addFeedback(mismatchingFeedback));
			assertFalse(history.isEmpty());
		}
		assertFalse(history.isEmpty());
		assertFalse(history.addFeedback(mismatchingFeedback));
		assertFalse(history.isEmpty());
	}

	@Test
	public void isGameOver_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		assertFalse(history.isGameOver());
		for (int i = 0; i < template.getBoardHeight() - 1; i++) {
			assertTrue(history.addFeedback(mismatchingFeedback));
			assertFalse(history.isGameOver());
		}
		assertTrue(history.addFeedback(mismatchingFeedback));
		assertTrue(history.isGameOver());
		assertFalse(history.addFeedback(matchingFeedback));
	}

	@Test
	public void isGameOver_returns_true_if_game_is_won() {
		assertTrue(history.addFeedback(matchingFeedback));
		assertTrue(history.isGameOver());
		assertFalse(history.addFeedback(resignedGameFeedback));
	}

	@Test
	public void isGameOver_returns_true_if_game_is_resigned() {
		assertTrue(history.addFeedback(resignedGameFeedback));
		assertTrue(history.isGameOver());
		assertFalse(history.addFeedback(matchingFeedback));
	}

	@Test
	public void isWon_returns_correct_values_after_adding_mismatching_feedback_multiple_times() {
		for (int i = 0; i < template.getBoardHeight(); i++) {
			assertTrue(history.addFeedback(mismatchingFeedback));
			assertFalse(history.isWon());
		}
		assertFalse(history.addFeedback(matchingFeedback));
		assertFalse(history.isWon());
	}

	@Test
	public void isWon_returns_true_if_game_is_won() {
		assertTrue(history.addFeedback(matchingFeedback));
		assertTrue(history.isWon());
		assertFalse(history.addFeedback(resignedGameFeedback));
		assertTrue(history.isWon());
	}

	@Test
	public void isWon_returns_false_if_game_is_resigned() {
		assertTrue(history.addFeedback(resignedGameFeedback));
		assertFalse(history.isWon());
		assertFalse(history.addFeedback(matchingFeedback));
		assertFalse(history.isWon());
	}

}
