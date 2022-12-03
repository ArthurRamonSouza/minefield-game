public class Space {
    private boolean bomb = false;
    private boolean hidden = true;
    private int bombsNear = 0;

    public int getBombsNear() {
        return bombsNear;
    }

    public void addBombsNear() {
        this.bombsNear++;
    }

    public void addBomb(){
        bomb = true;
    }

    public boolean hasBomb(){
        return bomb;
    }

    public void show(){
        hidden = false;
    }

    public boolean isHidden(){
        return hidden;
    }
}
