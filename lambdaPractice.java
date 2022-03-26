package practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class lambdaPractice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Car> cars = generateCars();
		List<Car> filtered = filter(cars, new FilterMethod() {
			@Override
			public boolean test(Car car) {
				return (car.getModelNum() < 2 && car.getMake().charAt(0) == 'T');
			}
		});
		//System.out.println(filtered);
		filtered.forEach(System.out::println);
	}
	
	public static ArrayList<Car> generateCars() {
		ArrayList<Car> cars = new ArrayList<Car>();
		cars.add(new Car(1, "Toyota", "Camry"));
		cars.add(new Car(2, "Toyota", "Corolla"));
		cars.add(new Car(3, "Honda", "Element"));
		cars.add(new Car(4, "Acura", "ILX"));
		cars.add(new Car(5, "Nissan", "Rogue"));
		return cars;
	}
	
	public static List<Car> filter(List<Car> cars, FilterMethod filterMethod) {
		List<Car> filteredCars = new ArrayList<Car>();
		for (Car c : cars) {
			if (filterMethod.test(c)) {
				filteredCars.add(c);
			}
		}
		return filteredCars;
	}
	

}

class Car {
	private int modelNum;
	private String make;
	private String model;
	
	public Car(int num, String make, String model) {
		this.modelNum = num;
		this.make = make;
		this.model = model;
	}
	public int getModelNum() {
		return modelNum;
	}
	public void setModelNum(int modelNum) {
		this.modelNum = modelNum;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String s) {
		model = s;
	}
	@Override
	public String toString() {
		return String.format("ModelNum: %d, Make: %s, Model: %s", modelNum, make, model);
	}
}

interface FilterMethod {
	boolean test(Car c);
}