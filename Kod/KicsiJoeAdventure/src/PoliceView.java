/**
 * Rendőr kirajzolásáért felelős osztály
 */
import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class PoliceView {

	private Image Texture;
    private Graphics2D g;

	private AffineTransform trans;

	public PoliceView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("police.png");

		trans = new AffineTransform();
	}

	/**
	 * 
	 * @param p
	 */
	public void Draw(Police p){
		Road r = p.getRoadUnderMe();
		trans.setToIdentity();
		trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
		switch(p.getPlannedDirection()){
			case 0:
				trans.rotate(-(Math.PI/2),((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
				break;
			case 1:
				// no rotation //
				break;
			case 2:
				trans.rotate((Math.PI/2),((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
				break;
			case 3:
				trans.rotate(Math.PI,((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
				break;
		}
		g.drawImage(Texture,trans,null);
	}

}