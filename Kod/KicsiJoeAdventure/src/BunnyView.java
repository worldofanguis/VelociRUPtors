import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;


public class BunnyView {

	private Image Texture;
    private Graphics2D g;

	public BunnyView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("bunny.png");
	}

	/**
	 * 
	 * @param b
	 */
	public void Draw(Bunny b){
		Road r = b.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
	}

}