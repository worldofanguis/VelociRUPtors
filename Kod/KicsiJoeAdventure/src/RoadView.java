import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;

public class RoadView {

	private Image Texture;
	private Image ArrowUp;
    private Graphics2D g;
	private AffineTransform trans;

	public RoadView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("road.png");
		ArrowUp = ImageLib.Load("arrow_up.png");

		trans = new AffineTransform();
	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Road r){
        g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);

		AvailableRoads ar = r.getNextRoads();
		if(ar.roads[0] != null){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			trans.rotate(-(Math.PI/2),((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
			g.drawImage(ArrowUp,trans,null);
		}
		if(ar.roads[1] != null){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			g.drawImage(ArrowUp,trans,null);
		}
		if(ar.roads[2] != null){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			trans.rotate(Math.PI/2,((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
			g.drawImage(ArrowUp,trans,null);
		}
		if(ar.roads[3] != null){
			trans.setToIdentity();
			trans.setToTranslation(r.X*Texture.getWidth(null),r.Y*Texture.getHeight(null));
			trans.rotate(Math.PI,((double)Texture.getWidth(null))/2.0,((double)Texture.getHeight(null))/2.0);
			g.drawImage(ArrowUp,trans,null);
		}
	}

}