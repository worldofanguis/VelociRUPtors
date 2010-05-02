
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
	private Image backBuffer = null;
	Graphics bufferGraphics = null;

        //Rajzvászon
        private MapCanvas canvas;

        /*
         * képek tárolása. Lehetne hashes lista,
         * most csak példa
         */
        Image ut;
        Image auto;

	public View(){
            imageLoading();
	}

        public void paint(Graphics g) {
            bufferGraphics = g;
            if(backBuffer==null){
                    backBuffer = createImage(800,500);
                }
            bufferGraphics = backBuffer.getGraphics();

            Draw();
            
            g.drawImage(backBuffer, 0, 0, null);
        }

        public void update(Graphics g)
        {
              paint(g);
        }

        public void display()
        {
            repaint();
        }

	public void Draw(){
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
            
        }

        public void paint(Graphics g) {
            bufferGraphics = g;
            if(backBuffer==null){
                    backBuffer = createImage(800,500);
                }
            bufferGraphics = backBuffer.getGraphics();

            Draw();

            g.drawImage(backBuffer, 0, 0, null);
        }

        public void display()
        {
          repaint();
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

}