
import img.ImageLib;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class BankView {

	private Image Texture;
    private BufferedImage backbuffer;

	public BankView(BufferedImage buffer){
        backbuffer = buffer;
        Texture = ImageLib.Load("bank.png");
	}

	/**
	 * 
	 * @param b
	 */
	public void Draw(Bank b){

	}

}