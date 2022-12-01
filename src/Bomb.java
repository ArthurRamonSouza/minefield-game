
public class Bomb {
    private int coordinateX;
    private int coordinateY;    

    public Bomb(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateX(int x) {
        this.coordinateX = x;
    }

    public void setCoordinateY(int y) {
        this.coordinateY = y;
    }
}
