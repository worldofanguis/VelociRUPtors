import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class StopView {

	private Image Texture;
    private Graphics2D g;

	public StopView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("stop_a50.png");
	}

	/**
	 * 
	 * @param s
	 */
	public void Draw(StopSign s){
		Road r = s.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
	}

}