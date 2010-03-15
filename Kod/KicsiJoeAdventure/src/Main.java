import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	public static Game game;
    public static void main(String[] args) {
        game = new Game();
		game.setID("game");

		System.out.println("1 - Utkozesek");
		System.out.println("2 - Civilgeneralas es eltuntetes");
		System.out.println("3 - Mozgatas");
		System.out.println("4 - Epuletek");
		System.out.println("5 - Kozlekedes / Stop és Piros tabla");
		System.out.println("6 - Kozlekedes / Exit tabla");
		System.out.println("7 - Inicializalas");

		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}

			 if(line.equals("1")) game.TestMap1();
		else if(line.equals("2")) game.TestMap2();
		else if(line.equals("3")) game.TestMap3();
		else if(line.equals("4")) game.TestMap4();
		else if(line.equals("5")) game.TestMap5();
		else if(line.equals("6")) game.TestMap6();
		else if(line.equals("7")) game.TestMap7();
    }

}
