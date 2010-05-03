
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Controller extends JFrame implements ActionListener,KeyListener,WindowListener{
    public static Game game;
    public static View view;
	public static JTextField txt;

	private JButton newGameButton;
	private JButton exitGameButton;

    Controller(){
		game = new Game();
		game.Initialization();

		view = new View();

		setTitle("Kicsi Joe Adventures");

		setLayout( new BorderLayout() );

		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		newGameButton = new JButton("New Game");
		exitGameButton = new JButton("Exit Game");

		newGameButton.addActionListener(this);
		exitGameButton.addActionListener(this);

		p1.add(newGameButton);
		p1.add(exitGameButton);
		JLabel l1 = new JLabel("Time: ");
		JLabel time = new JLabel("");
		time.setSize(100, 20);
		JLabel l2 = new JLabel("Score: ");
		JLabel score = new JLabel("4444");
		score.setSize(100, 20);

		p1.add(l1);
		p1.add(time);
		p1.add(l2);
		p1.add(score);
		time.setText("999");

		add("North",p1);

	    add("Center", view);

		JPanel p2 = new JPanel(new FlowLayout());


		txt = new JTextField("Alap üzenet", 20);
		txt.setEditable( false );
        txt.setFocusable(false);
	   
		p2.add(txt);

		add("South",p2);

		addKeyListener(this);
        addWindowListener(this);

		pack();
        setResizable(false);
        setVisible(true);
		this.requestFocus();

        run();
    }

	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitGameButton)
			System.exit(1);
		else if(e.getSource() == newGameButton){

		}
			

        this.requestFocus();
	}

	public void keyTyped(KeyEvent e) {
		txt.setText("keytyped");
	}

	public void keyPressed(KeyEvent e) {
		txt.setText("keypressed");
	}

	public void keyReleased(KeyEvent e) {
		txt.setText("keyreleased");
	}

	private void run() {
        int Time = 0;
		while(true) {
            try {
                if(Time > 10) {
                    Time = 0;
                    game.Update();
					game.Draw();
                }
                view.repaint();
                Thread.sleep(100);
                Time++;
            } catch (InterruptedException ex) {
                
            }
		}
	}

    public void windowOpened(WindowEvent e) {
    }
    public void windowClosing(WindowEvent e) {
        System.exit(1);
    }
    public void windowClosed(WindowEvent e) {
    }
    public void windowIconified(WindowEvent e) {
    }
    public void windowDeiconified(WindowEvent e) {
    }
    public void windowActivated(WindowEvent e) {
    }
    public void windowDeactivated(WindowEvent e) {
    }
}
