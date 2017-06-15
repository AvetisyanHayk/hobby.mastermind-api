package be.howest.game.mastermind;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

public class TemplateTest {

	@Test
	public void getId_returns_correct_id() {
		int boardWidth = Template.MIN_BOARD_WIDTH + 1;
		int colorCount = Template.MIN_COLOR_COUNT + 1; 
		assertEquals(0, new Template(0, boardWidth,colorCount).getId());
		assertEquals(1, new Template(1, boardWidth,colorCount).getId());
	}
	
	@Test
	public void getBoardWidth_returns_correct_boardWidth() {
		int boardWidth1 = Template.MIN_BOARD_WIDTH;
		int boardWidth2 = boardWidth1 + 1;
		int boardWidth3 = Template.MAX_BOARD_WIDTH;
		int boardWidth4 = boardWidth3 - 1;
		int colorCount = Template.MIN_COLOR_COUNT + 1;
		assertEquals(boardWidth1, new Template(0, boardWidth1, colorCount).getBoardWidth());
		assertEquals(boardWidth2, new Template(0, boardWidth2, colorCount).getBoardWidth());
		assertEquals(boardWidth3, new Template(0, boardWidth3, colorCount).getBoardWidth());
		assertEquals(boardWidth4, new Template(0, boardWidth4, colorCount).getBoardWidth());
	}
	
	@Test
	public void getColorCount_returns_correct_colorCount() {
		int boardWidth = Template.MIN_BOARD_WIDTH + 1;
		int colorCount1 = Template.MIN_COLOR_COUNT;
		int colorCount2 = colorCount1 + 1;
		int colorCount3 = Template.MAX_COLOR_COUNT;
		int colorCount4 = colorCount3 - 1;
		assertEquals(colorCount1, new Template(0, boardWidth, colorCount1).getColorCount());
		assertEquals(colorCount2, new Template(0, boardWidth, colorCount2).getColorCount());
		assertEquals(colorCount3, new Template(0, boardWidth, colorCount3).getColorCount());
		assertEquals(colorCount4, new Template(0, boardWidth, colorCount4).getColorCount());
	}
	
	@Test
	public void getBoardHeight_calculates_and_returns_correct_boardHeight() {
		int id = 2;
		assertEquals(8, new Template(id, 4, 4).getBoardHeight());
		assertEquals(8, new Template(id, 4, 5).getBoardHeight());
		assertEquals(14, new Template(id, 7, 5).getBoardHeight());
	}
	
	@Test
	public void two_MasterMindTemplates_with_same_boardWidth_and_colorCount_are_same() {
		int boardWidth = 7;
		int colorCount = 5;
		Template masterMindTemplate1 = new Template(2, boardWidth, colorCount);
		Template masterMindTemplate2 = new Template(4, boardWidth, colorCount);
		assertEquals(masterMindTemplate1, masterMindTemplate2);
	}
	
	@Test
	public void two_MasterMindTemplates_with_same_boardWidth_and_colorCount_have_same_hashCode() {
		int boardWidth = 7;
		int colorCount = 5;
		Template masterMindTemplate1 = new Template(2, boardWidth, colorCount);
		Template masterMindTemplate2 = new Template(4, boardWidth, colorCount);
		assertEquals(masterMindTemplate1.hashCode(), masterMindTemplate2.hashCode());
	}
	
	@Test
	public void two_MasterMindTemplates_with_different_boardWidth_and_colorCount_are_not_same() {
		int id = 2;
		Template masterMindTemplate1 = new Template(id, 5, 4);
		Template masterMindTemplate2 = new Template(id, 4, 4);
		Template masterMindTemplate3 = new Template(id, 4, 5);
		assertNotEquals(masterMindTemplate1, masterMindTemplate2);
		assertNotEquals(masterMindTemplate2, masterMindTemplate3);
		assertNotEquals(masterMindTemplate3, masterMindTemplate1);
	}
	
	@Test
	public void two_MasterMindTemplates_with_different_boardWidth_and_colorCount_have_different_hashCodes() {
		int id = 2;
		Template masterMindTemplate1 = new Template(id, 5, 4);
		Template masterMindTemplate2 = new Template(id, 4, 4);
		Template masterMindTemplate3 = new Template(id, 4, 5);
		assertNotEquals(masterMindTemplate1.hashCode(), masterMindTemplate2.hashCode());
		assertNotEquals(masterMindTemplate2.hashCode(), masterMindTemplate3.hashCode());
		assertNotEquals(masterMindTemplate3.hashCode(), masterMindTemplate1.hashCode());
	}
	
	@Test
	public void template_with_negative_id_is_not_valid() {
		assertFalse(new Template(-1, Template.MIN_BOARD_WIDTH + 1,
				Template.MIN_COLOR_COUNT + 1).isValid());
	}
	
	@Test
	public void template_with_smaller_boardWidth_than_minimum_is_not_valid() {
		int boardWidth = Template.MIN_BOARD_WIDTH - 1;
		assertFalse(new Template(2, boardWidth,
				Template.MIN_COLOR_COUNT + 1).isValid());
	}
	
	@Test
	public void template_with_larger_boardWidth_than_maximum_is_not_valid() {
		int boardWidth = Template.MAX_BOARD_WIDTH + 1;
		assertFalse(new Template(2, boardWidth,
				Template.MIN_COLOR_COUNT + 1).isValid());
	}
	
	@Test
	public void template_with_smaller_colorCount_than_minimum_is_not_valid() {
		int colorCount = Template.MIN_COLOR_COUNT - 1;
		assertFalse(new Template(2, Template.MIN_BOARD_WIDTH + 1,
				colorCount).isValid());
	}
	
	@Test
	public void new_instance_with_larger_colorCount_than_maximum_is_not_valid() {
		int colorCount = Template.MAX_COLOR_COUNT + 1;
		assertFalse(new Template(2, Template.MIN_BOARD_WIDTH + 1,
				colorCount).isValid());
	}

}
