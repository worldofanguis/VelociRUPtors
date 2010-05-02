
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
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

	Image ut;
	Image auto;

	public View() {
		imageLoading();
		backBuffer = new BufferedImage(800, 500, BufferedImage.TYPE_INT_RGB);
		//ezzel fog rajzolni a panel a képre
		bufferGraphics =  (Graphics2D) backBuffer.getGraphics();
		setPreferredSize(new Dimension(800,500));

		//bufferGraphics.setColor(Color.GREEN);
		//bufferGraphics.fillRect(20, 20, 500, 500);
	}

	public void Draw(){
            for (int i = 0; i<800; i+=45)
                for (int j = 0; j<500; j+=45)
                    bufferGraphics.drawImage(ut, i, j, this);

            for (int i = 0; i<800; i+=45)
                for (int j = 0; j<500; j+=45)
                    if ( ((i - j) % 20) == 0 )
                        bufferGraphics.drawImage(auto, i, j, this);
	}
	
	@Override
	public void paint(Graphics g){
            Draw();
            g.drawImage(backBuffer,0,0,null);
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
//		bufferGraphics.drawImage(ut, 10, 10, this);
	    bufferGraphics.setColor(Color.red);
	    bufferGraphics.drawString("LAMMPPPP", 40, 40);
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
     * Betölteni a képeket
     */
    private void imageLoading() {
        ut = load("img/road.png");
        auto = load("img/civil.png");
    }

   public Image load(String nev){
        Image image = null;
        boolean betoltve = false;
        while(!betoltve){
            try {
                URL url = this.getClass().getResource(nev);
                image = Toolkit.getDefaultToolkit().getImage(url);
//                image = ImageIO.read(new File("src/img/"+nev.toLowerCase()+".PNG"));
                if(image.getWidth(null)!=0 && image.getWidth(null)!=-1){
                    betoltve = true;
                }else{
                    Thread.sleep(10);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return image;
    }

}