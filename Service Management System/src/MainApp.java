import java.util.*;

abstract class Person {
    protected int id;
    protected String name;
    protected String email;

    public Person(int id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public abstract void displayInfo();
}

class Client extends Person {

}

class Employee extends Person {

}

class Service {

}

class ServiceRequest { // Everything is subject to change, this is just a placeholder for the service request class, including variable names and types
    private int requestId; 
    private Client client; 
    private Service service; 
    private Employee employee; 
    private double hoursWorked; 

    public ServiceRequest(int requestId, Client client, Service service, Employee employee, double hoursWorked) {
        this.requestId = requestId;
        this.client = client;
        this.service = service;
        this.employee = employee;
        this.hoursWorked = hoursWorked;
    }

    public int getRequestId() {
        return requestId;
    }

    public Client getClient() {
        return client;
    }

    public Service getService() {
        return service;
    }

    public Employee getEmployee() {
        return employee;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public double calculateTotalCost() {
        return (service.getPrice() + employee.getHourlyRate()) * hoursWorked;
    }

    public double calculateTotalCost(double discount) {
        return calculateTotalCost() - discount;
    }

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

            switch (mainMenuChoice) {
                case 1:
                    //Clisha
                    break;

                case 2:
                    //Clisha
                    break;

                case 3:
                    //Clisha
                    break;

                case 4:
                    int transactionMenuChoice;
                    do { 
                        System.out.println("\nManage Service Transactions");
                        System.out.println("1. Create Service Request");
                        System.out.println("2. Assign Employee to Client");
                        System.out.println("3. Calculate Total Cost");
                        System.out.println("0. Back");

                        System.out.print("\nEnter your choice: ");
                        transactionMenuChoice = inputScanner.nextInt();
                        inputScanner.nextLine(); // Consume newline

                        switch (transactionMenuChoice) {
                            case 1:
                                if (clients.isEmpty() || employees.isEmpty() || services.isEmpty()) {
                                    System.out.println("\n[ERROR] Cannot create service request!");
                                    System.out.println("Please ensure there are clients, employees, and services.");
                                    continue;
                                }

                                // Step 1: Client Selection
                                System.out.println("\n--- Step 1/4: Select Client ---");
                                for (int i = 0; i < clients.size(); i++) {
                                    Client c = clients.get(i);
                                    System.out.printf("%d. %s (ID: %d, Company: %s)%n", 
                                        i + 1, c.name, c.id, c.getCompanyName());
                                }
                                System.out.print("Select Client (1-" + clients.size() + "): ");
                                int clientChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                if (clientChoice < 0 || clientChoice >= clients.size()) {
                                    System.out.println("[ERROR] Invalid client selection!");
                                    continue;
                                }

                                                                // Step 2: Service Selection
                                System.out.println("\n--- Step 2/4: Select Service ---");
                                for (int i = 0; i < services.size(); i++) {
                                    Service s = services.get(i);
                                    System.out.printf("%d. %s (ID: %d, Base Price: PHP %.2f)%n", 
                                        i + 1, s.getServiceName(), s.getServiceId(), s.getPrice());
                                }
                                System.out.print("Select Service (1-" + services.size() + "): ");
                                int serviceChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                if (serviceChoice < 0 || serviceChoice >= services.size()) {
                                    System.out.println("[ERROR] Invalid service selection!");
                                    continue;
                                }

                                                                // Step 3: Employee Selection
                                System.out.println("\n--- Step 3/4: Select Employee ---");
                                for (int i = 0; i < employees.size(); i++) {
                                    Employee e = employees.get(i);
                                    System.out.printf("%d. %s (ID: %d, Position: %s, Rate: PHP %.2f/hr)%n", 
                                        i + 1, e.name, e.id, e.getPosition(), e.getHourlyRate());
                                }
                                System.out.print("Select Employee (1-" + employees.size() + "): ");
                                int employeeChoice = inputScanner.nextInt() - 1;
                                inputScanner.nextLine();
                                
                                if (employeeChoice < 0 || employeeChoice >= employees.size()) {
                                    System.out.println("[ERROR] Invalid employee selection!");
                                    continue;
                                }

                                                                // Step 4: Hours Worked Input
                                System.out.println("\n--- Step 4/4: Enter Hours ---");
                                System.out.print("Hours Worked: ");
                                double hoursWorked = inputScanner.nextDouble();
                                inputScanner.nextLine();

                                break;

                            case 2:
                                break;

                            case 3:
                                break;

                            case 0:
                                System.out.println("Returning to main menu...");
                                break;

                            default:
                                System.out.println("Invalid choice. Please try again.");
                                break;
                        }

                    } while (transactionMenuChoice != 0);
                    break;

                case 5:
                    //Axel
                    break;

                case 0:
                    System.out.println("Exiting... Goodbye!"); //Sample
                    break;

                default:
                    System.out.println("Invalid choice. Please try again."); //HUI
                    break;
            }

        } while (mainMenuChoice != 0);
        inputScanner.close();
    } 

}




