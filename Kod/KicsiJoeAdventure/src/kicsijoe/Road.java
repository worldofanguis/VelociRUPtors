package kicsijoe;

public class Road {

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
		return new AvailableRoads(next);
	}

	public Car hasCar(){
		System.out.println("Road - hasCar");
		return carOnMe;
	}

	public TrafficController hasTrafficController(){
		System.out.println("Road - hasTrafficController");
		return trafficController;
	}

	public Building hasBuilding(){
		System.out.println("Road - hasBuilding");
		return building;
	}

	public void setCar(Car car){
		System.out.println("Road - setCar");
		carOnMe = car;
	}

	public void removeCar(){
		System.out.println("Road - removeCar");
		carOnMe = null;
	}

}