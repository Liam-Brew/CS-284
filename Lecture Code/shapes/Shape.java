package shapes;

public abstract class Shape implements Colorable {
	// Data field
	protected String color;
	
	// Constructor
	Shape(String color) {
		this.color=color;
	}

	// Methods
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	
	public String toString() {
		return "My color is "+color;
	}
	
	public abstract double area();
	
}
