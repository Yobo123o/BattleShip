import javax.swing.*;
import java.awt.*;

/**
 * StatsPanel displays the player's current stats:
 * - Misses
 * - Strikes
 * - Total Hits
 * - Total Misses
 * - Ships Remaining
 */
public class StatsPanel extends JPanel {
    private int missCount = 0, totalMisses = 0, totalHits = 0, strikes = 0;
    private int shipsRemaining = 5;

    private JLabel missLabel, strikeLabel, hitLabel, totalMissLabel, shipsRemainingLabel;

    /**
     * Constructs the statistics display bar.
     */
    public StatsPanel() {
        setLayout(new GridLayout(1, 5));
        missLabel = new JLabel("Misses: 0");
        strikeLabel = new JLabel("Strikes: 0");
        hitLabel = new JLabel("Total Hits: 0");
        totalMissLabel = new JLabel("Total Misses: 0");
        shipsRemainingLabel = new JLabel("Ships Remaining: 5");
        add(missLabel);
        add(strikeLabel);
        add(hitLabel);
        add(totalMissLabel);
        add(shipsRemainingLabel);
    }

    /**
     * Registers a miss and updates strike counter if 5 in a row.
     */
    public void registerMiss() {
        missCount++;
        totalMisses++;
        if (missCount >= 5) {
            strikes++;
            missCount = 0;
        }
        update();
    }

    /**
     * Registers a successful hit.
     * Resets consecutive miss count.
     */
    public void registerHit() {
        missCount = 0;
        totalHits++;
        update();
    }

    /**
     * Registers when a ship has been fully sunk.
     */
    public void registerSunkShip() {
        shipsRemaining--;
        shipsRemainingLabel.setText("Ships Remaining: " + shipsRemaining);
    }

    /**
     * Resets all statistics to default values.
     */
    public void reset() {
        missCount = totalMisses = totalHits = strikes = 0;
        shipsRemaining = 5;
        update();
    }

    /**
     * Updates the displayed labels with current values.
     */
    private void update() {
        missLabel.setText("Misses: " + missCount);
        strikeLabel.setText("Strikes: " + strikes);
        hitLabel.setText("Total Hits: " + totalHits);
        totalMissLabel.setText("Total Misses: " + totalMisses);
        shipsRemainingLabel.setText("Ships Remaining: " + shipsRemaining);
    }

    public int getStrikes() { return strikes; }

    public int getTotalHits() { return totalHits; }
}
