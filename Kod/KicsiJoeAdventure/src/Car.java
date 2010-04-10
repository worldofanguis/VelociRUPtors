
import java.util.Random;

/**
 * Class Car:
 * Absztrakt osztály az összes járműre vonatkozó tulajdonságokkal
 * és metódusokkal.
 */

public abstract class Car extends ClassID {

    /**
     * A tervezett haladási irány (lsd Directions osztály)
     */
    protected int plannedDirection;

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

    public Car(){
        Main.game.addCar(this);
        tickCount = 0;
        plannedDirection = 0;
    }

    /**
     * Az autó alá rakja a paraméterként kapott utat
     * (pálya felépítésekor lehet szükség rá)
     * @param road Az út, amelyikre ráhelyezzük.
     */
    public void setRoadUnderCar(Road road){
        roadUnderMe = road;
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

        // plannedDirection beállítása //
        if (Main.game.randomEnabled)
        {
            //Random generálás engedélyezve.
            Random random = new Random();
            plannedDirection = random.nextInt(4);
            while (ar.roads[plannedDirection] == null)
                plannedDirection = random.nextInt(4);
        } else {
            // Random generálás nincs engedélyezve, feltételezzük,
            //hogy a tesztelő olyat állított be időben be, ami jó
            if ( ar.roads[plannedDirection] == null)
            {
                System.out.println("Rossz utiranybeallitas");
                System.exit(1); //utána úgyis sírna nullexception miatt.
            }
        }

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
    public void MoveTo(Road road){
        Car c = road.hasCar();
        if ( c != null ) {
            Interaction(c);
        } else {
            roadUnderMe.removeCar();
            roadUnderMe = road;
            road.setCar(this);
        }
    }

    /**
     * A frissítő/léptető függvény
     * @return A visszatérési érték true, ha az autó még a pályán van.
     */
    public boolean Update(){
        boolean ret = true;
        if(tickCount > 0) tickCount--;

        if(tickCount == 0){
           ar = roadUnderMe.getNextRoads();

           //ExitCar
           if(DeadEnd(ar)){
               roadUnderMe.removeCar();
               ret = false;
           }

           //Mozgatás, ha lehetséges
           if(ret) Move();
        }
        
        String p = (ret) ? "" : "-"; //Kilépett
        Output.print("CAR - ID:"+ID+" RoadID:"+roadUnderMe.ID+" Tick:"+tickCount+" "+p);
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
        plannedDirection = Direction;
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
    public abstract void Interaction(Car car);

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