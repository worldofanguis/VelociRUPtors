import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class HideoutView {

	private Image Texture;
    private BufferedImage backbuffer;

	public HideoutView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("hideout.png");
	}

	/**
	 * 
	 * @param h
	 */
	public void Draw(Hideout h){

	}

}