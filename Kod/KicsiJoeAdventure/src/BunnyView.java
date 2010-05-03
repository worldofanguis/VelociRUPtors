import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;


public class BunnyView {

	private Image Texture;
    private BufferedImage backbuffer;

	public BunnyView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("bunny.png");
	}

	/**
	 * 
	 * @param b
	 */
	public void Draw(Bunny b){

	}

}