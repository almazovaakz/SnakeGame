# Snake Game

A classic Snake Game implemented in Java with enhanced features and a modular codebase. This project uses Java Swing for the GUI and demonstrates clean code practices with a focus on separation of concerns, reusability, and configurability.

## Features

- **Score Counter:**  
  Displays the current score (apples eaten) in real time.

- **Main Game Window with Menu:**
    - **New Game:** Restart the game without restarting the application.
    - **Highest Score:** View the highest score achieved during previous sessions.
    - **Settings:** Customize the game board size, speed, edge traversal mode, and extra reaction time before collisions.
    - **Exit:** Close the game.

- **Configurable Settings:**
    - **Board Size:** Adjust the width and height of the game board.
    - **Speed:** Modify the game’s frame rate.
    - **Edge Traversal:** Enable or disable the snake wrapping around the edges.
    - **Extra Reaction Time:** Configure a delay before registering a collision when near the board boundary.

## Code Structure

The project is organized into several Java classes for maintainability and clarity:

- **Constants.java:**  
  Contains all constant values such as colors, fonts, game messages, and default settings.

- **GameSettings.java:**  
  Manages game configuration settings including board dimensions, frame rate, edge traversal mode, and reaction delay.

- **ScoreManager.java:**  
  Handles the current and highest score tracking.

- **GraphicHelper.java:**  
  Provides utility methods for drawing game objects (snake and apple).

- **SnakeGame.java:**  
  The main game panel where the game logic, rendering, and collision detection are implemented.

- **GameFrame.java:**  
  Sets up the main application window, including the menu bar with options for starting a new game, viewing the highest score, changing settings, and exiting.

- **SettingsDialog.java:**  
  A dialog window that allows users to adjust game settings interactively.

## Requirements

- **Java Development Kit (JDK) 8 or higher**

## Getting Started

    - ** git clone https://github.com/almazovaakz/SnakeGame.git
    - ** use run configuration StartSnakeGame.run.xml
    - ** enjoy!!!