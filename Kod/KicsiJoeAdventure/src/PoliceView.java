import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class PoliceView {

	private Image Texture;
    private BufferedImage backbuffer;

	public PoliceView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("police.png");
	}

	/**
	 * 
	 * @param p
	 */
	public void Draw(Police p){

	}

}