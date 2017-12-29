
public class Core {
	double[] a1;
	static double[] s1 = {0,0,0,0,0,0,0,0};
	static double x= 0;
	static double t=0;
	static Network net = new Network();
	
	//gradient decent
	static GradientDecent gd = new MancGD(); 
	static double prediction;
	static double gradient[][] = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,1,1,1,1,1,1},{0,0,0,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,0,0,0,0,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1}};
	static double gradientk[] = {1,1,1,1,1,1,1,1};
	static double gradienth[] = {1,1,1,1,1,1,1,1};
	static double gradientb[] = {1,1,1,1,1,1,1,1};
	static double gradiento;
	static double gradientp;
	
	static void iterate() {
		
		for(int i=0; i<7; i++) {
		s1[i] = s1[i+1];
		}
		s1[7] = Prior.getX(t);

	}
	
	
	public static void main(String[] args) {

		

		//work
		for(;t<100000;t++) {
			System.out.println("the position: " + s1[0]+" "+s1[1]+" "+s1[2]+" "+s1[3]+" "+s1[4]+" "+s1[5]+" "+s1[6]+" "+s1[7]);
		iterate();
		prediction = net.layer2(net.layer1(s1));
	       System.out.println("prediction: "+prediction);
	       System.out.println("distance:"+gd.objectfunction(prediction, s1[7]));
	       
		//gradient decent
	       for(int p=0; p<8;p++) {
	   		for(int i=0; i<8-p;i++) {
	   		double temp = gd.objectfunction(prediction, s1[7]);
	   		net.a[p][p+i]=net.a[p][p+i]+0.001;
	   		double dedicate = gd.objectfunction(prediction, s1[7]);
	   		gradient[p][i] = (dedicate - temp)/0.001;
	   		net.a[p][p+i]=net.a[p][p+i]-0.001;
	   		}
	      }

	   		for(int i=0; i<8;i++) {
	   		double temp = gd.objectfunction(prediction, s1[7]);
	   		net.k[i]=net.k[i]+0.001;
	   		double dedicate = gd.objectfunction(prediction, s1[7]);
	   		gradientk[i] = (dedicate - temp)/0.001;
	   		net.k[i]=net.k[i]-0.001;
	   		}
	   		
	   		for(int i=0; i<8;i++) {
	   		double temp = gd.objectfunction(prediction, s1[7]);
	   		net.h[i]=net.h[i]+0.001;
	   		double dedicate = gd.objectfunction(prediction, s1[7]);
	   		gradienth[i] = (dedicate - temp)/0.001;
	   		net.h[i]=net.h[i]-0.001;
	   		}
	   		
	   		for(int i=0; i<8;i++) {
	   		double temp = gd.objectfunction(prediction, s1[7]);
	   		net.b[i]=net.b[i]+0.001;
	   		double dedicate = gd.objectfunction(prediction, s1[7]);
	   		gradientb[i] = (dedicate - temp)/0.001;
	   		net.b[i]=net.b[i]-0.001;
	   		}
	      
	   		double temp = gd.objectfunction(prediction, s1[7]);
	   		net.o=net.o+0.001;
	   		double dedicate = gd.objectfunction(prediction, s1[7]);
	   		gradiento = (dedicate - temp)/0.001;
	   		net.o=net.o-0.001;
	   		
	   	    temp = gd.objectfunction(prediction, s1[7]);
	   		net.p=net.p+0.001;
	   	    dedicate = gd.objectfunction(prediction, s1[7]);
	   		gradientp = (dedicate - temp)/0.001;
	   		net.p=net.p-0.001;
	   		
	   		

	   		
		       for(int p=0; p<8;p++) {
			   		for(int i=0; i<8-p;i++) {
			   			net.a[p][p+i]=net.a[p][p+i]-gradient[p][p+i]*0.001;
			   		}
			      }
		       
		   		for(int i=0; i<8;i++) {
		   			net.k[i]=net.k[i]-gradientk[i]*0.001;
		   		}
		   		
		   		for(int i=0; i<8;i++) {
		   			net.h[i]=net.h[i]-gradienth[i]*0.001;
		   		}
		   		
		   		for(int i=0; i<8;i++) {
		   			net.b[i]=net.b[i]-gradienth[i]*0.001;
		   		}
		   		

		   			net.o=net.o-gradiento*0.001;
		   			net.p=net.p-gradientp*0.001;
		   		
	   		
	   		
	   		
		}
		


}
}

 class Prior{
	static double getX( double t) {
		return (double) (3* Math.cos(0.1*t));
		
	}
}



