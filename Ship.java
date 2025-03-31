import java.awt.Color;
import java.awt.Point;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/**
 * Represents a ship on the board with position, color, and hit tracking.
 */
public class Ship {
    private final List<Point> coordinates = new ArrayList<>();
    private final Set<Point> hits = new HashSet<>();
    private final int size;
    private final Color color;

    /**
     * Constructs a ship with its coordinates and color.
     *
     * @param coords List of grid points the ship occupies
     * @param color  The color to display when the ship is revealed
     */
    public Ship(List<Point> coords, Color color) {
        this.size = coords.size();
        this.color = color;
        coordinates.addAll(coords);
    }

    /**
     * Registers a hit if the given point matches part of the ship.
     *
     * @param row The row index of the hit
     * @param col The column index of the hit
     * @return True if it's a hit; false otherwise
     */
    public boolean hit(int row, int col) {
        Point p = new Point(row, col);
        if (coordinates.contains(p)) {
            hits.add(p);
            return true;
        }
        return false;
    }

    /**
     * Checks if the ship has been completely sunk.
     *
     * @return True if all positions are hit
     */
    public boolean isSunk() {
        return hits.containsAll(coordinates);
    }

    public List<Point> getCoordinates() {
        return new ArrayList<>(coordinates);
    }

    public Set<Point> getHits() {
        return new HashSet<>(hits);
    }

    public int getSize() {
        return size;
    }

    public Color getColor() {
        return color;
    }
}
