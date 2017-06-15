package be.howest.game.mastermind;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SecretTest {
	
	private Template template = null;
	private int[] colorCodes;
	private int[] attempt;
	private Secret secret;
	
	@Before
	public void before() {
		template = new Template(100, 8, 10);
		colorCodes = new int[]{0, 5, 3, 4, 8, 7, 2, 1};
		attempt = new int[]{0, 5, 4, 7, 1, -1};
		secret = new Secret(colorCodes, template);
	}

	@Test
	public void getColorCodes_returns_correct_color_codes() {
		assertArrayEquals(colorCodes, secret.getColorCodes());
	}

	@Test
	public void getTemplate_returns_correct_template() {
		assertEquals(template, secret.getTemplate());
	}
	
	@Test
	public void getBoardWidth_returns_correct_boardWidth() {
		assertEquals(template.getBoardWidth(), secret.getBoardWidth());
	}
	
	@Test
	public void getColorCount_returns_correct_colorCount() {
		assertEquals(template.getColorCount(), secret.getColorCount());
	}
	
	@Test
	public void getBoardHeight_returns_correct_boardHeight() {
		assertEquals(template.getBoardHeight(), secret.getBoardHeight());
	}
	
	@Test
	public void getColorMatchCount_returns_total_count_of_matched_colors() {
		assertEquals(5, secret.getColorMatchCount(attempt));
	}
	
	@Test
	public void getPositionMatchCount_returns_total_count_of_matched_colors_at_correct_position() {
		assertEquals(2, secret.getPositionMatchCount(attempt));
	}
	
	@Test
	public void null_secret_is_not_valid() {
		assertFalse(Secret.isValid(null));
	}
	
	@Test
	public void secret_with_invalid_color_codes_is_not_valid() {
		int[] invalidColorCodes1 = {1, 2, 11, 3, 4, 5, 6, 7};
		int[] invalidColorCodes2 = {0, 1, 2, 3, 4, 5, 6, 7, 8};
		int[] invalidColorCodes3 = {0};
		int[] invalidColorCodes4 = {};
		assertFalse(Secret.isValid(new Secret(invalidColorCodes1, template)));
		assertFalse(Secret.isValid(new Secret(invalidColorCodes2, template)));
		assertFalse(Secret.isValid(new Secret(invalidColorCodes3, template)));
		assertFalse(Secret.isValid(new Secret(invalidColorCodes4, template)));
	}
	
	@Test
	public void secret_with_invalid_template_not_valid() {
		Template invalidTemplate = new Template(-9, 8, 10);
		int[] validColorCodes = {0, 1, 2, 3, 4, 5, 6, 7};
		assertFalse(Secret.isValid(new Secret(validColorCodes, invalidTemplate)));
	}
	
}
