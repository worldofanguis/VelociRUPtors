/**
 * A bank megjelenítéséért felelős osztály
 */
import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class BankView {

    /**
     * A bankot ábrázoló kép
     */
	private Image Texture;

    /**
     * Amire rajzolunk
     */
    private Graphics2D g;

    /**
     * Konstruktor, beállítja a képet
     * @param graphics erre rajzolunk
     */
	public BankView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("bank.png");
	}

	/**
	 * Megfelelően kirajzolja a bankot
	 * @param b A bankpéldány amit ki kell rajzolni
	 */
	public void Draw(Bank b){
		Road r = b.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);
	}

}