package img;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;

public class ImageLib {

    public static Image Load(String nev){
        Image image = null;
        boolean betoltve = false;
        while(!betoltve){
            try {
                URL url = img.ImageLib.class.getResource(nev);
                image = Toolkit.getDefaultToolkit().getImage(url);
                if(image.getWidth(null)!=0 && image.getWidth(null)!=-1){
                    betoltve = true;
                }else{
                    Thread.sleep(10);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return image;
    }

    public static Image Rotate(Image image,int Degrees){
        return null;
    }

}
