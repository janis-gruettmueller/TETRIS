import java.awt.*;

public class Piece {
    private Point currentPosition;
    private int rotation;
    private int shapeID;

    public Piece(int xPos, int yPos) {
        this.currentPosition = new Point(xPos, yPos);
        this.rotation = 0;
    }


    public void setRotation(int rotation) {
        this.rotation = rotation;
    }
    public void setShapeID(int shapeID) {
        this.shapeID = shapeID;
    }


    public void setCurrentXPosition(int xPos) {
        this.currentPosition.x = xPos;
    }
    public void setCurrentYPosition(int yPos) {
        this.currentPosition.y = yPos;
    }


    public int getRotation() {
        return rotation;
    }
    public Point getCurrentPosition() {
        return currentPosition;
    }
    public int getShapeID() {
        return shapeID;
    }
}
