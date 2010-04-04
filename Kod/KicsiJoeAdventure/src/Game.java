/**
 * Class Game:
 * Az egész játék vezérléséért felelős osztály.
 * Szkeletonként itt találhatóak a tesztpályák is.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.*;

public class Game {

        /**
         * A pályán található autók referenciái.
         */
	private List<Car> cars;

        /**
         * A pályán található lámpák referenciái.
         */
	private List<Lamp> lamps;

	private List<Road> roads;

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
         * Konstruktor, alaphelyzet (nem rabolták ki a bankot)
         */
	public Game(){
		cars = new LinkedList<Car>();
		lamps = new LinkedList<Lamp>();
		roads = new LinkedList<Road>();
		bankIsRobbed = false;
                points = 0;
	}

        /**
         * Pontszám módosítása.
         * @param add Az érték, amit hozzáadunk a pontszámhoz.
         */
        public void AddPoints(int add) {
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
            String p = (Success) ? new String("true") : new String("false");
  	}

        /**
         * A játék kezdetkor a pálya elkészítése, az elemek elhelyezése.
         */
	public void Initialization(){
		loadMapFromFile("map.txt");
	}

        /**
         * A játékállapot lekérdezése.
         * (Megj.: Szkeletonról lévén szó, ezt most a felhasználó adja meg)
         * @return A játék aktuális állapota: true, ha már történt bankrablás;
         * false, ha még nem rabolták ki a bankot.
         */
	public boolean isBankRobbed(){

		System.out.print("Ki van rabolva a bank? y = igen, barmilyen mas billentyu = nem: ");
		String line = null;
		try {
			 line = (new BufferedReader(new InputStreamReader(System.in))).readLine();
		} catch (IOException ex) {

		}
		if(line.equals("y")) bankIsRobbed = true; else bankIsRobbed = false;

		String p = (bankIsRobbed) ? new String("true") : new String("false");
		return bankIsRobbed;
	}

        /**
         * A külső fájlban tárolt információk alapján a pálya felépítése.
         * (Megj.: Szkeletonban ez még nincs implementálva, csak imitáljuk
         * a működését)
         * @param Filename Azon .txt fájl elérési útvonala, amely alapján
         * felépítjük a pályát.
         */
	public void loadMapFromFile(String Filename) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(new File(Filename)));

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
						}else if(first.compareTo("RoadNumber") == 0){
							// Create the requied roads //
							for(int i=0;i<Integer.parseInt(second);i++){
								Road road = new Road();
								road.setID(i);
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
								roads.get(roadIndex).setBuilding(new Bank());
								break;
							case 2:	// hideout //
								roads.get(roadIndex).setBuilding(new Hideout());
								break;
							case 3:	// lamp //
								roads.get(roadIndex).setTrafficController(new Lamp());
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
		}catch(Exception e){
			System.out.println("Noob");
		}
	}

        /**
         * Az úthoz kapcsolódó elemek (autó, épület) elhelyezése rá - inicalizáláskor.
         * @param road Az út amelyet módosítunk.
         */
	public void populateRoad(Road road){
	
	}

        /**
         * Eltávolítja az aktuális autót az autók listájáról.
         */
	public void removeActualCar(){
	}

        /**
         * A frissítő függvény, amely meghívja az összes autóra a frissítést,
         * így léptetve a játékot.
         */
	public void Update(){

		/* Car.Update() returns false
		 * removeActualCar();
		 */
	}

		/**
		 * Ez az undorítóancsúnya függvény rajzolja ki a konzolra vagy egy fileba a pályát
		 */
	public void ShowMap(PrintStream stream){
		roadStart.X = roadStart.Y = 0;
		roadStart.Iterated = true;

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

		// When this point is reached, all road has its X,Y coord set //

		int MinX = 0,MinY = 0;
		int MaxX = 0,MaxY = 0;

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
			current.Y -=MinY;
		}

		// And here we got every road with a positive X,Y coord //
		char Map[][] = new char[(MaxY-MinY+1)*3][(MaxX-MinX+1)*3];
		while(i.hasNext()){
			current = i.next();
			// Road SpecChar //
			Map[current.Y*3][current.X*3] = '@';
			Map[current.Y*3][current.X*3+1] = ' ';
			// Road Building //
			if(current.hasBuilding() != null)
				Map[current.Y*3][current.X*3+2] = current.hasBuilding().showMapChar();
			else
				Map[current.Y*3][current.X*3+2] = ' ';
			// Road ID //
			char[] roadID = current.ID.toString().toCharArray();
			for(int idi=0;idi<3;idi++){
				if(idi < roadID.length)
					Map[current.Y*3+1][current.X*3+idi] = roadID[idi];
				else
					Map[current.Y*3+1][current.X*3+idi] = ' ';
			}
			// Road Car //
			if(current.hasCar() != null)
				Map[current.Y*3+2][current.X*3] = current.hasCar().showMapChar();
			else
				Map[current.Y*3+2][current.X*3] = ' ';
			// Road Pickup //
			if(current.hasPickup() != null)
				Map[current.Y*3+2][current.X*3+1] = current.hasPickup().showMapChar();
			else
				Map[current.Y*3+2][current.X*3+1] = ' ';
			// Road TrafficController //
			if(current.hasTrafficController() != null)
				Map[current.Y*3+2][current.X*3+2] = current.hasTrafficController().showMapChar();
			else
				Map[current.Y*3+2][current.X*3+2] = ' ';
		}
		for(int ii=0;ii<(MaxY-MinY+1)*3;ii++)
		stream.println(Map[ii]);
	}

			/**
			 * ShowMap indítja el ezt a rekurzív fv-t
			 * ami bejárja az utakat és kitölti az X,Y koordinátájuk
			 */
	public void ShowMapSetRoad(Road current,Road prev,int direction){
		if(current.Iterated)
			return;

		AvailableRoads ar = current.getNextRoads();
		// Position this road //
		switch(direction){
			case 0:	// The prev road is right to us //
				current.X = prev.X-1;
				current.Y = prev.Y;
				break;
			case 1:	// The prev road is down to us //
				current.X = prev.X;
				current.Y = prev.Y-1;
				break;
			case 2:	// The prev Road is left to us //
				current.X = prev.X+1;
				current.Y = prev.Y;
				break;
			case 3:	// And up //
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
}