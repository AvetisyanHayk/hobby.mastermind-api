package be.howest.game.mastermind;

import be.howest.game.mastermind.commons.Color;

/**
 * MasterMind board (pawn) template
 * @author Hayk
 *
 */
public class Template {

	/**
	 * Minimum board width (number of holes on pawn per row)
	 */
	public static final int MIN_BOARD_WIDTH = 4;
	
	/**
	 * Maximum board width (number of holes on pawn per row)
	 */
	public static final int MAX_BOARD_WIDTH = 8;
	
	/**
	 * Minimum color count (number of colors that may be used in a MasterMind game)
	 */
	public static final int MIN_COLOR_COUNT = 4;
	
	/**
	 * Maximum color count (number of colors that may be used in a MasterMind game)
	 */
	public static final int MAX_COLOR_COUNT = Color.values().length;

	private final int id;
	private final int boardWidth;
	private final int colorCount;
	
	/**
	 * Template class constructor, specifying an id, board width, and color count
	 * @param id A unique id of a template
	 * @param boardWidth Number of holes on pawn per row
	 * @param colorCount Total count of colors that may be used in a MasterMind game
	 */
	public Template(int id, int boardWidth, int colorCount) {
		this.id = id;
		this.boardWidth = boardWidth;
		this.colorCount = colorCount;
	}

	/**
	 * Gets the id of template
	 * @return The id of template
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the board width (number of holes on pawn per row) of template
	 * @return Board width (number of holes on pawn per row)
	 */
	public int getBoardWidth() {
		return boardWidth;
	}
	
	/**
	 * Gets the total count of colors that may be used in a MasterMind game
	 * @return The total count of colors
	 */
	public int getColorCount() {
		return colorCount;
	}

	/**
	 * Calculates and gets the total board height (maximum attempts to guess the secret)
	 * Note: may not be set manually in this version; may only be overridden within a sub-class of the Template class 
	 * @return Calculated board height (maximum attempts to guess the secret)
	 */
	public int getBoardHeight() {
		int boardHeight = (boardWidth * 3 + colorCount) / 2;
		boardHeight += boardHeight % 2;
		return boardHeight;
	}
	
	/**
	 * Checks if the template is valid
	 * @return False if the template is not valid or True if it is valid
	 */
	public boolean isValid() {
		return Template.isValid(this);
	}
	
	/**
	 * Checks if a template-object is valid
	 * @param template a template-object to be checked on validity
	 * @return False if a template-object is null or has a negative id,
	 * or if it does not have valid board width and color count, or else True
	 */
	public static boolean isValid(Template template) {
		return template != null && template.id >= 0
				&& isValidBoardWidth(template.boardWidth)
				&& isValidColorCount(template.colorCount);
	}
	
	/**
	 * Checks if board width is valid
	 * @param boardWidth Number of holes on pawn per row
	 * @return False if board width is not between minimum and maximum allowed values, or else True
	 */
	public static boolean isValidBoardWidth(int boardWidth) {
		return boardWidth >= MIN_BOARD_WIDTH && boardWidth <= MAX_BOARD_WIDTH;
	}

	/**
	 * Checks if color count is valid
	 * @param colorCount Total count of colors that may be used in a MasterMind game
	 * @return False if color count is not between minimum and maximum allowed values, or else True
	 */
	public static boolean isValidColorCount(int colorCount) {
		return colorCount >= MIN_COLOR_COUNT && colorCount <= MAX_COLOR_COUNT;
	}
	
	/**
	 * Overrides hashCode of super class Object, based on board width and color count
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardWidth;
		result = prime * result + colorCount;
		return result;
	}

	/**
	 * Two template-instances are equal to each other if they point to the same object
	 * or if they have same board width and color count
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Template other = (Template) obj;
		if (boardWidth != other.boardWidth)
			return false;
		if (colorCount != other.colorCount)
			return false;
		return true;
	}

}
