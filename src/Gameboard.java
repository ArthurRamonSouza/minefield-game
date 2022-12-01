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

    public void generateBomb(){
        Random random = new Random();
        
        for(int i = 0; i < 10; i++){
            bombs[i] = new Bomb((random.nextInt(0, maxSize-1)),(random.nextInt(0, maxSize-1)));
        }

        //Checking if we have two or more bombs in the same position (x,y), if yes, we change the coordinates 
        for(int i = 1; i < 10; i++){
            for(int j = 0; j < i; j++){
                if((bombs[i].getCoordinateX() == bombs[j].getCoordinateX()) && (bombs[i].getCoordinateY() == bombs[j].getCoordinateY())){
                    bombs[j].setCoordinateX(random.nextInt(0, maxSize-1));
                    bombs[j].setCoordinateY(random.nextInt(0, maxSize-1));
                    i=-1;
                }
            }
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

    public void printBoardConsole(){
        //white square -> \u25A1
        //black square -> \u25A0
        char unicode = '\u25A0';

        for(int line = 0; line < length; line++){
            for(int i = 0; i < length; i++){
                if(!spaces[line][i].isHidden()){
                    unicode = '\u25A1';
                }
                System.out.print(unicode + " ");
            }
            System.out.println();
        }


    }

}