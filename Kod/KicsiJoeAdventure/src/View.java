
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
	
	//Erre a bufferre rajzolunk ha változás van
	

        //Rajzvászon
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
	    canvas = new GameCanvas();
	    canvas.setSize(800, 500);
	    add(canvas);
	    
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
    class GameCanvas extends Canvas{
	@Override
        public void paint(Graphics g) {
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