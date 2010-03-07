package kicsijoe;

public class Lamp implements TrafficController {

	private boolean state[] = new boolean[4];

	public Lamp(){

	}

	public void isGreen(Directions Diretion){
		System.out.println("Lamp - isGreen");
	}

	public boolean Update(){
		System.out.println("Lamp - Update");
		return false;
	}

	public void whatSign(Car car){
		System.out.println("Lamp - whatSign");
	}

}