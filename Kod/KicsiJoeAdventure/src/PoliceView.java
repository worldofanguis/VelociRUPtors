import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class PoliceView {

	private Image Texture;
    private Graphics2D g;

	public PoliceView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("police.png");
	}

	/**
	 * 
	 * @param p
	 */
	public void Draw(Police p){
		Road r = p.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);

	}

}