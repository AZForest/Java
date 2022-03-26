package practice;

public class lambaPractice2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		greet( new Greeting() {
			@Override
			public String simpleGreeting() {
				return "Hello";
			}
		});
		greet(() -> "Hi");
		greet2("Dan", "Hello", (name, message) -> message + " " + name);

	}
	
	public static void greet(Greeting greeting) {
		System.out.println(greeting.simpleGreeting());
	}
	public static void greet2(String name, String message, GreetingByName greeting) {
		System.out.println(greeting.simpleGreetingByName(name, message));
	}

}

interface Greeting {
	String simpleGreeting();
}
interface GreetingByName {
	String simpleGreetingByName(String name, String message);
}
