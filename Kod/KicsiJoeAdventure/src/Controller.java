
import java.awt.BorderLayout;
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
        private JLabel time;
        private JLabel score;
        private DirG dirg;
        private static bankState bankst;
        private static bunnyState bunnyst;

        private int sec;
        private int points;

        private static boolean run;

    Controller(){
		init();
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
		time = new JLabel("00:00");
		time.setSize(100, 20);
		JLabel l2 = new JLabel("Score: ");
		score = new JLabel(""+points+"");
		score.setSize(100, 20);

		p1.add(l1);
		p1.add(time);
		p1.add(l2);
		p1.add(score);

                dirg = new DirG();
                bankst = new bankState();
                bunnyst = new bunnyState();

                p1.add(dirg);
                p1.add(bankst);
                p1.add(bunnyst);

		add("North",p1);

	    add("Center", view);

		JPanel p2 = new JPanel(new FlowLayout());


		txt = new JTextField("Default", 20);
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
                    init();
                    game.Draw();
                    view.repaint();
                    time.setText("00:00");
                    score.setText(""+points);
                    msg("");
                    //addKeyListener(this);

		}
			

        this.requestFocus();
	}

	public void keyTyped(KeyEvent e) {
          	}

	public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if ( key == KeyEvent.VK_LEFT) {
                game.setRobberDirection(Directions.LEFT);
                dirg.Draw(0);
            }
            else if ( key == KeyEvent.VK_UP) {
                game.setRobberDirection(Directions.UP);
                dirg.Draw(1);
            }
            else if ( key == KeyEvent.VK_DOWN) {
                game.setRobberDirection(Directions.DOWN);
                dirg.Draw(3);
            }
            else if ( key == KeyEvent.VK_RIGHT) {
                game.setRobberDirection(Directions.RIGHT);
                dirg.Draw(2);
            } else if ( key == KeyEvent.VK_A) { //gyorsít
                txt.setText("sebesseg: "+game.setRobberVelocity(-1));
            } else if ( key == KeyEvent.VK_S) { //lassít
                txt.setText("sebesseg: "+game.setRobberVelocity(1));
            }
	}

	public void keyReleased(KeyEvent e) {
	}

	private void run() {
        int Time = 0;
        int temp = 0;
        while(true) {
		while(run) {
            try {
                if(Time > 10) {
                    Time = 0;
                    game.Update();
                    game.Draw();
                }
                view.repaint();
                if ( temp == 1000) {
                    temp = 0;
                    sec++;
                    points--;
                    time.setText(""+((sec/60 <10)?"0":"")+sec/60+":"+((sec%60<10)?"0":"")+(sec%60));
                    score.setText(""+points);
                }
                Thread.sleep(1);
                Time++;
                temp++;
            } catch (InterruptedException ex) {
                
            }
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

    static void msg(String string) {
        txt.setText(string);
    }

    static void finish() {
        run = false;
    }

    private void init() {
        game = new Game();
	game.Initialization();
        sec = 0;
        points = 10000;
        run = true;
    }

    static void bankChanged() {
        bankst.Draw(true);
    }

    static void bunnyChanged(boolean b) {
        bunnyst.Draw(b);
    }
}

