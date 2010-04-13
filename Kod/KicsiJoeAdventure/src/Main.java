
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/**
 * Class Main:
 * A fő osztály, itt található a szkeleton menüje.
 */

public class Main {
	private static String WorkingDirectory;
	public static Game game;

        /**
         * A program indulása.
         * @param args
         */
    public static void main(String[] args) {
		String myfile = "BunnyTests.txt";
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
			game.WorkingDirectory = WorkingDirectory;
			try{
				BufferedReader r = new BufferedReader(new FileReader(new File(WorkingDirectory,Filename)));
				while((line = r.readLine()) != null){
					game.CommandInterpreter(line);
				}
				game.outputStream.println("[END]");

				// ...OMG... //
				if(game.outputStream != System.out)
					game.outputStream.close();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		else if(Command.startsWith("SetWorkingDirectory(")){
			WorkingDirectory = Command.substring(20,Command.length()-1);
		}
	}
}

