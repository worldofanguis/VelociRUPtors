/**
 * Rejtekhely kirajzolásáért felelős osztály
 */
import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class HideoutView {

    /**
     * kép
     */
	private Image Texture;

    /**
     * amire
     */
    private Graphics2D g;

    /**
     * Konstruktor, vászon, kép
     * @param graphics
     */
	public HideoutView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("hideout.png");
	}

	/**
	 * Tényleges kirajzolás
	 * @param h
	 */
	public void Draw(Hideout h){
		Road r = h.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
	}

}