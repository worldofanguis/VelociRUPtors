

/**
 * Class ClassID:
 * Segédosztály a szkeleton verzióhoz.
 * Minden osztály örököl tőle, így adható nekik "objektum név".
 */
public abstract class ClassID {

    /** 
     *Az objektum neve
     */
    protected Integer ID;

    /**
     * 
     *Az objektumnév beállítása
     *
     * @param s A név.
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * 
     * Az objektumnév lekérdezése
     *
     */
    @Override
    public String toString(){
        return new String(ID.toString());
    }
}
