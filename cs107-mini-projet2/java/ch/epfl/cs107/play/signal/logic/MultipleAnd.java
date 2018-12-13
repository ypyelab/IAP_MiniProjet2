package ch.epfl.cs107.play.signal.logic;

public class MultipleAnd extends LogicSignal implements Logic {

	//set of parameters
	Logic [] sig;
	
	//constructor
	public MultipleAnd(Logic ... sig){
		this.sig = sig;
	}
	
	//methods
	@Override
	public boolean isOn() {
		int sum = 0;
		for(Logic s:sig) {
			if(s!=null && s.isOn()) {
				sum = sum+1;
			}
		}
		if (sum==sig.length) {
			return true;
		}
		return false;
	}
	
}