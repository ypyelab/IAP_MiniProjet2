package ch.epfl.cs107.play.signal.logic;

public class Or extends LogicSignal implements Logic {

	//set of parameters
	Logic s1,s2;
	
	//constructor
	public Or(Logic s1, Logic s2){
		this.s1 = s1;
		this.s2 = s2;
	}
	
	//methods
	@Override
	public boolean isOn() {
		{
			if(s1!=null && s2!=null) {
				if (s1.isOn() || s2.isOn()) {
					return true;
				}
			}
			return false;
		}
	}
	
}