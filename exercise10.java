import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.*;

public class Ex10 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//Problem 2
		//ArrayList<Employee> employees = readJsonFile("src/exercise10/sampleData.json");
		//interactWithData(employees);
		
	}
	
	//Problem 2
	public static void interactWithData(ArrayList<Employee> employees) {
		Scanner in = new Scanner(System.in);
		boolean inProgress = true;
		do {
			try {
				System.out.println("Menu");
				System.out.println("1) Print employee list");
				System.out.println("2) Search/Delete Employee");
				System.out.println("3) Exit");
				int choice = in.nextInt();
				switch (choice) {
					case 1:
						for (Employee e : employees) {
							System.out.println(e);
						}
						break;
					case 2:
						search(employees);
						break;
					case 3:
						System.out.println("Exit");
						inProgress = false;
						break;
					default:
						System.out.println("Invalid Input. Enter a value 1 - 3.");
						break;
				}
				in = new Scanner(System.in);
			} catch (InputMismatchException e) {
				System.out.println(e + " Enter an integer 1 - 3.");
				in = new Scanner(System.in);
			}
			
		} while (inProgress);
	}
	
	//Problem 2
	public static void search(ArrayList<Employee> employees) {
		try {
			Scanner in = new Scanner(System.in);
			ArrayList<Employee> selectedEmployees = new ArrayList<>();
			System.out.println("Enter id, name, department, or task");
			String category = in.next();
			switch (category) {
				case "id": 
					System.out.println("Enter id to search/delete");
					int id = in.nextInt();
					selectedEmployees = findById(employees, id);
					break;
				case "name":
					System.out.println("Enter first and last name to search/delete");
					String name = in.next();
					String lname = in.next();
					selectedEmployees = findByName(employees, name + " " + lname);
					break;
				case "department":
					System.out.println("Enter dpeartment to search/delete");
					String dept = in.next();
					selectedEmployees = findByDept(employees, dept);
					break;
				case "tasks":
					System.out.println("Enter task to search/delete");
					String tasks = in.next();
					selectedEmployees = findByTask(employees, tasks);
					break;
				default:
					throw new IllegalArgumentException();
			}
			selectedEmployees.forEach(System.out::println);
			System.out.println("Entries found: " + selectedEmployees.size() + ". Total entries: " + employees.size() + ".");
			System.out.println();
			if (selectedEmployees.size() > 0) {
				System.out.println("Delete selected entries? y/n");
				String ans = in.next();
				if (ans.equals("y")) {
					employees.removeAll(selectedEmployees);
					System.out.println("Entries Deleted.");
				}
			}
			System.out.println("Returning to menu");
		} catch (Exception e) {
			System.out.println("Invalid Input. Returning to menu.");
		}
	}
	
	//Problem 2
	public static ArrayList<Employee> findById(ArrayList<Employee> employees, int emp_id) {
		ArrayList<Employee> results = new ArrayList<>();
		for (Employee e : employees) {
			if (e.getId() == emp_id) results.add(e);
		}
		return results;
	}
	//Problem 2
	public static ArrayList<Employee> findByName(ArrayList<Employee> employees, String name) {
		ArrayList<Employee> results = new ArrayList<>();
		for (Employee e : employees) {
			if (e.getName().equals(name)) results.add(e);
		}
		return results;
	}
	//Problem 2
	public static ArrayList<Employee> findByDept(ArrayList<Employee> employees, String dept) {
		ArrayList<Employee> results = new ArrayList<>();
		for (Employee e : employees) {
			String d = e.getDepartment();
			String[] firstWord = d.split(" ");
			if (firstWord[0].equals(dept)) results.add(e);
		}
		return results;
	}
	//Problem 2
	public static ArrayList<Employee> findByTask(ArrayList<Employee> employees, String task) {
		ArrayList<Employee> results = new ArrayList<>();
		for (Employee e : employees) {
			if (e.getTasks().contains(task)) results.add(e);
		}
		return results;
	}
	//Problem 2
	public static ArrayList<Employee> readJsonFile(String jsonFile) {
		ArrayList<Employee> employees = new ArrayList<>();
		JsonParser parser = new JsonParser();
		try {
			Object obj = parser.parse(new FileReader(jsonFile));
			JsonArray arr = (JsonArray)obj;
			JsonObject jObject = (JsonObject)arr.get(0);
			//System.out.println(jObject.get("name"));
			for (JsonElement j : arr) {
				JsonObject emp = (JsonObject)j;
				int emp_id = emp.get("id").getAsInt();
				String emp_name = emp.get("name").getAsString();
				String emp_department = emp.get("department").getAsString();
				String[] tasks = emp.get("tasks").getAsString().split(",");
				String[] tasksTrim = new String[tasks.length];
				for (int i = 0; i < tasks.length; i++) {
					tasksTrim[i] = tasks[i].trim();
				}	
				ArrayList<String> emp_tasks = new ArrayList<>(Arrays.asList(tasksTrim));
				employees.add(new Employee(emp_id, emp_name, emp_department, emp_tasks));
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		return employees;
	}
}
