
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

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
	private Image backBuffer;
	Graphics bufferGraphics;
	public View(){
	    if(backBuffer==null){
		backBuffer = createImage(800,500);
	    }
	    bufferGraphics = backBuffer.getGraphics();
	}

	public void Draw(){
	    
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

}