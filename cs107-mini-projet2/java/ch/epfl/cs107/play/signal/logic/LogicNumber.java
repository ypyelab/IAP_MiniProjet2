package ch.epfl.cs107.play.signal.logic;

public class LogicNumber extends LogicSignal implements Logic {

	//set of parameters
	Logic [] sig;
	float nb;
	
	//constructor
	public LogicNumber(float nb, Logic ... sig){
		this.nb = nb;
		this.sig = sig;
	}
	
	//methods
	@Override
	public boolean isOn() {
		{
			if(nb < 0.0f || nb > Math.pow(2, sig.length) || sig.length > 12) {
				return false;
			}
			
			///parameters operation
			int count = 0;
			float nbSignal = 0.0f;
			
			///operation
			for(Logic s: sig) {
				if (s == null) {
					return false;
				}
				count = count + 1;
				nbSignal = nbSignal + ((float)(Math.pow(2, count))*s.getIntensity());
			}
			
			///decision
			if(nbSignal == nb) {
				return true;
			}
			
			return false;
		}
	}
	
}