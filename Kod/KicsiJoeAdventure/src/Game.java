/**
 * Class Game:
 * Az egész játék vezérléséért felelős osztály.
 * Szkeletonként itt találhatóak a tesztpályák is.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.*;

public class Game {
    //Játékot korlátozó konstansok
        public final int MaxPickUp = 2;
        public final int BunnyTick = 500;
	public final int MaxCivilSpeed = 40;
	public final int MinCivilSpeed = 80;
	public final int MaxPoliceSpeed = 15;
	public final int MinPoliceSpeed = 80;
        public final int MaxRobberSpeed = 10;
        public final int StopTime = 50;


    /**
     * A pályán található autók referenciái.
     */
    private LinkedList<Car> cars;

    /**
     * A pályán található lámpák referenciái.
     */
    private LinkedList<Lamp> lamps;

    /**
     * A pályán található lámpák referenciái.
     */
    private LinkedList<Pickup> pickups;

    /**
     * A pálya útjainak listája.
     */
    private LinkedList<Road> roads;

    /**
     * Értéke true, ha már kirabolták a bankot, egyébként false.
     */
    private boolean bankIsRobbed;

    /**
     * A játékos autójának referenciája.
     */
    private Car me;

    /**
     * A pálya első útja, amelyből felépül (bejárható) az úthálózat.
     */
    private Road roadStart;

    /**
     * A pályán maximálisan tartózkodható autók száma.
     */
    private int maxCarsOnMap;

    /**
     * A játékos pontszáma
     */
    private int points;

    /**
     * Random logika engedélyezéséhez használt változó
     */
    public boolean randomEnabled;

    /**
     * A program futásának kimenete
     */
    public PrintStream outputStream;

    /**
     * 
     */
    public String WorkingDirectory;

	private int MaxX;
	private int MaxY;


    /**
     * Konstruktor, alaphelyzet (nem rabolták ki a bankot)
     */
    public Game(){
       cars = new LinkedList<Car>();
       lamps = new LinkedList<Lamp>();
       roads = new LinkedList<Road>();
       pickups = new LinkedList<Pickup>();
       bankIsRobbed = false;
       points = 0;
       randomEnabled = true; //Random logika nem engedélyezett
       outputStream = System.out; //Kimeneti csatorna
    }

    /**
     * Pontszám módosítása.
     * @param add Az érték, amit hozzáadunk a pontszámhoz.
     */
    public void addPoints(int add) {
        points += add;
    }

    /**
     * Pontszám lekérdezése.
     * @return Az aktuális pontszám.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Akkor hívódik meg, ha bankrablás történt, ennek megfelelően
     * változik a játékállapot.
     */
    public void bankRobbed(){
        bankIsRobbed = true;
    }

    /**
     * A játék befejezése a paramétertől függően.
     * @param Success Értéke true, ha a játékos nyert; false, ha a rendőr
     * elkapta a játékost.
     */
    public void GameOver(boolean Success){
        String s = (Success)?"You won!":"You lost.";
        Controller.msg("Game over - "+s);
        Controller.finish();
    }

    /**
     * A játék kezdetkor a pálya elkészítése, az elemek elhelyezése.
     */
    public void Initialization(){
        loadMapFromFile("map.txt");
		CreateMap();
    }

    /**
     * Autó hozzáadása a tárolt listához.
     * @param logoCanvas A listába felvevendő autó
     * @return Hányas indexű elem lett, amit beraktunk.
     */
    public int addCar(Car c){
        cars.add(c);
        return cars.size()-1;
    }

    /**
     * Lámpa hozzáadása a tárolt listához
     * @param l A listába felvevendő lámpa
     * @return Hányas indexű elem lett, amit beraktunk.
     */
    public int addLamp(Lamp l){
        lamps.add(l);
        return lamps.size()-1;
    }

    /**
     * Pickup hozzáadása a tárolt listához
     * @param p A listába felvevendő pickup
     * @return Hányas indexű elem lett, amit beraktunk.
     */
    public int addPickup(Pickup p){
        pickups.add(p);
        return pickups.size()-1;
    }

    /**
     * A játékállapot lekérdezése.
     * @return A játék aktuális állapota: true, ha már történt bankrablás;
     * false, ha még nem rabolták ki a bankot.
     */
    public boolean isBankRobbed(){
            return bankIsRobbed;
    }

    /**
     * A külső fájlban tárolt információk alapján a pálya felépítése.
     * @param Filename Azon .txt fájl elérési útvonala, amely alapján
     * felépítjük a pályát.
     */
    public void loadMapFromFile(String Filename) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(new File(WorkingDirectory,Filename)));

            String line;
            int roadIndex = 0;
            int ReadState = -1; // 0 = Settings ; 1 = Map //

            while((line = in.readLine()) != null){
                if(line.startsWith("#") || line.isEmpty())
                    continue;
                else if(line.compareTo("[SETTINGS]") == 0) {
                    ReadState = 0;
                    continue;
                }
                else if(line.compareTo(("[MAP]")) == 0) {
                    ReadState = 1;
                    continue;
                }

                switch(ReadState){
                    case 0:	{ // SETTINGS //
                        StringTokenizer st = new StringTokenizer(line,":");
                        String first = st.nextToken();
                        String second = st.nextToken();
                        if(first.compareTo("MaxCivilNumber") == 0){
                            maxCarsOnMap = Integer.parseInt(second);
                        } else if(first.compareTo("RoadNumber") == 0){
                        // Create the requied roads //
                            for(int i=0;i<Integer.parseInt(second);i++){
                                Road road = new Road();
				populateRoad(road);

                                roads.add(road);
                            }
                        }
                        break;
                    }
                    case 1:	{ // MAP //
                        StringTokenizer st = new StringTokenizer(line,";");
                        // Az úton lévő dolgok beállítása //
                        String token = st.nextToken();
                        switch(Integer.parseInt(token)){
                            case 0:	// default road //
                                break;
                            case 1:	// bank //
                                if (roads.get(roadIndex).hasCar() != null )
                                        roads.get(roadIndex).removeCar();
                                roads.get(roadIndex).setBuilding(new Bank());
                                break;
                            case 2:	// hideout //
                                if (roads.get(roadIndex).hasCar() != null )
                                        roads.get(roadIndex).removeCar();
                                roads.get(roadIndex).setBuilding(new Hideout());
                                break;
                            case 3:	// lamp //
                                roads.get(roadIndex).setTrafficController(new Lamp());
                                ((Lamp)lamps.getLast()).setRoad(roads.get(roadIndex));
                                break;
                            case 6:	// rabló starthely //
                                    me = new Robber((MinPoliceSpeed+MaxRobberSpeed)/2);	// start speed;
                                    if (roads.get(roadIndex).hasCar() != null )
                                        roads.get(roadIndex).removeCar();
                                    roads.get(roadIndex).setCar(me);
                                    break;
                            case 7:	// police starthely //
                                    Police p = new Police((int)(Math.random()%(MinPoliceSpeed-MaxPoliceSpeed))+MaxPoliceSpeed);	// start speed;
                                    if (roads.get(roadIndex).hasCar() != null )
                                        roads.get(roadIndex).removeCar();
                                    roads.get(roadIndex).setCar(p);
                                    break;
                            case 8:	// stop //
                                roads.get(roadIndex).setTrafficController(new StopSign());
                                break;
                            case 9:	// exit //
                                roads.get(roadIndex).setTrafficController(new ExitSign());
                                break;
                            default:
                                System.out.println("ERROR - Unknown road type");
                        }
                        // Csatlakozások beállítása //

                        // Balra //
                        token = st.nextToken();
                        if(token.compareTo("-") != 0){
                            roads.get(roadIndex).setRoad(Directions.LEFT, roads.get(Integer.parseInt(token)));
                        }
                        // Fel //
                        token = st.nextToken();
                        if(token.compareTo("-") != 0){
                            roads.get(roadIndex).setRoad(Directions.UP, roads.get(Integer.parseInt(token)));
                        }
                        // Jobbra //
                        token = st.nextToken();
                        if(token.compareTo("-") != 0){
                            roads.get(roadIndex).setRoad(Directions.RIGHT, roads.get(Integer.parseInt(token)));
                        }
                        // Le //
                        token = st.nextToken();
                        if(token.compareTo("-") != 0){
                            roads.get(roadIndex).setRoad(Directions.DOWN, roads.get(Integer.parseInt(token)));
                        }

                        roadIndex++;
                        break;
                    }
                }
            }
            // Set the starting road //
            roadStart = roads.get(0);
			// Calc the road coords //
			CreateMap();

			for(int i=0;i<cars.size();i++)
				cars.get(i).updateAR();
                        pickUpGen();
        } catch(Exception e){
           System.out.println("Noob");
           e.printStackTrace();
        }
    }

    /**
     * Az úthoz kapcsolódó elemek (autó, épület) elhelyezése rá - inicalizáláskor.
     * @param road Az út amelyet módosítunk.
     */
    public void populateRoad(Road road){
		if(cars.size() < maxCarsOnMap){
			if(Math.random() < 0.1){
				Civil c = new Civil((int)(Math.random()%(MinCivilSpeed-MaxCivilSpeed))+MaxCivilSpeed);
				road.setCar(c);
			}
		}
    }

    /**
     * Eltávolítja az aktuális autót az autók listájáról.
     */
    public void removeActualCar(int index){
        //ide nem tudom kell-e utat állítani, mert DeadEndben benne van
        cars.remove(index);
    }

    /**
     * A frissítő függvény, amely meghívja az összes autóra a frissítést,
     * így léptetve a játékot.
     */
    public void Update(){
        int i = 0;

        //Lámpák frissítése
        for (i=0; i<lamps.size(); i++) {
            ( (Lamp)lamps.get(i) ).Update();
        }
            

        for (i=0; i< cars.size(); i++) {
            if ( !( ( (Car)cars.get(i) ).Update() ) ) {
                removeActualCar(i);
                --i; //Ez azért kell, mert csökken az utána jövők indexe.
            }

        }

        //Pickupok frissítése
        for (i=0; i< pickups.size(); i++) {
            if ( !( ( (Pickup)pickups.get(i) ).Update() ) ) {
                Road road;
                if ( (road = pickups.get(i).getRoadUnderMe()) != null)
                    road.setPickup(null);
                pickups.remove(i);
                --i; //Ez azért kell, mert csökken az utána jövők indexe.
                //Autóval valami?
			 }
        }

		// Civilek beengedése 10% hogy belép 1 ha van hely //
		populateRoad(roadStart);
                pickUpGen();
    }

	public void Draw(){
		int i;

		for (i=0; i<roads.size(); i++) {
			Road r = roads.get(i);
            r.Draw();

			if(r.hasBuilding() != null)
				r.hasBuilding().Draw();
			if(r.hasPickup() != null)
				r.hasPickup().Draw();
			if(r.hasCar() != null)
				r.hasCar().Draw();
			if(r.hasTrafficController() != null)
				r.hasTrafficController().Draw();
        }
	}

    public void CreateMap(){
       if(roadStart.Iterated == false){
		roadStart.X = roadStart.Y = 0;
		roadStart.Iterated = true;
	}

        AvailableRoads ar = roadStart.getNextRoads();
        if(ar.roads[0] != null){
            ShowMapSetRoad(ar.roads[0], roadStart, 0);
        }
        if(ar.roads[1] != null){
            ShowMapSetRoad(ar.roads[1], roadStart, 1);
        }
        if(ar.roads[2] != null){
            ShowMapSetRoad(ar.roads[2], roadStart, 2);
        }
        if(ar.roads[3] != null){
            ShowMapSetRoad(ar.roads[3], roadStart, 3);
        }

        // Ezen a ponton minden útnak már be van állítva az X és Y koordinátája //

        int MinX = 0,MinY = 0;
        MaxX = 0;
		MaxY = 0;

        ListIterator<Road> i = roads.listIterator();

        Road current;
        while(i.hasNext()){
            current = i.next();
            if(current.X < MinX) MinX = current.X;
            if(current.Y < MinY) MinY = current.Y;

            if(current.X > MaxX) MaxX = current.X;
            if(current.Y > MaxY) MaxY = current.Y;
        }

        while(i.hasPrevious()){
            current = i.previous();
            current.X -= MinX;
            current.Y -= MinY;
        }

		MaxX = (MaxX-MinX)+1;
		MaxY = (MaxY-MinY)+1;
    }

	public int getMapWidth(){
		return MaxX;
	}
	public int getMapHeight(){
		return MaxY;
	}

    /**
     * ShowMap indítja el ezt a rekurzív fv-t
     * ami bejárja az utakat és kitölti az X,Y koordinátájuk
     * @param current Az aktuális, amelynek beállítjuk pozícióját
     * @param prev Az út, ami mellé pozícionálunk
     * @param direction Az irány, amiben csatlakozunk az előző úthoz
     */
    public void ShowMapSetRoad(Road current,Road prev,int direction){
        if(current.Iterated)
            return; //Már bejártuk ezt az utat

        AvailableRoads ar = current.getNextRoads();
        // Pozícionáld ezt az új utat (prev)//
        switch(direction){
            case 0:	// A prev út tőlünk jobbra van//
                current.X = prev.X-1;
                current.Y = prev.Y;
                break;
            case 1:	// A prev út alattunk van //
                current.X = prev.X;
                current.Y = prev.Y-1;
                break;
            case 2:	// A prev út tőlünk balra van //
                current.X = prev.X+1;
                current.Y = prev.Y;
                break;
            case 3:	// A prev út felettünk van //
                current.X = prev.X;
                current.Y = prev.Y+1;
                break;
        }

        current.Iterated = true;

        if(ar.roads[0] != null){
            ShowMapSetRoad(ar.roads[0], current, 0);
        }
        if(ar.roads[1] != null){
            ShowMapSetRoad(ar.roads[1], current, 1);
        }
        if(ar.roads[2] != null){
            ShowMapSetRoad(ar.roads[2], current, 2);
        }
        if(ar.roads[3] != null){
            ShowMapSetRoad(ar.roads[3], current, 3);
        }
    }

    void setRobberDirection(Directions dir) {
        me.setDirection(dir.value);
    }

    int setRobberVelocity(int i) {
       me.setTick(i);
       return (me.startSpeed);
    }

    private void pickUpGen() {
                if ( pickups.size() < MaxPickUp ) {
                    int roadN = (int)(Math.random()*roads.size());
                    if ( roads.get(roadN).hasBuilding() == null) {
                        Bunny b = new Bunny();
                        roads.get(roadN).setPickup(b);
                    }
                }
    }
}