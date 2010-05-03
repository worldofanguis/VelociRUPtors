import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class RoadView {

	private Image Texture;
    private BufferedImage backbuffer;

	public RoadView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("road.png");
	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Road r){
        
	}

}