
public class Bomb {
    private int coordinateX;
    private int coordinateY;    

    public Bomb(int coordinateX, int coordinateY) {
        this.coordinateX = coordinateX;
        this.coordinateY = coordinateY;
    }

    public int getX() {
        return coordinateX;
    }

    public int getY() {
        return coordinateY;
    }

    public void setX(int x) {
        this.coordinateX = x;
    }

    public void setY(int y) {
        this.coordinateY = y;
    }
}
