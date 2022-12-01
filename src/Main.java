public class Main {
    public static void main(String[] args) {

        Gameboard gb = new Gameboard(Difficulty.EASY);
        gb.generateBomb();
        //gb.printing();
        //Testing generateBombs();

        Bomb[] bombs = gb.getBombs();
        Space[][] spaces = gb.getSpaces();

        
        for(int i = 0; i < 10; i++){
            System.out.printf("%d - bomb(%d,%d).\n", i, bombs[i].getCoordinateX(), bombs[i].getCoordinateY());
        }

        /*Counting the number of bombs
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
    
    }
}
