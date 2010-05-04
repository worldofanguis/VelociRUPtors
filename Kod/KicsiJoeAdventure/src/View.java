
import img.ImageLib;
import java.awt.*;
import java.awt.geom.AffineTransform;
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

class DirG extends Canvas {
    private BufferedImage backBuffer;
    private Graphics2D bufferGraphics;
    private Image Texture;
    private AffineTransform trans;
    public DirG() {
        backBuffer = new BufferedImage(45,45,BufferedImage.TYPE_INT_RGB);

        bufferGraphics =  (Graphics2D) backBuffer.getGraphics();
        setPreferredSize(new Dimension(45,45));

        bufferGraphics.setColor(Color.decode("#eeeeee"));
        bufferGraphics.fillRect(0, 0, 45, 45);

        Texture = ImageLib.Load("arrow_up_black.png");
        trans = new AffineTransform();
    }

    public void Draw(int i) {
        bufferGraphics.setColor(Color.decode("#eeeeee"));
        bufferGraphics.fillRect(0, 0, 45, 45);
        trans.setToIdentity();
	trans.setToTranslation(0,0);
        switch(i) {
            case 0:
                trans.rotate(-(Math.PI/2),((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
                break;
            case 1:

                break;
            case 2:
                trans.rotate((Math.PI/2),((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
                break;
            case 3:
                trans.rotate(Math.PI,((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
                break;
        }
        bufferGraphics.drawImage(Texture,trans,null);
        repaint();
    }

    public void paint(Graphics g) {
        g.drawImage(backBuffer, 0, 0, null);
    }
}

abstract class Icon extends Canvas {
    protected BufferedImage backBuffer;
    protected Graphics2D bufferGraphics;
    protected Image base, active;
    protected boolean state;
    public Icon() {
        state = false;
        backBuffer = new BufferedImage(45,45,BufferedImage.TYPE_INT_RGB);

        bufferGraphics =  (Graphics2D) backBuffer.getGraphics();
        setPreferredSize(new Dimension(45,45));

        bufferGraphics.setColor(Color.decode("#eeeeee"));
        bufferGraphics.fillRect(0, 0, 45, 45);
        bufferGraphics.drawImage(base,0,0,null);
    }
    public void paint(Graphics g) {
        g.drawImage(backBuffer, 0, 0, null);
    }
    public void Draw(boolean st){
        bufferGraphics.setColor(Color.decode("#eeeeee"));
        bufferGraphics.fillRect(0, 0, 45, 45);
        if (st)
            bufferGraphics.drawImage(active,0,0,this);
        else
           bufferGraphics.drawImage(base,0,0,this);
        state = st;
        repaint();
    }

}

class bankState extends Icon {
    public bankState() {
        super();
        base = ImageLib.Load("bank_base.png");
        active = ImageLib.Load("bank_active.png");
    }
}

class bunnyState extends Icon {
    public bunnyState() {
        super();
        base = ImageLib.Load("bunny_base.png");
        active = ImageLib.Load("bunny_active.png");
    }
}