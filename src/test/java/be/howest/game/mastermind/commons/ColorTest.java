package be.howest.game.mastermind.commons;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Locale;

import org.junit.Test;

public class ColorTest {

	@Test
	public void getName_returns_correct_name() {
		Arrays.asList(Color.values())
				.forEach(item -> {
					assertEquals(item.getName(), item.toString().toLowerCase(Locale.ENGLISH));
				});
	}
	
	@Test
	public void get_returns_null_if_name_is_null() {
		assertNull(Color.get(null));
	}
	
	@Test
	public void get_returns_null_if_name_does_not_exist() {
		assertNull(Color.get("color"));
	}
	
	@Test
	public void get_returns_appropriate_color() {
		for(Color color : Color.values()) {
			assertEquals(color, Color.get(color.getName()));
		}
	}

}
