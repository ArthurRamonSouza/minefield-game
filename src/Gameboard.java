import java.util.Random;

public class Gameboard {
   
    //This array multidimensional represents the spaces on the gameboard.
    //Space is a separeted class with attributes and methods.
    private Space[][] spaces;
    
    //Total of spaces of the board
    private int maxSize;
    
    //Quantity of spaces N in our board NxN
    private int length;

    //This array will contain the coordinates of the bombs
    private Bomb[] bombs = new Bomb[10];
    
    Gameboard(Difficulty dificulty){
        
        if(Difficulty.EASY == dificulty){
            spaces = new Space[8][8];
            maxSize = 64;
            length = 8;
        } else if(Difficulty.NORMAL == dificulty){
            spaces = new Space[9][9];
            maxSize = 81;
            length = 9;
        } else if(Difficulty.HARD == dificulty){
            spaces = new Space[10][10];
            maxSize = 100;
            length = 10;
        } else{
            spaces = new Space[14][14];
            maxSize = 196;
            length = 14;
        }
        
        //Initializing the spaces in the board
        for(int i = 0; i < length; i++){
            for(int j = 0 ; j < length; j++){
                spaces[i][j] = new Space();;
            }
        }
    }

    public Bomb[] getBombs() {
        return bombs;
    }
    
    public Space[][] getSpaces() {
        return spaces;
    }

    public int getLength() {
        return length;
    }

    public void generateBomb(){
        Random random = new Random();
        
        for(int i = 0; i < bombs.length; i++){
            bombs[i] = new Bomb((random.nextInt(0, length)),(random.nextInt(0, length)));
        }

        //Checking if we have two or more bombs in the same position (x,y), if yes, we change the coordinates 
        for(int i = 1; i < bombs.length; i++){
            for(int j = 0; j < i; j++){
                if((bombs[i].getCoordinateX() == bombs[j].getCoordinateX()) && (bombs[i].getCoordinateY() == bombs[j].getCoordinateY())){
                    bombs[j].setCoordinateX(random.nextInt(0, length));
                    bombs[j].setCoordinateY(random.nextInt(0, length));
                    i=-1;
                }
            }
        }

        //Planting the bombs
        for(int i = 0; i < bombs.length; i++){
            int x = bombs[i].getCoordinateX();
            int y = bombs[i].getCoordinateY();
            spaces[x][y].addBomb();
        }
       
    }

    /*
    public void printing(){
        int count = 0;
        //All spaces has 0 by default
        for(int i = 0; i < length; i++){
            for(int j = 0 ; j < length; j++){
                System.out.println("space object: " + spaces[i][j]);
                count++;
            }
        }
        System.out.println("space count:" + count++);
        for(int i = 0; i < 10; i++){
            System.out.printf("%d - bomb(%d,%d).\n", i, bombs[i].getCoordinateX(), bombs[i].getCoordinateY());
        }
        
    }
    */

    /*
    public void printBoardConsole(){
        //filled square -> \u25A0
        char unicode = '\u25A0';

        for(int line = 0; line < length; line++){
            for(int column = 0; column < length; column++){
                if(spaces[line][column].isHidden()){
                    unicode = '\u25A0';
                }
                if(spaces[line][column].hasBomb()){
                    unicode = 'X';
                }
                System.out.print(unicode + " ");
            }
            System.out.println();
        }
    }
    */

    public void printBoardConsole(){
        //filled square -> \u25A0
        char unicode = '\u25A0';

        for(int line = 0; line < length; line++){
            for(int column = 0; column < length; column++){
                if(spaces[line][column].isHidden()){
                    unicode = '\u25A0';
                }else{
                    unicode = ' ';
                }
                System.out.print(unicode + " ");
            }
            System.out.println();
        }
    }

    //Switch the property hidden if the space is hidden
    public void showSpace(int x, int y){
        if(spaces[x][y].isHidden() == true){
            spaces[x][y].show();
        } else{
            System.out.println("This space is not hidden!");
        }
    }

}