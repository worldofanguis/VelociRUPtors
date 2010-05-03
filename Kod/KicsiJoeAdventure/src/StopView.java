import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class StopView {

	private Image Texture;
    private BufferedImage backbuffer;

	public StopView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("stop_a50.png");
	}

	/**
	 * 
	 * @param s
	 */
	public void Draw(StopSign s){

	}

}