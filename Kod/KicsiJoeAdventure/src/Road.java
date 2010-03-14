

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
		Output.methodStarts("Road - getNextRoads");
		Output.methodEnds("Road - getNextRoads");
		return new AvailableRoads(next);
	}

	public Car hasCar(){
		Output.methodStarts("Road - hasCar");
		Output.methodEnds("Road - hasCar");
		return carOnMe;
	}

	public TrafficController hasTrafficController(){
		Output.methodStarts("Road - hasTrafficController");
		Output.methodEnds("Road - hasTrafficController");
		return trafficController;
	}

	public Building hasBuilding(){
		Output.methodStarts("Road - hasBuilding");
		Output.methodEnds("Road - hasBuilding");
		return building;
	}

	public void setCar(Car car){
		Output.methodStarts("Road - setCar");
		carOnMe = car;
		Output.methodEnds("Road - setCar");
	}

	public void removeCar(){
		Output.methodStarts("Road - removeCar");
		carOnMe = null;
		Output.methodEnds("Road - removeCar");
	}

}