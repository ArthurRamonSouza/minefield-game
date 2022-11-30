import java.util.Random;

public class Gameboard {
   
    //This array multidimensional represents the spaces on the gameboard.
    //Space is a separeted class with attributes and methods.
    static Space[][] spaces;
    private static int maxSize;
    private static int length;

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
    }
    int[] bombsPositions = new int[10];

    public void generateBomb(){
        Random bombGenerator = new Random();
        
        for(int i = 0; i < 10; i++){
            bombsPositions[i] = bombGenerator.nextInt(0, maxSize-1);
        }

        for(int i = 1; i < 10; i++){
            for(int j = 0; j < i; j++){
                if(bombsPositions[j] == bombsPositions[i]){
                    bombsPositions[j] = bombGenerator.nextInt(0, maxSize-1);
                    i--;
                }
            }
        }
    }

    public void printing(){
        //All spaces has 0 by default
        for(int i = 0; i < length; i++){
            for(int j = 0 ; j < length; j++){
                System.out.println("space empty?" + spaces[i][j]);
            }
        }

        for(int i = 0; i < 10; i++){
            System.out.println("bomb: " + bombsPositions[i]);
        }

    }
}