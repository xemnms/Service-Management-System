import java.util.*;

abstract class Person {

}

class Client extends Person {

}

class Employee extends Person {

}

class Service {

}

class ServiceRequest {

}

public class MainApp {

    static ArrayList<Client> clients = new ArrayList<>();
    static ArrayList<Employee> employees = new ArrayList<>();
    static ArrayList<Service> services = new ArrayList<>();
    static ArrayList<ServiceRequest> serviceRequests = new ArrayList<>();

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);

        int mainMenuChoice;
        do {
            System.out.println("IT Service Management System");
            System.out.println("1. Manage Clients");
            System.out.println("2. Manage Employees");
            System.out.println("3. Manage Services");
            System.out.println("4. Service Transactions");
            System.out.println("5. Reports");
            System.out.println("0. Exit");

            System.out.print("\nEnter your choice: ");
            mainMenuChoice = inputScanner.nextInt();
            inputScanner.nextLine(); // Consume newline

        } while (mainMenuChoice != 0);
        inputScanner.close();
    } 
}