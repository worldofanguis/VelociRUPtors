import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class RobberView {

	private Image Texture;
    private Graphics2D g;

	public RobberView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("robber.png");
	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Robber robber){
		Road r = robber.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
	}

}