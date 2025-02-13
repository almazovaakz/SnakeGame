import javax.swing.*;
import java.awt.*;

public class SettingsDialog extends JDialog {
    private boolean settingsUpdated = false;

    private final GameSettings settings;

    private JTextField widthField;
    private JTextField heightField;
    private JTextField frameRateField;
    private JSpinner reactionDelaySpinner;
    private JCheckBox edgeTraversalCheckBox;

    public SettingsDialog(JFrame parent, GameSettings settings) {
        super(parent, "Settings", true);
        this.settings = settings;
        initComponents();
        pack();
        setLocationRelativeTo(parent);
    }

    /** Initializes the settings UI components. */
    private void initComponents() {
        JPanel panel = new JPanel(new GridLayout(0,2,5,5));

        // Board width
        panel.add(new JLabel("Board Width:"));
        widthField = new JTextField(String.valueOf(settings.getBoardWidth()));
        panel.add(widthField);

        // Board height
        panel.add(new JLabel("Board Height:"));
        heightField = new JTextField(String.valueOf(settings.getBoardHeight()));
        panel.add(heightField);

        // Frame Rate
        panel.add(new JLabel("Frame Rate (FPS):"));
        frameRateField = new JTextField(String.valueOf(settings.getFrameRate()));
        panel.add(frameRateField);

        // Reaction Delay Frames
        panel.add(new JLabel("Reaction Delay (frames):"));
        reactionDelaySpinner = new JSpinner(new SpinnerNumberModel(settings.getReactionDelayFrames(), 1, 10, 1));
        panel.add(reactionDelaySpinner);

        // Edge Traversal Option
        panel.add(new JLabel("Enable Edge Traversal:"));
        edgeTraversalCheckBox = new JCheckBox();
        edgeTraversalCheckBox.setSelected(settings.isEdgeTraversalEnabled());
        panel.add(edgeTraversalCheckBox);

        add(panel, BorderLayout.CENTER);

        // Buttons panel
        JPanel buttonsPanel = new JPanel();
        JButton okButton = new JButton("OK");
        okButton.addActionListener(e -> onOk());
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> onCancel());
        buttonsPanel.add(okButton);
        buttonsPanel.add(cancelButton);
        add(buttonsPanel, BorderLayout.SOUTH);
    }

    private void onOk() {
        try {
            int newWidth = Integer.parseInt(widthField.getText());
            int newHeight = Integer.parseInt(heightField.getText());
            int newFrameRate = Integer.parseInt(frameRateField.getText());
            int newReactionDelay = (Integer) reactionDelaySpinner.getValue();
            boolean newEdgeTraversal = edgeTraversalCheckBox.isSelected();

            settings.setBoardWidth(newWidth);
            settings.setBoardHeight(newHeight);
            settings.setFrameRate(newFrameRate);
            settings.setReactionDelayFrames(newReactionDelay);
            settings.setEdgeTraversalEnabled(newEdgeTraversal);

            settingsUpdated = true;
            dispose();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void onCancel() {
        settingsUpdated = false;
        dispose();
    }

    public boolean isSettingsUpdated() {
        return settingsUpdated;
    }
}
