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

    }


    public void moveRight() {

    }

    public void moveLeft() {

    }

    public void moveDown() {

    }

    public void rotate() {

    }


    public boolean isCollision(int xPos, int yPos, int rotation) {


        return false;
    }

    public void fixToGrid() {

    }

    public void clearRows() {

    }

    public void deleteRow(int row) {

    }


    public void updateBoard() {

    }
}
