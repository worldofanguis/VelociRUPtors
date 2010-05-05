/**
 * Kámpakirajzolásáért felelős osztály
 */
import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class LampView {

    /**
     * Alapkép
     */
	private Image Texture;

    /**
     * piros fény
     */
    private Image RedLight;

    /**
     * zöld fény
     */
    private Image GreenLight;

    /**
     * amire
     */
    private Graphics2D g;

    /**
     * transzformációs mx
     */
	private AffineTransform trans;

	public LampView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("lamp_basic_none.png");
        RedLight = ImageLib.Load("lamp_light_red.png");
        GreenLight = ImageLib.Load("lamp_light_green.png");
		trans = new AffineTransform();
	}

	/**
	 * Tényleges kirajtzolása
     * A lámpa egy útszakaszon a legnagyobb prioritású elem
	 * @param l
	 */
	public void Draw(Lamp l){
		Road r = l.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
		boolean[] activeSides = l.getActiveSides();
		if(activeSides[0] != false){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			trans.rotate(-(Math.PI/2),((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
			if(!l.isGreen(0))
				g.drawImage(GreenLight,trans,null);
			else
				g.drawImage(RedLight,trans,null);
		}
		if(activeSides[1] != false){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			if(!l.isGreen(1))
				g.drawImage(GreenLight,trans,null);
			else
				g.drawImage(RedLight,trans,null);
		}
		if(activeSides[2] != false){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			trans.rotate(Math.PI/2,((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
			if(!l.isGreen(2))
				g.drawImage(GreenLight,trans,null);
			else
				g.drawImage(RedLight,trans,null);
		}
		if(activeSides[3] != false){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			trans.rotate(Math.PI,((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
			if(!l.isGreen(3))
				g.drawImage(GreenLight,trans,null);
			else
				g.drawImage(RedLight,trans,null);
		}

	}

}