import java.awt.Point;
import java.awt.Color;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;

/**
 * ShipPlacer handles randomized placement of ships onto the game board.
 * Ensures no overlaps and assigns unique colors per ship.
 */
public class ShipPlacer {
    private static final int[] SIZES = {5, 4, 3, 3, 2};
    private static final Color[] SHIP_COLORS = {
            new Color(178, 34, 34),     // red
            new Color(70, 130, 180),    // blue
            new Color(34, 139, 34),     // green
            new Color(255, 105, 180),   // pink
            new Color(255, 140, 0)      // orange
    };

    /**
     * Places ships randomly on the provided board.
     *
     * @param board 2D array of Cells to place ships on
     * @param ships List to store the resulting Ship objects
     */
    public static void placeShips(Cell[][] board, List<Ship> ships) {
        Random rand = new Random();

        for (int i = 0; i < SIZES.length; i++) {
            int size = SIZES[i];
            boolean placed = false;

            while (!placed) {
                boolean horizontal = rand.nextBoolean();
                int row = rand.nextInt(10);
                int col = rand.nextInt(10);

                List<Point> coords = new ArrayList<>();
                boolean valid = true;

                for (int j = 0; j < size; j++) {
                    int r = row + (horizontal ? 0 : j);
                    int c = col + (horizontal ? j : 0);
                    if (r >= 10 || c >= 10) {
                        valid = false;
                        break;
                    }

                    for (Ship s : ships) {
                        if (s.getCoordinates().contains(new Point(r, c))) {
                            valid = false;
                            break;
                        }
                    }

                    coords.add(new Point(r, c));
                }

                if (valid) {
                    ships.add(new Ship(coords, SHIP_COLORS[i]));
                    placed = true;
                }
            }
        }
    }
}
