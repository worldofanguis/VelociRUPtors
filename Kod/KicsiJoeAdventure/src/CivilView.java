import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class CivilView {

	private Image Texture;
    private Graphics2D g;
	private AffineTransform trans;

	public CivilView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("civil.png");

		trans = new AffineTransform();
	}

	/**
	 * 
	 * @param c
	 */
	public void Draw(Civil c){
		Road r = c.getRoadUnderMe();
		trans.setToIdentity();
		trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
		switch(c.getPlannedDirection()){
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