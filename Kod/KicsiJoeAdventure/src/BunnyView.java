/**
 * Nyúl kirajzolásáért felelős osztály
 */
import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;


public class BunnyView {

    /**
     * Kép
     */
	private Image Texture;

    /**
     * Amire rajzol
     */
    private Graphics2D g;

    /**
     * Konstruktor. képs és rajzvászon beállítás.
     * @param graphics
     */
	public BunnyView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("bunny.png");
	}

	/**
	 * Téynleges kirajzolás
	 * @param b kirajzolandó nyúl
	 */
	public void Draw(Bunny b){
		Road r = b.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
	}

}