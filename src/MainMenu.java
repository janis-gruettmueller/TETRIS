import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class MainMenu extends JFrame {
    static Game currentGame;
    static MainGame mainGameFrame;
    String playerName;
    User[] users;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    MainMenu frame = new MainMenu();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MainMenu() {
        if(currentGame == null) {
            currentGame = new Game();

            playerName = JOptionPane.showInputDialog("Please enter your Name: ");
            if(playerName == null) {
                System.exit(0);
            }
            users = loadUserData(currentGame, playerName);
        }


        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(currentGame.BOARD_WIDTH * (currentGame.BLOCK_SIZE + 5), currentGame.BOARD_HEIGHT * (currentGame.BLOCK_SIZE + 5));
        JPanel mainMenuPanel = new JPanel();
        mainMenuPanel.setLayout(new GridBagLayout());
        setContentPane(mainMenuPanel);
        setTitle("Tetris: Main Menu");


        JButton quitButton = new JButton("Quit");
        ActionListener quitAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveUserData(users);
                System.exit(0);
            }
        };
        quitButton.addActionListener(quitAction);


        JButton startButton = new JButton("Start");
        ActionListener startAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                mainGameFrame = new MainGame();
                mainGameFrame.setVisible(true);

                currentGame.init();
            }
        };
        startButton.addActionListener(startAction);


        JButton showScoreButton = new JButton("Show Highscore");
        ActionListener showScoreAction = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Current Player: " + MainMenu.currentGame.getCurrentUser().getUsername());
                System.out.println("Highscore: " + MainMenu.currentGame.getCurrentUser().getHighscore());
                System.out.println();
            }
        };
        showScoreButton.addActionListener(showScoreAction);

        mainMenuPanel.add(startButton);
        mainMenuPanel.add(showScoreButton);
        mainMenuPanel.add(quitButton);
    }

    public static User[] loadUserData(Game currentGame, String playerName) {
        User[] userData = null;

        try {
            FileInputStream fis = new FileInputStream("users.dat");
            ObjectInputStream in = new ObjectInputStream(fis);
            userData = (User[]) in.readObject();

            if(userData == null) {
                userData = new User[] {new User(playerName.toUpperCase())};
                currentGame.setCurrentUser(userData[0]);
            }

            for (User usr : userData) {
                if (usr.getUsername().equals(playerName.toUpperCase())) {
                    currentGame.setCurrentUser(usr);
                }
            }

            if (currentGame.getCurrentUser() == null) {
                currentGame.setCurrentUser(new User(playerName.toUpperCase()));

                User[] temp = new User[userData.length + 1];
                System.arraycopy(userData, 0, temp, 1, userData.length);
                temp[0] = currentGame.getCurrentUser();

                userData = temp;
            }
        } catch (IOException | ClassNotFoundException e) {
            if(userData == null) {
                userData = new User[] {new User(playerName.toUpperCase())};
                currentGame.setCurrentUser(userData[0]);
            } else {
                e.printStackTrace();
            }
        }

        return userData;
    }

    public static void saveUserData(User[] userData) {
        try {
            FileOutputStream fos = new FileOutputStream("users.dat");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(userData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
