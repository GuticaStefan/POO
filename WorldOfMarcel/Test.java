package WorldOfMarcel;

public class Test {
    public static void main(String[] args) {
        Game game = Game.getInstance() ;
        try {
            game.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
