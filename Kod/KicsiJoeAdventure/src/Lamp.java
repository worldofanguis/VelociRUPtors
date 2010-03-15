

public class Lamp extends ClassID implements TrafficController {

	private boolean state[] = new boolean[4];

	public Lamp(){

	}

	public boolean isGreen(Directions Direction){
		String p = new String();
        if(Direction == Directions.LEFT){ p = "LEFT"; }
		else if(Direction == Directions.UP){ p = "UP"; }
		else if(Direction == Directions.RIGHT){ p = "RIGHT"; }
        else if(Direction == Directions.DOWN){ p = "DOWN"; }
		Output.methodStarts(ID,"isGreen(" + p + ")");

                //...valamivel visszatér
                boolean ret = false;
                String p2 = (ret) ? new String("true") : new String("false");

		Output.methodEnds(ID,"isGreen(" + p + ")",p2);
		return ret;
	}

	public boolean Update(){
		Output.methodStarts(ID,"Update()");
                boolean ret = false;
                String p = (ret) ? new String("true") : new String("false");

		Output.methodEnds(ID,"Update()",p);
		return ret;
	}

	public void whatSign(Car car){
		Output.methodStarts(ID, "whatSign("+car.toString()+")");
		car.Interaction(this);
		Output.methodEnds(ID, "whatSign("+car.toString()+")");
	}

}