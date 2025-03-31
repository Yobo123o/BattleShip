import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Represents a single cell on the Battleship grid.
 * Each cell is a JButton that displays water, hit, or miss state using custom images.
 */
public class Cell extends JButton {
    private int row, col;

    /**
     * Constructs a Cell for a specific grid position and attaches a listener to process shots.
     *
     * @param row    The row of this cell
     * @param col    The column of this cell
     * @param board  The game board this cell belongs to
     */
    public Cell(int row, int col, GameBoard board) {
        this.row = row;
        this.col = col;
        setPreferredSize(new Dimension(40, 40));
        setBackground(Color.CYAN);
        setContentAreaFilled(false);
        setOpaque(true);
        addActionListener(e -> board.processShot(this));
    }

    public int getRow() { return row; }

    public int getCol() { return col; }

    /**
     * Marks the cell with a hit icon and disables further interaction.
     */
    public void markHit() {
        setIcon(loadScaledIcon("hit.png"));
        setDisabledIcon(getIcon());
        setEnabled(false);
    }

    /**
     * Marks the cell with a miss icon and disables further interaction.
     */
    public void markMiss() {
        setIcon(loadScaledIcon("miss.png"));
        setDisabledIcon(getIcon());
        setEnabled(false);
    }

    /**
     * Loads and scales an image icon from file to fit this cell.
     *
     * @param fileName The name of the image file
     * @return An ImageIcon scaled to 40x40 pixels
     */
    public static Icon loadScaledIcon(String fileName) {
        try {
            BufferedImage img = ImageIO.read(new File(fileName));
            Image scaled = img.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } catch (IOException e) {
            System.err.println("Image load failed: " + fileName);
            return null;
        }
    }
}
