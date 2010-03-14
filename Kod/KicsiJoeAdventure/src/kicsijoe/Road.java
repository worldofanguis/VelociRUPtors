package kicsijoe;

public class Road extends ClassID {

	private Building building;
	private Car carOnMe;
	private Road next[];
	private TrafficController trafficController;

	public Road(){
		next = new Road[4];
		building = null;
		carOnMe = null;
		trafficController = null;
	}

	public AvailableRoads getNextRoads(){
		Output.methodStarts(ID,"getNextRoads()");
                AvailableRoads ar = new AvailableRoads(next);
                ar.setID("ar");
		Output.methodEnds(ID,"getNextRoads()",ar.toString());
		return ar;
	}

	public Car hasCar(){
		Output.methodStarts(ID,"hasCar()");
		Output.methodEnds(ID,"hasCar()",carOnMe.toString());
		return carOnMe;
	}

	public TrafficController hasTrafficController(){
		Output.methodStarts(ID,"hasTrafficController()");
		Output.methodEnds(ID,"hasTrafficController()",trafficController.toString());
		return trafficController;
	}

	public Building hasBuilding(){
		Output.methodStarts(ID,"hasBuilding()");
		Output.methodEnds(ID,"hasBuilding()", building.toString());
		return building;
	}

	public void setCar(Car car){
		Output.methodStarts(ID,"setCar(" + car.toString() + ")");
		carOnMe = car;
		Output.methodEnds(ID,"setCar(" + car.toString() + ")");
	}

	public void removeCar(){
		Output.methodStarts(ID,"removeCar()");
		carOnMe = null;
		Output.methodEnds(ID,"removeCar()");
	}

}