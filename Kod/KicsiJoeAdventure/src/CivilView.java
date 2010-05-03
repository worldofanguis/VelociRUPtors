import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class CivilView {

	private Image Texture;
    private BufferedImage backbuffer;

	public CivilView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("civil.png");
	}

	/**
	 * 
	 * @param c
	 */
	public void Draw(Civil c){

	}

}