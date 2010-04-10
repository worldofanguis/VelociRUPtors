

/**
 * Class ClassID:
 * Segédosztály a prototípus verzióhoz.
 * A tesztkimenetekben megjelenő osztályok egyedi azonosító száma.
 */
public abstract class ClassID {
    /** 
     *Az objektum azonsítója
     */
    protected Integer ID;

    /**
     *Az azonosító beállítása
     * @param id Az azonosító szám
     */
    public void setID(int id) {
        ID = id;
    }

    /**
     * Az azonosító lekérdezése
     */
    @Override
    public String toString(){
        return new String(ID.toString());
    }
}
