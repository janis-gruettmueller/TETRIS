import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameOverMenu extends JFrame {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GameOverMenu frame = new GameOverMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public GameOverMenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(MainMenu.currentGame.BOARD_WIDTH * (MainMenu.currentGame.BLOCK_SIZE + 1), MainMenu.currentGame.BOARD_HEIGHT * (MainMenu.currentGame.BLOCK_SIZE + 1));
        JPanel gameOverPanel = new JPanel();
        setContentPane(gameOverPanel);
        setTitle("Tetris: Game Over Menu");
        gameOverPanel.setLayout(new GridBagLayout());


        JButton returnButton = new JButton("Return");
        ActionListener returnAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainMenu mainMenu = new MainMenu();
                mainMenu.setVisible(true);
            }
        };
        returnButton.addActionListener(returnAction);


        JButton replayButton = new JButton("Retry");
        ActionListener replayAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GameGUI gameGUI = new GameGUI();
                gameGUI.setVisible(true);
            }
        };
        replayButton.addActionListener(replayAction);

        // Potentially add code to display Score, Highscore and current user
        gameOverPanel.add(replayButton);
        gameOverPanel.add(returnButton);
    }
}
