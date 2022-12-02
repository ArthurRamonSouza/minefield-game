import java.util.NoSuchElementException;
import java.lang.IllegalStateException;
import java.util.Scanner;

public class MinefieldGame {
    public static void main(String[] args) {

        Gameboard gb = new Gameboard(Difficulty.EASY);
        gb.generateBomb();
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
       
        gb.printBoardConsole();
        makeMove(gb);   
    }

    //Getting the user input
    public static void makeMove(Gameboard gb){
        Scanner scan = new Scanner(System.in);

        System.out.print("Type the coordinate that you want to verify, like (x,y): ");
        try{
            String coordinates = scan.nextLine();

            //Negative number? Not here!
            if((coordinates.codePointAt(1) == '-') || (coordinates.codePointAt(3) == '-')){
                System.out.printf("Invalid coordinate!\nThe coordinates cannot be a negative number.\n");
                makeMove(gb);
            }

            //Parsing cahr to int
            int coordX = Character.getNumericValue(coordinates.codePointAt(1));
            int coordY = Character.getNumericValue(coordinates.codePointAt(3));

            //Possibles invalid coordinates will be treat here
            if((coordX < (gb.getLength()-1) && coordX >= 0) || (coordY < (gb.getLength()-1)  && coordX >= 0)){
                System.out.printf("Invalid coordinate!\nThe minimum coordinate is (0,0) and the maximum is (%d,%d).\n", (gb.getLength()-1), (gb.getLength()-1));
                makeMove(gb);
            }

        }catch(NoSuchElementException e1){
            e1.getMessage();

        }catch(IllegalStateException e2){
            e2.getMessage();

        }catch(IndexOutOfBoundsException e3){
            e3.getMessage();
            System.out.println("Invalid coordinate!");
            makeMove(gb);
        }finally{
            scan.close();
        }
    }

}

