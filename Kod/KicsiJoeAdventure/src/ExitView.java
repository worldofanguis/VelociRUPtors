/**
 * EXIT tábla kirajzolásáért felelős osztály
 */
import img.ImageLib;
import java.awt.Graphics2D;
import java.awt.Image;

public class ExitView {

    /**
     * kép
     */
	private Image Texture;

    /**
     * amire rajzol
     */
    private Graphics2D g;

    /**
     * Konstruktor, kép és vászon beállítása
     * @param graphics
     */
	public ExitView(Graphics2D graphics){
        g = graphics;
        Texture = ImageLib.Load("exit_a50.png");
	}

	/**
	 * Tényleges kirajzolás
	 * @param e
	 */
	public void Draw(ExitSign e){
		Road r = e.getRoadUnderMe();
		g.drawImage(Texture, r.X*Texture.getWidth(null), r.Y*Texture.getHeight(null),null);

        //Táblára rárajzolja nagyobb prioritású elemeket
		if(r.hasPickup() != null)
			r.hasPickup().Draw();
		if(r.hasCar() != null)
			r.hasCar().Draw();
	}

}