import helpers.GraphicHelper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SnakeGame extends JPanel implements ActionListener {
    private final GameSettings settings;
    private int boardWidth;
    private int boardHeight;
    private int timerDelay;

    private final int[] x;
    private final int[] y;
    private int dots;
    private int appleX;
    private int appleY;

    private boolean inGame;
    private boolean gameStarted;

    private Timer timer;
    private char direction;
    private int outOfBoundsCounter; // for extra reaction time

    public SnakeGame(GameSettings settings) {
        this.settings = settings;
        this.boardWidth = settings.getBoardWidth();
        this.boardHeight = settings.getBoardHeight();
        this.timerDelay = 1000 / settings.getFrameRate();  // frame delay in ms

        setPreferredSize(new Dimension(boardWidth, boardHeight));
        setBackground(Constants.BACKGROUND_COLOR);
        setFocusable(true);
        addKeyListener(new TAdapter());

        x = new int[900];
        y = new int[900];

        initGame();
    }

    /** Initializes or resets the game. */
    public void initGame() {
        inGame = true;
        gameStarted = false;
        direction = Constants.DIRECTION_RIGHT;
        dots = Constants.INIT_NUMBER_DOTS;
        outOfBoundsCounter = 0;

        // Initialize snake in the center of the board
        int startX = boardWidth / 2;
        int startY = boardHeight / 2;
        for (int i = 0; i < dots; i++) {
            x[i] = startX - i * Constants.DOT_SPACING;
            y[i] = startY;
        }

        locateApple();

        if (timer != null) {
            timer.stop();
        }
        timer = new Timer(timerDelay, this);
        timer.start();
    }

    /** Starts a new game (reset). */
    public void newGame() {
        initGame();
        repaint();
    }

    /** Randomly places the apple on the board. */
    private void locateApple() {
        int maxPosX = boardWidth / Constants.DOT_SIZE;
        int maxPosY = boardHeight / Constants.DOT_SIZE;
        appleX = ((int)(Math.random() * maxPosX)) * Constants.DOT_SIZE;
        appleY = ((int)(Math.random() * maxPosY)) * Constants.DOT_SIZE;
    }

    /** Checks if the snake’s head is at the apple’s position. */
    private void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            locateApple();
        }
    }

    /** Moves the snake (body and head) in the current direction. */
    private void move() {
        // Move snake body
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        // Move head
        switch(direction) {
            case Constants.DIRECTION_LEFT -> x[0] -= Constants.DOT_SIZE;
            case Constants.DIRECTION_RIGHT -> x[0] += Constants.DOT_SIZE;
            case Constants.DIRECTION_UP -> y[0] -= Constants.DOT_SIZE;
            case Constants.DIRECTION_DOWN -> y[0] += Constants.DOT_SIZE;
        }
    }

    /** Checks for collisions with self and boundaries. */
    private void checkCollision() {
        // Self collision check
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
                break;
            }
        }

        // Check boundaries (and apply edge traversal or reaction delay)
        checkBoundaryCollision();

        if (!inGame) {
            timer.stop();
            int currentScore = dots - Constants.INIT_NUMBER_DOTS;
            ScoreManager.updateHighScore(currentScore);
        }
    }

    /** Handles what happens when the snake reaches the board boundary. */
    private void checkBoundaryCollision() {
        if (settings.isEdgeTraversalEnabled()) {
            // Wrap the snake around to the opposite side
            if (x[0] < 0) {
                x[0] = boardWidth - Constants.DOT_SIZE;
            } else if (x[0] >= boardWidth) {
                x[0] = 0;
            }
            if (y[0] < 0) {
                y[0] = boardHeight - Constants.DOT_SIZE;
            } else if (y[0] >= boardHeight) {
                y[0] = 0;
            }
            outOfBoundsCounter = 0; // not used when wrapping
        } else {
            // Without edge traversal: allow a configurable extra reaction delay
            if (x[0] < 0 || x[0] >= boardWidth || y[0] < 0 || y[0] >= boardHeight) {
                outOfBoundsCounter++;
                if (outOfBoundsCounter >= settings.getReactionDelayFrames()) {
                    inGame = false;
                }
            } else {
                outOfBoundsCounter = 0; // reset counter if head is back in bounds
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (inGame) {
            GraphicHelper.drawObjects(g, appleX, appleY, dots, Constants.DOT_SIZE, x, y);
            drawScore(g);
        } else {
            gameOver(g);
        }
    }

    /** Draws the current score in the upper left corner. */
    private void drawScore(Graphics g) {
        String scoreText = Constants.SCORE_LABEL + (dots - Constants.INIT_NUMBER_DOTS);
        g.setColor(Constants.TEXT_COLOR);
        g.setFont(new Font(Constants.FONT_NAME, Constants.FONT_TYPE, Constants.FONT_SIZE));
        g.drawString(scoreText, 10, 20);
    }

    /** Displays the game over message. */
    private void gameOver(Graphics g) {
        String msg = Constants.GAME_OVER_MSG;
        Font font = new Font(Constants.FONT_NAME, Constants.FONT_TYPE, Constants.FONT_SIZE);
        FontMetrics metrics = getFontMetrics(font);
        g.setColor(Constants.TEXT_COLOR);
        g.setFont(font);
        g.drawString(msg, (boardWidth - metrics.stringWidth(msg)) / 2, boardHeight / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame && gameStarted) {
            checkApple();
            move();
            checkCollision();
        }
        repaint();
    }

    /** Listens for key presses to control the snake. */
    private class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT && direction != Constants.DIRECTION_RIGHT) {
                direction = Constants.DIRECTION_LEFT;
            }
            if (key == KeyEvent.VK_RIGHT && direction != Constants.DIRECTION_LEFT) {
                direction = Constants.DIRECTION_RIGHT;
            }
            if (key == KeyEvent.VK_UP && direction != Constants.DIRECTION_DOWN) {
                direction = Constants.DIRECTION_UP;
            }
            if (key == KeyEvent.VK_DOWN && direction != Constants.DIRECTION_UP) {
                direction = Constants.DIRECTION_DOWN;
            }
            if (key == KeyEvent.VK_SPACE) {
                gameStarted = true;
            }
        }
    }

    /** Called when settings are updated (e.g. from the settings dialog). */
    public void updateSettings() {
        this.boardWidth = settings.getBoardWidth();
        this.boardHeight = settings.getBoardHeight();
        this.timerDelay = 1000 / settings.getFrameRate();
        setPreferredSize(new Dimension(boardWidth, boardHeight));
        newGame();
    }
}
