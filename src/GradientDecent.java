
interface GradientDecent{
	double objectfunction(double prediction, double prior);
	void changeParameter(Network net);
	void getGradient(Network net) ;
}


 class MancGD implements GradientDecent {
	
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
	 
	 
//define the object-function, and the GD is getting the minimum of it
	public double objectfunction(double prediction, double prior) {
		return Math.sqrt(Math.pow((prediction-prior),2));
		
	}
		
	/**
	 * To calculate the gradient by a difference 0.001
	 * the step and variables can be put into Class GradientDecent
	 */
		public void getGradient(Network net) {
				//gradient decent
			       //gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step))
			       //layer1
			       for(int p=0; p<8; p++) {
			    	   for(int i=0; i<8-p; i++) {
					   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
					   		net.a[p][p+i]=net.a[p][p+i]+0.001;
					   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
					   		gradient[p][i] = (dedicate - temp)/0.001;
					   		net.a[p][p+i]=net.a[p][p+i]-0.001;
			   		}
			      }
			       
			   		for(int i=0; i<8; i++) {
				   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		net.h[i]=net.h[i]+0.001;
				   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		gradienth[i] = (dedicate - temp)/0.001;
				   		net.h[i]=net.h[i]-0.001;
			   		}
			   		
			   		for(int i=0; i<8; i++) {
				   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		net.b[i]=net.b[i]+0.001;
				   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		gradientb[i] = (dedicate - temp)/0.001;
				   		net.b[i]=net.b[i]-0.001;
			   		}
			      

			       //diff
			       for(int p=0; p<7; p++) {
			    	   for(int i=0; i<7; i++) {
			    		   double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
			    		   net.diffa[p][i]=net.diffa[p][i]+0.001;
				   			double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   			gradientdiffa[p][i] = (dedicate - temp)/0.001;
				   			net.diffa[p][i]=net.diffa[p][i]-0.001;
			   		}
			       }
			       

			   		for(int i=0; i<7; i++) {
				   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		net.diffh[i]=net.diffh[i]+0.001;
				   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		gradientdiffh[i] = (dedicate - temp)/0.001;
				   		net.diffh[i]=net.diffh[i]-0.001;
			       }
			       
			   		for(int i=0; i<7; i++) {
				   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		net.diffb[i]=net.diffb[i]+0.001;
				   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		gradientdiffb[i] = (dedicate - temp)/0.001;
				   		net.diffb[i]=net.diffb[i]-0.001;
			       }
			   	
			       
			       //layer2
			       for(int p=0; p<8; p++) {
			   		   for(int i=0; i<8;i++) {
				   	     	double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   	     	net.k[p][i]=net.k[p][i]+0.001;
				   	     	double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   	     	gradientk[p][i] = (dedicate - temp)/0.001;
				   	     	net.k[p][i]=net.k[p][i]-0.001;
			   		}
			       }
			   		

			   		for(int i=0; i<8; i++) {
				   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		net.o[i]=net.o[i]+0.001;
				   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		gradiento[i] = (dedicate - temp)/0.001;
				   		net.o[i]=net.o[i]-0.001;
			   		}
			   		
			   		
			   		for(int i=0; i<8; i++) {
				   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		net.p[i]=net.p[i]+0.001;
				   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				   		gradientp[i] = (dedicate - temp)/0.001;
				   		net.p[i]=net.p[i]-0.001;
			   		}
			   		
			   		

				       //diff2
				       for(int p=0; p<3; p++) {
				    	   for(int i=0; i<7;i++) {
				    		   double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
				    		   net.diff2a[p][i]=net.diff2a[p][i]+0.001;
				    		   double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
					   			gradientdiff2a[p][i] = (dedicate - temp)/0.001;
					   			net.diff2a[p][i]=net.diff2a[p][i]-0.001;
				   		}
				       }
				       

				   		for(int i=0; i<3;i++) {
					   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
					   		net.diff2h[i]=net.diff2h[i]+0.001;
					   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
					   		gradientdiff2h[i] = (dedicate - temp)/0.001;
					   		net.diff2h[i]=net.diff2h[i]-0.001;
				       }
				       
				   		for(int i=0; i<3;i++) {
					   		double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
					   		net.diff2b[i]=net.diff2b[i]+0.001;
					   		double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
					   		gradientdiff2b[i] = (dedicate - temp)/0.001;
					   		net.diff2b[i]=net.diff2b[i]-0.001;
				       }
				       
			   		
			   		// layer3
					    for(int p=0; p<4; p++) {
						    for(int i=0; i<11;i++) {
							   	double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							   	net.outa[p][i]=net.outa[p][i]+0.001;
							 	double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							   	gradientouta[p][i] = (dedicate - temp)/0.001;
							   	net.outa[p][i]=net.outa[p][i]-0.001;
						   		}
						       }
						       

						for(int i=0; i<4;i++) {
							double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							net.outk[i]=net.outk[i]+0.001;
							double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							gradientoutk[i] = (dedicate - temp)/0.001;
							net.outk[i]=net.outk[i]-0.001;
						       }
						
						
						for(int i=0; i<4;i++) {
							double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							net.outb[i]=net.outb[i]+0.001;
							double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							gradientoutb[i] = (dedicate - temp)/0.001;
							net.outb[i]=net.outb[i]-0.001;
						       }
						       
			   		//layer 4
			   		
							 
						for(int i=0; i<4;i++) {
							double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							net.layer4a[i]=net.layer4a[i]+0.001;
							double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
							gradientlayer4a[i] = (dedicate - temp)/0.001;
						    net.layer4a[i]=net.layer4a[i]-0.001;
								   			}
								       
								       

					    double temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
						net.layer4k=net.layer4k+0.001;
						double dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
						gradientlayer4k = (dedicate - temp)/0.001;
						net.layer4k=net.layer4k-0.001;
								       
								       
						temp = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
						net.layer4b=net.layer4b+0.001;
						dedicate = gd.objectfunction(Core.predict(), Prior.getX(Core.t + Core.step));
						gradientlayer4b = (dedicate - temp)/0.001;
						net.layer4b=net.layer4b-0.001;
			}
		
		
		/**
		 * Change The Parameter based on the gradient decence
		 */
		public void changeParameter(Network net) {
			
			
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
}
