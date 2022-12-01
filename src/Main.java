public class Main {
    public static void main(String[] args) {

        Gameboard gb = new Gameboard(Difficulty.EASY);
        gb.generateBomb();
        //gb.printing();
        gb.printBoardConsole();
    
    }
}
