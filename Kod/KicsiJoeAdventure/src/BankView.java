
import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class BankView {

	private Image Texture;
    private Graphics2D g;

	public BankView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("bank.png");
	}

	/**
	 * 
	 * @param b
	 */
	public void Draw(Bank b){
		Road r = b.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
	}

}