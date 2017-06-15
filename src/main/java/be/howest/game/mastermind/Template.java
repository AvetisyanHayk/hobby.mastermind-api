package be.howest.game.mastermind;

public class Template {

	public static final int MIN_BOARD_WIDTH = 4;
	public static final int MAX_BOARD_WIDTH = 8;
	public static final int MIN_COLOR_COUNT = 4;
	public static final int MAX_COLOR_COUNT = 10;

	private final int id;
	private final int boardWidth;
	private final int colorCount;
	
	public Template(int id, int boardWidth, int colorCount) {
		this.id = id;
		this.boardWidth = boardWidth;
		this.colorCount = colorCount;
	}

	public int getId() {
		return id;
	}

	public int getBoardWidth() {
		return boardWidth;
	}
	
	public int getColorCount() {
		return colorCount;
	}

	public int getBoardHeight() {
		int boardHeight = (boardWidth * 3 + colorCount) / 2;
		boardHeight += boardHeight % 2;
		return boardHeight;
	}
	
	public boolean isValid() {
		return Template.isValid(this);
	}
	
	public static boolean isValid(Template template) {
		return template != null && template.id >= 0
				&& isValidBoardWidth(template.boardWidth)
				&& isValidColorCount(template.colorCount);
	}
	
	public static boolean isValidBoardWidth(int boardWidth) {
		return boardWidth >= MIN_BOARD_WIDTH && boardWidth <= MAX_BOARD_WIDTH;
	}

	public static boolean isValidColorCount(int colorCount) {
		return colorCount >= MIN_COLOR_COUNT && colorCount <= MAX_COLOR_COUNT;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + boardWidth;
		result = prime * result + colorCount;
		return result;
	}

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
