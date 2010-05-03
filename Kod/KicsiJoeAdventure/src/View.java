
import img.ImageLib;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class View extends JPanel
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
	
	//Erre a bufferre rajzolunk ha változás van
	private BufferedImage backBuffer;
	Graphics2D bufferGraphics;

	public View() {
		backBuffer = new BufferedImage(800,500,BufferedImage.TYPE_INT_RGB);
		//ezzel fog rajzolni a panel a képre
		bufferGraphics =  (Graphics2D) backBuffer.getGraphics();
		setPreferredSize(new Dimension(800,500));

		// Create the view classes //
		loadViews();
		bufferGraphics.setColor(Color.green);
		bufferGraphics.fillRect(0, 0, 800, 500);
	}

	@Override
	public void paint(Graphics g){
        g.drawImage(backBuffer,0,0,null);
    }

	/**
	 * 
	 * @param bank
	 */
	public void Draw(Bank bank){
        bankView.Draw(bank);
	}

	/**
	 * 
	 * @param hideout
	 */
	public void Draw(Hideout hideout){
        hideoutView.Draw(hideout);
	}

	/**
	 * 
	 * @param s
	 */
	public void Draw(StopSign s){
        stopView.Draw(s);
	}

	/**
	 * 
	 * @param l
	 */
	public void Draw(Lamp l){
        lampView.Draw(l);
//		bufferGraphics.drawImage(ut, 10, 10, this);
//	    bufferGraphics.setColor(Color.red);
//	    bufferGraphics.drawString("LAMMPPPP", 40, 40);
	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Road r){
        roadView.Draw(r);
	}

	/**
	 * 
	 * @param c
	 */
	public void Draw(Civil c){
        civilView.Draw(c);
	}

	/**
	 * 
	 * @param p
	 */
	public void Draw(Police p){
        policelView.Draw(p);
	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Robber r){
        robberView.Draw(r);
	}

	/**
	 * 
	 * @param b
	 */
	public void Draw(Bunny b){
        bunnyView.Draw(b);
	}

	/**
	 * 
	 * @param e
	 */
	public void Draw(ExitSign e){
        exitView.Draw(e);
	}  

    /**
     * Betölteni a képeket
     */
    private void loadViews() {
        policelView = new PoliceView(bufferGraphics);
        civilView = new CivilView(bufferGraphics);
        robberView = new RobberView(bufferGraphics);
        roadView = new RoadView(bufferGraphics);
        bankView = new BankView(bufferGraphics);
        hideoutView = new HideoutView(bufferGraphics);
        stopView = new StopView(bufferGraphics);
        exitView = new ExitView(bufferGraphics);
        bunnyView = new BunnyView(bufferGraphics);
        lampView = new LampView(bufferGraphics);
    }

}