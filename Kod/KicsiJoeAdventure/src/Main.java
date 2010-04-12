
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
		String myfile = "C:\\Ároné\\km\\VelociRUPtors\\Kod\\KicsiJoeAdventure\\TesztScripts.txt";
		if(args.length != 1){
			//System.out.println("U FAIL!!! USE THE COMMAND LINE PARAMTER TO SPECIFY A TESTING SCRIPT!!!");
			//return;
			
		}
		String line;
		try{
		    BufferedReader br;
		    if(args.length == 0){
			br = new BufferedReader(new FileReader(new File(myfile)));
		    }
		    else{
			br = new BufferedReader(new FileReader(new File(args[0])));
		    }
			while((line = br.readLine()) != null){
				ExececuteTests(line);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
    }

	public static void ExececuteTests(String Command){
		if(Command.startsWith("Execute(")){
			String Filename = Command.substring(8,Command.length()-1);
			String line;
			game = new Game();
			try{
                            game.outputStream.println("[START]");
                            BufferedReader r = new BufferedReader(new FileReader(new File(Filename)));
                            while((line = r.readLine()) != null){
                            game.CommandInterpreter(line);
                            }
                            game.outputStream.println("[END]");
                            game.outputStream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}

