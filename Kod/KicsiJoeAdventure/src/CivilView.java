import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class CivilView {

	private Image Texture;
    private Graphics2D g;

	public CivilView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("civil.png");
	}

	/**
	 * 
	 * @param c
	 */
	public void Draw(Civil c){

	}

}