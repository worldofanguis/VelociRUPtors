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
       randomEnabled = false; //Random logika nem engedélyezett
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
        Main.game.outputStream.println("[GAMEOVER]");
		System.exit(2);
    }

    /**
     * A játék kezdetkor a pálya elkészítése, az elemek elhelyezése.
     */
    public void Initialization(){
        //loadMapFromFile("map.txt");
    }

    /**
     * Autó hozzáadása a tárolt listához.
     * @param c A listába felvevendő autó
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
                                ((Lamp)lamps.getLast()).setRoad(roads.get(roadIndex));
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
        for (i=0; i<lamps.size(); i++)
            ( (Lamp)lamps.get(i) ).Update();

        for (i=0; i< cars.size(); i++)
        {
            if ( !( ( (Car)cars.get(i) ).Update() ) )
            {
                removeActualCar(i);
                --i; //Ez azért kell, mert csökken az utána jövők indexe.
            }

        }

        //Pickupok frissítése
        for (i=0; i< pickups.size(); i++)
        {
            if ( !( ( (Pickup)pickups.get(i) ).Update() ) )
            {
                pickups.remove(i);
                --i; //Ez azért kell, mert csökken az utána jövők indexe.
                //Autóval valami?
             }
        }

    }

    /**
     * Ez a függvény írja ki karakteresen a paraméterként kapott streamre
     * a pályát.
     * @param stream A stream, ahova "kirajzoljuk" a pályát
     * Ez az undorítóancsúnya függvény rajzolja ki a konzolra vagy egy fileba a pályát
     */
    public void ShowMap(PrintStream stream){
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

        // Itt már minden útnak pozitív az X és Y koordinátája //
        char Map[][] = new char[(MaxY-MinY+1)*3][(MaxX-MinX+1)*3];
		// Kitöltük a mappot default <space> ekkel //
		for(int s=0;s<(MaxY-MinY+1)*3;s++)
			for(int o=0;o<(MaxX-MinX+1)*3;o++)
				Map[s][o] = ' ';

		// És kezdődjön a móka //
        while(i.hasNext()){
            current = i.next();
            // Road SpecChar //
            Map[current.Y*3][current.X*3] = '@';
//            Map[current.Y*3][current.X*3+1] = ' ';
            // Road Building //
            if(current.hasBuilding() != null)
                Map[current.Y*3][current.X*3+2] = current.hasBuilding().showMapChar();
//            else
//              Map[current.Y*3][current.X*3+2] = ' ';
            // Road ID //
            char[] roadID = current.ID.toString().toCharArray();
            for(int idi=0;idi<3;idi++){
                if(idi < roadID.length)
                    Map[current.Y*3+1][current.X*3+idi] = roadID[idi];
//                else
//                    Map[current.Y*3+1][current.X*3+idi] = ' ';
            }
            // Road Car //
            if(current.hasCar() != null)
                Map[current.Y*3+2][current.X*3] = current.hasCar().showMapChar();
//            else
//                Map[current.Y*3+2][current.X*3] = ' ';
            // Road Pickup //
            if(current.hasPickup() != null)
                Map[current.Y*3+2][current.X*3+1] = current.hasPickup().showMapChar();
//            else
//                Map[current.Y*3+2][current.X*3+1] = ' ';
            // Road TrafficController //
            if(current.hasTrafficController() != null)
                Map[current.Y*3+2][current.X*3+2] = current.hasTrafficController().showMapChar();
//            else
//                Map[current.Y*3+2][current.X*3+2] = ' ';

        }

        for(int ii=0;ii<(MaxY-MinY+1)*3;ii++)
            stream.println(Map[ii]);
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

    /**
     * Parancsértelmező a prototípus tesztjeihez.
     * @param Command Parancs
     */
    public void CommandInterpreter(String Command){
        if(Command.startsWith("RandomEnabled(")){
            if(Command.substring(14,Command.length()-1).equalsIgnoreCase("true")){
                randomEnabled = true;
            }else{
                randomEnabled = false;
            }
        }else if(Command.startsWith("SetBankState(")){
            if(Command.substring(13,Command.length()-1).equalsIgnoreCase("true")){
                bankIsRobbed = true;
            }else{
                bankIsRobbed = false;
            }
        }else if(Command.startsWith("LoadMap(")){
            loadMapFromFile(Command.substring(8,Command.length()-1));
        }else if(Command.startsWith("SetOutput(")){
            String s = Command.substring(10,Command.length()-1);
            if(s.isEmpty())
                outputStream = System.out;
            else{
                try{
                    outputStream = new PrintStream(new File(WorkingDirectory,s));
                }catch(Exception e){
                    System.out.println("FileNotFound");
                    outputStream = System.out;
                }
            }
            outputStream.println("[START]");
        }else if(Command.startsWith("SetDirection(")){
            StringTokenizer st = new StringTokenizer(Command.substring(13,Command.length()-1),",");
            cars.get(Integer.parseInt(st.nextToken())).setDirection(Integer.parseInt(st.nextToken()));
        }else if(Command.startsWith("SetTick(")){
            StringTokenizer st = new StringTokenizer(Command.substring(8,Command.length()-1),",");
            cars.get(Integer.parseInt(st.nextToken())).setTick(Integer.parseInt(st.nextToken()));
	}else if(Command.startsWith("SetPickupTick(")){
            StringTokenizer st = new StringTokenizer(Command.substring(14,Command.length()-1),",");
            pickups.get(Integer.parseInt(st.nextToken())).setTick(Integer.parseInt(st.nextToken()));
        }else if(Command.startsWith("CivilGen(")){
            StringTokenizer st = new StringTokenizer(Command.substring(9,Command.length()-1),",");
            roads.get(Integer.parseInt(st.nextToken())).setCar(new Civil(Integer.parseInt(st.nextToken())));
        }else if(Command.startsWith("RobberGen(")){
            StringTokenizer st = new StringTokenizer(Command.substring(10,Command.length()-1),",");
            roads.get(Integer.parseInt(st.nextToken())).setCar(new Robber(Integer.parseInt(st.nextToken())));
        }else if(Command.startsWith("PoliceGen(")){
            StringTokenizer st = new StringTokenizer(Command.substring(10,Command.length()-1),",");
            roads.get(Integer.parseInt(st.nextToken())).setCar(new Police(Integer.parseInt(st.nextToken())));
        }else if(Command.startsWith("BunnyGen(")){
            StringTokenizer st = new StringTokenizer(Command.substring(9,Command.length()-1),",");
            Bunny bunny = new Bunny();
            Road road = roads.get(Integer.parseInt(st.nextToken()));
			road.setPickup(bunny);
			bunny.setRoad(road);
        }else if(Command.startsWith("RabbitGen(")){
            StringTokenizer st = new StringTokenizer(Command.substring(10,Command.length()-1),",");
			Bunny bunny = new Bunny();
            Road road = roads.get(Integer.parseInt(st.nextToken()));
			road.setPickup(bunny);
			bunny.setRoad(road);
		}else if(Command.startsWith("ShowMap()")){
            ShowMap(outputStream);
        }else if(Command.startsWith("SetLampColor(")){
            StringTokenizer st = new StringTokenizer(Command.substring(13,Command.length()-1),",");
            lamps.get(Integer.parseInt(st.nextToken())).setColor(Boolean.parseBoolean(st.nextToken()));
        }else if(Command.startsWith("SetLampTick(")){
            StringTokenizer st = new StringTokenizer(Command.substring(12,Command.length()-1),",");
            lamps.get(Integer.parseInt(st.nextToken())).setTick(Integer.parseInt(st.nextToken()));
        }else if(Command.startsWith("Tick(")){
            String s = Command.substring(5,Command.length()-1);
            for(int i=0;i<Integer.parseInt(s);i++){
                Update();
            }
        }
    }

}