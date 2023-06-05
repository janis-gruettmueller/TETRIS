import javax.swing.*;

public class GUI extends JPanel {
    public static void main(String[] args) {
        Game game = new Game();

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(game.BOARD_WIDTH * (game.BLOCK_SIZE+1), game.BOARD_HEIGHT * (game.BLOCK_SIZE+1));

        frame.add(game);
        frame.addKeyListener(game);
        frame.setVisible(true);

        game.updateBoard();
    }
}
