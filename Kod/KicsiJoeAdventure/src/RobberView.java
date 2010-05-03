import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class RobberView {

	private Image Texture;
    private BufferedImage backbuffer;

	public RobberView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("robber.png");
	}

	/**
	 * 
	 * @param r
	 */
	public void Draw(Robber r){

	}

}