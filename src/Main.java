public class Main {
    public static void main(String[] args) {
        int max = 10;

        for(int i = 0; i < (max-1); i++){
            System.out.println("max: " + max);
        
            System.out.println(i);

        }

        Gameboard gb = new Gameboard(Difficulty.EASY);
        gb.generateBomb();
        gb.printing();
    }
}
