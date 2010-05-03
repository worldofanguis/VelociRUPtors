import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class HideoutView {

	private Image Texture;
    private Graphics2D g;

	public HideoutView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("hideout.png");
	}

	/**
	 * 
	 * @param h
	 */
	public void Draw(Hideout h){

	}

}