
interface GradientDecent{
	double objectfunction(double prediction, double prior);
}


 class MancGD implements GradientDecent {

	public double objectfunction(double prediction, double prior) {
		return Math.sqrt(Math.pow((prediction-prior),2));
	}
	
	
	
}
