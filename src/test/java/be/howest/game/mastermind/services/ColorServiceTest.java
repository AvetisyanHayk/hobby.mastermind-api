package be.howest.game.mastermind.services;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import be.howest.game.mastermind.commons.Color;



public class ColorServiceTest {

	private ColorService colorService;

	@Before
	public void before() {
		colorService = new ColorService();
	}

	@Test
	public void read_reads_correct_color() {
		for (int id = 0; id < colorService.count(); id++) {
			Color color = colorService.read(id);
			assertNotNull(color);
		}
		assertNull(colorService.read(colorService.count()));
	}

	@Test
	public void findAllByIds_returns_correct_colors() {
		int count = colorService.count();
		int[] colorIds = new int[count];
		for (int id = 0; id < count; id++) {
			colorIds[id] = count - id - 1;
		}
		List<Color> colors = colorService.findAllByIds(colorIds);
		for (Color color : colors) {
			assertNotNull(color);
		}
	}

	@Test
	public void count_returns_correct_count_of_colors() {
		int count = colorService.count();
		int[] colorIds = new int[count];
		for (int id = 0; id < count; id++) {
			colorIds[id] = count - id - 1;
		}
		List<Color> colors = colorService.findAllByIds(colorIds);
		assertEquals(count, colors.size());
	}

}
