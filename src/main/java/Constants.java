import java.awt.*;

public final class Constants {
    // Game Window
    public static final String GAME_TITLE = "Snake Game";
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 300;

    // Colors
    public static final Color BACKGROUND_COLOR = Color.BLACK;
    public static final Color SNAKE_COLOR = Color.GREEN;
    public static final Color APPLE_COLOR = Color.RED;
    public static final Color TEXT_COLOR = Color.WHITE;

    // Snake Settings
    public static final int DOT_SIZE = 10;
    public static final int INIT_NUMBER_DOTS = 3;
    public static final int DOT_SPACING = 10;

    // Fonts
    public static final String FONT_NAME = "Helvetica";
    public static final int FONT_TYPE = Font.BOLD;
    public static final int FONT_SIZE = 14;

    // Directions
    public static final char DIRECTION_RIGHT = 'R';
    public static final char DIRECTION_LEFT = 'L';
    public static final char DIRECTION_UP = 'U';
    public static final char DIRECTION_DOWN = 'D';

    // Messages
    public static final String GAME_OVER_MSG = "Game Over";
    public static final String SCORE_LABEL = "Score: ";
}
