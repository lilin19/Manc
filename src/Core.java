/**
 * 1.1.2018
 * @author tg
 * 'Manc' is a neuron network can predict the ellipse equation
 * It is a trial to regress a sampled analytical-function
 */

public class Core {
	double[] a1;
	//the 8 real positions array
	static double[] s1 = {0,0,0,0,0,0,0,0};
	//time
	static double t=0;
	//Neuron Network
	static Network net = new Network();
	
	static GradientDecent gd = new MancGD();

    
    //variable for calculation in layer 2, to make the output from 2 layers into layer2[] 
    public static double layer2[] = {0,0,0,0,0,0,0,0,0,0,0};
    
    /**
     * To generate a array of real position and iterate it within time
     */
	static void iterate() {
		
		for(int i=0; i<7; i++) {
		s1[i] = s1[i+1];
		}
		s1[7] = Prior.getX(t);

	}
	
	
	/**
	 * Sorry for pour encapsulation, the predict generate the predict value
	 * @ predict position
	 */
	static double predict() {
		layer2[8] = net.layer22(net.layer12(net.layer1diff(s1)))[0];
		layer2[9] = net.layer22(net.layer12(net.layer1diff(s1)))[1];
		layer2[10] = net.layer22(net.layer12(net.layer1diff(s1)))[2];
		for(int i=0; i<8; i++) {
			layer2[i] = net.layer2(net.layer1(s1))[i];
		}
		
		return net.layer4(net.layer3(layer2));
	}
	
	
	
	/**
	 * Static Main
	 * 
	 */
	public static void main(String[] args) {

		//work cycle 
			
		 //learning work flow
		for(; t<100000; t=t+2) {
			iterate();
			gd.getGradient(net);
			gd.changeParameter(net);

			//print the position array
			System.out.println("the position: " + s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]+" "+s1[5]+" "+s1[6]+" "+s1[7]+" "+Prior.getX(t+2));
		    //print the prediction and error
			System.out.println("prediction: "+predict());
			System.out.println("distance:"+gd.objectfunction(predict(), Prior.getX(t+2)));
		}

		
		//verification work flow
		for(t = 1.67886; t<500; t=t+2) {
			iterate();
			//print the position array
		   System.out.println("the position2: " + s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]+" "+s1[5]+" "+s1[6]+" "+s1[7]+" "+Prior.getX(t+2));
		    //print the prediction and error
	       System.out.println("prediction2: "+predict());
	       System.out.println("distance2:"+gd.objectfunction(predict(), Prior.getX(t+2)));
		}
}
		
}


//The target we are predicting
 class Prior{
	static double getX( double t) {
		return (double) (3* Math.cos(0.1*t));
		
	}
}
