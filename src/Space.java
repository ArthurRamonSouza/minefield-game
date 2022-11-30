public class Space {
    private boolean bomb = false;
    private boolean hidden = true;

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
