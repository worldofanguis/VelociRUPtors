import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class LampView {

	private Image Texture;
    private Image RedLight;
    private Image GreenLight;
    private Graphics2D g;

	public LampView(Graphics2D graphics){
        g = graphics;
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