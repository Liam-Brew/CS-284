package shapes;

public class Circle extends Shape {
	// Data fields
	private double radius;
	
	// Constructor
	Circle(double radius, String color) {
		super(color);
		this.radius = radius;
	}

	// Methods
	
	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}
	
   public String toString() {
	     return "Circle of radius "+radius;
   }
   
   public double area() {
	   return Math.PI*radius*radius;
   }
}
