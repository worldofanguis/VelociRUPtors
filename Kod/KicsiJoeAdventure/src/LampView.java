import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class LampView {

	private Image Texture;
    private Image RedLight;
    private Image GreenLight;
    private BufferedImage backbuffer;

	public LampView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("lamp_basic_none.png");
        RedLight = ImageLib.Load("lamp_light_red.png");
        GreenLight = ImageLib.Load("lamp_light_red.png");
	}

	/**
	 * 
	 * @param l
	 */
	public void Draw(Lamp l){

	}

}