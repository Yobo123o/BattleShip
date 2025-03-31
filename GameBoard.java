import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;

/**
 * Represents the game board containing all cells and ships.
 * Handles game play logic including hit detection and game-over conditions.
 */
public class GameBoard extends JPanel {
    private final Cell[][] cells = new Cell[10][10];
    private final List<Ship> ships = new ArrayList<>();
    private final GameController controller;
    private final StatsPanel stats;
    private boolean gameOver = false;

    /**
     * Constructs the game board and sets up layout and game objects.
     *
     * @param controller The main controller for the game
     * @param stats      The stats panel displaying game progress
     */
    public GameBoard(GameController controller, StatsPanel stats) {
        this.controller = controller;
        this.stats = stats;
        setLayout(new GridLayout(10, 10));
        initialize();
    }

    /**
     * Initializes the grid and places ships randomly.
     */
    private void initialize() {
        removeAll();
        ships.clear();
        gameOver = false;

        for (int r = 0; r < 10; r++) {
            for (int c = 0; c < 10; c++) {
                Cell cell = new Cell(r, c, this);
                cells[r][c] = cell;
                add(cell);
            }
        }

        ShipPlacer.placeShips(cells, ships);
        revalidate();
        repaint();
    }

    /**
     * Resets the board and stats for a new game.
     */
    public void reset() {
        initialize();
        stats.reset();
    }

    /**
     * Processes a shot on a specific cell and updates the game state accordingly.
     *
     * @param cell The cell clicked by the player
     */
    public void processShot(Cell cell) {
        if (gameOver || !cell.isEnabled()) return;

        boolean hit = false;
        for (Ship ship : ships) {
            if (ship.hit(cell.getRow(), cell.getCol())) {
                hit = true;
                cell.markHit();
                stats.registerHit();

                if (ship.isSunk()) {
                    for (Point p : ship.getCoordinates()) {
                        Cell c = cells[p.x][p.y];
                        c.setBackground(ship.getColor());
                    }
                    stats.registerSunkShip();
                    JOptionPane.showMessageDialog(this, "You sunk a ship!");
                }
                break;
            }
        }

        if (!hit) {
            cell.markMiss();
            stats.registerMiss();
        }

        if (stats.getTotalHits() == 17) {
            gameOver = true;
            controller.gameWon();
        }

        if (stats.getStrikes() == 3) {
            gameOver = true;
            revealRemainingShips(); // Moved BEFORE loss dialog
            controller.gameLost();
        }
    }

    /**
     * Reveals all ship locations after the player loses.
     * Icons are shown only for ship cells that were hit.
     */
    private void revealRemainingShips() {
        for (Ship ship : ships) {
            for (Point p : ship.getCoordinates()) {
                Cell c = cells[p.x][p.y];
                c.setBackground(ship.getColor());
                c.setEnabled(false);

                if (ship.getHits().contains(p)) {
                    c.setIcon(Cell.loadScaledIcon("hit.png"));
                    c.setDisabledIcon(c.getIcon());
                }
            }
        }
    }
}
