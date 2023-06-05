import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    final int BLOCK_SIZE = 25;
    final int BOARD_WIDTH = 10;
    final int BOARD_HEIGHT = 20;

    private boolean gameOver;
    private long score;

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
        createNewPiece();
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
        }
    }

    public void moveLeft() {
        Point currentPosition = currentPiece.getCurrentPosition();

        if(!isCollision(currentPosition.x - 1, currentPosition.y, currentPiece.getRotation())) {
            currentPiece.setCurrentXPosition(currentPosition.x - 1);
        }
    }

    public void moveDown() {
        Point currentPosition = currentPiece.getCurrentPosition();

        if(!isCollision(currentPosition.x, currentPosition.y + 1, currentPiece.getRotation())) {
            currentPiece.setCurrentYPosition(currentPosition.y + 1);
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


    public void updateBoard() {

    }
}
