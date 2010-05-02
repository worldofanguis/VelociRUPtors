
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.*;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class Controller{
    private View view;
    public static Game game = new Game();
    //private Timer timer;
    private Frame frame;
     //Ideiglenesen lógó helyett
     LogoCanvas logoCanvas = new LogoCanvas();
     
    
    
    Controller(){	
	view = new View();
	frame = new GameFrame();

	frame.setTitle("Kicsi Joe Adventures");
	frame.setSize(800,600);
	
	frame.setLayout( new BorderLayout() );

            Panel p1 = new Panel();
            p1.setLayout(new FlowLayout());
            Button newGameButton = new Button("New Game");
            Button exitGameButton = new Button("Exit Game");


            p1.add(newGameButton);
            p1.add(exitGameButton);
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

	    frame.add("Center", view);

            Panel p2 = new Panel(new FlowLayout());
           

            TextField txt = new TextField("Alap üzenet", 20);
            txt.setEditable( false );
	    logoCanvas.setSize(100,50);
            p2.add(logoCanvas);
            p2.add(txt);

            frame.add("South",p2);

	    frame.addWindowListener(new gameWindowListener());
	    frame.addKeyListener(new gameKeyListener());
	    frame.show();
            /* Ablak elemei */
	
	    game.setView(view);
	    game.WriteLampTest();
    }

    class gameWindowListener extends WindowAdapter{
	@Override
	    public void windowClosing(WindowEvent e){
		e.getWindow().dispose();
	    }
    }
    class gameKeyListener extends KeyAdapter{
	public void keyPressed(KeyEvent e){
	    game.WriteLampTest();
	}

    }
    
    class GameFrame extends Frame{
	public void paint(Graphics g){
	    logoCanvas.invalidate();
	    view.invalidate();
	}
    }

    class LogoCanvas extends Canvas{
	public void paint(Graphics g){
	    g.drawString("LOGO", 10, 10);
	}
    }


}
