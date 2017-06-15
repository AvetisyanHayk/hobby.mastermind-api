package be.howest.game.mastermind;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Objects;

import org.junit.Before;
import org.junit.Test;

public class FeedbackTest {

	private Template template = null;
	private int[] colorCodes = null;
	private int[] attempt = null;
	private Secret secret = null;
	private Feedback feedback = null;
	
	@Before
	public void before() {
		template = new Template(100, 8, 10);
		colorCodes = new int[]{0, 5, 3, 4, 8, 7, 2, 1};
		attempt = new int[]{0, 5, 4, 7, 1, -1};
		secret = new Secret(colorCodes, template);
		feedback = new Feedback(secret, attempt);
	}

	@Test(expected = NullPointerException.class)
	public void new_instance_with_null_as_secret_throws_NullPointerException() {
		assertNull(new Feedback(null, attempt));
	}
	
	@Test(expected = NullPointerException.class)
	public void new_instance_with_null_as_attempt_throws_NullPointerException() {
		assertFalse(new Feedback(secret, null).isValid());
	}
	
	@Test
	public void getAttempt_returns_correct_attempt() {
		assertArrayEquals(attempt, feedback.getAttempt());
	}
	
	@Test
	public void getAttempt_returns_immutable_attempt() {
		int[] immutableAttempt = feedback.getAttempt();
		immutableAttempt[0] = 9;
		assertArrayEquals(attempt, feedback.getAttempt());
		assertFalse(Objects.deepEquals(immutableAttempt, feedback.getAttempt()));
	}
	
	@Test
	public void getColorMatchCount_returns_total_count_of_matched_colors() {
		assertEquals(5, feedback.getColorMatchCount());
	}
	
	@Test
	public void getPositionMatchCount_returns_total_count_of_matched_colors_at_correct_position() {
		assertEquals(2, feedback.getPositionMatchCount());
	}
	
	@Test
	public void getColorMatchPositionMismatchCount_returns_total_count_of_matched_colors_at_incorrect_positions() {
		assertEquals(3, feedback.getColorMatchPositionMismatchCount());
	}
	
	@Test
	public void isWon_returns_true_if_game_is_not_resigned_and_all_colors_are_at_correct_positions() {
		int[] matchingAttempt = colorCodes;
		Feedback matchingFeedback = new Feedback(secret, matchingAttempt);
		assertTrue(matchingFeedback.isWon());
	}
	
	@Test
	public void isWon_returns_false_if_game_is_resigned_even_if_all_colors_are_at_correct_positions() {
		int[] matchingAttempt = colorCodes;
		Feedback resignedGameFeedback = new Feedback(secret, matchingAttempt, true);
		assertFalse(resignedGameFeedback.isWon());
	}
	
	@Test
	public void isWon_returns_false_if_not_all_colors_are_at_correct_positions() {
		assertFalse(feedback.isWon());
	}
	
	@Test
	public void isGameOver_returns_true_if_game_is_won() {
		int[] matchingAttempt = colorCodes;
		Feedback matchingFeedback = new Feedback(secret, matchingAttempt);
		assertTrue(matchingFeedback.isGameOver());
	}
	
	@Test
	public void isGameOver_returns_true_if_game_is_resigned() {
		int[] matchingAttempt = colorCodes;
		Feedback resignedGameFeedback = new Feedback(secret, matchingAttempt, true);
		Feedback anotherResignedGameFeedback = new Feedback(secret, attempt, true);
		assertTrue(resignedGameFeedback.isGameOver());
		assertTrue(anotherResignedGameFeedback.isGameOver());
	}
	
	@Test
	public void isGameOver_returns_false_if_game_is_not_won_or_resigned() {
		assertFalse(feedback.isGameOver());
	}

	@Test
	public void larger_attempt_array_than_secret_array_is_not_valid() {
		assertFalse(Feedback.isValidAttempt(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9},
				secret.getBoardWidth(), secret.getColorCount()));
	}

}
