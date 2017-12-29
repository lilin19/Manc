
interface Neuron {
	double sumUp(double[] x, double paraSum[]);
	double activate(double offset,double slope);
}

class Hexlinear implements Neuron{
	 double sum;
	 double y;
	public double activate(double offset, double slope) {
		y = sum*slope + offset;
		return y;
	}

	
	public double sumUp(double x[], double paraSum[]) {
		sum = 0;
     for(int i=0; i<x.length; i++) {
    	 sum += paraSum[i] * x[i];
	}
	return sum;

}

	

	
}