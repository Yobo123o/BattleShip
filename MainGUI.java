/**
 * MainGUI launches the Battleship game using the GameController.
 */
public class MainGUI {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> new GameController().start());
    }
}
