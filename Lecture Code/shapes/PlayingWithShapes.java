package shapes;

public class PlayingWithShapes {

	public static void MyPrint(Shape s) {
		 System.out.println(s.toString());
	}
	
	public static void PrintColor(Colorable s) {
		System.out.println(s.getColor());
	}
	
	public static void main(String[] args) {
		/* this method creates two rectangle objects 
		 * and then performs some simple operations on
		 * them 
		 */
		Rectangle r1 = new Rectangle(2.0,3.4);
		Rectangle r2 = new Rectangle(3.7,8.9);
		System.out.println("The base of r1 is:"+ r1.getBase());
		System.out.println("The base of r2 is:"+ r2.getBase());
		r1.setBase(5.4);
		System.out.println("The base of r1 is:"+ r1.getBase());
		System.out.println(Rectangle.getNumberOfRect());
		
		Circle c1 = new Circle(3.4,"Orange");
		System.out.println(c1.getRadius());
		System.out.println("The color of c1 is: "+c1.getColor());
		
		MyPrint(c1);
		MyPrint(r2);
		
		Shape[] ss = new Shape[2];
		
		ss[0] = c1;
		ss[1] = r1;
		
		for (Shape s:ss) {
			PrintColor(s);
		}
		
		MotorVehicle mv = new MotorVehicle();
		mv.setColor("Green");
		
		PrintColor(mv);
		
		System.out.println(r1.getInfo().getX()); // prints a double
		System.out.println(r1.getInfoWithColor().getY()); // prints a string
		
		int x=3;
		
		Pair<Double,String> p = new Pair<>(2.3,"hello");
		
	}

}
