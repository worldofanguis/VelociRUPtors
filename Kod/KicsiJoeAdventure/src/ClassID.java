

/**
 * Class ClassID:
 * Segédosztály a szkeleton verzióhoz.
 * Minden osztály örököl tőle, így adható nekik "objektum név".
 */
public abstract class ClassID {

    /** 
     *Az objektum neve
     */
    protected String ID;

    /**
     * 
     *Az objektumnév beállítása
     *
     * @param s A név.
     */
    public void setID(String s) {
        ID = new String(s);
    }

    /**
     * 
     * Az objektumnév lekérdezése
     *
     */
    @Override
    public String toString(){
        return ID;
    }
}
