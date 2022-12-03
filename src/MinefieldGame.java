import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.Scanner;

public class MinefieldGame {
    public static void main(String[] args) {
        Gameboard gb = new Gameboard(Difficulty.EASY);
        int[] coordinates = new int[2];

        //gb.printing();

        /*//Testing generateBombs();
        Bomb[] bombs = gb.getBombs();
        for(int i = 0; i < 10; i++){
            C
        */

        /*//Counting the number of bombs
        Space[][] spaces = gb.getSpaces();

        int count = 0;
        for(int i = 0; i < (gb.getLength()-1); i++){
            for(int j = 0; j < (gb.getLength()-1); j++){
               if(spaces[i][j].hasBomb()){
                    count++;
               }
            }
        }
        System.out.printf("%d bombs in the game.\n", count);
        */

        gb.generateBomb();
        gb.printBoardConsole();
        makeMove(gb, coordinates);
        gb.showSpace(coordinates[0],coordinates[1]);
        gb.printBoardConsole();
        gameOver(gb);

    }

    //Getting and storing the user input
    public static int[] makeMove(Gameboard gb, int[] coordinates){
        Scanner scan = new Scanner(System.in);
        System.out.print("Type the coordinate that you want to verify, like (x,y): ");

        try{
            String strgCoordinates = scan.nextLine();

            //Parsing cahr to int
            int coordX = Character.getNumericValue(strgCoordinates.codePointAt(1));
            int coordY = Character.getNumericValue(strgCoordinates.codePointAt(3));

            //Negative number? Some inappropriate character was typed.
            if((coordX < 0) || (coordY < 0)){
                System.out.printf("Invalid coordinate!\nThe must be a positive number.\n");
                makeMove(gb, coordinates);
            }

            //Others possibles invalid coordinates will be treat here
            if(coordX > (gb.getLength()-1) || coordY > (gb.getLength()-1)){
                System.out.printf("Invalid coordinate!\nThe minimum coordinate is (0,0) and the maximum is (%d,%d).\n", (gb.getLength()-1), (gb.getLength()-1));
                makeMove(gb, coordinates);
            }

            coordinates[0] = coordX;
            coordinates[1] = coordY;

        }catch(NoSuchElementException e1){
            e1.getMessage();

        }catch(IllegalStateException e2){
            e2.getMessage();

        }catch(IndexOutOfBoundsException e3){
            e3.getMessage();
            System.out.println("Invalid coordinate!");
            makeMove(gb, coordinates);

        }finally{
            scan.close();
        }

        bombsNear(gb, coordinates);
        return coordinates;
    }

    //Shows on the board all the bombs in the game. 
    public static void gameOver(Gameboard gb){
        //filled square -> \u25A0
        char unicode = ' ';

        for(int line = 0; line < gb.getLength(); line++){
            for(int column = 0; column <  gb.getLength(); column++){
                if(gb.getSpaces()[line][column].isHidden()){
                    unicode = ' ';
                }
                if(gb.getSpaces()[line][column].hasBomb()){
                    unicode = 'X';
                }
                System.out.print(unicode + " ");
            }
            System.out.println();
        }
    }

    public static void bombsNear(Gameboard gb,  int[] coordinates){
        Space[][] spaces = gb.getSpaces();

        // (x-1, y-1) (x-1, y) (x-1, y+1)
        // (x, y-1) (x, y)   (x, y+1)
        // (x+1, y-1) (x+1, y) (x+1, y+1)
       
        if(spaces[(coordinates[0]-1)][(coordinates[1]-1)].hasBomb()){
            spaces[(coordinates[0]-1)][(coordinates[1])].addBombsNear();
            spaces[(coordinates[0])][(coordinates[1]-1)].addBombsNear();
        }else{
            spaces[(coordinates[0]-1)][(coordinates[1]-1)].show();
            spaces[(coordinates[0]-1)][(coordinates[1])].show();
            spaces[(coordinates[0])][(coordinates[1]-1)].show();
        }
        
        if(spaces[(coordinates[0]-1)][(coordinates[1])].hasBomb()){
            spaces[(coordinates[0]-1)][(coordinates[1]-1)].addBombsNear();
            spaces[(coordinates[0]-1)][(coordinates[1]+1)].addBombsNear();
        } else{
            spaces[(coordinates[0]-1)][(coordinates[1])].show();
            spaces[(coordinates[0]-1)][(coordinates[1]-1)].show();
            spaces[(coordinates[0]-1)][(coordinates[1]+1)].show();
        }

        if(spaces[(coordinates[0]-1)][(coordinates[1]+1)].hasBomb()){
            spaces[(coordinates[0]-1)][(coordinates[1])].addBombsNear();
            spaces[(coordinates[0])][(coordinates[1]+1)].addBombsNear();
        }else{
            spaces[(coordinates[0]-1)][(coordinates[1]+1)].show();
            spaces[(coordinates[0]-1)][(coordinates[1])].show();
            spaces[(coordinates[0])][(coordinates[1]+1)].show();
        }

        if(spaces[(coordinates[0])][(coordinates[1]-1)].hasBomb()){
            spaces[(coordinates[0]-1)][(coordinates[1]-1)].addBombsNear();
            spaces[(coordinates[0]+1)][(coordinates[1]-1)].addBombsNear();
        }else{
            spaces[(coordinates[0])][(coordinates[1]-1)].show();
            spaces[(coordinates[0]-1)][(coordinates[1]-1)].show();
            spaces[(coordinates[0]+1)][(coordinates[1]-1)].show();
        }

        if(spaces[(coordinates[0])][(coordinates[1]+1)].hasBomb()){
            spaces[(coordinates[0]-1)][(coordinates[1]+1)].addBombsNear();
            spaces[(coordinates[0]+1)][(coordinates[1]+1)].addBombsNear();
        }else{
            spaces[(coordinates[0])][(coordinates[1]+1)].show();
            spaces[(coordinates[0]-1)][(coordinates[1]+1)].show();
            spaces[(coordinates[0]+1)][(coordinates[1]+1)].show();
        }

        if(spaces[(coordinates[0]+1)][(coordinates[1]-1)].hasBomb()){
            spaces[(coordinates[0])][(coordinates[1]-1)].addBombsNear();
            spaces[(coordinates[0]+1)][(coordinates[1])].addBombsNear();
        }else{
            spaces[(coordinates[0]+1)][(coordinates[1]-1)].show();
            spaces[(coordinates[0])][(coordinates[1]-1)].show();
            spaces[(coordinates[0]+1)][(coordinates[1])].show();
        }

        if(spaces[(coordinates[0]+1)][(coordinates[1]+1)].hasBomb()){
            spaces[(coordinates[0])][(coordinates[1]+1)].addBombsNear();
            spaces[(coordinates[0]+1)][(coordinates[1])].addBombsNear();
        }else{
            spaces[(coordinates[0]+1)][(coordinates[1]+1)].show();
            spaces[(coordinates[0])][(coordinates[1]+1)].show();
            spaces[(coordinates[0]+1)][(coordinates[1])].show();
        }
    }
}