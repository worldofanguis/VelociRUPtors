/**
 * Class Main:
 * A fő osztály, itt található a szkeleton menüje.
 */

public class Main {
	public static Game game;

        /**
         * A program indulása.
         * @param args
         */
    public static void main(String[] args) {
        game = new Game();
		game.loadMapFromFile("C:\\Users\\Ferdi\\Desktop\\Szoftver Labor4\\VelociRUPtors\\TesztMap.txt");
		game.ShowMap(System.out);
    }

}
