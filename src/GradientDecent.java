
interface GradientDecent{
	double objectfunction(double prediction, double prior);
}


 class MancGD implements GradientDecent {
//define the object-function, and the GD is getting the minimum of it
	public double objectfunction(double prediction, double prior) {
		return Math.sqrt(Math.pow((prediction-prior),2));
	}
	
	
	
}
