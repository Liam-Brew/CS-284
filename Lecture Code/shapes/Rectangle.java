package shapes;

/**
 * This class represents the geometric shape of a rectangle
 * @author eduardobonelli
 *
 */
public class Rectangle extends Shape {
	// Data fields
	private double base;
	private double height;
	static private int numberOfRectangles=0;
	
	// Constructors
	Rectangle(double base, double height, String color) {
		super(color);
		this.base=base;
		this.height=height;
		numberOfRectangles++;
	}

	Rectangle(double base, double height) {
		super("Blue");
		this.base=base;
		this.height=height;
		numberOfRectangles++;
	}

	// Methods
	
	public double getBase() {
		return base;
	}

	/**
	 * Sets the base of the rectangle to the value of the parameter
	 * @param base The new value for the base
	 */
	public void setBase(double base) {
		this.base = base;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}
	
	public static int getNumberOfRect() {
		return numberOfRectangles;
	}
		
	public String toString() {
		return "Rectangle of base "+base+" and height "+height+". "+super.toString();
	}

	public double area() {
		return base*height;
	}
	
	public Pair<Double,Double> getInfo() {
		return new Pair<Double,Double>(base,height);
	}
	
	public Pair<Double,String> getInfoWithColor() {
		return new Pair<Double,String>(base,color);
	}
}
