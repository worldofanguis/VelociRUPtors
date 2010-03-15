/**
 * Class Road:
 * Egy útszakaszt megtestesítő osztály.
 */

public class Road extends ClassID {

        /**
         * Az út mellett található épület referenciája.
         */
	private Building buildingOnMe;

        /**
         * Az útszakaszon lévő jármű referenciája.
         */
	private Car carOnMe;

        /**
         * Az útszakaszhoz kapcsolódó utak referenciái.
         */
	private Road next[];

        /**
         * Az útszakaszon található forgalomirányító-készülék referenciája.
         */
	private TrafficController trafficController;

        /**
         * Konstruktor. Az inicializáláshoz kapott objektumnév: r.
         */
	public Road(){
            setID("r");
            Output.methodStarts(ID, "Road()");
		next = new Road[4];
		buildingOnMe = null;
		carOnMe = null;
		trafficController = null;
            Output.methodEnds(ID, "Road()");
	}

        /**
         * AvailableRoads osztály generálása az adott útszakaszhoz.
         * @return A lehetséges útirányok egy AvailableRoads osztályban.
         */
	public AvailableRoads getNextRoads(){
		Output.methodStarts(ID,"getNextRoads()");
                AvailableRoads ar = new AvailableRoads(next);
                ar.setID("ar");
		Output.methodEnds(ID,"getNextRoads()",ar.toString());
		return ar;
	}

        /**
         * Van-e rajta autó?
         * @return A rajta lévő autó referenciája.
         */
	public Car hasCar(){
		Output.methodStarts(ID,"hasCar()");
		Output.methodEnds(ID,"hasCar()",carOnMe.toString());
		return carOnMe;
	}

        /**
         * Van rajta forgalomirányító-készülék?
         * @return A rajta lévő forgalomirányító-készülék referenciája.
         */
	public TrafficController hasTrafficController(){
		Output.methodStarts(ID,"hasTrafficController()");
		if(trafficController != null){
		    Output.methodEnds(ID,"hasTrafficController()", trafficController.toString());
		    return trafficController;
		}
		else{
		    Output.methodEnds(ID,"hasTrafficController() -no Traffic Controller found");
		    return null;
		}
		
		
	}

	/**
         * Van mellette épület?
         * @return Az út melletti épület referenciája.
         */
        public Building hasBuilding(){
		Output.methodStarts(ID,"hasBuilding()");
		if(buildingOnMe != null){
		    Output.methodEnds(ID,"hasBuilding()", buildingOnMe.toString());
		    return buildingOnMe;
		}		    
		else{
		    Output.methodEnds(ID,"hasBuilding() - no building found");
		    return null;
		}
		
	}

        /**
         * Épület beállítása (inicializáláshoz)
         * @param building Az út mellé helyezendő épület.
         */
	public void setBuilding(Building building){
		Output.methodStarts(ID,"setBuilding(" + building.toString() + ")");
		buildingOnMe = building;
		Output.methodEnds(ID,"setBuilding(" + building.toString() + ")");
	}

        /**
         * Az útra helyezi az adott autót.
         * @param car Az útra helyezendő autó referenciája.
         */
	public void setCar(Car car){
		Output.methodStarts(ID,"setCar(" + car.toString() + ")");
		carOnMe = car;
                car.setRoadUnderCar(this);
		Output.methodEnds(ID,"setCar(" + car.toString() + ")");
	}

	public void setTrafficController(TrafficController tc){
		Output.methodStarts(ID,"setTrafficController(" + tc.toString() + ")");
		trafficController = tc;
		Output.methodEnds(ID,"setTrafficController(" + tc.toString() + ")");
	}

        /**
         * Eltávolítja az útról az aktuálisan ott lévő autót.
         */
	public void removeCar(){
		Output.methodStarts(ID,"removeCar()");
		carOnMe = null;
		Output.methodEnds(ID,"removeCar()");
	}

        /**
         * Az útszakaszhoz csatlakoztat további útszakaszt (inicializálás).
         * @param Direction Az adott irány, ahol csatlakozik
         * @param road A csatlakozó út
         */
	public void setRoad(Directions Direction,Road road){
		Output.methodStarts(ID,"setRoad("+road.toString()+")");
		next[Direction.value] = road;
		Output.methodEnds(ID,"setRoad("+road.toString()+")");
	}
}