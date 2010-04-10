
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
		String line;
		try{
			BufferedReader r = new BufferedReader(new FileReader(new File("..\\..\\TesztCommands.txt")));
			while((line = r.readLine()) != null){
				game.CommandInterpreter(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }

}
