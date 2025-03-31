import javax.swing.*;
import java.awt.*;

/**
 * GameController manages the main window, game controls, and overall game flow.
 * It initializes the UI and handles Play Again, Help, and Quit interactions.
 */
public class GameController {
    private GameBoard board;
    private StatsPanel stats;

    /**
     * Initializes and launches the Battleship game window.
     */
    public void start() {
        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        stats = new StatsPanel();
        board = new GameBoard(this, stats);

        frame.add(stats, BorderLayout.NORTH);
        frame.add(board, BorderLayout.CENTER);

        JPanel controls = new JPanel();
        JButton help = new JButton("Help");
        JButton playAgain = new JButton("Play Again");
        JButton quit = new JButton("Quit");

        controls.add(help);
        controls.add(playAgain);
        controls.add(quit);
        frame.add(controls, BorderLayout.SOUTH);

        help.addActionListener(e -> {
            JOptionPane.showMessageDialog(null,
                    """
                    Click any cell to fire at hidden ships!
                    A 💥 icon means you hit a ship!
                    A 💧 icon means you missed!
    
                    Ship Types (5 total):
                    • Carrier (5 cells)
                    • Battleship (4 cells)
                    • Cruiser (3 cells)
                    • Submarine (3 cells)
                    • Destroyer (2 cells)
    
                    ⛔ 5 misses in a row = 1 Strike
                    ❌ 3 Strikes = Game Over
                    ✅ Sink all ships to win!
                    """,
                    "How to Play",
                    JOptionPane.INFORMATION_MESSAGE
            );
        });

        playAgain.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(null, "Start a new game?", "Play Again", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                board.reset();
                stats.reset();
            }
        });

        quit.addActionListener(e -> {
            int res = JOptionPane.showConfirmDialog(null, "Quit game?", "Quit", JOptionPane.YES_NO_OPTION);
            if (res == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Called when the player wins the game and offers to play again.
     */
    public void gameWon() {
        int res = JOptionPane.showConfirmDialog(null, "You sank all ships! You win!\nPlay again?", "Victory", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            board.reset();
            stats.reset();
        }
    }

    /**
     * Called when the player loses the game and offers to play again.
     */
    public void gameLost() {
        int res = JOptionPane.showConfirmDialog(null, "3 strikes! You lose.\nPlay again?", "Defeat", JOptionPane.YES_NO_OPTION);
        if (res == JOptionPane.YES_OPTION) {
            board.reset();
            stats.reset();
        }
    }
}
