
import java.awt.*;
import java.net.URL;

public class View extends Panel
{

	private PoliceView policelView;
	private CivilView civilView;
	private RobberView robberView;
	private RoadView roadView;
	private BankView bankView;
	private HideoutView hideoutView;
	private StopView stopView;
	private ExitView exitView;
	private BunnyView bunnyView;
	private LampView lampView;
	public CivilView m_CivilView;
	public PoliceView m_PoliceView;
	public RobberView m_RobberView;
	public RoadView m_RoadView;
	public BankView m_BankView;
	public HideoutView m_HideoutView;
	public StopView m_StopView;
	public ExitView m_ExitView;
	public BunnyView m_BunnyView;
	public LampView m_LampView;

        //Interakcióhoz szükséges elemek
        public Button ng;
        public Button eg;
        public Label score;
        public Label time;
	
	//Erre a bufferre rajzolunk ha változás van
	private Image backBuffer;
	Graphics bufferGraphics;
	

        //Rajzvászon
        private MapCanvas canvas;
        private GameCanvas canvas;
	private Graphics bufferGraphics;
	private Image backBuffer;

        /*
         * képek tárolása. Lehetne hashes lista,
         * most csak példa
         */
        Image ut;
        Image auto;

	public View(){
            imageLoading();

	    /* Ablak elemei */
            setLayout( new BorderLayout() );

            Panel p1 = new Panel();
            p1.setLayout(new FlowLayout());
            ng = new Button("New Game");
            eg = new Button("Exit Game");


            p1.add(ng);
            p1.add(eg);
            Label l1 = new Label("Time: ");
            time = new Label("");
            time.setSize(100, 20);
            Label l2 = new Label("Score: ");
            score = new Label("4444");
            score.setSize(100, 20);

            p1.add(l1);
            p1.add(time);
            p1.add(l2);
            p1.add(score);
            time.setText("999");

            add("North",p1);

            canvas = new MapCanvas();
            add("Center",canvas);

            Panel p2 = new Panel(new FlowLayout());
            //Ideiglenesen lógó helyett
            Canvas c = new Canvas();
            c.setSize(100,50);

            TextField txt = new TextField("Alap üzenet", 20);
            txt.setEditable( false );

            p2.add(c);
            p2.add(txt);

            add("South",p2);
            /* Ablak elemei */


	    canvas = new GameCanvas();
	    canvas.setSize(800, 500);
	    add(canvas);
	    
	}

	public void Draw(){
	    
	}

	public void Draw(){
	    if(backBuffer == null)
		backBuffer = this.createImage(800, 500);
	    Graphics g=backBuffer.getGraphics();
	    g.fillRect(20, 20, 500, 500);
	    
	}

	/**
	 * 
	 * @param bank
	 */
	public void Draw(Bank bank){

	}

	/**
	 * 
	 * @param hideout
	 */
	public void Draw(Hideout hideout){

	}

	/**
	 * 
	 * @param s
	 */
	public void Draw(StopSign s){

	}

	/**
	 * 
	 * @param l
	 */
	public void Draw(Lamp l){
	    Graphics g = backBuffer.getGraphics();
	    g.setColor(Color.red);
	    g.drawString("LAMMPPPP", 40, 40);
	    canvas.invalidate();

	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Road r){

	}

	/**
	 * 
	 * @param c
	 */
	public void Draw(Civil c){

	}

	/**
	 * 
	 * @param p
	 */
	public void Draw(Police p){

	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Robber r){

	}

	/**
	 * 
	 * @param b
	 */
	public void Draw(Bunny b){

	}

	/**
	 * 
	 * @param e
	 */
	public void Draw(ExitSign e){

	}



        /**
         * Külön osztály annak, ahova rajzoljuk a pályát
         * a paint függvénye miatt, mert az hívódik meg ha kell implicit.
         * Át kell valahogy adni neki pályát.
         */
    class MapCanvas extends Canvas {
        private MapCanvas() {
            setBackground(Color.red);
        }

    class GameCanvas extends Canvas{
	@Override
        public void paint(Graphics g) {
            bufferGraphics = g;
            if(backBuffer==null){
                    backBuffer = createImage(800,500);
                }
            bufferGraphics = backBuffer.getGraphics();

//            for (int i = 0; i<800; i+=50)
//                for (int j = 0; j<500; j+=50)
//                    bufferGraphics.drawImage(ut, i, j, null);
//
//            for (int i = 0; i<800; i+=50)
//                for (int j = 0; j<500; j+=50)
//                    if ( ((i - j) % 20) == 0 )
//                        bufferGraphics.drawImage(auto, i, j, null);

            Toolkit toolkit = Toolkit.getDefaultToolkit();
            URL url = this.getClass().getResource("vmi.jpg");
            Image img = toolkit.getImage(url);

            bufferGraphics.drawImage(img, 0, 0, null);

            g.drawImage(backBuffer, 0, 0, null);
        }





    }
    

    /**
     * Betölteni a képeket
     */
    private void imageLoading() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();

        URL url = this.getClass().getResource("../img/road.png");

        ut = toolkit.getImage(url);

        url = this.getClass().getResource("../img/civil.png");

        auto = toolkit.getImage(url);
    }

    public void paint(Graphics g){
	canvas.invalidate();
    }
}
