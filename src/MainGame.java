import javax.swing.*;
import java.awt.*;

// something doesn't work in this class -> if we figure it out we are done
public class MainGame extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainGame frame = new MainGame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainGame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainMenu.currentGame.BOARD_WIDTH * (MainMenu.currentGame.BLOCK_SIZE + 1), MainMenu.currentGame.BOARD_HEIGHT * (MainMenu.currentGame.BLOCK_SIZE + 1));
        setTitle("Tetris: Main Game");

        add(MainMenu.currentGame);
        addKeyListener(MainMenu.currentGame);
    }
}
