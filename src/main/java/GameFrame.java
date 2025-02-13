import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {
    private final GameSettings settings;
    private final SnakeGame gamePanel;

    public GameFrame() {
        settings = new GameSettings();
        setTitle(Constants.GAME_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gamePanel = new SnakeGame(settings);
        add(gamePanel, BorderLayout.CENTER);

        setJMenuBar(createMenuBar());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    /** Creates the menu bar with options for New Game, Highest Score, Settings, and Exit. */
    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu gameMenu = new JMenu("Game");

        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(e -> gamePanel.newGame());

        JMenuItem highScoreItem = new JMenuItem("Highest Score");
        highScoreItem.addActionListener(e -> {
            int highScore = ScoreManager.getHighScore();
            JOptionPane.showMessageDialog(this, "Highest Score: " + highScore, "High Score", JOptionPane.INFORMATION_MESSAGE);
        });

        JMenuItem settingsItem = new JMenuItem("Settings");
        settingsItem.addActionListener(e -> {
            SettingsDialog dialog = new SettingsDialog(this, settings);
            dialog.setVisible(true);
            if (dialog.isSettingsUpdated()) {
                gamePanel.updateSettings();
                pack();
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));

        gameMenu.add(newGameItem);
        gameMenu.add(highScoreItem);
        gameMenu.add(settingsItem);
        gameMenu.add(exitItem);

        menuBar.add(gameMenu);
        return menuBar;
    }

    public static void main(String[] args) {
        new GameFrame();
    }
}
