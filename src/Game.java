import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

public class Game extends JPanel implements KeyListener {
    public boolean gameOver;
    public int level;

    private Timer timer;

    final int BLOCK_SIZE = 25;
    final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;

    private long score;

    private User currentUser;

    private Piece currentPiece;
    private ArrayList<Point> gridPoints;
    private boolean[][] grid;

    private final Color[] tetraminoColors = new Color[] {Color.cyan, Color.blue, Color.orange, Color.yellow, Color.green, new Color(128, 0, 128), Color.red};
    private final Point[][][] tetraminos = new Point[][][] {
            // I-Piece
            {
                    {new Point(0, 1), new Point(1, 1), new Point(2, 1), new Point(3, 1)},
                    {new Point(2, 0), new Point(2, 1), new Point(2, 2), new Point(2, 3)},
                    {new Point(0, 2), new Point(1, 2), new Point(2, 2), new Point(3, 2)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(1, 3)}
            },
            // J-Piece
            {
                    {new Point(0, 1), new Point(0, 0), new Point(1, 1), new Point(2, 1)},
                    {new Point(1, 0), new Point(2, 0), new Point(1, 1), new Point(1, 2)},
                    {new Point(0, 1), new Point(2, 1), new Point(1, 1), new Point(2, 2)},
                    {new Point(0, 2), new Point(1, 0), new Point(1, 1), new Point(1, 2)}
            },
            // L-Piece
            {
                    {new Point(0, 1), new Point(1, 1), new Point(2, 0), new Point(2, 1)},
                    {new Point(1, 0), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
                    {new Point(0, 2), new Point(0, 1), new Point(1, 1), new Point(2, 1)},
                    {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(1, 2)}
            },
            // O-Piece
            {
                    {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)},
                    {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)},
                    {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)},
                    {new Point(0, 0), new Point(1, 0), new Point(0, 1), new Point(1, 1)}
            },
            // S-Piece
            {
                    {new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2, 0)},
                    {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(2, 2)},
                    {new Point(2, 1), new Point(1, 1), new Point(1, 2), new Point(0, 2)},
                    {new Point(0, 0), new Point(0, 1), new Point(1, 1), new Point(1, 2)}
            },
            // T-Piece
            {
                    {new Point(0, 1), new Point(1, 1), new Point(1, 0), new Point(2, 1)},
                    {new Point(1, 0), new Point(1, 1), new Point(2, 1), new Point(1, 2)},
                    {new Point(0, 1), new Point(1, 1), new Point(1, 2), new Point(2, 1)},
                    {new Point(0, 1), new Point(1, 0), new Point(1, 1), new Point(1, 2)}
            },
            // Z-Piece
            {
                    {new Point(0, 0), new Point(1, 0), new Point(1, 1), new Point(2, 1)},
                    {new Point(2, 0), new Point(2, 1), new Point(1, 1), new Point(1, 2)},
                    {new Point(0, 1), new Point(1, 1), new Point(1, 2), new Point(2, 2)},
                    {new Point(1, 0), new Point(1, 1), new Point(0, 1), new Point(0, 2)}
            }
    };

    public Game() {
        gridPoints = new ArrayList<Point>();
        grid = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
        gameOver = false;
        currentUser = null;
        createNewPiece();
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }


    public void init() {
        //System.out.println("Game started");
        gameOver = false;
        createNewPiece();
        updateBoard();
    }


    public void createNewPiece() {
        Random rand = new Random();
        currentPiece = new Piece(3, -2);
        currentPiece.setShapeID(rand.nextInt(0, 7));
    }


    public void moveRight() {
        Point currentPosition = currentPiece.getCurrentPosition();

        if(!isCollision(currentPosition.x + 1, currentPosition.y, currentPiece.getRotation())) {
            currentPiece.setCurrentXPosition(currentPosition.x + 1);
            repaint();
        }
    }

    public void moveLeft() {
        Point currentPosition = currentPiece.getCurrentPosition();

        if(!isCollision(currentPosition.x - 1, currentPosition.y, currentPiece.getRotation())) {
            currentPiece.setCurrentXPosition(currentPosition.x - 1);
            repaint();
        }
    }

    public void moveDown() {
        Point currentPosition = currentPiece.getCurrentPosition();

        if(!isCollision(currentPosition.x, currentPosition.y + 1, currentPiece.getRotation())) {
            currentPiece.setCurrentYPosition(currentPosition.y + 1);
            repaint();
        }
    }

    public void rotate() {
        Point currentPosition = currentPiece.getCurrentPosition();
        int rotation = currentPiece.getRotation();

        if(!isCollision(currentPosition.x + 1, currentPosition.y + 1, rotation) && !isCollision(currentPosition.x - 1, currentPosition.y + 1, rotation)) {
            if(rotation == 3) {
                currentPiece.setRotation(0);
            } else {
                currentPiece.setRotation(rotation+1);
            }

            repaint();
        }
    }


    public boolean isCollision(int xPos, int yPos, int rotation) {
        for (Point p : tetraminos[currentPiece.getShapeID()][rotation]) {
            // Piece collision
            if(gridPoints.contains(new Point(p.x + xPos, p.y + yPos))) {
                return true;
            }

            // Wall collision
            if(xPos + p.x == BOARD_WIDTH || xPos + p.x < 0) {
                return true;
            }
            if(yPos + p.y == BOARD_HEIGHT-1) {
                return true;
            }
        }

        return false;
    }

    public void fixToGrid() {
        for (Point p : tetraminos[currentPiece.getShapeID()][currentPiece.getRotation()]) {
            Point newPoint = new Point(p.x + currentPiece.getCurrentPosition().x, p.y + currentPiece.getCurrentPosition().y);
            // Game Over logik
            if(newPoint.y <= 0) {
                gameOver = true;
                return;
            }

            gridPoints.add(newPoint);
        }

        for (Point p : gridPoints) {
            grid[p.y][p.x] = true;
        }

        clearRows();
    }

    public void clearRows() {
        int numberOfRows = 0;

        for (int i = 0; i < grid.length; i++) {
            int counter = 0;
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == true) {
                    counter++;
                    grid[i][j] = false;
                }
            }

            if (counter == grid[i].length) {
                numberOfRows++;
                deleteRow(i);
            }
        }

        switch(numberOfRows) {
            case 1 -> score += 100;
            case 2 -> score += 300;
            case 3 -> score += 500;
            case 4 -> score += 800;
        }

        if(score > currentUser.getHighscore()) {
            currentUser.setHighscore(score);
        }
    }

    public void deleteRow(int row) {
        for (int i = 0; i < grid[row].length; i++) {
            gridPoints.remove(new Point(i, row));
        }

        for (Point p : gridPoints) {
            if(p.y < row) {
                grid[p.y][p.x] = false;
                p.y += 1;
            }
        }
    }

    public void newLevel(int time) {
        System.out.println("Level: " + level++);
        timer.setDelay(time);
    }

    public void updateBoard() {
        // code that makes piece fall down
        level = 1;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                moveDown();
                repaint();

                if (isCollision(currentPiece.getCurrentPosition().x, currentPiece.getCurrentPosition().y + 1, currentPiece.getRotation())) {
                    fixToGrid();
                    createNewPiece();

                    // 5 Level
                    if (score >= 500 && level == 1) {
                        newLevel(900);
                    } else if (score >= 1000 && level == 2) {
                        newLevel(700);
                    } else if (score >= 1500 && level == 3) {
                        newLevel(500);
                    } else if (score >= 2000 && level == 4) {
                        newLevel(300);
                    } else if (score >= 3000 && level == 5) {
                        newLevel(100);
                    }
                }

                if(gameOver) {
                    System.out.println("GAME OVER!" + "\n\nPlayer: " + currentUser.getUsername() + "\nScore: " + score + "\nHighScore: " + currentUser.getHighscore() + "\n");
                    gridPoints.clear();
                    grid = new boolean[BOARD_HEIGHT][BOARD_WIDTH];
                    timer.stop();

                    MainMenu.mainGameFrame.dispose();
                    GameOverMenu gameOverMenu = new GameOverMenu();
                    gameOverMenu.setVisible(true);
                }
            }
        });
        timer.start();
    }

    // Zeichnen des Spielfeldes und der Spielsteine
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the currently falling piece
        drawPiece(g);
        drawGrid(g);
    }

    public void drawPiece(Graphics g) {
        g.setColor(tetraminoColors[currentPiece.getShapeID()]);

        for (Point p : tetraminos[currentPiece.getShapeID()][currentPiece.getRotation()]) {
            g.fillRect((p.x + currentPiece.getCurrentPosition().x) * (BLOCK_SIZE+1), (p.y + currentPiece.getCurrentPosition().y) * (BLOCK_SIZE+1), BLOCK_SIZE, BLOCK_SIZE);
        }
    }

    public void drawGrid(Graphics g) {
        g.setColor(Color.gray);

        for (Point p : gridPoints) {
            g.fillRect(p.x * (BLOCK_SIZE+1), p.y * (BLOCK_SIZE+1), BLOCK_SIZE, BLOCK_SIZE);
        }

    }

    // Implementierung der Methoden aus dem KeyListener Interface
    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP -> this.rotate();
            case KeyEvent.VK_DOWN -> this.moveDown();
            case KeyEvent.VK_LEFT -> this.moveLeft();
            case KeyEvent.VK_RIGHT -> this.moveRight();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {}
}
