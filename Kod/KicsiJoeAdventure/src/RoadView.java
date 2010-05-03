import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class RoadView {

	private Image Texture;
	private Image ArrowUp;
    private Graphics2D g;

	public RoadView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("road.png");
		ArrowUp = ImageLib.Load("arrow_up.png");
	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Road r){
        //g
	}

}