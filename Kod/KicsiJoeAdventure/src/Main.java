
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		String myfile = "EpuletekEsGeneralas.txt";

//		new Controller();

                /*
                 * Controllerbe kell majd
                 */
                View view = new View();
                Frame frame = new Frame("Próba");
                frame.setLayout( new BorderLayout() );

                Panel p1 = new Panel();
                p1.setLayout(new FlowLayout());
                Button ng = new Button("New Game");
                Button eg = new Button("Exit Game");

                p1.add(ng);
                p1.add(eg);
                Label l1 = new Label("Time: ");
                Label time = new Label("");
                time.setSize(100, 20);
                Label l2 = new Label("Score: ");
                Label score = new Label("4444");
                score.setSize(100, 20);

                p1.add(l1);
                p1.add(time);
                p1.add(l2);
                p1.add(score);
                time.setText("999");

                frame.add("North",p1);
                view.display();
                frame.add("Center",view);

                Panel p2 = new Panel(new FlowLayout());
                //Ideiglenesen lógó helyett
                Canvas c = new Canvas();
                c.setSize(100,50);

                TextField txt = new TextField("Alap üzenet", 20);
                txt.setEditable( false );

                p2.add(c);
                p2.add(txt);

                frame.add("South",p2);

                frame.addWindowListener( new WindowAdapter()
                    {
                         public void windowClosing(WindowEvent e)
                         {
                          System.exit(-1);
                         }
                    }
                );
                
                frame.setSize(800,600);
                frame.setResizable(false);
                frame.setVisible(true);
                /* /controller */

		/*if(args.length != 1){
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
		}*/

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

