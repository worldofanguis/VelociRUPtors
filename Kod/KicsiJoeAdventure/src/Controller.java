/**
 * A vezérlésért felelős osztály, összekapcsolja a modellt és
 * a megjelenítést.
 */
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Controller extends JFrame implements ActionListener,KeyListener,WindowListener{
    /**
     * Játék
     */
    public static Game game;

    /**
     * Megjelenítés
     */
    public static View view;

    /**
     *
     * Üzenet mező
     */
	public static JTextField txt;

    /**
     * Új játék gomb
     */
    private JButton newGameButton;

    /**
     * Kilépés a játékból gomb
     */
	private JButton exitGameButton;

    /**
     * Időkijelzéshez
     */
    private JLabel time;

    /**
     * Pontszám
     */
    private JLabel score;

    /**
     * Kiválasztott irány megjelenítése
     */
    private DirG dirg;

    /**
     * Bankállapot megjelenítése
     */
    private static bankState bankst;

    /***
     * Nyúlállapot megjelenítése
     */
    private static bunnyState bunnyst;

    /**
     * Eltelt idő
     */
    private int sec;

    //private int points;

    /**
     * Igaz, ha éppen futhat a játék.
     */
    private static boolean run;

    /**
     * Konstruktor
     * Elemek létrehozása, ablakhoz hozzáadása és rendezése.
     * Futtatás.
     */
    Controller(){
		init();
        view = new View();
        setTitle("Kicsi Joe Adventures");

		setLayout( new BorderLayout() );

        //Felső csík
		JPanel p1 = new JPanel();
		p1.setLayout(new FlowLayout());

        //Gombok
		newGameButton = new JButton("New Game");
		exitGameButton = new JButton("Exit Game");
		newGameButton.addActionListener(this);
		exitGameButton.addActionListener(this);
		p1.add(newGameButton);
		p1.add(exitGameButton);

        //Allapotjelzok
		JLabel l1 = new JLabel("Time: ");
		time = new JLabel("00:00");
		time.setSize(100, 20);
		JLabel l2 = new JLabel("Score: ");
		score = new JLabel(""+Controller.game.getPoints()+"");
		score.setSize(100, 20);
		p1.add(l1);
		p1.add(time);
		p1.add(l2);
		p1.add(score);


       //Extra megjelenítések
        dirg = new DirG();
        bankst = new bankState();
        bunnyst = new bunnyState();
        p1.add(dirg);
        p1.add(bankst);
        p1.add(bunnyst);

		add("North",p1);

        //Középen a pálya
	    add("Center", view);

        //Alsó csík
		JPanel p2 = new JPanel(new FlowLayout());

        //Üzenet
		txt = new JTextField("Default", 20);
		txt.setEditable( false );
        txt.setFocusable(false);	   
		p2.add(txt);

		add("South",p2);

        //Ablak eseményei, fókusz
		addKeyListener(this);
        addWindowListener(this);
		pack();
        setResizable(false);
        setVisible(true);
		this.requestFocus();

        //Mehet
        run();
    }

    /**
     * Gombeseményekhez
     * @param e
     */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == exitGameButton)
			System.exit(1);
		else if(e.getSource() == newGameButton){
                    init();
                    game.Draw();
                    view.repaint();
                    time.setText("00:00");
                    score.setText(""+Controller.game.getPoints());
                    msg("");
                    //addKeyListener(this);

		}
			

        this.requestFocus();
	}

    /**
     * Billentyű lenyomás
     * @param e
     */
	public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if ( key == KeyEvent.VK_LEFT) { //balra nyíl
            game.setRobberDirection(Directions.LEFT);
            dirg.Draw(0);
        }
        else if ( key == KeyEvent.VK_UP) { //felfele nyíl
            game.setRobberDirection(Directions.UP);
            dirg.Draw(1);
        }
        else if ( key == KeyEvent.VK_DOWN) { //lefele nyíl
            game.setRobberDirection(Directions.DOWN);
            dirg.Draw(3);
        }
        else if ( key == KeyEvent.VK_RIGHT) { //jobbra nyíl
            game.setRobberDirection(Directions.RIGHT);
            dirg.Draw(2);
        } else if ( key == KeyEvent.VK_A) { //A bill -> gyorsít
            txt.setText("sebesseg: "+game.setRobberVelocity(-1));
        } else if ( key == KeyEvent.VK_S) { //S bill -> lassít
            txt.setText("sebesseg: "+game.setRobberVelocity(1));
        }
	}

        /**
     * X
     * @param e
     */
    public void windowClosing(WindowEvent e) {
        System.exit(1);
    }

    /**
     * Main loop
     */
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
                    Controller.game.addPoints(-1);
                    time.setText(""+((sec/60 <10)?"0":"")+sec/60+":"+((sec%60<10)?"0":"")+(sec%60));
                    score.setText(""+Controller.game.getPoints());
                }
                Thread.sleep(1);
                Time++;
                temp++;
            } catch (InterruptedException ex) {
                
            }
		}
        }
	}

    //Nem implementált események
    public void keyTyped(KeyEvent e) {
    }
    public void keyReleased(KeyEvent e) {
	}
    public void windowOpened(WindowEvent e) {
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
    //Nem implementált események vége

    /**
     * Üzenetsávra írás
     * @param string
     */
    static void msg(String string) {
        txt.setText(string);
    }

    /**
     * Játék vége
     */
    static void finish() {
        run = false;
    }

    /**
     * Tagváltozók inicializálása
     * (őket kell újra a new game esetén)
     */
    private void init() {
		game = null;
        game = new Game();
        game.Initialization();
        sec = 0;
        Controller.game.addPoints(10000);
        run = true;
    }

    /**
     * Megváltozott a bankállapot, rajzoltatunk
     */
    static void bankChanged() {
        bankst.Draw(true);
    }

    /**
     * Megváltozott, hogy van-e nyulunk, rajzoltatunk.
     * @param b
     */
    static void bunnyChanged(boolean b) {
        bunnyst.Draw(b);
    }
}

