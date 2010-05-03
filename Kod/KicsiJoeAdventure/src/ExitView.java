import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class ExitView {

	private Image Texture;
    private Graphics2D g;

	public ExitView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("exit_a50.png");
	}

	/**
	 * 
	 * @param e
	 */
	public void Draw(ExitSign e){

	}

}