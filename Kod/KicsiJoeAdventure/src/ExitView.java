import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class ExitView {

	private Image Texture;
    private BufferedImage backbuffer;

	public ExitView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("exit_a50.png");
	}

	/**
	 * 
	 * @param e
	 */
	public void Draw(ExitSign e){

	}

}