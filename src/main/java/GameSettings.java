public class GameSettings {
    private int boardWidth;
    private int boardHeight;
    private int frameRate; // in frames per second
    private boolean edgeTraversalEnabled;
    private int reactionDelayFrames; // extra reaction delay frames when near collision

    public GameSettings() {
        // default settings
        this.boardWidth = Constants.DEFAULT_WIDTH;
        this.boardHeight = Constants.DEFAULT_HEIGHT;
        this.frameRate = 20; // default speed (FPS)
        this.edgeTraversalEnabled = false;
        this.reactionDelayFrames = 3; // extra reaction time (in frames)
    }

    // Getters and setters
    public int getBoardWidth() {
        return boardWidth;
    }

    public void setBoardWidth(int boardWidth) {
        this.boardWidth = boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public void setBoardHeight(int boardHeight) {
        this.boardHeight = boardHeight;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        this.frameRate = frameRate;
    }

    public boolean isEdgeTraversalEnabled() {
        return edgeTraversalEnabled;
    }

    public void setEdgeTraversalEnabled(boolean edgeTraversalEnabled) {
        this.edgeTraversalEnabled = edgeTraversalEnabled;
    }

    public int getReactionDelayFrames() {
        return reactionDelayFrames;
    }

    public void setReactionDelayFrames(int reactionDelayFrames) {
        this.reactionDelayFrames = reactionDelayFrames;
    }
}
