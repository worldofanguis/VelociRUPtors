
import java.util.Random;

/**
 * Class Car:
 * Absztrakt osztály az összes járműre vonatkozó tulajdonságokkal
 * és metódusokkal.
 */

public abstract class Car extends ClassID {

    /**
     * A tervezett továbbhaladási irány (lsd Directions osztály)
     */
    protected int plannedDirection;

    /**
     * A kiválasztott haladási irány, erre próbálunk mindig menni (lsd Directions osztály)
     */
    protected int selectedDirection;

    /**
     * Azon út referenciája, amelyiken található az autó.
     */
    protected Road roadUnderMe;

    /**
     * A kezdetben generált véletlenszerű sebesség
     */
    protected int startSpeed;

    /**
     * A léptetéshez szükséges visszaszámláló
     */
    protected int tickCount;

    /**
     * Ebben tároljuk az elérhető utakat
     */
    protected AvailableRoads ar;

    public Car(int Speed){
        ID = Main.game.addCar(this);
        tickCount = startSpeed = Speed;
        plannedDirection = 0;
	selectedDirection = -1;
        Main.game.outputStream.println("ICAR - ID:"+ID+" Tipus:"+showMapChar()+" MaxTick:"+startSpeed);
    }

    /**
     * Az autó alá rakja a paraméterként kapott utat
     * (pálya felépítésekor lehet szükség rá)
     * @param road Az út, amelyikre ráhelyezzük.
     */
    public void setRoadUnderCar(Road road){
        roadUnderMe = road;
	ar = roadUnderMe.getNextRoads();
    }

    /**
     * Le lehet-e tartóztatni? (Rosszfiú?)
     * @return Alapértelmezésben (civil és rendőr miatt) false
     * értéket ad vissza.
     */
    public boolean canBeArrested(){
        return false;
    }

    /**
     * Az autó sebességét adja vissza.
     * @return Az autó aktuális sebessége (~mikor indul el újra).
     */
    public int getSpeed(){
        return tickCount;
    }

    /**
     * Autó mozgatása.
     */
    public void Move(){
        // Mozgatás //

        //Közlekedésirányító ellenőrzése a plannedDirection-ön//
        //Ez meghívja a megfelelő Interaction fv-t//
        //Ha egy előző interakció megállította a kocsit, akkor ne nézze meg ezt.
        TrafficController tc;
        if(tickCount == 0)
            if((tc=ar.roads[plannedDirection].hasTrafficController()) != null)
                tc.whatSign(this);

        // Mozgás plannedDirection felé, ha nem kell várnunk //
        if(tickCount == 0)
            MoveTo(ar.roads[plannedDirection]);

        // Mozgatás - vége //
    }

    /**
     * Az autót egy konkrét útra helyezi.
     * @param road Az út, amelyikre áthelyezzük.
     */
    public abstract void MoveTo(Road road);

    public void Interaction(Building building){
	ar.roads[plannedDirection] = null;
	plannedDirection = getValidDirection();
    }

    /**
     * A frissítő/léptető függvény
     * @return A visszatérési érték true, ha az autó még a pályán van.
     */
    public boolean Update(){
        boolean ret = true;
	if(tickCount > 0){ tickCount--;

	    

	    //A Building interakció miatt a plannedDirection-t muszáj vagyunk már most beállítani
	     // plannedDirection beállítása //
	    // ha lehet, a selectedDirection irányába megyünk

	    //ha még nincs beállítva választott útirány, beállítunk
	    if(selectedDirection==-1){
		selectedDirection = getValidDirection();
	    }

	    // plannedDirection beállítása selectedDirection-ra, vagy ha arra nem lehet, válasszon
	    if(ar.roads[selectedDirection]== null){
		plannedDirection = getValidDirection();
	    }
	    else{
		plannedDirection = selectedDirection;
	    }

	    //Building interakció minden update-ben megpróbáljuk
	    //A Car-ba kell beleírni, különben a Civilek, Rendőrök befordulnának ide
	    //plusz így nem csak a rabló tud épületekkel interakcióba lépni
	    Building building;
	    if(ar.roads[plannedDirection]!=null && ((building = ar.roads[plannedDirection].hasBuilding()) != null)){
		   building.whatBuilding(this); //ez majd meghívja a kocsi interakcióját
	    }
	}
	else if(tickCount == 0){
	//ExitCar
           if(DeadEnd(ar)){
               roadUnderMe.removeCar();
               ret = false;
           }

           //Mozgatás, ha lehetséges
           if(ret) Move();
        }
        
        String p = (ret) ? "" : " -"; //Kilépett
        Main.game.outputStream.println("CAR - ID:"+ID+" RoadID:"+roadUnderMe.ID+" Tick:"+tickCount+p);
        // Mozgatás - vége //
        return ret;
    }

    /**
     * A teszteléshez szükséges segédfüggvény (akkor kérdezzük meg a
     * felhasználót, hogy merre akar menni, ha több lehetséges útirány
     * van).
     * @param ar Lehetséges útirányok
     * @return true, ha több lehetséges továbbhaladási irány is van.
     */
    protected boolean moreThan1AR(AvailableRoads ar){
        int arC = 0;
        for(int i=0;i<4;i++){
                if(ar.roads[i] != null)
                        arC++;
        }
        return (arC > 1);
    }

    /**
     * Belső segédfüggvény annak eldöntésére, hogy az útszakasz
     * zsákutca-e (nem vezet ki belőle további útszakasz)
     * @param ar Lehetséges útirányok
     * @return true, ha nincs továbbhaladási lehetőség
     */
    protected boolean DeadEnd(AvailableRoads ar){
        int arC = 0;
        for(int i=0;i<4;i++){
                if(ar.roads[i] != null)
                        arC++;
        }
        return (arC == 0);
    }

    /**
     * Az útirány konkrét beállítása a determinisztikus
     * tesztesetekhez.
     * @param Direction Az útirány
     */
    public void setDirection(int Direction){
        selectedDirection = Direction;
    }

    public int getValidDirection(){
	int retDirection = 0;
	if(moreThan1AR(ar)){	// több szabad irány van, választunk //
		if (Main.game.randomEnabled)
		{
		    //Random generálás engedélyezve.
		    Random random = new Random();
		    retDirection = random.nextInt(4);
		    while (ar.roads[retDirection] == null)
			retDirection = random.nextInt(4);
		} else {
		    // Random generálás nincs engedélyezve, feltételezzük,
		    //hogy a tesztelő olyat állított be időben be, ami jó
		    if ( ar.roads[retDirection] == null)
		    {
//			System.out.println("Rossz utiranybeallitas");
//			System.exit(1); //utána úgyis sírna nullexception miatt.

			//ha több irányba is mehetünk, de amerre akarunk, nem lehet -> T elágazás
			//nem rabló esetén ilyenkor balról kezdve az óramutató járásával megegyező irányban keresünk járható utat
			//rabló esetén lehetne valami cselesebbet,
			//pl nekimegyünk, megállunk egy időre, stb,
			//de egyelőre legyen ott is így, úgyse csináltunk neki külön
			//tesztet, meg hát 2 kredit.
			for(int i=retDirection=0; i<4;i++){
			    if(ar.roads[retDirection++]!=null)
				return --retDirection;
			}
			System.out.println("Nincs út semerre, már ki kellett volna, hogy lépjen a kocsi, valami csúnyaság van");
			System.exit(1); //utána úgyis sírna nullexception miatt.
		    }
		}
	    } else {// csak 1 irány szabad //
		    for(int i=0;i<4;i++){
			    if(ar.roads[i] != null){
				    retDirection = i;
				    break;
			    }
		    }
	    }
	return retDirection;
    }

    /**
     * A léptetésig hátralevő ciklusszám beállítása
     * @param Tick A ciklusszám
     */
    public void setTick(int Tick){
        tickCount = Tick;
    }


    /**
     * STOP tábla interakciós függvény, absztrakt,
     * a leszármazottaknak kell implementálni
     * @param sign STOP tábla akivel találkozik.
     */
    public abstract void Interaction(StopSign sign);

    /**
     * EXIT tábla interakciós függvény, absztrakt,
     * a leszármazottaknak kell implementálni
     * @param sign EXIT tábla akivel találkozik.
     */
    public abstract void Interaction(ExitSign sign);

    /**
     * Bank interakciós függvény, absztrakt,
     * a leszármazottaknak kell implementálni
     * @param bank Bank típusú épület akivel találkozik.
     */
    public abstract void Interaction(Bank bank);

    /**
     * Rejtekhely interakciós függvény, absztrakt,
     * a leszármazottaknak kell implementálni
     * @param hideout Rejtekhely típusú épület akivel találkozik.
     */
    public abstract void Interaction(Hideout hideout);

    /**
     * Lámpa interakaciós függvény, absztrakt,
     * a leszármazottaknak kell implementálni
     * @param lamp Lámpa akivel találkozik.
     */
    public abstract void Interaction(Lamp lamp);

    /**
     * Jármű interakaciós függvény (ütközés), absztrakt,
     * a leszármazottaknak kell implementálni
     * @param car Az autó aki ott van, ahova menni szeretne.
     */
    public abstract void Interaction(Civil civil);
	public abstract void Interaction(Robber robber);
	public abstract void Interaction(Police police);
    /**
     * Felvehető nyúllal történő interakció.
     * @param bunny A nyúl, akit fel lehet venni.
     */
    public abstract void Interaction(Bunny bunny);

    /**
     * A pálya megjelenítésekor az autótípusokat egyedien
     * reprezentáló karaktersorozat egységes eléséhez szükséges függvény.
     * @return A karakter.
     */
    public abstract char showMapChar();

}