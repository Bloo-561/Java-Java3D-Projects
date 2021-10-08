package examples;

public class MyFirstProgram {
	
	public static void message(String myString, int myInt) {
		System.out.println("This is my String: " + myString);
		System.out.println("and this is my int: " + myInt);
	}
	
	public static void main(String[] args) {
		message("Hello World!",4);
	}

}
