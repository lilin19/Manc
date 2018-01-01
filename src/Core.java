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
	//for position calculation
	static double x= 0;
	//time
	static double t=0;
	//Neuron Network
	static Network net = new Network();
	
	//variables of gradient decence
	static GradientDecent gd = new MancGD(); 
	static double gradient[][] = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,1,1,1,1,1,1},{0,0,0,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,0,0,0,0,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1}};
	static double gradientk[][] = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,1,1,1,1,1,1},{0,0,0,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,0,0,0,0,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1}};
	static double gradienth[] = {1,1,1,1,1,1,1,1};
	static double gradientb[] = {1,1,1,1,1,1,1,1};

	static double gradiento[] = {1,1,1,1,1,1,1,1};
	static double gradientp[] = {1,1,1,1,1,1,1,1};
	
	static double gradientdiffa[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
	static double gradientdiffh[] = {1,1,1,1,1,1,1};
	static double gradientdiffb[] = {1,1,1,1,1,1,1};
	
    static double gradientdiff2a[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
    static double gradientdiff2h[] = {1,1,1};
    static double gradientdiff2b[] = {1,1,1};
    
    static double gradientouta[][] = {{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1}};
    static double gradientoutk[] = {1,1,1,1};
    static double gradientoutb[] = {1,1,1,1};
    
    static double gradientlayer4a[] = {1,1,1,1};
    static double gradientlayer4k = 1;
    static double gradientlayer4b = 1;
    
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

		

		//work cycle n=1000000 times
		for(; t<10000000; t=t+10) {

			
			//work flow
		iterate();
		getGradient();
		changeParameter();

		//print the position array
		System.out.println("the position: " + s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]+" "+s1[5]+" "+s1[6]+" "+s1[7]);
		    //print the prediction and error
	       System.out.println("prediction: "+predict());
	       System.out.println("distance:"+gd.objectfunction(predict(), s1[7]));
	      
	   		
		}
}
	
	
	/**
	 * Change The Parameter based on the gradient decence
	 */
	static void changeParameter() {
		
		
	       for(int p=0; p<8; p++) {
		   		for(int i=0; i<8-p; i++) {
		   			net.a[p][p+i]=net.a[p][p+i]-gradient[p][p+i]*0.001;
		   		}
		      }
	       
	       for(int p=0; p<8; p++) {
	   		    for(int i=0; i<8; i++) {
	   			    net.k[p][i]=net.k[p][i]-gradientk[p][i]*0.001;
	   		                           }
	       }
	       
	   		for(int i=0; i<8; i++) {
	   			net.h[i]=net.h[i]-gradienth[i]*0.001;
	   		}
	   		
	   		for(int i=0; i<8; i++) {
	   			net.b[i]=net.b[i]-gradientb[i]*0.001;
	   		}
	   		
	   		for(int i=0; i<8; i++) {
	   			net.o[i]=net.o[i]-gradiento[i]*0.001;
	   		}
	   		
	   		for(int i=0; i<8; i++) {
	   			net.p[i]=net.p[i]-gradientp[i]*0.001;
	   		}
	   		
		       for(int p=0; p<7; p++) {
			   		for(int i=0; i<7; i++) {
			   			net.diffa[p][i]=net.diffa[p][i]-gradientdiffa[p][i]*0.001;
			   		}
			       }
			       
			   		for(int i=0; i<7; i++) {
			   			net.diffb[i]=net.h[i]-gradientdiffb[i]*0.001;
			   		}
			   		
			   		for(int i=0; i<7; i++) {
			   			net.diffh[i]=net.diffh[i]-gradientdiffh[i]*0.001;
			   		}
			   		
				       for(int p=0; p<3; p++) {
					   		for(int i=0; i<7; i++) {
					   			net.diff2a[p][i]=net.diff2a[p][i]-gradientdiff2a[p][i]*0.001;
					   		}
					       }
					       
					   		for(int i=0; i<3; i++) {
					   			net.diff2b[i]=net.h[i]-gradientdiff2b[i]*0.001;
					   		}
					   		
					   		for(int i=0; i<3; i++) {
					   			net.diff2h[i]=net.diffh[i]-gradientdiff2h[i]*0.001;
					   		}
					   		
						       for(int p=0; p<4; p++) {
							   		for(int i=0; i<10; i++) {
							   			net.outa[p][i]=net.outa[p][i]-gradientouta[p][i]*0.001;
							   		}
							       }
							       
							   		for(int i=0; i<3; i++) {
							   			net.outb[i]=net.h[i]-gradientoutb[i]*0.001;
							   		}
							   		
							   		for(int i=0; i<3; i++) {
							   			net.outk[i]=net.outk[i]-gradientoutk[i]*0.001;
							   		}
							   		
							   		
							   		
							   		for(int i=0; i<4; i++) {
							   			net.layer4a[i]=net.layer4a[i]-gradientlayer4a[i]*0.001;
							   		}
			   		

	   			net.layer4k = net.layer4k-gradientlayer4k*0.001;
	   			net.layer4b = net.layer4b-gradientlayer4b*0.001;
	   		
	}
	
	/**
	 * To calculate the gradient by a difference 0.001
	 * the step and variables can be put into Class GradientDecent
	 */
	static void getGradient() {
		//gradient decent
	       
	       //layer1
	       for(int p=0; p<8; p++) {
	   		for(int i=0; i<8-p; i++) {
		   		double temp = gd.objectfunction(predict(), s1[7]);
		   		net.a[p][p+i]=net.a[p][p+i]+0.001;
		   		double dedicate = gd.objectfunction(predict(), s1[7]);
		   		gradient[p][i] = (dedicate - temp)/0.001;
		   		net.a[p][p+i]=net.a[p][p+i]-0.001;
	   		}
	      }
	       
	   		for(int i=0; i<8; i++) {
		   		double temp = gd.objectfunction(predict(), s1[7]);
		   		net.h[i]=net.h[i]+0.001;
		   		double dedicate = gd.objectfunction(predict(), s1[7]);
		   		gradienth[i] = (dedicate - temp)/0.001;
		   		net.h[i]=net.h[i]-0.001;
	   		}
	   		
	   		for(int i=0; i<8; i++) {
		   		double temp = gd.objectfunction(predict(), s1[7]);
		   		net.b[i]=net.b[i]+0.001;
		   		double dedicate = gd.objectfunction(predict(), s1[7]);
		   		gradientb[i] = (dedicate - temp)/0.001;
		   		net.b[i]=net.b[i]-0.001;
	   		}
	      

	       //diff
	       for(int p=0; p<7; p++) {
	    	   for(int i=0; i<7; i++) {
	    		   double temp = gd.objectfunction(predict(), s1[7]);
	    		   net.diffa[p][i]=net.diffa[p][i]+0.001;
		   			double dedicate = gd.objectfunction(predict(), s1[7]);
		   			gradientdiffa[p][i] = (dedicate - temp)/0.001;
		   			net.diffa[p][i]=net.diffa[p][i]-0.001;
	   		}
	       }
	       

	   		for(int i=0; i<7; i++) {
		   		double temp = gd.objectfunction(predict(), s1[7]);
		   		net.diffh[i]=net.diffh[i]+0.001;
		   		double dedicate = gd.objectfunction(predict(), s1[7]);
		   		gradientdiffh[i] = (dedicate - temp)/0.001;
		   		net.diffh[i]=net.diffh[i]-0.001;
	       }
	       
	   		for(int i=0; i<7; i++) {
		   		double temp = gd.objectfunction(predict(), s1[7]);
		   		net.diffb[i]=net.diffb[i]+0.001;
		   		double dedicate = gd.objectfunction(predict(), s1[7]);
		   		gradientdiffb[i] = (dedicate - temp)/0.001;
		   		net.diffb[i]=net.diffb[i]-0.001;
	       }
	   	
	       
	       //layer2
	       for(int p=0; p<8; p++) {
	   		   for(int i=0; i<8;i++) {
		   	     	double temp = gd.objectfunction(predict(), s1[7]);
		   	     	net.k[p][i]=net.k[p][i]+0.001;
		   	     	double dedicate = gd.objectfunction(predict(), s1[7]);
		   	     	gradientk[p][i] = (dedicate - temp)/0.001;
		   	     	net.k[p][i]=net.k[p][i]-0.001;
	   		}
	       }
	   		

	   		for(int i=0; i<8; i++) {
		   		double temp = gd.objectfunction(predict(), s1[7]);
		   		net.o[i]=net.o[i]+0.001;
		   		double dedicate = gd.objectfunction(predict(), s1[7]);
		   		gradiento[i] = (dedicate - temp)/0.001;
		   		net.o[i]=net.o[i]-0.001;
	   		}
	   		
	   		
	   		for(int i=0; i<8; i++) {
		   		double temp = gd.objectfunction(predict(), s1[7]);
		   		net.p[i]=net.p[i]+0.001;
		   		double dedicate = gd.objectfunction(predict(), s1[7]);
		   		gradientp[i] = (dedicate - temp)/0.001;
		   		net.p[i]=net.p[i]-0.001;
	   		}
	   		
	   		

		       //diff2
		       for(int p=0; p<3; p++) {
		    	   for(int i=0; i<7;i++) {
		    		   double temp = gd.objectfunction(predict(), s1[7]);
		    		   net.diff2a[p][i]=net.diff2a[p][i]+0.001;
		    		   double dedicate = gd.objectfunction(predict(), s1[7]);
			   			gradientdiff2a[p][i] = (dedicate - temp)/0.001;
			   			net.diff2a[p][i]=net.diff2a[p][i]-0.001;
		   		}
		       }
		       

		   		for(int i=0; i<3;i++) {
			   		double temp = gd.objectfunction(predict(), s1[7]);
			   		net.diff2h[i]=net.diff2h[i]+0.001;
			   		double dedicate = gd.objectfunction(predict(), s1[7]);
			   		gradientdiff2h[i] = (dedicate - temp)/0.001;
			   		net.diff2h[i]=net.diff2h[i]-0.001;
		       }
		       
		   		for(int i=0; i<3;i++) {
			   		double temp = gd.objectfunction(predict(), s1[7]);
			   		net.diff2b[i]=net.diff2b[i]+0.001;
			   		double dedicate = gd.objectfunction(predict(), s1[7]);
			   		gradientdiff2b[i] = (dedicate - temp)/0.001;
			   		net.diff2b[i]=net.diff2b[i]-0.001;
		       }
		       
	   		
	   		// layer3
			       for(int p=0; p<4; p++) {
				   		for(int i=0; i<11;i++) {
					   		double temp = gd.objectfunction(predict(), s1[7]);
					   		net.outa[p][i]=net.outa[p][i]+0.001;
					   		double dedicate = gd.objectfunction(predict(), s1[7]);
					   		gradientouta[p][i] = (dedicate - temp)/0.001;
					   		net.outa[p][i]=net.outa[p][i]-0.001;
				   		}
				       }
				       

				   		for(int i=0; i<4;i++) {
					   		double temp = gd.objectfunction(predict(), s1[7]);
					   		net.outk[i]=net.outk[i]+0.001;
					   		double dedicate = gd.objectfunction(predict(), s1[7]);
					   		gradientoutk[i] = (dedicate - temp)/0.001;
					   		net.outk[i]=net.outk[i]-0.001;
				       }
				       
				   		for(int i=0; i<4;i++) {
					   		double temp = gd.objectfunction(predict(), s1[7]);
					   		net.outb[i]=net.outb[i]+0.001;
					   		double dedicate = gd.objectfunction(predict(), s1[7]);
					   		gradientoutb[i] = (dedicate - temp)/0.001;
					   		net.outb[i]=net.outb[i]-0.001;
				       }
				       
	   		//layer 4
	   		
					 
						   		for(int i=0; i<4;i++) {
							   		double temp = gd.objectfunction(predict(), s1[7]);
							   		net.layer4a[i]=net.layer4a[i]+0.001;
							   		double dedicate = gd.objectfunction(predict(), s1[7]);
							   		gradientlayer4a[i] = (dedicate - temp)/0.001;
							   		net.layer4a[i]=net.layer4a[i]-0.001;
						   		}
						       
						       

							   		double temp = gd.objectfunction(predict(), s1[7]);
							   		net.layer4k=net.layer4k+0.001;
							   		double dedicate = gd.objectfunction(predict(), s1[7]);
							   		gradientlayer4k = (dedicate - temp)/0.001;
							   		net.layer4k=net.layer4k-0.001;
						       
						       
							   		temp = gd.objectfunction(predict(), s1[7]);
							   		net.layer4b=net.layer4b+0.001;
							   		dedicate = gd.objectfunction(predict(), s1[7]);
							   		gradientlayer4b = (dedicate - temp)/0.001;
							   		net.layer4b=net.layer4b-0.001;
	}
	
}


//The target we are predicting
 class Prior{
	static double getX( double t) {
		return (double) (3* Math.cos(0.1*t));
		
	}
}



