
import java.util.Random;

/**
 * Class Car:
 * Absztrakt osztály az összes járműre vonatkozó tulajdonságokkal
 * és metódusokkal.
 */

public abstract class Car {

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

    /**
     * Konstruktor
     * @param Speed Alapsebesség
     */
    public Car(int Speed){
        Controller.game.addCar(this);
        tickCount = startSpeed = Speed;
        plannedDirection = 0;
        selectedDirection = -1;
    }

    /**
     * Az autó alá rakja a paraméterként kapott utat
     * (pálya felépítésekor lehet szükség rá)
     * @param road Az út, amelyikre ráhelyezzük.
     */
    public void setRoad(Road road){
        roadUnderMe = road;
		ar = roadUnderMe.getNextRoads();
    }

    /**
     * Az alatta levő út lekérdezése.
     * @return Az út
     */
	public Road getRoadUnderMe() {
		return roadUnderMe;
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

    /**
     * Épület interakció, csak rablónak kell máshogy ezért az alap itt van.
     * @param building AZ épület
     */
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

		//ha még nincs beállítva választott útirány, beállítunk
		if(selectedDirection==-1){
			selectedDirection = getValidDirection();
		}
		// plannedDirection beállítása selectedDirection-ra, vagy ha arra nem lehet, válasszon
		if(ar.roads[selectedDirection]== null){
			plannedDirection = getValidDirection();
            selectedDirection = plannedDirection; //hogy ne válasszon újra
		}else{
			plannedDirection = selectedDirection;
                        
		}

		if(tickCount > 0){
			tickCount--;
			//A Building interakció miatt a plannedDirection-t muszáj vagyunk már most beállítani
			// plannedDirection beállítása //
		    // ha lehet, a selectedDirection irányába megyünk


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

    /**
     * Tényleges haladási irány lekérdezése
     * @return azirány
     */
	public int getPlannedDirection(){
		return plannedDirection;
	}

    /**
     * Generál az autónak egy jó útirányt, amerre tényleg lehet menni.
     * @return Az útirány
     */
    public int getValidDirection(){
        int retDirection = 0;
        if(moreThan1AR(ar)){	// több szabad irány van, választunk //
            if (Controller.game.randomEnabled)
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
     * Frissíti, hogy merre vannak elérhető utak
     */
	public void updateAR(){
		ar = roadUnderMe.getNextRoads();
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
     * @param civil Az autó aki ott van, ahova menni szeretne.
     */
    public abstract void Interaction(Civil civil);

	/**
     * Jármű interakaciós függvény (ütközés), absztrakt,
     * a leszármazottaknak kell implementálni
     * @param robber Az autó aki ott van, ahova menni szeretne.
     */
	public abstract void Interaction(Robber robber);
	/**
     * Jármű interakaciós függvény (ütközés), absztrakt,
     * a leszármazottaknak kell implementálni
     * @param police Az autó aki ott van, ahova menni szeretne.
     */
	public abstract void Interaction(Police police);
    /**
     * Felvehető nyúllal történő interakció.
     * @param bunny A nyúl, akit fel lehet venni.
     */
    public abstract void Interaction(Bunny bunny);

    /**
     * Kirajzoltatás.
     */
    public abstract void Draw();

}