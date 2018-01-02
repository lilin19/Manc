/**
 * the structure of Manc's network
 * @author tg
 *
 *(in,out)
 *
 *layer1(8,8) ----->layer2(8,8) -----> layer3(11,4) -----> layer4(4,1)
 *diff1(7,7)  ----->diff2(7,3)  ----->
 */
public class Network {
	//variable of neurons
	//layer1
	public double a[][] = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,1,1,1,1,1,1},{0,0,0,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,0,0,0,0,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1}};
    public double h[] = {1,1,1,1,1,1,1,1};
    public double b[] = {1,1,1,1,1,1,1,1};
    //layer 2
    public double k[][] = {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,1,1,1,1,1,1},{0,0,0,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,0,0,0,0,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1}};
    public double o[] = {1,1,1,1,1,1,1,1};
    public double p[] = {1,1,1,1,1,1,1,1};
    //layer diff
    public double diffa[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1,},{1,1,1,1,1,1,1,},{1,1,1,1,1,1,1,},{1,1,1,1,1,1,1,},{1,1,1,1,1,1,1,},{1,1,1,1,1,1,1,}};
    public double diffh[] = {1,1,1,1,1,1,1};
    public double diffb[] = {1,1,1,1,1,1,1};
    
    //layer diff2
    public double diff2a[][] = {{1,1,1,1,1,1,1},{1,1,1,1,1,1,1},{1,1,1,1,1,1,1}};
    public double diff2h[] = {1,1,1};
    public double diff2b[] = {1,1,1};
    //layer 3
    public double outa[][] = {{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1},{1,1,1,1,1,1,1,1,1,1,1}};
    public double outk[] = {1,1,1,1};
    public double outb[] = {1,1,1,1};
    //layer 4
    public double layer4a[] = {1,1,1,1};
    public double layer4k = 1;
    public double layer4b = 1;
    
    //instance of neurons
    //layer1
		Neuron[] n = {new Hexlinear(), new Hexlinear(), new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear() };
		
		//layer diff
		Neuron[] diff = {new Hexlinear(), new Hexlinear(), new Hexlinear(), new Hexlinear(), new Hexlinear(), new Hexlinear(),new Hexlinear()};
		
		//layer 2
		Neuron[] m = {new Hexlinear(), new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear()};
		
		//layer diff2
		Neuron[] diff2 = {new Hexlinear(), new Hexlinear(), new Hexlinear()};
		
		//layer 3
		Neuron[] out = {new Hexlinear(), new Hexlinear(), new Hexlinear(), new Hexlinear()};
		
		//layer 4
		Neuron[] layer4 = {new Hexlinear()};
		
		
		//generating the difference of the the neuron
	public double[] layer1diff(double x[]) {
			double layer1diff[] = {0,0,0,0,0,0,0};
			
			for(int i=0; i<7; i++) {
				layer1diff[i] = x[1+i]-x[i];
			}

			return layer1diff;
			
		}

	//layer 1
	public double[] layer1(double x[]) {
		double layer1[] = {0,0,0,0,0,0,0,0};
		
		for(int i=0; i<8; i++) {
		   n[i].sumUp(x, a[i]);
		   layer1[i] = n[i].activate(h[i],b[i]);
	}
		return layer1;
		
		
}
	
	public double[] layer12(double x[]) {
		double diffout[] = {0,0,0,0,0,0,0};
		for(int i=0; i<7; i++) {
			
		   diff[i].sumUp(x, diffa[i]);
		   diffout[i] = diff[i].activate(diffh[i],diffb[i]);
		}
		
		return diffout;
	}
	
	//layer 2
	public double[] layer2(double layer1[] ) {
		double layer2[] = {0,0,0,0,0,0,0,0};

		for(int i=0; i<8; i++) {
			m[i].sumUp(layer1, k[i]);
			layer2[i] = m[i].activate(o[i],p[i]);
		}
		
		return layer2;	
}
	
	public double[] layer22(double layer1[] ) {
		double layer2[] = {0,0,0};

		for(int i=0; i<3; i++) {
			diff2[i].sumUp(layer1, diff2a[i]);
			layer2[i] = m[i].activate(diff2b[i],diff2h[i]);
		}
		
		return layer2;	
}
	
	
	//layer 3
	public double[] layer3(double layer2[] ) {
		double layer3[] = {0,0,0,0} ;

		for(int i=0; i<4; i++) {
			out[i].sumUp(layer2, outa[i]);
			layer3[i] = m[i].activate(outk[i],outb[i]);
		}
		
		return layer3;	
}
	
	//layer 4
	
	public double layer4(double layer3[]) {
		double layer4out;
		layer4[0].sumUp(layer3, layer4a);
		layer4out = layer4[0].activate(layer4k,layer4b);
		return layer4out;
	}
	
}