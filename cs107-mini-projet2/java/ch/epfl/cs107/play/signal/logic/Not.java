package ch.epfl.cs107.play.signal.logic;

public class Not extends LogicSignal implements Logic {

	//set of parameters
	Logic s;
	
	//constructor
	public Not(Logic s){
		this.s = s;
	}
	
	//methods
	@Override
	public boolean isOn() {
		{
			if(s!=null && !s.isOn()) {
				return true;
			}
			return false;
		}
	}
	
}