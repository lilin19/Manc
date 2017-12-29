
public class Network {
	public double a[][]= {{1,1,1,1,1,1,1,1},{0,1,1,1,1,1,1,1},{0,0,1,1,1,1,1,1},{0,0,0,1,1,1,1,1},{0,0,0,0,1,1,1,1},{0,0,0,0,0,1,1,1},{0,0,0,0,0,0,1,1},{0,0,0,0,0,0,0,1}};
    public double h[]= {1,1,1,1,1,1,1,1};
    public double k[]= {1,1,1,1,1,1,1,1};
    public double b[] = {1,1,1,1,1,1,1,1};
    public double o = 1;
    public double p = 1;
		Neuron[] n = {new Hexlinear(), new Hexlinear(), new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear(),new Hexlinear() };
		Neuron[] m = {new Hexlinear()};

	public double[] layer1(double x[]) {
		double layer1[] = {0,0,0,0,0,0,0,0};
		for(int i=0; i<8; i++) {
		n[i].sumUp(x, a[i]);
		layer1[i] = n[i].activate(h[i],b[i]);
	}
		return layer1;
		
		
}
	public double layer2(double layer1[] ) {
		double layer2;

		m[0].sumUp(layer1, b);
		layer2 = m[0].activate(o,p);

		return layer2;
		
		
}
	
	
}