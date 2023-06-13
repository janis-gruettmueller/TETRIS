import javax.swing.*;
import java.awt.*;

// something doesn't work in this class -> if we figure it out we are done
public class GameGUI extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GameGUI frame = new GameGUI();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GameGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainMenu.currentGame.BOARD_WIDTH * (MainMenu.currentGame.BLOCK_SIZE + 1), MainMenu.currentGame.BOARD_HEIGHT * (MainMenu.currentGame.BLOCK_SIZE + 1));
        JPanel gamePanel = new JPanel();
        setContentPane(gamePanel);
        setTitle("Tetris: Main Game");
        gamePanel.setLayout(null);

        //gamePanel.add(MainMenu.currentGame);
        //gamePanel.addKeyListener(MainMenu.currentGame);

        //MainMenu.currentGame.init();

        if(MainMenu.currentGame.gameOver) {
            dispose();
            GameOverMenu gameOverMenu = new GameOverMenu();
            gameOverMenu.setVisible(true);
        }
    }
}
